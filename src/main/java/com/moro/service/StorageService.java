package com.moro.service;

import com.moro.model.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void storeUserPhoto(User user, MultipartFile file);

    byte[] downloadAsByteArray(String filename);

    void delete(String filename);
}
