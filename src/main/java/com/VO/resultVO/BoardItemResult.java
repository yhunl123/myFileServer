package com.VO.resultVO;

import com.VO.resultVO.apiResult.BoardItemWrite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BoardItemResult {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardItemWriteDataView {
        private BoardItemWrite view;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardItemData extends ResultVO {
        private BoardItemResult.BoardItemWriteDataView data;
    }
}
