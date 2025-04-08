package com.powerup.realestate.properties.domain.ports.in;

import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import com.powerup.realestate.properties.infrastructure.entities.CategoryEntity;


public interface CategoryServicePort {
    void save(CategoryModel categoryModel);
    CategoryModel findByCategoryId(Long categoryId);
    PageResult<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc);
}
