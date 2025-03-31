package com.powerup.realestate.properties.application.services;

import com.powerup.realestate.properties.application.dto.request.SaveCategoryRequest;
import com.powerup.realestate.properties.application.dto.response.CategoryResponse;
import com.powerup.realestate.properties.application.dto.response.SaveCategoryResponse;
import com.powerup.realestate.properties.domain.utils.page.PageResult;

public interface CategoryService {
    SaveCategoryResponse save(SaveCategoryRequest request);
    PageResult<CategoryResponse> getCategories(Integer page, Integer size, boolean orderAsc);
}
