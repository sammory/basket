package com.zerobase.zerolms.basket.service;

import com.zerobase.zerolms.basket.dto.BasketDto;
import com.zerobase.zerolms.basket.entity.Basket;
import com.zerobase.zerolms.basket.mapper.BasketMapper;
import com.zerobase.zerolms.basket.model.BasketInput;
import com.zerobase.zerolms.basket.model.BasketParam;
import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.entity.Product;
import com.zerobase.zerolms.product.model.ServiceResult;
import com.zerobase.zerolms.product.repository.BasketRepository;
import com.zerobase.zerolms.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BasketServiceImpl implements BasketService {

    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;


    @Override
    public ServiceResult add(BasketInput parameter) {

        ServiceResult result = new ServiceResult();

        Optional<Product> optionalProduct = productRepository.findById(parameter.getProductId());
        if (!optionalProduct.isPresent()) {
            result.setResult(false);

            result.setMessage("상품 정보가 존재하지 않습니다.");
            return result;
        }

        Product product = optionalProduct.get();

        // 이미 신청정보가 있는지 확인
        String[] statusList = {Basket.STATUS_REQ};
        long count = basketRepository.countByProductIdAndEmailAndStatusIn(product.getId(),
                parameter.getEmail(), Arrays.asList(statusList));
        if (count > 0) {
            result.setResult(false);
            result.setMessage("이미 동일한 상품이 존재합니다.");
            return result;
        }

        Basket basket = Basket.builder()
                .productId(product.getId())
                .email(parameter.getEmail())
                .payPrice(product.getSalePrice())
                .quantity(parameter.getQuantity())
                .regDt(LocalDateTime.now())
                .status(Basket.STATUS_REQ)
                .build();
        basketRepository.save(basket);

        result.setResult(true);
        result.setMessage("");
        return result;
    }

    // 장바구니 상품삭제 구현
    @Override
    public boolean del(long productId) {

        basketRepository.deleteByProductId(productId);

        return true;
    }


    @Override
    public List<ProductDto> listAll() {
        return null;
    }

    @Override
    public List<BasketDto> myBasket(String email) {

        BasketParam parameter = new BasketParam();
        parameter.setEmail(email);
        List<BasketDto> list = basketMapper.selectListMyBasket(parameter);

        return list;
    }

    @Override
    public boolean update(Long id, long quantity) {

        Optional<Basket> optionalBasket = basketRepository.findById(id);
        if (!optionalBasket.isPresent()) {
            throw new UsernameNotFoundException("장바구니 정보가 존재하지 않습니다.");
        }
        Basket basket = optionalBasket.get();

        basket.setQuantity(quantity);
        basketRepository.save(basket);

        return true;
    }

    @Override
    public long totalPay(List<BasketDto> list) {



        long totalPay = 0;
        for (BasketDto x : list) {
            totalPay += x.getPayPrice() * x.getQuantity();
        }

        return totalPay;
    }


}
