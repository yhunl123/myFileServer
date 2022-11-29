package com.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO implements Serializable {
    private Integer memberId;

    private String memberName;

    private String memberPw;

    private String memberCtDate;

    private String userToken;

    private String memberAuth;

}
