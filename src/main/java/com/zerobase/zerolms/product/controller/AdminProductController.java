package com.zerobase.zerolms.product.controller;

import com.zerobase.zerolms.admin.repository.CategoryService;
import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.model.ProductInput;
import com.zerobase.zerolms.product.model.ProductParam;
import com.zerobase.zerolms.product.service.ProductService;
import com.zerobase.zerolms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.UUID;

@Slf4j
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

    private String[] getNewSavaFile(String baseLocalPath, String baseUrlPath, String originalFilename) {

        LocalDate now = LocalDate.now();

        String[] dirs = {
                String.format("%s/%d/", baseLocalPath, now.getYear()),
                String.format("%s/%d/%02d/", baseLocalPath, now.getYear(), now.getMonthValue()),
                String.format("%s/%d/%02d/%02d/", baseLocalPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth())};

        String urlDir = String.format("%s/%d/%02d/%02d/", baseUrlPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth());


        for (String dir : dirs) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.mkdir();
            }
        }

        String fileExtension = "";
        if (originalFilename != null) {
            int dotPos = originalFilename.lastIndexOf(".");
            if (dotPos > -1) {
                fileExtension = originalFilename.substring(dotPos + 1);
            }
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String newFilename = String.format("%s%s", dirs[2], uuid);
        String newUrlFilename = String.format("%s%s", urlDir, uuid);
        if (fileExtension.length() > 0) {
            newFilename += "." + fileExtension;
            newUrlFilename += "." + fileExtension;
        }

        return new String[]{newFilename, newUrlFilename};
    }

    @PostMapping(value = {"/admin/product/add.do", "/admin/product/edit.do"})
    public String addSubmit(Model model, HttpServletRequest request
            , MultipartFile file
            , ProductInput parameter) {


        String savaFilename = "";
        String urlFilename = "";

        if (file != null) {
            String originalFilename = file.getOriginalFilename();

            String baseLocalPath = "C:/dev/zerobaseEx/basket/files";
            String baseUrlPath = "/files";
            String[] arrFilename = getNewSavaFile(baseLocalPath, baseUrlPath, originalFilename);

            savaFilename = arrFilename[0];
            urlFilename = arrFilename[1];

            try {
                File newFile = new File(savaFilename);
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
            } catch (IOException e) {
                log.info("########################## - 1");
                log.info(e.getMessage());
            }
        }

        parameter.setFilename(savaFilename);
        parameter.setUrlFilename(urlFilename);


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
    public String del(Model model, HttpServletRequest request
            , ProductInput parameter) {

        boolean result = productService.del(parameter.getIdList());

        return "redirect:/admin/product/list.do";
    }
}

