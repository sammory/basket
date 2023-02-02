package com.zerobase.zerolms.admin.repository;

import com.zerobase.zerolms.admin.dto.CategoryDto;
import com.zerobase.zerolms.admin.model.CategoryInput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<CategoryDto> list();
    /**
     * 카테고리 신규 추가
     */
    boolean add(String categoryName);
    /**
     * 카테고리 수정
     */
    boolean update(CategoryInput parameter);
    /**
     * 카테고리 삭제
     */
    boolean del(long id);

    /**
     * 프론트 카테고리 정보
     */
    List<CategoryDto> frontList(CategoryDto build);
}
