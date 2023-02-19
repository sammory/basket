package com.zerobase.zerolms.product.repository;

import com.zerobase.zerolms.basket.entity.Basket;
import com.zerobase.zerolms.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface BasketRepository extends JpaRepository<Basket, Long> {

    long countByProductIdAndEmailAndStatusIn(long productId, String email, Collection<String> statusList);

    @Transactional
    void deleteByProductId(long productId);
}
