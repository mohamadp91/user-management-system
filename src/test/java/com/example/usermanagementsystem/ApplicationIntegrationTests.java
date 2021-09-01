package com.example.usermanagementsystem;

import com.example.usermanagementsystem.model.UserModel;
import com.example.usermanagementsystem.repository.UserRepository;
import net.minidev.json.JSONValue;
import org.json.JSONException;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserManagementSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationIntegrationTests {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void shouldAddUser() throws Exception {


        UserModel user = new UserModel("ali", "rezaee", new Date().toString(), "hello@gamil.com");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/users")
                .queryParam("firstName", user.getFirstName())
                .queryParam("lastName",user.getLastName())
                .queryParam("emailAddress",user.getEmailAddress());


        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                entity,
                String.class);

        assertTrue(userRepository.existsById(user.getId()));

    }

    @Test
    public void shouldGetUsers() throws Exception {

        HttpEntity<?> entity = new HttpEntity<>(headers);

        UserModel user = new UserModel("1", "ali", new Date().toString(), "hello@gamil.com");
        List<UserModel> users = new ArrayList<>();
        users.add(user);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/users");

        userRepository.save(user);

        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);

        String jsonTextExpected = JSONValue.toJSONString(users);

        JSONAssert.assertEquals(jsonTextExpected, response.getBody(), false);
    }

    @Test
    public void ShouldGetUserById() throws JSONException {

        HttpEntity<?> entity = new HttpEntity<>(headers);

        UserModel user = new UserModel("1", "ali", new Date().toString(), "hello@gamil.com");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/user")
                .queryParam("id", user.getId());

        userRepository.save(user);

        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);

        String jsonTextExpected = JSONValue.toJSONString(user);

        JSONAssert.assertEquals(jsonTextExpected, response.getBody(), false);
    }


    @Test
    public void shouldDeleteUserById() throws Exception {

        HttpEntity<?> entity = new HttpEntity<>(headers);

        UserModel user = new UserModel("1", "ali", new Date().toString(), "hello@gamil.com");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/users")
                .queryParam("id",user.getId());

        userRepository.save(user);

        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.DELETE,
                entity,
                String.class);

        String jsonTextExpected = JSONValue.toJSONString(user);

        JSONAssert.assertEquals(jsonTextExpected, response.getBody(), false);
        assertFalse(userRepository.existsById(user.getId()));

    }

}