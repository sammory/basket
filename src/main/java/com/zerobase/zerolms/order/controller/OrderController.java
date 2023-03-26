package com.zerobase.zerolms.order.controller;

import com.zerobase.zerolms.admin.dto.MemberDto;
import com.zerobase.zerolms.basket.dto.BasketDto;
import com.zerobase.zerolms.basket.service.BasketService;
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

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final ProductService productService;
    private final MemberService memberService;
    private final OrderService orderService;
    private final BasketService basketService;

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

    // 구매자 정보수정
    @PostMapping("/infoUpdate")
    public String orderInfoUpdate(Model model
            , MemberInput parameter
            , Principal principal
            , @RequestParam Long id
            , @RequestParam("pageDivide") String pageDivide) {

        String email = principal.getName();
        parameter.setEmail(email);

        ServiceResult result = memberService.updateMember(parameter);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";

        }

        if (pageDivide.equals("direct")) {
            // 바로구매 페이지로 리턴
            return "redirect:/order/direct-buy/" + id;
        } else {
            // 장바구니 페이지로 리턴
            return "redirect:/order/basket-buy";
        }
    }

    // 바로구매처리 기능
    @PostMapping("/direct-buy/payment")
    public String productOrder(Model model
            , MemberInput parameter
            , Principal principal
            , @RequestParam Long id
            , @RequestParam("totalPrice") long totalPrice
            , @RequestParam("pageDivide") String pageDivide) {

        String email = principal.getName();
        parameter.setEmail(email);

        ServiceResult result = orderService.paymentCash(parameter, totalPrice);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        if (pageDivide.equals("direct")) {
            // 바로구매 페이지로 리턴
            return "redirect:/order/direct-buy/" + id;
        } else {
            // 장바구니 페이지로 리턴
            return "redirect:/order/basket-buy";
        }

    }

    // 장바구니 상품구매 페이지
    @GetMapping("/basket-buy")
    public String basketTotalPayment(Model model
            , ProductParam parameter
            , Principal principal) {

        String email = principal.getName();
        MemberDto detail = memberService.detail(email);
        ProductDto productDetail = productService.frontDetail(parameter.getId());

        // 장바구니 목록 총 상품가격
        List<BasketDto> list = basketService.myBasket(email);
        long total = basketService.totalPay(list);

        model.addAttribute("detail", detail);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("list", list);
        model.addAttribute("total", total);

        return "/order/basket-buy";
    }

}
