package com.powerup.realestate.properties.infrastructure.endpoints.rest;

import com.powerup.realestate.properties.application.dto.request.SaveCategoryRequest;
import com.powerup.realestate.properties.application.dto.response.CategoryResponse;
import com.powerup.realestate.properties.application.dto.response.SaveCategoryResponse;
import com.powerup.realestate.properties.application.services.CategoryService;
import com.powerup.realestate.properties.domain.utils.page.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/")
    @Operation(
            summary = "Endpoint protegido",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<SaveCategoryResponse> save(@RequestBody SaveCategoryRequest saveCategoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(saveCategoryRequest));
    }

    @GetMapping("/")
    @Operation(
            summary = "Endpoint protegido",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<PageResult<CategoryResponse>> getAllCategories(@RequestParam Integer page,
                                                                         @RequestParam Integer size,
                                                                         @RequestParam boolean orderAsc) {
        return ResponseEntity.ok(categoryService.getCategories(page, size, orderAsc));
    }
}
