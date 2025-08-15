package com.powerup.realestate.properties.domain.ports.out;


import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.utils.page.PageResult;

import java.util.Optional;


public interface CategoryPersistencePort {
    void save(CategoryModel categoryModel);
    CategoryModel getCategoryByName(String categoryName);
    Optional<CategoryModel> getCategoryById(Long categoryId);
    PageResult<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc);

}
