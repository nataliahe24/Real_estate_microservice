package com.powerup.realestate.category.domain.usecases;

import com.powerup.realestate.category.domain.exceptions.CategoryAlreadyExistsException;
import com.powerup.realestate.category.domain.model.CategoryModel;
import com.powerup.realestate.category.domain.ports.out.CategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

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

     @Test
     void getCategories_ShouldReturnSortedList_WhenOrderIsAsc() {
         CategoryModel categoryModel1 = new CategoryModel(
                 (long) 1,
                 "Name1",
                 "Desc1"
         );
         CategoryModel categoryModel2 = new CategoryModel(
                 (long) 2,
                 "Name2",
                 "Desc2"
         );

         Integer page = 1;
         Integer size = 2;
         boolean orderAsc = true;

         List<CategoryModel> categoryList = List.of(categoryModel1, categoryModel2);

         when(categoryPersistencePort.getCategories(page, size, orderAsc)).thenReturn(categoryList);

         List<CategoryModel> paginatedCategoryList = categoryUseCase.getCategories(page, size, orderAsc);

         verify(categoryPersistencePort).getCategories(page, size, orderAsc);
         assertEquals(categoryList, paginatedCategoryList);
     }
}