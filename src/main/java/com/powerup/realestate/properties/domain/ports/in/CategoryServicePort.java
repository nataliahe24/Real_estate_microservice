package com.powerup.realestate.properties.domain.ports.in;

import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.utils.page.PageResult;

import java.util.Optional;


public interface CategoryServicePort {
    void save(CategoryModel categoryModel);
    Optional<CategoryModel> findByCategoryId(Long categoryId);
    PageResult<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc);
}
