package com.zerobase.zerolms.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ResetPasswordInput {
    private String email;
    private String userName;

    private String id;
    private String password;
}
