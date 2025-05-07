package com.powerup.realestate.properties.infrastructure.mappers;

import com.powerup.realestate.properties.application.dto.response.CategoryNamesResponse;
import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.infrastructure.entities.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    CategoryEntity modelToEntity(CategoryModel categoryModel);
    CategoryModel entityToModel(CategoryEntity categoryEntity);
    List<CategoryModel> entityListToModelList(List<CategoryEntity> categories);
    List<CategoryNamesResponse> findAllCategoryNames(List<CategoryEntity> categoryEntities);
}
