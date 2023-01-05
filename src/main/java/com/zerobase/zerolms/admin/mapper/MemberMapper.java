package com.zerobase.zerolms.admin.mapper;


import com.zerobase.zerolms.admin.dto.MemberDto;
import com.zerobase.zerolms.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    long selectListCount(MemberParam parameter);
    // 리스트형태로 리턴
    List<MemberDto> selectList(MemberParam parameter);
}
