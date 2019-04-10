package com.moro.service.impl;

import com.moro.model.entity.Image;
import com.moro.model.entity.User;
import com.moro.model.exception.StorageException;
import com.moro.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@Transactional
public class FileSystemStorageService implements StorageService {

    private static final String ROOT = "src/main/resources/storage";

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService() {
        this.rootLocation = Path.of(ROOT);
    }

    @Override
    public void storeUserPhoto(final User user,
                               final MultipartFile file) {
        validateFile(file);

        if (user.getImage() == null) {
            Image image = new Image();
            image.setUrl(trySaveFile(file, user.getUserId()));

            user.setImage(image);
        } else {
            delete(user.getImage().getUrl());
            user.getImage().setUrl(trySaveFile(file, user.getUserId()));
        }
    }

    private void validateFile(final MultipartFile file) {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }

        if (Objects.requireNonNull(file.getOriginalFilename()).contains("..")) {
            throw new StorageException(
                    "Cannot store file with relative path outside current directory");
        }
    }

    private String trySaveFile(final MultipartFile file,
                               final Integer userId) {
        String filename = userId + "-" + StringUtils.cleanPath(file.getOriginalFilename());

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);

            return filename;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public byte[] downloadAsByteArray(final String filename) {
        try {
            Path file = resolvePath(filename);

            if (Files.exists(file) || Files.isReadable(file)) {
                return Files.readAllBytes(file);
            } else {
                throw new StorageException(
                        "Could not read file: " + filename);
            }
        } catch (IOException e) {
            throw new StorageException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void delete(final String filename) {
        File file = rootLocation.resolve(filename).toFile();

        if (!file.delete()) {
            throw new StorageException("Could not delete file: " + filename);
        }
    }

    private Path resolvePath(final String filename) {
        return rootLocation.resolve(filename);
    }
}
