package com.zerobase.zerolms.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String imagePath;
    String subject;

    @Lob
    String contents;
    long price;
    long samePrice;
    LocalDateTime saleEndDt;

    LocalDateTime regDt;  // 등록일
    LocalDateTime udtDt;  // 수정일
}
