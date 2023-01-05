package com.zerobase.zerolms.admin.dto;

import com.zerobase.zerolms.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto {

    String email;
    String userName;
    String phone;
    String password;
    String address;
    LocalDateTime regDt;
    LocalDateTime udtDt;

    boolean emailAuthYn;
    LocalDateTime emailAuthDt;
    String emailAuthKey;

    String resetPasswordKey;
    LocalDateTime resetPasswordLimitDt;

    boolean adminYn;
    String userStatus;

//    private String zipcode;
//    private String addr;
//    private String addrDetail;

    // 추가컬럼
    long totalCount;
    long seq;

    public static MemberDto of(Member member) {

        return MemberDto.builder()
                .email(member.getEmail())
                .userName(member.getUserName())
                .phone(member.getPhone())
                //.password(member.getPassword())
                .regDt(member.getRegDt())
//                .udtDt(member.getUdtDt())
                .emailAuthYn(member.isEmailAuthYn())
                .emailAuthDt(member.getEmailAuthDt())
                .emailAuthKey(member.getEmailAuthKey())
                .resetPasswordKey(member.getResetPasswordKey())
                .resetPasswordLimitDt(member.getResetPasswordLimitDt())
                .adminYn(member.isAdminYn())
                .userStatus(member.getUserStatus())

//                .zipcode(member.getZipcode())
//                .addr(member.getAddr())
//                .addrDetail(member.getAddrDetail())

                .build();
    }

}
