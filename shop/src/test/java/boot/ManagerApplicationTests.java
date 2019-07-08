package boot;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.myproject.shop.core.model.ManagerEntity;
import org.myproject.shop.core.repository.ManagerRepository;
import org.myproject.shop.rest.api.impl.ManagerController;
import org.myproject.shop.rest.dto.ManagerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.Charset;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ManagerApplicationTests extends AbstractTestBase {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    ManagerController managerController;
    @Autowired
    ManagerEntity initialManager;
    @Autowired
    ManagerDto managerDto;

    @After
    public void tearDown() {
        managerRepository.deleteAll();
    }

    @After
    public void afterMethod() {
        managerRepository.deleteAll();
    }

    @Before
    public void createTest() {
        managerRepository.save(new ManagerEntity("Jack", "Bauer"));
        managerRepository.save(new ManagerEntity("Chloe", "O'Brian"));

        initialManager = managerRepository.save(new ManagerEntity("Jack", "Bauer"));

        managerDto = new ManagerDto("JSDF", "sadas");

    }

    @Test
    public void getAllManagers() throws Exception {


        mockMvc.perform(get("/managers/list").session(admin())
                .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOneManager() throws Exception {


        this.mockMvc.perform(get("/managers/" + initialManager.getId()).session(admin()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.firstName", is(initialManager.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(initialManager.getLastName())));
    }

    @Test
    public void getOneManagerNotFound() throws Exception {


        this.mockMvc.perform(get("/managers/" + 5L).session(admin()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createOneManager() throws Exception {


        mockMvc.perform(post("/managers/").session(admin())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(managerDto)))
               // .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void updateOneManager() throws Exception {


        this.mockMvc.perform(get("/managers" + "/" + initialManager.getId()).session(admin()))
                .andExpect(jsonPath("$.firstName", is(initialManager.getFirstName())));

    }

    @Test
    public void deleteOneManagerNotFound() throws Exception {

        this.mockMvc.perform(delete("/managers/" + 7L).session(admin()))
                .andExpect(status().isNotFound());
    }


    @Test
    public void deleteOneManager() throws Exception {


        this.mockMvc.perform(delete("/managers/" + initialManager.getId()).session(admin()))
                .andExpect(status().isNoContent());
    }


    private String json(Object o) throws IOException {

        return new Gson().toJson(o);
    }


}
