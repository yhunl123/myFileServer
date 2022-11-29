package com.controller;

import com.VO.resultVO.FileResult;
import com.VO.resultVO.LoginResult;
import com.service.FileService;
import com.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/api/file/")
public class FileController {

    @Autowired
    private FileService fileService;

    @ResponseBody
    @PostMapping("/fileUpload")
    public FileResult.FileUploadData getLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> params) {
        return fileService.fileUpload(request, response, params);
    }

}
