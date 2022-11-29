package com.VO.resultVO;

import com.VO.FileVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FileResult {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FileUploadDataView {
        private FileVO view;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FileUploadData extends ResultVO {
        private FileResult.FileUploadDataView data;
    }
}
