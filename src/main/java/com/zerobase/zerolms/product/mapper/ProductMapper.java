package com.zerobase.zerolms.product.mapper;


import com.zerobase.zerolms.admin.dto.MemberDto;
import com.zerobase.zerolms.product.dto.ProductDto;
import com.zerobase.zerolms.product.model.ProductParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    long selectListCount(ProductParam parameter);
    // 리스트형태로 리턴
    List<ProductDto> selectList(ProductParam parameter);
}
