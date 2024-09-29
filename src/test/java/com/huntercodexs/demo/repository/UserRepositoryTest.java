package com.huntercodexs.demo.repository;

import com.huntercodexs.demo.entity.UserEntity;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.huntercodexs.demo.config.ConstantsConfig.USERS;
import static com.huntercodexs.demo.config.ConstantsConfig.USERS_UP;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void testGetUser() {

        UserEntity userEntity = saveUserEntityHelper();
        userEntity = userRepository.findById(userEntity.getId()).orElse(null);

        assertTrue(userRepository.findAll().size() > 0);
        assertNotNull(userEntity);

        UserEntity finalUserEntity = userEntity;
        assertAll(
                () -> assertEquals(USERS[0][1], finalUserEntity.getName()),
                () -> assertEquals(USERS[0][2], finalUserEntity.getUsername()),
                () -> assertEquals(USERS[0][3], finalUserEntity.getPassword()),
                () -> assertEquals(USERS[0][4], finalUserEntity.getEmail())
        );
    }

    @Test
    void testEditUser() {

        UserEntity userEntity = saveUserEntityHelper();
        assertNotNull(userEntity);

        assertAll(
                () -> assertEquals(USERS[0][1], userEntity.getName()),
                () -> assertEquals(USERS[0][2], userEntity.getUsername()),
                () -> assertEquals(USERS[0][3], userEntity.getPassword()),
                () -> assertEquals(USERS[0][4], userEntity.getEmail())
        );
    }

    @Test
    void testDeleteUser() {

        UserEntity userEntity = saveUserEntityHelper();

        assertTrue(userRepository.findAll().size() > 0);
        userEntity = userRepository.findById(userEntity.getId()).orElse(null);
        assertNotNull(userEntity);

        userRepository.delete(userEntity);

        assertTrue(userRepository.findById(userEntity.getId()).isEmpty());
    }

    @NotNull
    private UserEntity saveUserEntityHelper() {

        //TODO: Check this logic and functionality, looks like confusing
        UserEntity userFromEntity = UserEntity.builder().name("anySender").build();

        UserEntity userToEntity = UserEntity.builder().name("anyReceiver").build();

        userRepository.save(userFromEntity);
        userRepository.save(userToEntity);

        UserEntity userEntity = UserEntity.builder()
                .name(USERS[0][1])
                .username(USERS[0][2])
                .password(USERS[0][3])
                .email(USERS[0][4])
                .build();

        userEntity = userRepository.save(userEntity);

        return userEntity;
    }

    @NotNull
    private UserEntity getUpdatedUserEntityHelper(UserEntity userEntity) {
        userEntity.setName(USERS_UP[0][0]);
        userEntity.setUsername(USERS_UP[0][2]);
        userEntity.setPassword(USERS_UP[0][3]);
        userEntity.setEmail(USERS_UP[0][4]);

        userEntity = userRepository.save(userEntity);
        return userEntity;
    }
}
