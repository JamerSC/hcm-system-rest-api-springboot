package com.jamersc.springboot.hcm_api.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUtils {

    /***
     * File Handling Utilities 📁
     * Handle file uploads for resumes and CVs.
     * Can centralize the logic for saving, deleting, and naming files.
     * Example Usage: Methods to save a MultipartFile to a specific directory,
     * generate a unique filename, or validate a file's type.
     * */

    private static final String UPLOAD_DIR = "uploads/resumes";

    public static String saveFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFilename = file.getOriginalFilename();
        String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
        Path filePath = uploadPath.resolve(uniqueFilename);
        Files.copy(file.getInputStream(), filePath);

        return filePath.toString();
    }
}
