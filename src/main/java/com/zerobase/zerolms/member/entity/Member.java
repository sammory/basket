package com.zerobase.zerolms.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Member {

    @Id
    private String email;

    private String userName;
    private String phone;
    private String password;
    private String address;
    private LocalDateTime regDt;

    private boolean emailAuthYn;
    private LocalDateTime emailAuthDt;
    private String emailAuthKey;

    private String resetPasswordKey;

    private LocalDateTime resetPasswordLimitDt; // 초기화가능한 날짜 제한 ex) 몇일까지만 초기화가능

    private boolean adminYn; // 관리자인지 여부 확인

}
