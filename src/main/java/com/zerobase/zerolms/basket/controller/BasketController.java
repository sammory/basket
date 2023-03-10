package com.zerobase.zerolms.basket.controller;

import com.zerobase.zerolms.basket.dto.BasketDto;
import com.zerobase.zerolms.basket.model.BasketInput;
import com.zerobase.zerolms.basket.service.BasketService;
import com.zerobase.zerolms.member.service.MemberService;
import com.zerobase.zerolms.product.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BasketController extends BaseController {

    private final BasketService basketService;
    private final MemberService memberService;

    // 장바구니 목록
    @GetMapping("/basket/list")
    public String basketList(Model model, Principal principal) {

        String email = principal.getName();
        List<BasketDto> list = basketService.myBasket(email);

        long total = basketService.totalPay(list);

        model.addAttribute("list", list);
        model.addAttribute("total", total);

        return "/basket/list";
    }



//   장바구니 품목 삭제
    @PostMapping("/basket/delete")
    public String del(Model model, BasketInput parameter) {

        boolean result = basketService.del(parameter.getProductId());

        return "redirect:/basket/list";
    }

    //   장바구니 상품 수량변경
    @PostMapping("/basket/update")
    public String update(Model model, BasketInput parameter) {

        boolean result = basketService.update(parameter.getId(), parameter.getQuantity());

        return "redirect:/basket/list";
    }

}
