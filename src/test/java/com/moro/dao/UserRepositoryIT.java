package com.moro.dao;

import com.moro.dao.repository.UserRepository;
import com.moro.model.entity.User;
import com.moro.model.entity.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAllUsers() {
        Page<User> users = userRepository.findAll(PageRequest.of(0, 2));

        Assertions.assertTrue(users.hasContent());
    }

    @Test
    void findUserById() {
        Optional<User> user = userRepository.findById(1);

        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals(1, user.get().getUserId().intValue());
    }

    @Test
    void addNewUser() {
        User user = getUser();

        userRepository.saveAndFlush(user);

        Assertions.assertNotNull(user.getUserId());
    }

    @Test
    void editUser() {
        User user = userRepository.findById(1).get();
        String newUsername = "Jackson22";

        user.setName(newUsername);

        userRepository.saveAndFlush(user);

        Assertions.assertEquals(newUsername, user.getName());
    }

    @Test
    void tryAddUserWithExistentEmail() {
        User user = getUser();
        user.setEmail(userRepository.findById(1).get().getEmail());

        Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> userRepository.saveAndFlush(user));
    }

    private User getUser() {
        return User.builder()
                .email("newuser@mail.com")
                .name("new_user")
                .password("password")
                .userRole(UserRole.builder().userRoleId(1).build())
                .build();
    }
}
