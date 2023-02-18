package com.zerobase.zerolms.basket.mapper;


import com.zerobase.zerolms.basket.dto.BasketDto;
import com.zerobase.zerolms.basket.model.BasketParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasketMapper {


    long selectListCount(BasketParam parameter);
    // 리스트형태로 리턴
    List<BasketDto> selectList(BasketParam parameter);

    List<BasketDto> selectListMyBasket(BasketParam parameter);


}
