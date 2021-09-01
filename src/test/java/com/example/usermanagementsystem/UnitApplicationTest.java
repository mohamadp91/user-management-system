package com.example.usermanagementsystem;

import com.example.usermanagementsystem.controller.UserController;
import com.example.usermanagementsystem.model.UserModel;
import net.minidev.json.JSONValue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
class UnitApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController userController;

    private long id = 1;

    @Test
    void shouldAddUser() throws Exception {

        Date date = new Date();
        UserModel user = new UserModel("ali","ahmadi", date.toString(), "gmail@gmail.com");


        when(userController.addUser(user)).thenReturn(user);

        String requestJson = JSONValue.toJSONString(user);

        this.mockMvc.perform(post("/users")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void shouldGetUsers() throws Exception {

        Date date = new Date();

        List<UserModel> users = new ArrayList<>();
        UserModel user = new UserModel("ali","ahmadi", date.toString(), "gmail@gmail.com");
        UserModel user2 = new UserModel("reza","ahmadi", date.toString(), "hello@gmail.com");

        users.add(user);
        users.add(user2);

        when(userController.getAllUsers()).thenReturn(users);


        String requestJson = JSONValue.toJSONString(user);

        this.mockMvc.perform(get("/users/" + id)
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetUserById() throws Exception {

        Date date = new Date();

        UserModel user = new UserModel("ali","ahmadi", date.toString(), "gmail@gmail.com");


        when(userController.getUserById(id)).thenReturn(user);

        String requestJson = JSONValue.toJSONString(user);

        this.mockMvc.perform(get("/users/" + id)
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void shouldDeleteUserById() throws Exception {

        Date date = new Date();
        UserModel user = new UserModel("ali","ahmadi", date.toString(), "gmail@gmail.com");


        when(userController.deleteUser(id)).thenReturn(user);

        String requestJson = JSONValue.toJSONString(user);

        this.mockMvc.perform(delete("/users/" + id)
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
