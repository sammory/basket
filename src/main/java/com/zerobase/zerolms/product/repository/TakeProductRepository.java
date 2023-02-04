package com.zerobase.zerolms.product.repository;

import com.zerobase.zerolms.product.entity.Product;
import com.zerobase.zerolms.product.entity.TakeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface TakeProductRepository extends JpaRepository<TakeProduct, Long> {

    long countByProductIdAndEmailAndStatusIn(long productId, String email, Collection<String> statusList);
}
