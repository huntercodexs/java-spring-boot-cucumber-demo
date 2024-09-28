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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mapstruct.factory.Mappers;

import java.util.*;

import static com.huntercodexs.demo.exception.AppExceptionHandler.USER_NOT_FOUND_EXCEPTION;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserRepository userRepository;

    final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    final UUID userId = randomUUID();

    @BeforeEach
    void setUp() {
        userServiceImpl = new UserServiceImpl(userRepository, userMapper);
    }

    @Test
    void testGetUser_ValidUserId_ReturnsUser() {

        Optional<UserEntity> userEntity = Optional.of(getUserEntityHelper());
        when(userRepository.findById(any())).thenReturn(userEntity);

        UserModel userModel = userServiceImpl.getUser(randomUUID());
        assertAll(
                () -> assertEquals(userId, userModel.getId()),
                () -> assertEquals("anyName", userModel.getName()),
                () -> assertEquals("anyUsername", userModel.getUsername()),
                () -> assertEquals("anyPassword", userModel.getPassword()),
                () -> assertEquals("anyEmail", userModel.getEmail())
        );
    }

    @Test
    void testGetAllUsers_ExistingUsersAlreadyLoadedToTheSystem_ReturnsListWithDetailsForEachUser() {

        UserEntity userEntity = getUserEntityHelper();
        List<UserEntity> userEntityList = new ArrayList<>();
        userEntityList.add(userEntity);
        when(userRepository.findAll()).thenReturn(userEntityList);

        List<UserModel> allUsers = userServiceImpl.getAllUsers();
        assertFalse(allUsers.isEmpty());

        UserModel userModel = allUsers.get(0);
        assertAll(
                () -> assertEquals(userId, userModel.getId()),
                () -> assertEquals("anyName", userModel.getName()),
                () -> assertEquals("anyUsername", userModel.getUsername()),
                () -> assertEquals("anyPassword", userModel.getPassword()),
                () -> assertEquals("anyEmail", userModel.getEmail())
        );
    }

    @Test
    void testGetAllUsers_NoExistentUsers_ReturnsEmptyList() {

        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testCreateUser_ValidUserBody_ReturnsValidUser() {

        UserEntity userEntity = getUserEntityHelper();
        when(userRepository.save(any())).thenReturn(userEntity);

        UserModel userModel = userServiceImpl.createUser(userMapper.toModel(userEntity));
        assertAll(
                () -> assertEquals(userId, userModel.getId()),
                () -> assertEquals("anyName", userModel.getName()),
                () -> assertEquals("anyUsername", userModel.getUsername()),
                () -> assertEquals("anyPassword", userModel.getPassword()),
                () -> assertEquals("anyEmail", userModel.getEmail())
        );
    }

    @Test
    void testUpdateUserDetails_ValidUserBody_UpdatesAndReturnsTheNewUserDetails() {

        UserEntity userEntityUpdated = UserEntity.builder()
                .id(userId)
                .name("anyUpdatedName")
                .username("anyUpdatedUsername")
                .password("anyUpdatePassword")
                .email("anyUpdatedEmail")
                .build();

        UserEntity userEntity = getUserEntityHelper();
        when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        when(userRepository.save(any())).thenReturn(userEntityUpdated);

        UserModel user = userMapper.toModel(userEntity);
        user = userServiceImpl.updateUser(userId, user);

        UserModel finalUser = user;
        assertAll(
                () -> assertEquals(userId, finalUser.getId()),
                () -> assertEquals("anyName", finalUser.getName()),
                () -> assertEquals("anyUsername", finalUser.getUsername()),
                () -> assertEquals("anyPassword", finalUser.getPassword()),
                () -> assertEquals("anyEmail", finalUser.getEmail())
        );
    }

    @Test
    void testUpdateUserDetails_UserNotFound_ThrowsUserNotFoundException() {

        UserModel userModel = userMapper.toModel(getUserEntityHelper());
        UserNotFoundException exception =
                assertThrows(UserNotFoundException.class, () -> userServiceImpl.updateUser(userId, userModel));

        assertEquals(USER_NOT_FOUND_EXCEPTION, exception.getMessage());

    }

    @Test
    void testDeleteUser_DeletionSuccessful_DoesNotThrowException() {

        when(userRepository.findById(any())).thenReturn(Optional.of(getUserEntityHelper()));
        userServiceImpl.deleteUser(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUser_ThrowsExceptionUserNotFound() {

        UUID userId = randomUUID();
        UserNotFoundException exception =
                assertThrows(UserNotFoundException.class, () -> userServiceImpl.deleteUser(userId));

        assertEquals(USER_NOT_FOUND_EXCEPTION, exception.getMessage());
        verify(userRepository, never()).deleteById(userId);
    }

    private UserEntity getUserEntityHelper() {
        return UserEntity.builder()
                .id(userId)
                .name("anyName")
                .username("anyUsername")
                .password("anyPassword")
                .email("anyEmail")
                .build();
    }
}
