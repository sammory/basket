package com.zerobase.zerolms.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MemberInput {

    private String userId;
    private String userName;
    private String email;
    private String password;
    private String phone;

}
