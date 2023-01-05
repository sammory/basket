package com.zerobase.zerolms.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor // 멤버서비스 가져오기위해 추가 / 생성자 자동생성
@Controller
public class AdminMemberController {

    @GetMapping("/admin/member/list.do")
    public String list() {

        return "/admin/member/list";
    }

}

