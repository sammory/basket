package com.zerobase.zerolms.admin.repository;

import com.zerobase.zerolms.admin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> { // <>에는 테이블명, 키

//    Optional<List<Category>> findAllOrderBySortValueDesc();


}
