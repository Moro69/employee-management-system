package com.moro.service;

import com.moro.model.dto.RegistrationModel;
import com.moro.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> findAllUsers(Pageable pageable);

    User findUserById(Integer userId);

    User findUserByEmail(String email);

    User addUser(RegistrationModel model);

    User updateUser(User user);

    void deleteUserById(Integer userId);
}
