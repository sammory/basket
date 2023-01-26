package com.zerobase.zerolms.admin.controller;

import com.zerobase.zerolms.admin.dto.MemberDto;
import com.zerobase.zerolms.admin.model.MemberParam;
import com.zerobase.zerolms.admin.model.MemberInput;
import com.zerobase.zerolms.member.service.MemberService;
import com.zerobase.zerolms.product.controller.BaseController;
import com.zerobase.zerolms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor // 멤버서비스 가져오기위해 추가 / 생성자 자동생성
@Controller
public class AdminMemberController extends BaseController {

    private final MemberService memberService;

    @GetMapping("/admin/member/list.do")
    public String list(Model model, MemberParam parameter) {

        parameter.init();
        List<MemberDto> members = memberService.list(parameter);

        // 페이징
        long totalCount = 0;
        if (members != null && members.size() > 0) {
            totalCount = members.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", members);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "/admin/member/list";
    }

    @GetMapping("/admin/member/detail.do")
    public String detail(Model model, MemberParam parameter) {

        parameter.init();

        MemberDto member = memberService.detail(parameter.getEmail());
        model.addAttribute("member", member);

        return "/admin/member/detail";
    }

    @PostMapping("/admin/member/status.do")
    public String status(Model model, MemberInput parameter) {

        boolean result = memberService.updateStatus(parameter.getEmail(), parameter.getUserStatus());

        return "redirect:/admin/member/detail.do?email=" + parameter.getEmail();
    }

    @PostMapping("/admin/member/password.do")
    public String password(Model model, com.zerobase.zerolms.member.model.MemberInput parameter) {

        boolean result = memberService.updatePassword(parameter.getEmail(), parameter.getPassword());

        return "redirect:/admin/member/detail.do?email=" + parameter.getEmail();
    }

}

