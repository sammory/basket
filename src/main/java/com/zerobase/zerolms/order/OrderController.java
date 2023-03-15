package com.zerobase.zerolms.order;

import com.zerobase.zerolms.admin.dto.MemberDto;
import com.zerobase.zerolms.member.model.MemberInput;
import com.zerobase.zerolms.member.service.MemberService;
import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.model.ProductParam;
import com.zerobase.zerolms.product.model.ServiceResult;
import com.zerobase.zerolms.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final ProductService productService;
    private final MemberService memberService;

    // 결제 페이지
    @GetMapping("/direct-buy/{id}")
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

    @PostMapping("/direct-buy/{id}")
    public RedirectView orderInfoUpdate(Model model
            , MemberInput parameter
            , Principal principal
            , @PathVariable Long id) {

        String email = principal.getName();
        parameter.setEmail(email);

        ServiceResult result = memberService.updateMember(parameter);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return new RedirectView("/common/error", true);
        }

        return new RedirectView("/order/direct-buy/" + id, true);
    }

    @PostMapping("/order-basket")
    public String orderBasket(Model model
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
