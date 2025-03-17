package com.powerup.realestate.category.domain.ports.in;

import com.powerup.realestate.category.domain.model.CategoryModel;
import com.powerup.realestate.category.domain.utils.page.PageResult;


public interface CategoryServicePort {
    void save(CategoryModel categoryModel);
    PageResult<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc);
}
