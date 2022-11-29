package com.VO.resultVO.apiResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginToken {
    private Integer memberId;

    private String memberName;

    private String userToken;

    private String memberAuth;
}
