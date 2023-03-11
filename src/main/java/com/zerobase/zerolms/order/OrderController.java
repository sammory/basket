package com.zerobase.zerolms.order;

import com.zerobase.zerolms.admin.dto.MemberDto;
import com.zerobase.zerolms.member.service.MemberService;
import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.model.ProductParam;
import com.zerobase.zerolms.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final ProductService productService;
    private final MemberService memberService;

    // 결제 페이지
    @GetMapping("/direct-buy")
    public String direct(Model model
            , ProductParam parameter
            , Principal principal) {

        String email = principal.getName();
        MemberDto detail = memberService.detail(email);
        ProductDto productDetail = productService.frontDetail(parameter.getId());

        model.addAttribute("detail", detail);
        model.addAttribute("productDetail", productDetail);


        return "/order/direct-buy";
    }

    @PostMapping("/order-complete")
    public String directComplete(Model model
            , ProductParam parameter
            , Principal principal) {

//        String email = principal.getName();
//        MemberDto detail = memberService.detail(email);
//        ProductDto productDetail = productService.frontDetail(parameter.getId());
//
//        model.addAttribute("detail", detail);
//        model.addAttribute("productDetail", productDetail);


        return "/order/direct-buy";
    }

    @GetMapping("/order-buy")
    public String order(Model model) {


        return "/order/order-buy";
    }
}
