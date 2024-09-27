package com.huntercodexs.demo.controller;


import com.huntercodexs.demo.config.BaseDocTestConfig;
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
import static com.huntercodexs.demo.exception.AppExceptionHandler.USER_NOT_FOUND_EXCEPTION;
import static com.huntercodexs.demo.util.Utils.convertToNewObject;
import static com.huntercodexs.demo.util.Utils.jsonStringFromObject;
import static java.util.UUID.randomUUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest extends BaseDocTestConfig {

    UUID userId;
    UserModel userResponse;
    String jsonRequest;
    String jsonResponse;

    @MockBean
    UserService userService;

    @BeforeAll
    void initData() {
        userId = randomUUID();

        UserModel userRequest = UserModel.builder()
                .name("anyName")
                .username("anyUsername")
                .password("anyPassword")
                .email("anyEmail")
                .build();

        jsonRequest = jsonStringFromObject(userRequest);
        userResponse = convertToNewObject(userRequest, UserModel.class);
        userResponse.setId(userId);

        jsonResponse = jsonStringFromObject(userResponse);
    }

    @Test
    void testGetUser() throws Exception {

        UserModel userModel = this.userResponse;
        when(userService.getUser(userId)).thenReturn(userModel);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/demo/users/{userId}", userId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(jsonResponse))
                .andDo(document("get-user", resource("Get a user's details")))
                .andReturn();
    }

    @Test
    void testGetAllUsers() throws Exception {

        UserModel user = this.userResponse;
        when(userService.getAllUsers()).thenReturn(List.of(user));

        List<UserModel> userList = new ArrayList<>();
        userList.add(userResponse);

        String jsonResponse = jsonStringFromObject(userList);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/demo/users/"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(jsonResponse))
                .andDo(document("get-users", resource("Get a list of users")))
                .andReturn();
    }

    @Test
    void testCreateUser() throws Exception {

        UserModel userModel = userResponse;
        when(userService.createUser(any())).thenReturn(userModel);

        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/demo/users/")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(jsonResponse))
                .andDo(document("create-user", resource("Create a user")))
                .andReturn();
    }

    @Test
    void testUpdateUser() throws Exception {

        UserModel userModel = userResponse;
        when(userService.updateUser(any(), any())).thenReturn(userModel);

        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/demo/users/{userId}", userId)
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(jsonResponse))
                .andDo(document("update-user", resource("Update a user's details")))
                .andReturn();
    }

    @Test
    void testUpdateUser_ThrowsExceptionWhenUserDoesNotExist() throws Exception {

        when(userService.updateUser(any(), any())).thenThrow(new UserNotFoundException(USER_NOT_FOUND_EXCEPTION));

        String jsonError = "{\"message\": \"jsonError\"}";
        jsonError = jsonError.replace("jsonError", USER_NOT_FOUND_EXCEPTION);

        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/demo/users/{userId}", userId)
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf(userId)))
                .andExpect(status().is(404))
                .andExpect(content().json(jsonError))
                .andDo(document(
                        "update-user-throws-exception-when-user-does-not-exist",
                        resource("Updating a user's details throws exception when user does not exist")))
                .andReturn();
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/v1/demo/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(document("delete-user",
                        resource("Delete a user")))
                .andReturn();
    }

    @Test
    void testDeleteUser_ThrowsExceptionWhenUserNotFound() throws Exception {
        doThrow(new UserNotFoundException(USER_NOT_FOUND_EXCEPTION)).when(userService).deleteUser(any());

        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/v1/demo/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(document(
                        "delete-user-throws-exception-when-user-not-found",
                        resource("Deleting a user throws exception when user is not found")))
                .andReturn();
    }
}
