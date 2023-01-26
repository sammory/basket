package com.zerobase.zerolms.product.service;

import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.entity.Product;
import com.zerobase.zerolms.product.mapper.ProductMapper;
import com.zerobase.zerolms.product.model.ProductInput;
import com.zerobase.zerolms.product.model.ProductParam;
import com.zerobase.zerolms.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public boolean add(ProductInput parameter) {

        Product product = Product.builder()
                .subject(parameter.getSubject())
                .regDt(LocalDateTime.now())
                .build();
        productRepository.save(product);

        return true;
    }

    @Override
    public List<ProductDto> list(ProductParam parameter) {

        long totalCount = productMapper.selectListCount(parameter);

        List<ProductDto> list = productMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (ProductDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;
    }
}
