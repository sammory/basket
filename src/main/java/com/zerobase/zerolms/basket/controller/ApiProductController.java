package com.zerobase.zerolms.basket.controller;

import com.zerobase.zerolms.admin.repository.CategoryService;
import com.zerobase.zerolms.basket.model.BasketInput;
import com.zerobase.zerolms.basket.service.BasketService;
import com.zerobase.zerolms.common.model.ResponseResult;
import com.zerobase.zerolms.product.controller.BaseController;
import com.zerobase.zerolms.product.model.ServiceResult;
import com.zerobase.zerolms.product.model.TakeProductInput;
import com.zerobase.zerolms.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor // 멤버서비스 가져오기위해 추가 / 생성자 자동생성
@RestController // json 형태로 리턴
public class ApiProductController extends BaseController {

    private final BasketService basketService;

    @PostMapping("/api/basket/add.api")
    public ResponseEntity<?> productAdd(Model model
            , @RequestBody BasketInput parameter
            , Principal principal) {

        parameter.setEmail(principal.getName());

        ServiceResult result = basketService.add(parameter);
        if (!result.isResult()) {
            ResponseResult responseResult = new ResponseResult(false, result.getMessage());
            return ResponseEntity.ok().body(responseResult);
        }
        ResponseResult responseResult = new ResponseResult(true);
        return ResponseEntity.ok().body(responseResult);
    }
}

