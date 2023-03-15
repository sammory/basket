package com.zerobase.zerolms.member.controller;

import com.zerobase.zerolms.admin.dto.MemberDto;
import com.zerobase.zerolms.member.model.MemberInput;
import com.zerobase.zerolms.member.model.ResetPasswordInput;
import com.zerobase.zerolms.member.service.MemberService;
import com.zerobase.zerolms.product.dto.TakeProductDto;
import com.zerobase.zerolms.product.model.ServiceResult;
import com.zerobase.zerolms.product.service.TakeProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final TakeProductService takeProductService;

    @RequestMapping("/member/login")
    public String login() {

        return "member/login";
    }

    @GetMapping("/member/find-password")
    public String findPassword() {

        return "member/find_password";
    }

    @PostMapping("/member/find-password")
    public String findPasswordSubmit(Model model, ResetPasswordInput parameter) {

        boolean result = false;
        try {
            result = memberService.sendResetPassword(parameter);
        } catch (Exception e) {

        }

        model.addAttribute("result", result);

        return "member/find_password_result";
    }

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

    @GetMapping("/member/info")
    public String memberInfo(Model model, Principal principal) {

        String email = principal.getName();
        MemberDto detail = memberService.detail(email);

        model.addAttribute("detail", detail);

        return "member/info";
    }

    @PostMapping("/member/info")
    public String memberInfoSubmit(Model model
            , MemberInput parameter
            , Principal principal) {

        String email = principal.getName();
        parameter.setEmail(email);

        ServiceResult result = memberService.updateMember(parameter);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "/common/error";
        }

        return "redirect:/member/info";
    }
//    @PostMapping("/order/direct-buy2")
//    public String memberOrderUpdate(Model model
//            , MemberInput parameter
//            , Principal principal
//            , @RequestParam ("id") Long productId) {
//
//        String email = principal.getName();
//        parameter.setEmail(email);
//
//        ServiceResult result = memberService.updateMember(parameter);
//        if (!result.isResult()) {
//            model.addAttribute("message", result.getMessage());
//            return "/common/error";
//        }
//
//        return "/order/direct-buy?id=" + productId;
//    }

    @GetMapping("/member/password")
    public String memberPassword(Model model, Principal principal) {

        String email = principal.getName();
        MemberDto detail = memberService.detail(email);
        model.addAttribute("detail", detail);

        return "member/password";
    }

    @PostMapping("/member/password")
    public String memberPasswordSubmit(Model model
            , MemberInput parameter
            , Principal principal) {

        String email = principal.getName();
        parameter.setEmail(email);

        ServiceResult result = memberService.updateMemberPassword(parameter);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/info";
    }

    @GetMapping("/member/takeproduct")
    public String memberTakePurchase(Model model, Principal principal) {

        String email = principal.getName();
        List<TakeProductDto> list = takeProductService.myProduct(email);

        model.addAttribute("list", list);

        return "member/takeproduct";
    }

    @GetMapping("/member/withdraw")
    public String memberWithdraw(Model model) {

        return "member/withdraw";
    }

    @PostMapping("/member/withdraw")
    public String memberWithdrawSubmit(Model model
            , MemberInput parameter
            , Principal principal) {

        String email = principal.getName();

        ServiceResult result = memberService.withdraw(email, parameter.getPassword());
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
        }

        return "redirect:/member/logout";
    }

}
