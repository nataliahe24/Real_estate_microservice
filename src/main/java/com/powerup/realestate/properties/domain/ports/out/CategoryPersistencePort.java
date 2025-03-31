package com.powerup.realestate.properties.domain.ports.out;

import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.utils.page.PageResult;


public interface CategoryPersistencePort {
    void save(CategoryModel categoryModel);
    CategoryModel getCategoryByName(String categoryName);
    PageResult<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc);
}
