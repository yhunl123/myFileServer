package com.VO.resultVO.apiResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardItemList {
    private Integer boardItemId;

    private String boardItemTitle;

    private String boardItemCtDate;

}
