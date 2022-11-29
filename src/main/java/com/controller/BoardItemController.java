package com.controller;

import com.VO.resultVO.BoardItemResult;
import com.service.BoardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/api/board/")
public class BoardItemController {

    @Autowired
    private BoardItemService boardItemService;

    @ResponseBody
    @PostMapping("/write")
    public BoardItemResult.BoardItemData boardWrite(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> params) {
        return boardItemService.saveBoardItem(request, response, params);
    }
}
