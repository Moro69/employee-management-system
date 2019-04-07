package com.moro.dao;

import com.moro.dao.repository.UserRoleRepository;
import com.moro.model.entity.UserRole;
import com.moro.model.enums.UserRoleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRoleRepositoryIT {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    void findAdminUserRoleById() {
        UserRole userRole = userRoleRepository.findById(1).get();

        Assertions.assertEquals(UserRoleEnum.ADMIN, userRole.getName());
    }

    @Test
    void findUserUserRoleById() {
        UserRole userRole = userRoleRepository.findById(2).get();

        Assertions.assertEquals(UserRoleEnum.USER, userRole.getName());
    }

    @Test
    void findUserRoleByName() {
        UserRole userRole = userRoleRepository.findByName(UserRoleEnum.ADMIN).get();

        Assertions.assertEquals(UserRoleEnum.ADMIN, userRole.getName());
    }
}
