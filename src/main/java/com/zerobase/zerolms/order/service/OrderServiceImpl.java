package com.zerobase.zerolms.order.service;

import com.zerobase.zerolms.member.entity.Member;
import com.zerobase.zerolms.member.model.MemberInput;
import com.zerobase.zerolms.member.repository.MemberRepository;
import com.zerobase.zerolms.product.model.ServiceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    @Override
    public ServiceResult paymentCash(MemberInput parameter, long totalPay) {

        ServiceResult result = new ServiceResult();
        String email = parameter.getEmail();

        Optional<Member> optionalMember = memberRepository.findById(email);
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();
        if (member.getCash() < totalPay) {
            result.setResult(false);
            result.setMessage("잔액이 부족합니다.");
            return result;
        }
        long cash = member.getCash() - totalPay;
        member.setCash(cash);
        memberRepository.save(member);

        return result;
    }
}
