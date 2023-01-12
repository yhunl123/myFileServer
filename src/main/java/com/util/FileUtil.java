package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void printFileInfo(MultipartFile file) {
        logger.info("================================File Input Start==============================");
        logger.info("Start file information");
        logger.info("file name : " + file.getOriginalFilename());
        logger.info("file tag name : " + file.getName());
        logger.info("file size : " + file.getSize());
        logger.info("file content type : " + file.getContentType());
        logger.info("End file information.\n");
        logger.info("================================File Input Start==============================");
    }

    public void makeDir(String path) {
        File dirPath = new File(path);

        if (!dirPath.exists()) {
            dirPath.mkdir();
        }
    }

//    public ResponseEntity<Resource> setHeader(String path, String name, String oriName) throws IOException {
//        File file = new File(path + "/" + name);
//
//        String fileName = new String(oriName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
//
//        HttpHeaders headers = new HttpHeaders();
//
//        FileInputStream stream = new FileInputStream(file);
//        ByteArrayResource bResource = new ByteArrayResource(stream.readAllBytes());
////        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//
//        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
//        headers.add(HttpHeaders.PRAGMA, "no-cache");
//        headers.add(HttpHeaders.EXPIRES, "0");
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
//        headers.add("filename", fileName);
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(bResource.contentLength())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(bResource);
//    }
}
