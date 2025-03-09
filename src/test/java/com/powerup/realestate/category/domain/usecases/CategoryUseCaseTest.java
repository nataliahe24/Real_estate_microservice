package com.powerup.realestate.category.domain.usecases;

import com.powerup.realestate.category.domain.exceptions.CategoryAlreadyExistsException;
import com.powerup.realestate.category.domain.model.CategoryModel;
import com.powerup.realestate.category.domain.ports.out.CategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {
     @Mock
    private CategoryPersistencePort categoryPersistencePort;

     @InjectMocks
    private CategoryUseCase categoryUseCase;

     @BeforeEach
    void setUp() {
         MockitoAnnotations.openMocks(this);
     }

     @Test
    void save_ShouldSaveCategory_WhenCategoryDoesNotExist() {
         CategoryModel categoryModel = new CategoryModel(
                 (long) 1,
                 "Test Category",
                 "Test Description"
         );

         when(categoryPersistencePort.getCategoryByName("Test Category")).thenReturn(null);

         categoryUseCase.save(categoryModel);

         verify(categoryPersistencePort).getCategoryByName("Test Category");
         verify(categoryPersistencePort).save(categoryModel);
     }

     @Test
    void save_ShouldThrowException_WhenCategoryAlreadyExists() {
         CategoryModel categoryModel = new CategoryModel(
                 (long) 1,
                 "Test Category",
                 "Test Description"
         );

         when(categoryPersistencePort.getCategoryByName("Test Category")).thenReturn(categoryModel);

         assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.save(categoryModel));

         verify(categoryPersistencePort).getCategoryByName("Test Category");
         verify(categoryPersistencePort, never()).save(categoryModel);

     }
}