package com.VO.resultVO.apiResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUpload {
    private Integer memberId;

    private String fileName;

    private String filePath;

    private Integer boardItemId;

    private String fileOrigName;

    private String fileSize;

    private String fileUpDate;
}
