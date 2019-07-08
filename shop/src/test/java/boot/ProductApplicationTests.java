package boot;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.myproject.shop.core.model.ProductEntity;
import org.myproject.shop.core.repository.ProductRepository;
import org.myproject.shop.rest.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ProductApplicationTests extends AbstractTestBase {


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));


    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductEntity initialProduct;
    @Autowired
    ProductDto productDto;

    @After
    public void afterMethod() {
        productRepository.deleteAll();
    }

    @After
    public void tearDown() {
        productRepository.deleteAll();
    }

    @Before
    public void createTest(){
        productRepository.save(new ProductEntity("sadsa"));
        productRepository.save(new ProductEntity("gfgh"));

        initialProduct = productRepository.save(new ProductEntity("USB"));

//        productDto = new ProductDto(1L, "JSDF");
    }


    @Test
    public void getAllProducts() throws Exception {



        mockMvc.perform(get("/products/list").session(admin())
                .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOneProduct() throws Exception {



        this.mockMvc.perform(get("/products/" + initialProduct.getId()).session(admin()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.name", is(initialProduct.getName())));

    }


    @Test
    public void createOneProduct() throws Exception {



        mockMvc.perform(post("/products/").session(admin())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(contentType)))
               // .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void updateOneProduct() throws Exception {


        this.mockMvc.perform(get("/products" + "/" + initialProduct.getId()).session(admin()))
                .andExpect(jsonPath("$.name", is(initialProduct.getName())));


    }

    @Test
    public void deleteOneProductNotFound() throws Exception {

        this.mockMvc.perform(delete("/products/" + 1L).session(admin()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteOneProduct() throws Exception {


        this.mockMvc.perform(delete("/products" + "/" + initialProduct.getId()).session(admin()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getOneProductNotFound() throws Exception {


        this.mockMvc.perform(get("/products/" + 5L).session(admin()))
                .andExpect(status().isNotFound());
    }

    private String json(Object o) throws IOException {

        return new Gson().toJson(o);
    }


}


