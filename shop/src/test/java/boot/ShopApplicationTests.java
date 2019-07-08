package boot;


import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.myproject.shop.core.model.ShopEntity;
import org.myproject.shop.core.repository.ShopRepository;
import org.myproject.shop.rest.dto.ShopDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ShopApplicationTests extends AbstractTestBase {


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));


    @Autowired
    ShopRepository shopRepository;
    @Autowired
    ShopEntity initialShop;
    @Autowired
    ShopDto shopDto;

    @After
    public void afterMethod() {
        shopRepository.deleteAll();
    }
    @After
    public void tearDown() {
        shopRepository.deleteAll();
    }
    @Before
    public void createTest() {
        shopRepository.save(new ShopEntity("g"));
        shopRepository.save(new ShopEntity("gfgh"));

        initialShop = shopRepository.save(new ShopEntity("Kaufland12"));

        shopDto = new ShopDto();
    }


    @Test
    public void getAllShops() throws Exception {


        mockMvc.perform(get("/shops/list").session(admin())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getOneShop() throws Exception {


        this.mockMvc.perform(get("/shops/" + initialShop.getId()).session(admin()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.name", is(initialShop.getName())));

    }


    @Test
    public void createOneShop() throws Exception {


        mockMvc.perform(post("/shops/").session(admin())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(shopDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateOneShop() throws Exception {


        this.mockMvc.perform(get("/shops" + "/" + initialShop.getId()).session(admin()))
                .andExpect(jsonPath("$.name", is(initialShop.getName())));


    }

    @Test
    public void deleteOneShopNotFound() throws Exception {

        this.mockMvc.perform(delete("/shops/" + 1L).session(admin()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteOneShop() throws Exception {


        this.mockMvc.perform(delete("/shops" + "/" + initialShop.getId()).session(admin()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getOneShopNotFound() throws Exception {


        this.mockMvc.perform(get("/shops/" + 5L).session(admin()))
                .andExpect(status().isNotFound());

    }

    private String json(Object o) throws IOException {

        return new Gson().toJson(o);
    }

}
