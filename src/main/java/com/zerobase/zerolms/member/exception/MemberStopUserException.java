package com.zerobase.zerolms.member.exception;

// RuntimeException 상속받아서 따로 처리안함
public class MemberStopUserException extends RuntimeException {
    // 이메일 활성화 안됬을 때 에러
    public MemberStopUserException(String error) {
        super(error);
    }
}
