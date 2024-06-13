package com.ccb.controllers;

import com.ccb.common.R;
import com.ccb.model.pojo.Image;
import com.ccb.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    ImageService imageService;

    @Value("${upload.dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public R<Image> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return R.error("文件为空");
        }

        try {
            // 保存文件到服务器文件系统
            //byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
            //Files.write(path, bytes);

            // 创建Image对象并保存到数据库
            Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setType(file.getContentType());
            image.setData(file.getBytes());


            imageService.save(image);

            return R.success(image);
        } catch (IOException e) {
            log.error("文件上传失败", e);
            return R.error("上传失败");
        }
    }

    @GetMapping("/getImage")
    public R<Image> getImage(@RequestParam("id") Integer id) {
        return R.success(imageService.getById(id));

    }
}

