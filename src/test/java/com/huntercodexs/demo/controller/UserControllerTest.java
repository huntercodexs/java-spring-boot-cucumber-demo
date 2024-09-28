package com.huntercodexs.demo.controller;


import com.huntercodexs.demo.config.DocumentationConfig;
import com.huntercodexs.demo.exception.UserNotFoundException;
import com.huntercodexs.demo.model.UserModel;
import com.huntercodexs.demo.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.huntercodexs.demo.config.ConstantsConfig.URI_USERS;
import static com.huntercodexs.demo.config.ConstantsConfig.USERS;
import static com.huntercodexs.demo.exception.ExceptionEnumerator.USER_NOT_FOUND;
import static com.huntercodexs.demo.exception.ExceptionEnumerator.getException;
import static com.huntercodexs.demo.util.Utils.convertToNewObject;
import static com.huntercodexs.demo.util.Utils.jsonStringFromObject;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest extends DocumentationConfig {

    UserModel userResponse;
    String jsonRequest;
    String jsonResponse;

    @MockBean
    UserService userService;

    @BeforeAll
    void initData() {
        UserModel userRequest = UserModel.builder()
                .name(USERS[0][1])
                .username(USERS[0][2])
                .password(USERS[0][3])
                .email(USERS[0][4])
                .build();

        jsonRequest = jsonStringFromObject(userRequest);
        userResponse = convertToNewObject(userRequest, UserModel.class);
        userResponse.setId(UUID.fromString(USERS[0][0]));

        jsonResponse = jsonStringFromObject(userResponse);
    }

    @Test
    void testGetUser() throws Exception {

        UserModel userModel = this.userResponse;
        when(userService.getUser(UUID.fromString(USERS[0][0]))).thenReturn(userModel);

        mockMvc.perform(RestDocumentationRequestBuilders
                        .get(URI_USERS +"/{userId}", UUID.fromString(USERS[0][0])))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(jsonResponse))
                .andDo(document("get-user", resource("User details")))
                .andReturn();
    }

    @Test
    void testGetAllUsers() throws Exception {

        UserModel user = this.userResponse;
        when(userService.getAllUsers()).thenReturn(List.of(user));

        List<UserModel> userList = new ArrayList<>();
        userList.add(userResponse);

        String jsonResponse = jsonStringFromObject(userList);

        mockMvc.perform(RestDocumentationRequestBuilders
                        .get(URI_USERS +"/"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(jsonResponse))
                .andDo(document("get-users", resource("Users list")))
                .andReturn();
    }

    @Test
    void testCreateUser() throws Exception {

        UserModel userModel = userResponse;
        when(userService.createUser(any())).thenReturn(userModel);

        mockMvc.perform(RestDocumentationRequestBuilders
                        .post(URI_USERS +"/")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(jsonResponse))
                .andDo(document("create-user", resource("User Create")))
                .andReturn();
    }

    @Test
    void testUpdateUser() throws Exception {

        UserModel userModel = userResponse;
        when(userService.updateUser(any(), any())).thenReturn(userModel);

        mockMvc.perform(RestDocumentationRequestBuilders
                        .put(URI_USERS +"/{userId}", UUID.fromString(USERS[0][0]))
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(jsonResponse))
                .andDo(document("update-user", resource("User Update")))
                .andReturn();
    }

    @Test
    void testUpdateUser_ThrowsExceptionWhenUserDoesNotExist() throws Exception {

        when(userService.updateUser(any(), any())).thenThrow(new UserNotFoundException(getException(USER_NOT_FOUND)));

        String jsonError = "{\"message\": \"jsonError\"}";
        jsonError = jsonError.replace("jsonError", getException(USER_NOT_FOUND));

        mockMvc.perform(RestDocumentationRequestBuilders
                        .put(URI_USERS +"/{userId}", UUID.fromString(USERS[0][0]))
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf(UUID.fromString(USERS[0][0]))))
                .andExpect(status().is(404))
                .andExpect(content().json(jsonError))
                .andDo(document(
                        "update-user-throws-exception-when-user-does-not-exist",
                        resource("User Update Throws Exception If User does not exist")))
                .andReturn();
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders
                        .delete(URI_USERS +"/{userId}", UUID.fromString(USERS[0][0]))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(document("delete-user", resource("User Delete")))
                .andReturn();
    }

    @Test
    void testDeleteUser_ThrowsExceptionWhenUserNotFound() throws Exception {
        doThrow(new UserNotFoundException(getException(USER_NOT_FOUND))).when(userService).deleteUser(any());

        mockMvc.perform(RestDocumentationRequestBuilders
                        .delete(URI_USERS +"/{userId}", UUID.fromString(USERS[0][0]))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(document(
                        "delete-user-throws-exception-when-user-not-found",
                        resource("User Delete Throws Exception If User does not exist")))
                .andReturn();
    }
}
