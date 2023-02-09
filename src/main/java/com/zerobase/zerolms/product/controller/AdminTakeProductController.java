package com.zerobase.zerolms.product.controller;

import com.zerobase.zerolms.admin.repository.CategoryService;
import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.dto.TakeProductDto;
import com.zerobase.zerolms.product.model.ProductInput;
import com.zerobase.zerolms.product.model.ProductParam;
import com.zerobase.zerolms.product.model.ServiceResult;
import com.zerobase.zerolms.product.model.TakeProductParam;
import com.zerobase.zerolms.product.service.ProductService;
import com.zerobase.zerolms.product.service.TakeProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor // final 생성자 생성하기 위해
@Controller
public class AdminTakeProductController extends BaseController {

    private final ProductService productService;
    private final TakeProductService takeProductService;


    @GetMapping("/admin/takeproduct/list.do")
    public String list(Model model, TakeProductParam parameter
            , BindingResult bindingResult) {

        parameter.init();
        List<TakeProductDto> list = takeProductService.list(parameter);

        // 페이징
        long totalCount = 0;
        if (!CollectionUtils.isEmpty(list)) {
            totalCount = list.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", list);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        List<ProductDto> productList = productService.listAll();
        model.addAttribute("productList", productList);

        return "/admin/takeproduct/list";
    }

    @PostMapping("/admin/takeproduct/status.do")
    public String satus(Model model, TakeProductParam parameter) {

        ServiceResult result = takeProductService.updateStatus(parameter.getId(), parameter.getStatus());
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/admin/takeproduct/list.do";
    }


}

