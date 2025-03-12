package com.powerup.realestate.category.domain.model;

import com.powerup.realestate.category.domain.exceptions.DescriptionMaxSizeExceededException;
import com.powerup.realestate.category.domain.exceptions.NameMaxSizeExceededException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void shouldCreateCategoryWhenNameExceeds50Characters() {
        String validName= "n".repeat(51);
        String validDescription = "description";

        assertThrows(NameMaxSizeExceededException.class,() ->  new CategoryModel(1L,validName,validDescription));
    }
    @Test
    void shouldThrowExceptionWhenDescriptionExceeds90Characters() {
        String validName = "name";
        String longDescription = "d".repeat(91);

        assertThrows(DescriptionMaxSizeExceededException.class,
                () -> new CategoryModel(1L, validName, longDescription)
        );
    }
    @Test
    void shoulsetNamewithinLimit() {
        CategoryModel category = new CategoryModel(1L,"ValidName","Valid Description");
        String validName= "n".repeat(50);

        assertDoesNotThrow(() -> category.setName(validName));
        assertEquals(validName, category.getName());
    }

    @Test
    void shouldNotCallGetNameWhenNameExceedsLimit() {
        // Creamos un spy de CategoryModel para monitorear llamadas a sus métodos.
        CategoryModel categorySpy = spy(new CategoryModel(1L, "ValidName", "ValidDescription"));
        String invalidName = "n".repeat(51);

        assertThrows(NameMaxSizeExceededException.class, () -> categorySpy.setName(invalidName));

        verify(categorySpy, never()).getName();
    }
    @Test
    void shoulsetDescriptionwithinLimit() {
        CategoryModel category = new CategoryModel(1L,"ValidName","ValidDescription");
        String validDescrption= "d".repeat(90);

        assertDoesNotThrow(() -> category.setDescription(validDescrption));
        assertEquals(validDescrption, category.getDescription());
    }
    @Test
    void shoulNotsetDescriptionExceedsLimit() {
        CategoryModel categorySpy =spy(new CategoryModel(1L, "ValidName","ValidDesciption"));
        String invalidDescription="d".repeat(91);

        assertThrows(DescriptionMaxSizeExceededException.class,()-> categorySpy.setDescription(invalidDescription));
        verify(categorySpy, never()).getDescription();

    }



}