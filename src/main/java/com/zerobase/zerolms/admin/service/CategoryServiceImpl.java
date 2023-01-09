package com.zerobase.zerolms.admin.service;

import com.zerobase.zerolms.admin.categoryDto.CategoryDto;
import com.zerobase.zerolms.admin.entity.Category;
import com.zerobase.zerolms.admin.model.CategoryInput;
import com.zerobase.zerolms.admin.repository.CategoryRepository;
import com.zerobase.zerolms.admin.repository.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private Sort getSortBySortValueDesc() {
        return Sort.by(Sort.Direction.DESC, "sortValue");
    }

    @Override
    public List<CategoryDto> list() {

//        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll(getSortBySortValueDesc());
        return CategoryDto.of(categories);

//        if (CollectionUtils.isEmpty(categories)) {
//
//            categories.forEach(e -> {
//                CategoryDto category = new CategoryDto();
//                category.setId(e.getId());
//                category.setCategoryName(e.getCategoryName());
//            });
//        }
    }

    @Override
    public boolean add(String categoryName) {

        // 카테고리명 중복인지 체크

        Category category = Category.builder()
                .categoryName(categoryName)
                .usingYn(true)
                .sortValue(0)
                .build();
        categoryRepository.save(category);

        return true;
    }

    @Override
    public boolean update(CategoryInput parameter) {

        Optional<Category> optionalCategory = categoryRepository.findById(parameter.getId());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setCategoryName(parameter.getCategoryName());
            category.setSortValue(parameter.getSortValue());
            category.setUsingYn(parameter.isUsingYn());
            categoryRepository.save(category);
        }

        return true;
    }

    @Override
    public boolean del(long id) {
        categoryRepository.deleteById(id);
        return true;
    }
}
