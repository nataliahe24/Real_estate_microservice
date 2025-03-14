package com.powerup.realestate.category.domain.usecases;

import com.powerup.realestate.category.domain.exceptions.CategoryAlreadyExistsException;
import com.powerup.realestate.category.domain.model.CategoryModel;
import com.powerup.realestate.category.domain.ports.in.CategoryServicePort;
import com.powerup.realestate.category.domain.ports.out.CategoryPersistencePort;
import com.powerup.realestate.category.domain.utils.page.PageResult;


public class CategoryUseCase implements CategoryServicePort {
    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void save(CategoryModel categoryModel) {
        CategoryModel category = categoryPersistencePort.getCategoryByName(categoryModel.getName());
        if (category != null) {
            throw new CategoryAlreadyExistsException();
        }
        categoryPersistencePort.save(categoryModel);
    }

    @Override
    public PageResult<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc) {
        return categoryPersistencePort.getCategories(page, size, orderAsc);
    }
}
