package com.powerup.realestate.category.application.mappers;

import com.powerup.realestate.category.application.dto.request.SaveCategoryRequest;
import com.powerup.realestate.category.application.dto.response.CategoryResponse;
import com.powerup.realestate.category.domain.model.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryDtoMapper {
    CategoryModel requestToModel(SaveCategoryRequest saveCategoryRequest);
    CategoryResponse modelToResponse(CategoryModel categoryModel);
    List<CategoryResponse> modelListToResponseList(List<CategoryModel> categories);
}
