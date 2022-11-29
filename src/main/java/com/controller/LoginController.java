package com.controller;

import com.VO.resultVO.LoginResult;
import com.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/api/login/")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @ResponseBody
    @PostMapping("/login")
    public LoginResult.LoginData getLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> params) {
        return loginService.getLogin(request, response, params);
    }
}
