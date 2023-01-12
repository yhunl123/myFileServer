package com.VO.resultVO;

import com.VO.resultVO.apiResult.BoardItemList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class BoardItemResult {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardItemListView {
        private List<BoardItemList> view;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardItemData extends ResultVO {
        private BoardItemResult.BoardItemListView data;
    }
}
