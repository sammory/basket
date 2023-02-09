package com.zerobase.zerolms.product.service;

import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.entity.Product;
import com.zerobase.zerolms.product.entity.TakeProduct;
import com.zerobase.zerolms.product.mapper.ProductMapper;
import com.zerobase.zerolms.product.model.ProductInput;
import com.zerobase.zerolms.product.model.ProductParam;
import com.zerobase.zerolms.product.model.ServiceResult;
import com.zerobase.zerolms.product.model.TakeProductInput;
import com.zerobase.zerolms.product.repository.ProductRepository;
import com.zerobase.zerolms.product.repository.TakeProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final TakeProductRepository takeProductRepository;
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
                .summary(parameter.getSummary())
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
        product.setSummary(parameter.getSummary());
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

    @Override
    public List<ProductDto> frontList(ProductParam parameter) {

        if (parameter.getCategoryId() < 1) {
            List<Product> productList = productRepository.findAll();
            return ProductDto.of(productList);
        }

        Optional<List<Product>> optionalProducts = productRepository.findByCategoryId(parameter.getCategoryId());
        if (optionalProducts.isPresent()) {
            return ProductDto.of(optionalProducts.get());
        }

        return null;
    }

    @Override
    public ProductDto frontDetail(long id) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return ProductDto.of(optionalProduct.get());
        }
        return null;
    }

    @Override
    public ServiceResult req(TakeProductInput parameter) {

        ServiceResult result = new ServiceResult();

        Optional<Product> optionalProduct = productRepository.findById(parameter.getProductId());
        if (!optionalProduct.isPresent()) {
            result.setResult(false);
            result.setMessage("구매요청 정보가 존재하지 않습니다.");
            return result;
        }

        Product product = optionalProduct.get();

        // 이미 담겨져 있는 상품이 있는지 확인
        String[] statusList = {TakeProduct.STATUS_REQ, TakeProduct.STATUS_COMPLETE};
        long count = takeProductRepository.countByProductIdAndEmailAndStatusIn(product.getId(),
                parameter.getEmail(), Arrays.asList(statusList));
        if (count > 0) {
            result.setResult(false);
            result.setMessage("이미 구매요청한 상품이 존재합니다.");
            return result;
        }

        TakeProduct takeProduct = TakeProduct.builder()
                .productId(product.getId())
                .email(parameter.getEmail())
                .payPrice(product.getSalePrice())
                .regDt(LocalDateTime.now())
                .status(TakeProduct.STATUS_REQ)
                .build();
        takeProductRepository.save(takeProduct);

        result.setResult(true);
        result.setMessage("");
        return result;
    }

    @Override
    public List<ProductDto> listAll() {

        List<Product> productList = productRepository.findAll();

        return ProductDto.of(productList);
    }

}
