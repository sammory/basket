package com.zerobase.zerolms.product.mapper;


import com.zerobase.zerolms.product.dto.TakeProductDto;
import com.zerobase.zerolms.product.model.TakeProductParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeProductMapper {

    long selectListCount(TakeProductParam parameter);
    // 리스트형태로 리턴
    List<TakeProductDto> selectList(TakeProductParam parameter);
}
