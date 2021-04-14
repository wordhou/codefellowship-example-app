package com.edhou.codefellowship.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {
    public final String upload_root = "uploaded-files/";

    public void saveFile(String dir, String fileName, MultipartFile multipartFile) throws IOException {
        Path path = Paths.get(upload_root + dir);

        if (!Files.exists(path)) Files.createDirectories(path);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = path.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file:" + fileName, ioe);
        }
    }
}
