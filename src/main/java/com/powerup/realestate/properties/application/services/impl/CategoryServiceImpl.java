package com.powerup.realestate.properties.application.services.impl;

import com.powerup.realestate.properties.application.dto.request.SaveCategoryRequest;
import com.powerup.realestate.properties.application.dto.response.CategoryNamesResponse;
import com.powerup.realestate.properties.application.dto.response.CategoryResponse;
import com.powerup.realestate.properties.application.dto.response.SaveCategoryResponse;
import com.powerup.realestate.properties.application.mappers.CategoryDtoMapper;
import com.powerup.realestate.properties.application.services.CategoryService;
import com.powerup.realestate.properties.domain.ports.in.CategoryServicePort;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import com.powerup.realestate.commons.configurations.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryServicePort categoryServicePort;
    private final CategoryDtoMapper categoryDtoMapper;

    @Override
    public SaveCategoryResponse save(SaveCategoryRequest request) {
        categoryServicePort.save(categoryDtoMapper.requestToModel(request));
        return new SaveCategoryResponse(Constants.SAVE_CATEGORY_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public PageResult<CategoryResponse> getCategories(Integer page, Integer size, boolean orderAsc) {
        return categoryDtoMapper.modelListToResponseList(categoryServicePort.getCategories(page, size, orderAsc));
    }

    @Override
    public List<CategoryNamesResponse> getCategoriesByNames(boolean orderAsc) {
        return categoryDtoMapper.modelListToResponseList(
                categoryServicePort.getCategoriesByNames(orderAsc)
        );
    }
}
