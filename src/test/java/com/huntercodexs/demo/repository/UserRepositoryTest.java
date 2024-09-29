package com.huntercodexs.demo.repository;

import com.huntercodexs.demo.entity.UserEntity;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.huntercodexs.demo.config.ConstantsConfig.USERS;
import static com.huntercodexs.demo.config.ConstantsConfig.USERS_UP;
import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private void reset() {
        try {
            UserEntity userEntity = userRepository.findByUsername(USERS[0][2]);
            if (userEntity != null) {
                userRepository.delete(userEntity);
            }
        } catch (RuntimeException re) {
            System.out.println("USERS NOT FOUND TO DELETE");
        }
    }

    @Order(1)
    @Test
    void createUserTest() {

        reset();
        UserEntity userEntity = userEntityToCreate(0);
        assertNotNull(userEntity);

        userRepository.save(userEntity);

        UserEntity userEntityCheck = userRepository.findByUsername(USERS[0][2]);
        assertNotNull(userEntityCheck);

        assertAll(
                () -> assertEquals(USERS[0][1], userEntityCheck.getName()),
                () -> assertEquals(USERS[0][2], userEntityCheck.getUsername()),
                () -> assertEquals(USERS[0][3], userEntityCheck.getPassword()),
                () -> assertEquals(USERS[0][4], userEntityCheck.getEmail())
        );

    }

    @Order(2)
    @Test
    void getUserTest() {

        UserEntity userEntity = userRepository.findByUsername(USERS[0][2]);
        assertNotNull(userEntity);

        assertAll(
                () -> assertEquals(USERS[0][1], userEntity.getName()),
                () -> assertEquals(USERS[0][2], userEntity.getUsername()),
                () -> assertEquals(USERS[0][3], userEntity.getPassword()),
                () -> assertEquals(USERS[0][4], userEntity.getEmail())
        );

        userRepository.save(userEntity);
    }

    @Order(3)
    @Test
    void updateUserTest() {

        UserEntity userEntity = userRepository.findByUsername(USERS[0][2]);
        assertNotNull(userEntity);

        userEntity.setName(USERS_UP[0][1]);
        userEntity.setUsername(USERS_UP[0][2]);
        userEntity.setPassword(USERS_UP[0][3]);
        userEntity.setEmail(USERS_UP[0][4]);

        userRepository.save(userEntity);

        UserEntity userEntityCheck = userRepository.findByUsername(USERS_UP[0][2]);
        assertNotNull(userEntityCheck);

        assertAll(
                () -> assertEquals(USERS_UP[0][1], userEntityCheck.getName()),
                () -> assertEquals(USERS_UP[0][2], userEntityCheck.getUsername()),
                () -> assertEquals(USERS_UP[0][3], userEntityCheck.getPassword()),
                () -> assertEquals(USERS_UP[0][4], userEntityCheck.getEmail())
        );
    }

    @Order(4)
    @Test
    void deleteUserTest() {

        UserEntity userEntity = userRepository.findByUsername(USERS[0][2]);
        assertNotNull(userEntity);

        userRepository.delete(userEntity);

        assertTrue(userRepository.findById(userEntity.getId()).isEmpty());
    }

    private UserEntity userEntityToCreate(int userIndex) {

        return UserEntity.builder()
                .name(USERS[userIndex][1])
                .username(USERS[userIndex][2])
                .password(USERS[userIndex][3])
                .email(USERS[userIndex][4])
                .build();
    }

}
