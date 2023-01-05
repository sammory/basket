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
public class Member implements MemberCode{

    @Id
    private String email;

    private String userName;
    private String phone;
    private String password;
    private LocalDateTime regDt;

    private boolean emailAuthYn;
    private LocalDateTime emailAuthDt;
    private String emailAuthKey;

    private String resetPasswordKey;

    private LocalDateTime resetPasswordLimitDt; // 초기화가능한 날짜 제한 ex) 몇일까지만 초기화가능

    private boolean adminYn; // 관리자인지 여부 확인

    private String userStatus; // 이용 가능한상태, 정지상태

    private String zipcode;
    private String addr;
    private String addrDetail;

}
