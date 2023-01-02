package com.zerobase.zerolms.member.service;

import com.zerobase.zerolms.member.model.MemberInput;

public interface MemberService {

    boolean register(MemberInput parameter);

    /**
     * uuid에 해당하는 게정을 활성화 함.
     */
    boolean emailAuth(String uuid);
}
