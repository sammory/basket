package com.zerobase.zerolms.order.controller;

import com.zerobase.zerolms.admin.dto.MemberDto;
import com.zerobase.zerolms.member.model.MemberInput;
import com.zerobase.zerolms.member.service.MemberService;
import com.zerobase.zerolms.order.service.OrderService;
import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.model.ProductParam;
import com.zerobase.zerolms.product.model.ServiceResult;
import com.zerobase.zerolms.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final ProductService productService;
    private final MemberService memberService;
    private final OrderService orderService;

    // 바로구매 페이지
    @GetMapping("/direct-buy/{id}")
    public String directBuy(Model model
            , ProductParam parameter
            , Principal principal) {

        String email = principal.getName();
        MemberDto detail = memberService.detail(email);
        ProductDto productDetail = productService.frontDetail(parameter.getId());

        model.addAttribute("detail", detail);
        model.addAttribute("productDetail", productDetail);

        return "/order/direct-buy";
    }

    // 바로구매 배송지정보 수정
    @PostMapping("/direct-buy/{id}")
    public String orderInfoUpdate(Model model
            , MemberInput parameter
            , Principal principal
            , @PathVariable Long id) {

        String email = principal.getName();
        parameter.setEmail(email);

        ServiceResult result = memberService.updateMember(parameter);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";

        }

        return "redirect:/order/direct-buy/" + id;
    }

    // 바로구매처리
    @PostMapping("/direct-buy/payment")
    public String productOrder(Model model
            , MemberInput parameter
            , Principal principal
            , @RequestParam Long id
            , @RequestParam("totalPay") long totalPay) {

        String email = principal.getName();
        parameter.setEmail(email);

        ServiceResult result = orderService.paymentCash(parameter, totalPay);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/order/direct-buy/" + id;
    }

}
