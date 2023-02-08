package com.zerobase.zerolms.product.service;

import com.zerobase.zerolms.product.dto.TakeProductDto;
import com.zerobase.zerolms.product.entity.TakeProduct;
import com.zerobase.zerolms.product.entity.TakeProductCode;
import com.zerobase.zerolms.product.mapper.TakeProductMapper;
import com.zerobase.zerolms.product.model.ServiceResult;
import com.zerobase.zerolms.product.model.TakeProductParam;
import com.zerobase.zerolms.product.repository.TakeProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TakeProductServiceImpl implements TakeProductService {

    private final TakeProductRepository takeProductRepository;
    private final TakeProductMapper takeProductMapper;


    @Override
    public List<TakeProductDto> list(TakeProductParam parameter) {

        long totalCount = takeProductMapper.selectListCount(parameter);

        List<TakeProductDto> list = takeProductMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (TakeProductDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;
    }

    @Override
    public TakeProductDto detail(long id) {

        Optional<TakeProduct> optionalTakeProduct = takeProductRepository.findById(id);
        if (optionalTakeProduct.isPresent()) {
            return TakeProductDto.of(optionalTakeProduct.get());
        }

        return null;
    }

    @Override
    public ServiceResult updateStatus(long id, String status) {

        Optional<TakeProduct> optionalTakeProduct = takeProductRepository.findById(id);
        if (!optionalTakeProduct.isPresent()) {
            return new ServiceResult(false,"구매 정보가 존재하지 않습니다.");
        }

        TakeProduct takeProduct = optionalTakeProduct.get();

        takeProduct.setStatus(status);
        takeProductRepository.save(takeProduct);

        return new ServiceResult(true);
    }

    @Override
    public List<TakeProductDto> myProduct(String email) {

        TakeProductParam parameter = new TakeProductParam();
        parameter.setEmail(email);
        List<TakeProductDto> list = takeProductMapper.selectListMyProduct(parameter);

        return list;
    }

    @Override
    public ServiceResult cancel(long id) {

        Optional<TakeProduct> optionalTakeProduct = takeProductRepository.findById(id);
        if (!optionalTakeProduct.isPresent()) {
            return new ServiceResult(false,"상품 정보가 존재하지 않습니다.");
        }
        TakeProduct takeProduct = optionalTakeProduct.get();

        takeProduct.setStatus(TakeProductCode.STATUS_CANCEL);
        takeProductRepository.save(takeProduct);

        return new ServiceResult();
    }
}
