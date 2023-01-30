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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private LocalDate getLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public boolean add(ProductInput parameter) {

        //2022-12-20
        //20221220

        LocalDate saleEnDt = getLocalDate(parameter.getSaleEnDtText());

        Product product = Product.builder()
                .categoryId(parameter.getCategoryId())
                .subject(parameter.getSubject())
                .keyword(parameter.getKeyword())
                .contents(parameter.getContents())
                .price(parameter.getPrice())
                .salePrice(parameter.getSalePrice())
                .saleEndDt(saleEnDt)
                .regDt(LocalDateTime.now())
                .build();
        productRepository.save(product);

        return true;
    }

    @Override
    public boolean set(ProductInput parameter) {

        LocalDate saleEnDt = getLocalDate(parameter.getSaleEnDtText());

        Optional<Product> optionalProduct = productRepository.findById(parameter.getId());
        if (!optionalProduct.isPresent()) {
            // 수정할 데이터가 없음
            return false;
        }

        Product product = optionalProduct.get();
        product.setCategoryId(parameter.getCategoryId());
        product.setSubject(parameter.getSubject());
        product.setKeyword(parameter.getKeyword());
        product.setContents(parameter.getContents());
        product.setPrice(parameter.getPrice());
        product.setSalePrice(parameter.getSalePrice());
        product.setSaleEndDt(saleEnDt);
        product.setUdtDt(LocalDateTime.now());

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

    @Override
    public ProductDto getById(long id) {

        return productRepository.findById(id).map(ProductDto::of).orElse(null);
    }

    @Override
    public boolean del(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for(String x : ids) {
                long id = 0l;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }
                if (id > 0) {
                    productRepository.deleteById(id);
                }
            }
        }

        return true;
    }

}
