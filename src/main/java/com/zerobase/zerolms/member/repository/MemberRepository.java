package com.zerobase.zerolms.member.repository;

import com.zerobase.zerolms.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByEmailAuthKey(String emailAuthKey);
    Optional<Member> findByEmailAndUserName(String email, String userName);
    Optional<Member> findByResetPasswordKey(String resetPasswordKey);


}
