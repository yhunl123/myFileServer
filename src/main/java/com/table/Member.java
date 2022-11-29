package com.table;

import lombok.Data;

import javax.persistence.*;

@Table(name = "Member")
@Entity
@Data
public class Member {
    @Id
    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_pw")
    private String memberPw;

    @Column(name = "member_ct_date")
    private String memberCtDate;

    @Column(name = "user_token")
    private String userToken;

    @Column(name = "member_auth")
    private String memberAuth;
}
