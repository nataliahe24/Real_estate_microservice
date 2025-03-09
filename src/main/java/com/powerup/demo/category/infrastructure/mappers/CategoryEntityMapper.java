package com.example.demo.category.infrastructure.mappers;

import com.example.demo.category.domain.model.CategoryModel;
import com.example.demo.category.infrastructure.entities.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    CategoryEntity modelToEntity(CategoryModel categoryModel);
    CategoryModel entityToModel(CategoryEntity categoryEntity);
    List<CategoryModel> entityListToModelList(List<CategoryEntity> categories);
}
