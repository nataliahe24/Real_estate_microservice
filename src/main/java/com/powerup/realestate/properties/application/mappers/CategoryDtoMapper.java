package com.powerup.realestate.properties.application.mappers;

import com.powerup.realestate.properties.application.dto.request.SaveCategoryRequest;
import com.powerup.realestate.properties.application.dto.response.CategoryNamesResponse;
import com.powerup.realestate.properties.application.dto.response.CategoryResponse;
import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryDtoMapper {
    CategoryModel requestToModel(SaveCategoryRequest saveCategoryRequest);

    List<CategoryNamesResponse> modelListToResponseList (List<CategoryNamesResponse> categories);

    default CategoryModel toCategoryModel(Long id) {
        return CategoryModel.builder().id(id).build();
    }
    default Long toCategoryId(CategoryModel categoryModel) {
        return categoryModel.getId();
    }

    PageResult<CategoryResponse> modelListToResponseList(PageResult<CategoryModel> categories);
}
