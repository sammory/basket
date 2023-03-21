package com.zerobase.zerolms.order.service;

import com.zerobase.zerolms.member.model.MemberInput;
import com.zerobase.zerolms.product.model.ServiceResult;

public interface OrderService {
    /**
     * 보유 포인트로 상품 바로구매
     */
    ServiceResult paymentCash(MemberInput parameter,long totalPay);
}
