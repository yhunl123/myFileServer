package com.service;

import com.VO.BoardVO;
import com.VO.FileVO;
import com.VO.resultVO.BoardItemResult;
import com.VO.resultVO.ErrorVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.repository.BoardItemRepository;
import com.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class BoardItemService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BoardItemRepository boardItemRepository;
    private MemberRepository memberRepository;

    private final ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);

    public BoardItemResult.BoardItemData saveBoardItem(HttpServletRequest request, HttpServletResponse response, Map params) {
        HttpSession session = request.getSession(false);

        BoardItemResult.BoardItemData resultVO = new BoardItemResult.BoardItemData();

        BoardVO boardVO = new BoardVO();
        ErrorVO errorVO = new ErrorVO();

        try {
            String userToken = session.getAttribute("userToken").toString();

            Map userInfo = memberRepository.findByUserToken(userToken);

            if (!userInfo.isEmpty()) {
                boardVO.setBoardItemTitle(params.get("boardItemTitle").toString());
                boardVO.setBoardItemContents(params.get("boardItemContents").toString());



            } else {
                resultVO.setSuccess(false);
                errorVO.setCode(110);
                errorVO.setMessage("User not find");
            }

            resultVO.setError(errorVO);
            return resultVO;
        } catch (Exception e) {
            e.printStackTrace();
            resultVO.setSuccess(false);
            errorVO.setCode(-1);
            errorVO.setMessage("Internet Server Error");
            resultVO.setError(errorVO);

            return resultVO;
        }
    }
}
