package com.zerobase.zerolms.member.exception;

// RuntimeException 상속받아서 따로 처리안함
public class MemberNotEmailAuthException extends RuntimeException {
    // 이메일 활성화 안됬을 때 에러
    public MemberNotEmailAuthException(String error) {
        super(error);
    }
}
