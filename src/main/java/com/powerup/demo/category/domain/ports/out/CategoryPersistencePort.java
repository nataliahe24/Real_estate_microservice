package com.example.demo.category.domain.ports.out;

import com.example.demo.category.domain.model.CategoryModel;

import java.util.List;

public interface CategoryPersistencePort {
    void save(CategoryModel categoryModel);
    CategoryModel getCategoryByName(String categoryName);
    List<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc);
}
