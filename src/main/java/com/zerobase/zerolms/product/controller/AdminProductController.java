package com.zerobase.zerolms.product.controller;

import com.zerobase.zerolms.admin.repository.CategoryService;
import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.model.ProductInput;
import com.zerobase.zerolms.product.model.ProductParam;
import com.zerobase.zerolms.product.service.ProductService;
import com.zerobase.zerolms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor // final 생성자 생성하기 위해
@Controller
public class AdminProductController extends BaseController {

    private final ProductService productService;
    private final CategoryService categoryService;


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

    @GetMapping(value = {"/admin/product/add.do", "/admin/product/edit.do"})
    public String add(Model model, HttpServletRequest request
            , ProductInput parameter) {

        // 카테고리 정보를 내려줘야 함
        model.addAttribute("category", categoryService.list());

        boolean editMode = request.getRequestURI().contains("/edit.do");
        ProductDto detail = new ProductDto();

        if (editMode) {
            long id = parameter.getId();
            ProductDto existProduct = productService.getById(id);
            if (existProduct == null) {
                // error 처리
                model.addAttribute("message", "상품정보가 존재하지 않습니다.");
                return "/common/error";
            }
            detail = existProduct;
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);

        return "/admin/product/add";
    }

    @PostMapping(value = {"/admin/product/add.do", "/admin/product/edit.do"})
    public String addSubmit(Model model, HttpServletRequest request
            , ProductInput parameter) {

        boolean editMode = request.getRequestURI().contains("/edit.do");

        if (editMode) {
            long id = parameter.getId();
            ProductDto existProduct = productService.getById(id);
            if (existProduct == null) {
                // error 처리
                model.addAttribute("message", "상품정보가 존재하지 않습니다.");
                return "/common/error";
            }
            boolean result = productService.set(parameter);
        } else {
            boolean result = productService.add(parameter);
        }

        return "redirect:/admin/product/list.do";

    }

    @PostMapping("/admin/product/delete.do")
    public String del(Model model,HttpServletRequest request
            , ProductInput parameter) {

        boolean result = productService.del(parameter.getIdList());

        return "redirect:/admin/product/list.do";
    }
}

