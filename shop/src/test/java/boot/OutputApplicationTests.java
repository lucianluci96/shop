package boot;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.myproject.shop.core.model.OutputEntity;
import org.myproject.shop.core.model.ProductEntity;
import org.myproject.shop.core.model.ShopEntity;
import org.myproject.shop.core.repository.OutputRepository;
import org.myproject.shop.core.repository.ProductRepository;
import org.myproject.shop.core.repository.ShopRepository;
import org.myproject.shop.rest.api.impl.OutputController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import java.io.IOException;
import java.nio.charset.Charset;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class OutputApplicationTests extends AbstractTestBase {


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));


    @Autowired
    OutputRepository outputRepository;
    @Autowired
    OutputController outputController;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    OutputEntity initialOutput;
    @Autowired
    OutputEntity newOutput;
    Long randomId = 1L;

    @After
    public void afterMethod() {
        outputRepository.deleteAll();
    }
    @After
    public void tearDown() {
        outputRepository.deleteAll();
    }

    @Before
    public void createTest() {
        ShopEntity shop = new ShopEntity("shop");
        shopRepository.save(shop);
        ProductEntity productEntity = new ProductEntity("product");

        productRepository.save(productEntity);
        outputRepository.save(new OutputEntity(productRepository.findOne(productEntity.getId()), shop, 15L));

        ProductEntity productOne = productRepository.save(new ProductEntity("Chair"));
        ShopEntity shopOne = shopRepository.save(new ShopEntity("Lidl"));


        initialOutput = outputRepository.save(new OutputEntity(productOne, shopOne, 3));

        newOutput = new OutputEntity(productOne, shopOne, 7L);


    }

    @Test
    public void getAllOutputs() throws Exception {


        mockMvc.perform(get("/outputs/list").session(admin())
                .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOneOutput() throws Exception {


        this.mockMvc.perform(get("/outputs/" + initialOutput.getId()).session(admin()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.product.name", is(initialOutput.getProduct().getName())))
                .andExpect(jsonPath("$.shop.name", is(initialOutput.getShop().getName())));


    }
    @Test
    public void getOneOutputNotFound() throws Exception {


        this.mockMvc.perform(get("/outputs/" + 5L).session(admin()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createOneOutput() throws Exception {


        String json = json(newOutput.toDto());


        mockMvc.perform(post("/outputs").session(admin())
                .contentType(MediaType.APPLICATION_JSON)
                .content((json)))
               // .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void updateOneOutput() throws Exception {

        ProductEntity productOne = productRepository.save(new ProductEntity("Pen"));
        ShopEntity shopOne = shopRepository.save(new ShopEntity("Lidl"));
        OutputEntity initialOutput = outputRepository.save(new OutputEntity(productOne, shopOne, 15));


        this.mockMvc.perform(get("/outputs" + "/" + initialOutput.getId()).session(admin()))
                .andExpect(jsonPath("$.product.name", is(initialOutput.getProduct().getName())))
                .andExpect(jsonPath("$.shop.name", is(initialOutput.getShop().getName())));


    }

    @Test
    public void deleteOneOutput() throws Exception {

        this.mockMvc.perform(delete("/outputs" + "/" + randomId).session(admin()))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void deleteOneOutputNotFound() throws Exception {


        this.mockMvc.perform(get("/outputs/" + 5L).session(admin()))
                .andExpect(status().isNotFound());
    }

    private String json(Object o) throws IOException {

        return new Gson().toJson(o);
    }


}
