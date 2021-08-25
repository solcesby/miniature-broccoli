package com.itechart.springproject.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.itechart.springproject.AbstractIntegrationTest;
import com.itechart.springproject.dto.user.UserCreateDTO;
import com.itechart.springproject.dto.user.UserDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.itechart.springproject.utils.TestUtils.generateUserCreateDTO;
import static java.lang.String.valueOf;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.com.trilead.ssh2.crypto.Base64.encode;

public class UserControllerTest extends AbstractIntegrationTest {

    @Test
    public void saveUser_happyPath() throws Exception {
        //GIVEN
        UserCreateDTO userCreateDTO = generateUserCreateDTO();

        //WHEN
        MvcResult mvcResult = mockMvc.perform(post("/users")
                        .with(csrf())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateDTO)))
                .andExpect(status().isOk())
                .andReturn();

        UserDTO savedUser = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), UserDTO.class);

        //THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(savedUser.getFirstName()).isEqualTo(userCreateDTO.getFirstName());
            softAssertions.assertThat(savedUser.getLastName()).isEqualTo(userCreateDTO.getLastName());
            softAssertions.assertThat(savedUser.getEmail()).isEqualTo(userCreateDTO.getEmail());
            softAssertions.assertThat(savedUser.getPhone()).isEqualTo(userCreateDTO.getPhone());
        });
    }

    @Test
    public void saveUserIfEmailIsTaken() throws Exception {
        //GIVEN
        UserCreateDTO userCreateDTO = generateUserCreateDTO();
        createUser(userCreateDTO);

        String takenEmail = userCreateDTO.getEmail();

        UserCreateDTO invalidDTO = generateUserCreateDTO();
        invalidDTO.setEmail(takenEmail);

        //WHEN

        //NestedServletException.class can be used instead of Exception.class because it is wrapper exception to the nested EntityExistsException.class
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            ResultActions resultActions = mockMvc.perform(post("/users")
                    .with(csrf())
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidDTO)));
        });

        String expectedMessage = "already exists";
        String actualMessage = exception.getMessage();

        //THEN
        Assertions.assertTrue(actualMessage.contains("Email:") && actualMessage.contains(expectedMessage));
    }

    @Test
    public void getAllUsers_happyPath() throws Exception {
        //GIVEN
        createUser(generateUserCreateDTO());
        createUser(generateUserCreateDTO());
        createUser(generateUserCreateDTO());
        createUser(generateUserCreateDTO());

        UserCreateDTO userCreateDTO = generateUserCreateDTO();

        String pass = "123456";
        userCreateDTO.setPassword(pass);

        UserDTO savedUser = createUser(userCreateDTO);

        String email = savedUser.getEmail();

        int page = 0;
        int size = 10;

        //WHEN
        MvcResult mvcResult = mockMvc.perform(get("/users/active?page={page}&size={size}", page, size)
                        .header(AUTHORIZATION, "Basic " +
                                valueOf(encode((email + ":" + pass).getBytes())))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andReturn();
        byte[] content = mvcResult.getResponse().getContentAsByteArray();
        List<UserDTO> users = objectMapper.readValue(content, new TypeReference<>() {
        });

        //THEN
        Assertions.assertEquals(5, users.size());
    }
}