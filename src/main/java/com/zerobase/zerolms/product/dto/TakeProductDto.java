package com.zerobase.zerolms.product.dto;

import com.zerobase.zerolms.product.entity.TakeProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TakeProductDto {

    Long id;
    long productId;
    String email;

    long payPrice; // 결재금액
    String status; // 상태 (수강신청, 결재완료, 수강취소)

    LocalDateTime regDt; // 신청일

    // JOIN
    String userName;
    String phone;
    String subject;

    // 추가컬럼
    long totalCount;
    long seq;

    public static TakeProductDto of(TakeProduct takeProduct) {

            return TakeProductDto.builder()
                    .id(takeProduct.getId())
                    .productId(takeProduct.getProductId())
                    .email(takeProduct.getEmail())
                    .payPrice(takeProduct.getPayPrice())
                    .status(takeProduct.getStatus())
                    .regDt(takeProduct.getRegDt())
                    .build();

    }

    // 구매요청 등록일 Format 처리
    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(formatter) : "";
    }
}
