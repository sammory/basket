package com.zerobase.zerolms.member.controller;

import com.zerobase.zerolms.member.entity.Member;
import com.zerobase.zerolms.member.model.MemberInput;
import com.zerobase.zerolms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/register")
    public String register() {

      return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(Model model,HttpServletRequest request
            , MemberInput parameter) {

        boolean result = memberService.register(parameter);
        model.addAttribute("result", result);

      return "member/register_complete";
    }

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("id");
        System.out.println(uuid);

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);

        return "member/email_auth";

    }
}
