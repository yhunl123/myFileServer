package com.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {
    private Integer fileId;

    private String fileName;

    private String filePath;

    private Integer boardItemId;

    private String fileOrigName;

    private String fileSize;

    private String fileUpDate;
}
