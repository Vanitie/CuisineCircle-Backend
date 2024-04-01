package com.example.server.service;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    boolean save(MultipartFile file,String fileName,String filePath);
}
