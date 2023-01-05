package com.zerobase.zerolms.admin.model;

import lombok.Data;

@Data
public class MemberInput {
    String email;
    String userStatus;
    String password;
}
