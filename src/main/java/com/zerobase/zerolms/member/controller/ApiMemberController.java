package com.zerobase.zerolms.member.controller;

import com.zerobase.zerolms.common.model.ResponseResult;
import com.zerobase.zerolms.product.dto.TakeProductDto;
import com.zerobase.zerolms.product.model.ServiceResult;
import com.zerobase.zerolms.product.model.TakeProductInput;
import com.zerobase.zerolms.product.service.TakeProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor // 생성자 자동으로 생성해줌
@RestController
public class ApiMemberController {
    private final TakeProductService takeProductService;

    @PostMapping("/api/member/product/cancel.api")
    public ResponseEntity<?> cancelProduct(Model model
            , @RequestBody TakeProductInput parameter
            , Principal principal) {

        String email = principal.getName();
        // 내 상품정보 인지 확인
        TakeProductDto detail = takeProductService.detail(parameter.getTakeProductId());
        if (detail == null) {
            ResponseResult responseResult = new ResponseResult(false, "구매 요청 정보가 존재하지 않습니다.");
            return ResponseEntity.ok().body(responseResult);
        }
        // 내 상품정보가 아닐 경우
        if (email == null || !email.equals(detail.getEmail())) {
            ResponseResult responseResult = new ResponseResult(false, "본인의 구매 요청 정보만 취소할 수 있습니다.");
            return ResponseEntity.ok().body(responseResult);
        }

        ServiceResult result = takeProductService.cancel(parameter.getTakeProductId());
        if (!result.isResult()) {
            ResponseResult responseResult = new ResponseResult(false, result.getMessage());
            return ResponseEntity.ok().body(responseResult);
        }

        ResponseResult responseResult = new ResponseResult(true);
        return ResponseEntity.ok().body(responseResult);
    }



}
