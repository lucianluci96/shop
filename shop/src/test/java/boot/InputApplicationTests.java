package boot;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.myproject.shop.core.model.InputEntity;
import org.myproject.shop.core.model.ProductEntity;
import org.myproject.shop.core.model.ShopEntity;
import org.myproject.shop.core.repository.InputRepository;
import org.myproject.shop.core.repository.ProductRepository;
import org.myproject.shop.core.repository.ShopRepository;
import org.myproject.shop.rest.api.impl.InputController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class InputApplicationTests extends AbstractTestBase {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));


    @Autowired
    InputRepository inputRepository;
    @Autowired
    InputController inputController;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    InputEntity initialInput;
    @Autowired
    InputEntity newInput;
    Long randomId = 1L;

    @Before
    public void createTestData() {
        ShopEntity shop = new ShopEntity("shop");
        shopRepository.save(shop);
        ProductEntity productEntity = new ProductEntity("product");

        productRepository.save(productEntity);
        inputRepository.save(new InputEntity(productRepository.findOne(productEntity.getId()), shop, 15L));

        ProductEntity productOne = productRepository.save(new ProductEntity("Water"));

        ShopEntity shopOne = shopRepository.save(new ShopEntity("Selgros"));

        inputRepository.save(new InputEntity(productOne, shopOne, 4L));
        newInput = new InputEntity(productOne, shopOne, 7L);


        initialInput = inputRepository.save(new InputEntity(productOne, shopOne, 15));

    }

    @After
    public void afterMethod() {
        inputRepository.deleteAll();

    }
    @After
    public void tearDown() {
        inputRepository.deleteAll();
    }
    @Test
    public void getAllInputs() throws Exception {


        mockMvc.perform(get("/inputs/list").session(admin())
                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOneInput() throws Exception {



        this.mockMvc.perform(get("/inputs/" + initialInput.getId()).session(admin()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))

                .andExpect(jsonPath("$.product.name", is(initialInput.getProduct().getName())))
                .andExpect(jsonPath("$.shop.name", is(initialInput.getShop().getName())));



    }

    @Test
    public void getOneInputNotFound() throws Exception {


        this.mockMvc.perform(get("/inputs/" + 5L).session(admin()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createOneInput() throws Exception {


        String json = json(newInput.toDto());

       // int initialRepositorySize = (int) inputRepository.count();

        mockMvc.perform(post("/inputs").session(admin())
                .contentType(MediaType.APPLICATION_JSON)
                .content((json)))
             //   .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void updateOneInput() throws Exception {




        this.mockMvc.perform(get("/inputs" + "/" + initialInput.getId()).session(admin()))
                .andExpect(jsonPath("$.product.name", is(initialInput.getProduct().getName())))
                .andExpect(jsonPath("$.shop.name", is(initialInput.getShop().getName())));


    }


    @Test
    public void deleteOneInput() throws Exception {

        this.mockMvc.perform(delete("/inputs" + "/" + randomId).session(admin()))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void deleteOneInputNotFound() throws Exception {


        this.mockMvc.perform(get("/inputs/" + 5L).session(admin()))
                .andExpect(status().isNotFound());
    }

    private String json(Object o) throws IOException {
        return new Gson().toJson(o);
    }
}
