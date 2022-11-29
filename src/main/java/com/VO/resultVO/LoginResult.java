package com.VO.resultVO;

import com.VO.resultVO.apiResult.LoginToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LoginResult {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginDataView {
        private LoginToken view;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginData extends ResultVO {
        private LoginDataView data;
    }
}
