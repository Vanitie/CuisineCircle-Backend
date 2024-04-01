package com.example.server.controller;

import com.example.server.service.Impl.StorageServiceImpl;
import com.example.server.vo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController//set the controller
@Slf4j//for the lombok
@RequestMapping("/")//set the /poi
public class CommonController {
    @Autowired
    private StorageServiceImpl storageService;
    @Value("${upload.accessPath}")
    private String accessPath;
    @Value("${upload.localPath}")
    private String localPath;
    @PostMapping(value = "/upload")
    public Result upload(HttpServletRequest request, HttpServletResponse response){
        MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
        MultipartFile file=multipartHttpServletRequest.getFile("file");//this is for receive the data
        String Filename=file.getOriginalFilename();

        log.info("name={},URL={}",Filename,localPath);

        storageService.save(file,Filename,localPath);//this is for store the data
        return Result.success(accessPath+Filename);
    }
}
