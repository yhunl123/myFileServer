package com.VO.resultVO;

import com.VO.FileVO;
import com.VO.resultVO.apiResult.FileUpload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

public class FileResult {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FileUploadDataView {
        private FileUpload view;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FileUploadData extends ResultVO {
        private FileResult.FileUploadDataView data;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FileResultViewClass {
        private FileVO view;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FileResultDataClass extends ResultVO {
        private FileResult.FileResultViewClass data;
    }


}
