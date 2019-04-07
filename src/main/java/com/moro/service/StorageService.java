package com.moro.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String storeUserPhoto(int userId, MultipartFile file);

    Resource loadAsResource(String filename);

    void delete(String filename);
}
