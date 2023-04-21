package com.service;

import com.VO.BoardItemVO;
import com.VO.FileVO;
import com.VO.resultVO.BoardItemResult;
import com.VO.resultVO.ErrorVO;
import com.VO.resultVO.apiResult.BoardItemList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.repository.BoardItemRepository;
import com.repository.FileDataRepository;
import com.repository.MemberRepository;
import com.table.BoardItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class BoardItemService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);

    @Autowired
    private BoardItemRepository boardItemRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private FileDataRepository fileDataRepository;



    public BoardItemResult.BoardItemData upload(HttpServletRequest request, HttpServletResponse response, Map params) {
        HttpSession session = request.getSession(false);

        BoardItemResult.BoardItemData resultVO = new BoardItemResult.BoardItemData();

        BoardItem boardItem = new BoardItem();
        BoardItemVO boardVO;
        FileVO fileVO;
        ErrorVO errorVO = new ErrorVO();

        try {
            String userToken = request.getHeader("Authorization");

            Map userInfo = memberRepository.getMember(userToken);

            if (userInfo.isEmpty()) {
                resultVO.setSuccess(false);
                errorVO.setCode(110);
                errorVO.setMessage("User not find");
                resultVO.setError(errorVO);
                return resultVO;
            }

            boardItem.setBoardItemTitle(params.get("boardItemTitle").toString());
            boardItem.setBoardItemContents(params.get("boardItemContents").toString());
            boardItem.setMemberId(Integer.valueOf(userInfo.get("memberId").toString()));

            BoardItem boardItemInfo = boardItemRepository.save(boardItem);
            boardVO = objectMapper.convertValue(boardItemInfo, BoardItemVO.class);

            if(!Objects.equals(params.get("fileName").toString(), "")) {
                String fileName = params.get("fileName").toString();
                fileVO = objectMapper.convertValue(fileDataRepository.searchFileDataByFileName(fileName), FileVO.class);
                fileVO.setBoardItemId(boardVO.getBoardItemId());

                fileDataRepository.updateFileBoardId(fileVO);
            }

            resultVO.setSuccess(true);
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

    public BoardItemResult.BoardItemData getList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params) {

        BoardItemResult.BoardItemData resultVO = new BoardItemResult.BoardItemData();

        BoardItemResult.BoardItemListView list = new BoardItemResult.BoardItemListView();

        List<BoardItemList> boardItemList = new ArrayList<>();
        ErrorVO errorVO = new ErrorVO();

        try {
            List<Map<String, Object>> resultList = boardItemRepository.getBoardItemList(params);

            for(Map<String, Object> detail : resultList) {
                BoardItemList item = objectMapper.convertValue(detail, BoardItemList.class);

                boardItemList.add(item);
            }

            list.setView(boardItemList);

            resultVO.setData(list);
            resultVO.setError(errorVO);
            resultVO.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resultVO.setSuccess(false);
            errorVO.setCode(-1);
            errorVO.setMessage("Internet Server Error");
            resultVO.setError(errorVO);

            return resultVO;
        }

        return resultVO;
    }
}
