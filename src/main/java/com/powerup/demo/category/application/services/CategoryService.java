package com.example.demo.category.application.services;

import com.example.demo.category.application.dto.request.SaveCategoryRequest;
import com.example.demo.category.application.dto.response.CategoryResponse;
import com.example.demo.category.application.dto.response.SaveCategoryResponse;
import com.example.demo.category.domain.model.CategoryModel;

import java.util.List;

public interface CategoryService {
    SaveCategoryResponse save(SaveCategoryRequest request);
    List<CategoryResponse> getCategories(Integer page, Integer size, boolean orderAsc);
}
