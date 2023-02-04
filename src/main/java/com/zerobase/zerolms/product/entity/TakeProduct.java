package com.zerobase.zerolms.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class TakeProduct implements TakeProductCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    long productId;
    String email;

    long payPrice; // 결재금액
    String status; // 상태 (수강신청, 결재완료, 수강취소)

    LocalDateTime regDt; // 신청일
}
