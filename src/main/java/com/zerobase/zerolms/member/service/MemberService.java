package com.zerobase.zerolms.member.service;

import com.zerobase.zerolms.admin.dto.MemberDto;
import com.zerobase.zerolms.admin.model.MemberParam;
import com.zerobase.zerolms.member.model.MemberInput;
import com.zerobase.zerolms.member.model.ResetPasswordInput;
import com.zerobase.zerolms.product.model.ServiceResult;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    boolean register(MemberInput parameter);

    /**
     * uuid에 해당하는 게정을 활성화 함.
     */
    boolean emailAuth(String uuid);

    /**
     * 입력한 이메일로 비밀번호 초기화 정보를 전송
     */
    boolean sendResetPassword(ResetPasswordInput parameter);

    /**
     * 입력받은 uuid에 대해서 password로 초기화 함
     */
    boolean resetPassword(String uuid, String password);

    /**
     * 입력받은 uuid값이 유효한지 확인
     */
    boolean checkResetPassword(String uuid);

    /**
     * 회원 목록 리턴(관리자에서만 사용 가능)
     */
    List<MemberDto> list(MemberParam parameter);

    /**
     * 회원 상세 정보
     */
    MemberDto detail(String userId);

    /**
     * 회원 상세태변경
     */
    boolean updateStatus(String email, String userStatus);

    /**
     * 회원 비밀번호 초기화
     */
    boolean updatePassword(String email, String password);

    /**
     * 회원정보 수정
     */
    ServiceResult updateMember(MemberInput parameter);

    /**
     * 회원정보 페이지내 비밀번호 변경 기능
     */
    ServiceResult updateMemberPassword(MemberInput parameter);

    /**
     * 회원을 탈퇴시켜주는 로직
     */
    ServiceResult withdraw(String email, String password);

}
