package com.moro.service.impl;

import com.moro.model.exception.StorageException;
import com.moro.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
@Transactional
public class FileSystemStorageService implements StorageService {

    private static final String ROOT = "./storage";

    private final Path rootLocation;

    public FileSystemStorageService() {
        this.rootLocation = Path.of(ROOT);
    }

    @Override
    public String storeUserPhoto(final int userId, final MultipartFile file) {
        String filename = userId + "-" + StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }

            if (filename.contains("..")) {
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Files.createDirectory(rootLocation).resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);

                return filename;
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Resource loadAsResource(final String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageException(
                        "Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
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

    private Path load(final String filename) {
        return rootLocation.resolve(filename);
    }
}
