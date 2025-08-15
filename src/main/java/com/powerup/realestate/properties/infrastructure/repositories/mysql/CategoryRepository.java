package com.powerup.realestate.properties.infrastructure.repositories.mysql;

import com.powerup.realestate.properties.application.dto.response.CategoryNamesResponse;
import com.powerup.realestate.properties.infrastructure.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
    Optional<CategoryEntity> findById(Long categoryId);
    Page<CategoryEntity> findAll(Pageable pageable);
    @Query("SELECT c.name FROM CategoryEntity c")
    List<CategoryNamesResponse> findAllCategoryNames(List list);

}
