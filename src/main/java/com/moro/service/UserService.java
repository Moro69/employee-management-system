package com.moro.service;

import com.moro.model.dto.RegistrationModel;
import com.moro.model.dto.UserDto;
import com.moro.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface UserService {

    Page<User> findAllUsers(Pageable pageable);

    User findUserById(Integer userId);

    User findUserByEmail(String email);

    User addUser(RegistrationModel model);

    User updateUser(Principal principal, UserDto dto);

    void deleteUserById(Integer userId);

    void uploadUserPhoto(Principal principal,
                         int userId,
                         MultipartFile photo);

    void deleteUserPhoto(Principal principal, int userId);

    byte[] getPhotoAsByteArray(int userId);

    User verify(Integer userId);
}
