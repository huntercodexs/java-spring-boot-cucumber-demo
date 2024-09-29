package com.huntercodexs.demo.service;

import com.huntercodexs.demo.entity.UserEntity;
import com.huntercodexs.demo.exception.UserNotFoundException;
import com.huntercodexs.demo.mapper.UserMapper;
import com.huntercodexs.demo.model.UserModel;
import com.huntercodexs.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.huntercodexs.demo.config.ConstantsConfig.USERS;
import static com.huntercodexs.demo.config.ConstantsConfig.USERS_UP;
import static com.huntercodexs.demo.exception.ExceptionEnumerator.USER_NOT_FOUND;
import static com.huntercodexs.demo.exception.ExceptionEnumerator.getException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, userMapper);
    }

    @Test
    void testGetUser_ValidUserId_ReturnsUser() {
        Optional<UserEntity> userEntity = Optional.of(getUserEntityHelper());
        when(userRepository.findById(any())).thenReturn(userEntity);

        UserModel userModel = userService.getUser(UUID.fromString(USERS[0][0]));
        assertAll(
                () -> assertEquals(USERS[0][0], userModel.getId().toString()),
                () -> assertEquals(USERS[0][1], userModel.getName()),
                () -> assertEquals(USERS[0][2], userModel.getUsername()),
                () -> assertEquals(USERS[0][3], userModel.getPassword()),
                () -> assertEquals(USERS[0][4], userModel.getEmail())
        );
    }

    @Test
    void testGetAllUsers_ExistingUsersAlreadyLoadedToTheSystem_ReturnsListWithDetailsForEachUser() {
        UserEntity userEntity = getUserEntityHelper();
        List<UserEntity> userEntityList = new ArrayList<>();
        userEntityList.add(userEntity);
        when(userRepository.findAll()).thenReturn(userEntityList);

        List<UserModel> allUsers = userService.getAllUsers();
        assertFalse(allUsers.isEmpty());

        UserModel userModel = allUsers.get(0);
        assertAll(
                () -> assertEquals(USERS[0][0], userModel.getId().toString()),
                () -> assertEquals(USERS[0][1], userModel.getName()),
                () -> assertEquals(USERS[0][2], userModel.getUsername()),
                () -> assertEquals(USERS[0][3], userModel.getPassword()),
                () -> assertEquals(USERS[0][4], userModel.getEmail())
        );
    }

    @Test
    void testGetAllUsers_NoExistentUsers_ReturnsEmptyList() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(userService.getAllUsers().isEmpty());
    }

    @Test
    void testCreateUser_ValidUserBody_ReturnsValidUser() {
        UserEntity userEntity = getUserEntityHelper();
        when(userRepository.save(any())).thenReturn(userEntity);

        UserModel userModel = userService.createUser(userMapper.toModel(userEntity));
        assertAll(
                () -> assertEquals(USERS[0][0], userModel.getId().toString()),
                () -> assertEquals(USERS[0][1], userModel.getName()),
                () -> assertEquals(USERS[0][2], userModel.getUsername()),
                () -> assertEquals(USERS[0][3], userModel.getPassword()),
                () -> assertEquals(USERS[0][4], userModel.getEmail())
        );
    }

    @Test
    void testUpdateUserDetails_ValidUserBody_UpdatesAndReturnsTheNewUserDetails() {
        UserEntity userEntityUpdated = UserEntity.builder()
                .id(UUID.fromString(USERS_UP[0][0]))
                .name(USERS_UP[0][1])
                .username(USERS_UP[0][2])
                .password(USERS_UP[0][3])
                .email(USERS_UP[0][4])
                .build();

        UserEntity userEntity = getUserEntityHelper();
        when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        when(userRepository.save(any())).thenReturn(userEntityUpdated);

        UserModel user = userMapper.toModel(userEntity);
        user = userService.updateUser(UUID.fromString(USERS_UP[0][0]), user);

        UserModel finalUser = user;
        assertAll(
                () -> assertEquals(USERS_UP[0][0], finalUser.getId().toString()),
                () -> assertEquals(USERS_UP[0][1], finalUser.getName()),
                () -> assertEquals(USERS_UP[0][2], finalUser.getUsername()),
                () -> assertEquals(USERS_UP[0][3], finalUser.getPassword()),
                () -> assertEquals(USERS_UP[0][4], finalUser.getEmail())
        );
    }

    @Test
    void testUpdateUserDetails_UserNotFound_ThrowsUserNotFoundException() {
        UserModel userModel = userMapper.toModel(getUserEntityHelper());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () ->
                userService.updateUser(UUID.fromString(USERS[0][0]), userModel));

        assertEquals(getException(USER_NOT_FOUND), exception.getMessage());
    }

    @Test
    void testDeleteUser_DeletionSuccessful_DoesNotThrowException() {
        when(userRepository.findById(any())).thenReturn(Optional.of(getUserEntityHelper()));
        userService.deleteUser(UUID.fromString(USERS[0][0]));
        verify(userRepository, times(1)).deleteById(UUID.fromString(USERS[0][0]));
    }

    @Test
    void testDeleteUser_ThrowsExceptionUserNotFound() {
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () ->
                userService.deleteUser(UUID.fromString(USERS[0][0])));

        assertEquals(getException(USER_NOT_FOUND), exception.getMessage());
        verify(userRepository, never()).deleteById(UUID.fromString(USERS[0][0]));
    }

    private UserEntity getUserEntityHelper() {
        return UserEntity.builder()
                .id(UUID.fromString(USERS[0][0]))
                .name(USERS[0][1])
                .username(USERS[0][2])
                .password(USERS[0][3])
                .email(USERS[0][4])
                .build();
    }
}
