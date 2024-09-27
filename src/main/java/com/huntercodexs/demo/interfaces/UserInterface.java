package com.huntercodexs.demo.interfaces;

import com.huntercodexs.demo.entity.UserEntity;
import com.huntercodexs.demo.model.UserModel;

import java.util.List;
import java.util.UUID;

public interface UserInterface {
    UserEntity getCurrentUser();
    UserModel getUser(UUID userId);
    List<UserModel> getAllUsers();
    UserModel createUser(UserModel userModel);
    void deleteUser(UUID userId);
    UserModel updateUser(UUID userId, UserModel userModel);
    UserModel fixUser(UUID userId, UserModel userModel);
}
