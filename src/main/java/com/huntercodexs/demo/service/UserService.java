package com.huntercodexs.demo.service;

import com.huntercodexs.demo.entity.UserEntity;
import com.huntercodexs.demo.exception.UserNotFoundException;
import com.huntercodexs.demo.interfaces.UserInterface;
import com.huntercodexs.demo.mapper.UserMapper;
import com.huntercodexs.demo.model.UserModel;
import com.huntercodexs.demo.repository.UserRepository;
import com.huntercodexs.demo.security.CustomUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.huntercodexs.demo.exception.ExceptionEnumerator.USER_NOT_FOUND;
import static com.huntercodexs.demo.exception.ExceptionEnumerator.getException;

@Service
public class UserService implements UserInterface {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return customUser.getUserEntity();
    }

    @Override
    public UserModel getUser(UUID userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            return userMapper.toModel(userEntity);
        } else {
            throw new UserNotFoundException(getException(USER_NOT_FOUND));
        }
    }

    @Override
    public List<UserModel> getAllUsers() {

        List<UserModel> userList = new ArrayList<>();
        List<UserEntity> userEntityList = userRepository.findAll();

        if (!userEntityList.isEmpty()) {
            userEntityList.forEach(userEntity -> userList.add(userMapper.toModel(userEntity)));
        }

        return userList;
    }

    @Override
    public UserModel createUser(UserModel userModel) {
        UserEntity userEntity = userRepository.save(userMapper.toEntity(userModel));
        return userMapper.toModel(userEntity);
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(getException(USER_NOT_FOUND)));
        userRepository.deleteById(userId);
    }

    @Override
    public UserModel updateUser(UUID userId, UserModel user) {

        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userMapper.toEntity(user);
            userEntity.setId(userId);

            userEntity = userRepository.save(userEntity);
            return userMapper.toModel(userEntity);
        } else {
            throw new UserNotFoundException(getException(USER_NOT_FOUND));
        }
    }

    @Override
    public UserModel fixUser(UUID userId, UserModel userModel) {
        return null;
    }

}
