package com.controller;

import com.VO.resultVO.BoardItemResult;
import com.service.BoardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/api/board/")
public class BoardItemController {

    @Autowired
    private BoardItemService boardItemService;

    @ResponseBody
    @GetMapping("/getList")
    public BoardItemResult.BoardItemData getList(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> params) {
        return boardItemService.getList(request, response, params);
    }

    @ResponseBody
    @PostMapping("/upload")
    public BoardItemResult.BoardItemData boardWrite(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> params) {
        return boardItemService.upload(request, response, params);
    }
}
