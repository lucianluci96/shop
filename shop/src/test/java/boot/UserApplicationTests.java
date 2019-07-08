package boot;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.myproject.shop.core.model.UserEntity;
import org.myproject.shop.core.repository.UserRepository;
import org.myproject.shop.rest.api.impl.UserController;
import org.myproject.shop.rest.dto.RoleEnum;
import org.myproject.shop.rest.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import java.io.IOException;
import java.nio.charset.Charset;



import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserApplicationTests extends AbstractTestBase {


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserController userController;
    @Autowired
    UserEntity initialUser;
    @Autowired
    UserDto userDto;


    @After
    public void afterMethod() {
        userRepository.deleteAll();
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Before
    public void createTest() {
        userRepository.save(new UserEntity("Jack", "Bauer", RoleEnum.ADMIN));
        userRepository.save(new UserEntity("Chloe", "O'Brian", RoleEnum.USER));

        initialUser = userRepository.save(new UserEntity("Jack", "Bauer", RoleEnum.ADMIN));

        userDto = new UserDto("asdsa", "JSDF", RoleEnum.ADMIN);
    }

    @Test
    public void getAllUsers() throws Exception {


        mockMvc.perform(get("/users/list").session(admin())
                .accept(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOneUser() throws Exception {


        this.mockMvc.perform(get("/users/" + initialUser.getId()).session(admin()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.username", is(initialUser.getUsername())));
    }
    @Test
    public void getOneUsertNotFound() throws Exception {


        this.mockMvc.perform(get("/users/" + 5L).session(admin()))
                .andExpect(status().isNotFound());
    }
    @Test
    public void createOneUser() throws Exception {


        mockMvc.perform(post("/shops/").session(admin())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(userDto)))
                //.andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    public void updateOneUser() throws Exception {


        this.mockMvc.perform(get("/users" + "/" + initialUser.getId()).session(admin()))
                .andExpect(jsonPath("$.username", is(initialUser.getUsername())));

    }

    @Test
    public void deleteOneUser() throws Exception {


        this.mockMvc.perform(delete("/users" + "/" + initialUser.getId()).session(admin()))
                .andExpect(status().isNoContent());


    }

    @Test
    public void deleteOneUserNotFound() throws Exception {


        this.mockMvc.perform(get("/users/" + 5L).session(admin()))
                .andExpect(status().isNotFound());

    }

    private String json(Object o) throws IOException {

        return new Gson().toJson(o);
    }

}

