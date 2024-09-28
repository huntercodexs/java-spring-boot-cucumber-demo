package com.huntercodexs.demo.service;

import com.huntercodexs.demo.entity.UserEntity;
import com.huntercodexs.demo.interfaces.UserInterface;
import com.huntercodexs.demo.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserInterface {

    @Override
    public UserEntity getCurrentUser() {
        return null;
    }

    @Override
    public UserModel getUser(UUID userId) {
        return null;
    }

    @Override
    public List<UserModel> getAllUsers() {
        return List.of();
    }

    @Override
    public UserModel createUser(UserModel userModel) {
        return null;
    }

    @Override
    public void deleteUser(UUID userId) {
    }

    @Override
    public UserModel updateUser(UUID userId, UserModel userModel) {
        return null;
    }

    @Override
    public UserModel fixUser(UUID userId, UserModel userModel) {
        return null;
    }
}
