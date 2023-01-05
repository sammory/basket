package com.zerobase.zerolms.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러역할
public class AdminMainController {

    //주소를 맵핑하기위해서 펑션이 필요
    @GetMapping("/admin/main.do")
    public String main() {

        return "/admin/main";
    }
}
