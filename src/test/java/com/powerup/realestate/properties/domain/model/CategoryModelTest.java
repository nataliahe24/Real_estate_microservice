package com.powerup.realestate.properties.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryModelTest {

    @Test
    void shouldCreateCategoryModelSuccessfully() {
        Long id = 1L;
        String name = "Name";
        String description = "description";

        CategoryModel category = new CategoryModel(id, name, description);

        assertEquals(id, category.getId());
        assertEquals(name, category.getName());
        assertEquals(description, category.getDescription());
    }

    @Test
    void shouldCreateCategoryWhenNameHas50CharactersOrLess() {
        String validName= "n".repeat(50);
        String validDescription = "description";

        assertDoesNotThrow( () -> new CategoryModel(1L, validName, validDescription));
    }
    @Test
    void shouldCreateCategoryWhenDescriptionHas50CharactersOrLess() {
        String validName= "name";
        String validDescription = "d".repeat(90);

        assertDoesNotThrow( () -> new CategoryModel(1L, validName, validDescription));
    }

    @Test
    void shouldSetNameWithinLimit() {
        CategoryModel category = new CategoryModel(1L,"ValidName","Valid Description");
        String validName= "n".repeat(50);

        assertDoesNotThrow(() -> category.setName(validName));
        assertEquals(validName, category.getName());
    }

    @Test
    void shouldSetDescriptionWithinLimit() {
        CategoryModel category = new CategoryModel(1L,"ValidName","ValidDescription");
        String validDescription = "d".repeat(90);

        assertDoesNotThrow(() -> category.setDescription(validDescription));
        assertEquals(validDescription, category.getDescription());
    }

}