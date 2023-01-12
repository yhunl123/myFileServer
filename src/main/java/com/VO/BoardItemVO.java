package com.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardItemVO implements Serializable {
    private Integer boardItemId;

    private String boardItemTitle;

    private String boardItemContents;

    private Integer memberId;

    private String boardItemCtDate;
}
