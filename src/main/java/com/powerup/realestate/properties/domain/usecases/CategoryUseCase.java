package com.powerup.realestate.properties.domain.usecases;

import com.powerup.realestate.properties.application.dto.response.CategoryNamesResponse;
import com.powerup.realestate.properties.domain.exceptions.CategoryAlreadyExistsException;
import com.powerup.realestate.properties.domain.exceptions.DescriptionMaxSizeExceededException;
import com.powerup.realestate.properties.domain.exceptions.NameMaxSizeExceededException;
import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.ports.in.CategoryServicePort;
import com.powerup.realestate.properties.domain.ports.out.CategoryPersistencePort;
import com.powerup.realestate.properties.domain.utils.page.PageResult;

import java.util.List;
import java.util.Optional;

import static com.powerup.realestate.properties.domain.utils.constants.CategoryDomainConstants.DESCRIPTION_MAX_CHARACTERS;
import static com.powerup.realestate.properties.domain.utils.constants.CategoryDomainConstants.NAME_MAX_CHARACTERS;


public class CategoryUseCase implements CategoryServicePort {
    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void save(CategoryModel categoryModel) {
        CategoryModel category = categoryPersistencePort.getCategoryByName(categoryModel.getName());
        String name = categoryModel.getName();
        String description = categoryModel.getDescription();

        if (name.length() > NAME_MAX_CHARACTERS) throw new NameMaxSizeExceededException();
        if (description.length() > DESCRIPTION_MAX_CHARACTERS) throw new DescriptionMaxSizeExceededException();

        if (category != null) {
            throw new CategoryAlreadyExistsException();
        }
        categoryPersistencePort.save(categoryModel);
    }

    @Override
    public Optional<CategoryModel> findByCategoryId(Long categoryId) {
        if (categoryId == null){
            return Optional.empty();
        }
        return categoryPersistencePort.getCategoryById(categoryId);
    }

    @Override
    public PageResult<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc) {
        return categoryPersistencePort.getCategories(page, size, orderAsc);
    }

    @Override
    public List<CategoryNamesResponse> getCategoriesByNames(boolean orderAsc) {
        return categoryPersistencePort.getCategoriesByNames(orderAsc);
    }
}
