package com.zerobase.zerolms.product.controller;

import com.zerobase.zerolms.admin.dto.CategoryDto;

import com.zerobase.zerolms.admin.repository.CategoryService;
import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.model.ProductParam;
import com.zerobase.zerolms.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor // 멤버서비스 가져오기위해 추가 / 생성자 자동생성
@Controller
public class ProductController extends BaseController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/product")
    public String product(Model model
            , ProductParam parameter) {

        List<ProductDto> list = productService.frontList(parameter);
        model.addAttribute("list", list);

        int productTotalCount = 0;
        List<CategoryDto> categoryList = categoryService.frontList(CategoryDto.builder().build());
        if (categoryList != null) {
            for (CategoryDto x : categoryList) {
                productTotalCount += x.getProductCount();
            }
        }

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("productTotalCount", productTotalCount);

        return "/product/index";
    }

    @GetMapping("/product/{id}")
    public String productDetail(Model model
            , ProductParam parameter) {

        ProductDto detail = productService.frontDetail(parameter.getId());
        model.addAttribute("detail", detail);

        return "/product/detail";
    }


}

