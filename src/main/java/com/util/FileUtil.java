package com.util;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;

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

    public BufferedImage extract(String filePath, long timeUs) throws Exception {
        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filePath)) {
            grabber.start();

            // timeUs에 해당하는 프레임으로 위치 이동
            int frameNumber = (int) Math.round(grabber.getFrameRate() * timeUs / 1000000.0);
            grabber.setFrameNumber(frameNumber);

            // 프레임 이미지 추출
            Frame frame = grabber.grabImage();
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage image = converter.convert(frame);

            grabber.stop();
            return image;
        }
    }
}
