package com.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
    private Integer boardItemId;

    private String boardItemTitle;

    private String boardItemContents;

    private Integer memberId;

    private String boardItemCtDate;
}
