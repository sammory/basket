package com.zerobase.zerolms.product.controller;

import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.model.ProductInput;
import com.zerobase.zerolms.product.model.ProductParam;
import com.zerobase.zerolms.product.service.ProductService;
import com.zerobase.zerolms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor // final 생성자 생성하기 위해
@Controller
public class AdminProductController extends BaseController {

    private final ProductService productService;

    @GetMapping("/admin/product/list.do")
    public String list(Model model, ProductParam parameter) {

        parameter.init();
        List<ProductDto> productList = productService.list(parameter);

        // 페이징
        long totalCount = 0;
        if (!CollectionUtils.isEmpty(productList)) {
            totalCount = productList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", productList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "/admin/product/list";
    }

    @GetMapping("/admin/product/add.do")
    public String add(Model model) {

        return "/admin/product/add";
    }

    @PostMapping("/admin/product/add.do")
    public String addSubmit(Model model, ProductInput parameter) {

        boolean result = productService.add(parameter);

        return "redirect:/admin/product/list.do";
    }

}

