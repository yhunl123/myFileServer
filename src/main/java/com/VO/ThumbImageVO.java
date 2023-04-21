package com.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThumbImageVO {
    private Integer pkSeq;

    private String fdThumbPath;

    private String fdThumbName;

    private Long fdThumbSize;

    private Integer fkFileSeq;
}
