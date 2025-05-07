package com.powerup.realestate.properties.infrastructure.adapters.persistence;

import com.powerup.realestate.properties.application.dto.response.CategoryNamesResponse;
import com.powerup.realestate.properties.domain.model.CategoryModel;
import com.powerup.realestate.properties.domain.ports.out.CategoryPersistencePort;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import com.powerup.realestate.properties.infrastructure.entities.CategoryEntity;
import com.powerup.realestate.properties.infrastructure.mappers.CategoryEntityMapper;
import com.powerup.realestate.properties.infrastructure.repositories.mysql.CategoryRepository;
import com.powerup.realestate.commons.configurations.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryPersistencePort {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void save(CategoryModel categoryModel) {
        categoryRepository.save(categoryEntityMapper.modelToEntity(categoryModel));
    }

    @Override
    public CategoryModel getCategoryByName(String categoryName) {
        return categoryEntityMapper.entityToModel(categoryRepository.findByName(categoryName).orElse(null));
    }

    @Override
    public Optional<CategoryModel> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(categoryEntityMapper::entityToModel);
    }

    @Override
    public PageResult<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc) {
        Pageable pagination;
        if (orderAsc) pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).ascending());
        else pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).descending());

        Page<CategoryEntity> pageCategories = categoryRepository.findAll(pagination);
        List<CategoryModel> pageModel = categoryEntityMapper.entityListToModelList(pageCategories.getContent());
        return new PageResult<>(pageModel, page, size, (int)  pageCategories.getTotalElements());

    }

    @Override
    public List<CategoryNamesResponse> getCategoriesByNames(boolean orderAsc) {
        Sort sort = orderAsc
                ? Sort.by(Constants.PAGEABLE_FIELD_NAME).ascending()
                : Sort.by(Constants.PAGEABLE_FIELD_NAME).descending();

        List<CategoryEntity> categoryEntities = categoryRepository.findAll(sort);
        return categoryEntityMapper.findAllCategoryNames(categoryEntities);
    }
}
