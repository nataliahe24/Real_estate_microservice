package com.powerup.realestate.category.application.services;

import com.powerup.realestate.category.application.dto.request.SaveCategoryRequest;
import com.powerup.realestate.category.application.dto.response.CategoryResponse;
import com.powerup.realestate.category.application.dto.response.SaveCategoryResponse;
import com.powerup.realestate.category.domain.utils.page.PageResult;

public interface CategoryService {
    SaveCategoryResponse save(SaveCategoryRequest request);
    PageResult<CategoryResponse> getCategories(Integer page, Integer size, boolean orderAsc);
}
