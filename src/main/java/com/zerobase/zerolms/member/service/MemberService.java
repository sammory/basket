package com.zerobase.zerolms.member.service;

import com.zerobase.zerolms.member.model.MemberInput;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    boolean register(MemberInput parameter);

    /**
     * uuid에 해당하는 게정을 활성화 함.
     */
    boolean emailAuth(String uuid);
}
