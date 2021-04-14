package com.edhou.codefellowship.config;

import com.edhou.codefellowship.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvgConfig implements WebMvcConfigurer {
    @Autowired
    FileUploadService fileUploadService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileUploadService.upload_root + "**")
                .addResourceLocations("file:" + fileUploadService.upload_root);
    }
}

