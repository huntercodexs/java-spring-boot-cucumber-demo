package com.huntercodexs.demo.repository;

import com.huntercodexs.demo.entity.UserEntity;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
                () -> assertEquals("anyName", finalUserEntity.getName()),
                () -> assertEquals("anyUsername", finalUserEntity.getUsername()),
                () -> assertEquals("anyPassword", finalUserEntity.getPassword()),
                () -> assertEquals("anyEmail", finalUserEntity.getEmail())
        );
    }

    @Test
    void testEditUser() {

        UserEntity userEntity = saveUserEntityHelper();
        assertNotNull(userEntity);

        assertAll(
                () -> assertEquals("anyName", userEntity.getName()),
                () -> assertEquals("anyUsername", userEntity.getUsername()),
                () -> assertEquals("anyPassword", userEntity.getPassword()),
                () -> assertEquals("anyEmail", userEntity.getEmail())
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

        UserEntity userFromEntity = UserEntity.builder().name("anySender").build();
        UserEntity userToEntity = UserEntity.builder().name("anyReceiver").build();
        userFromEntity = userRepository.save(userFromEntity);
        userToEntity = userRepository.save(userToEntity);

        UserEntity userEntity = UserEntity.builder()
                .name("anyName")
                .username("anyUsername")
                .password("anyPassword")
                .email("anyEmail")
                .build();
        userEntity = userRepository.save(userEntity);

        return userEntity;
    }

    @NotNull
    private UserEntity getUpdatedUserEntityHelper(UserEntity userEntity) {
        userEntity.setName("anyUpdatedName");
        userEntity.setUsername("anyUsername");
        userEntity.setPassword("anyPassword");
        userEntity.setEmail("anyEmail");

        userEntity = userRepository.save(userEntity);
        return userEntity;
    }
}
