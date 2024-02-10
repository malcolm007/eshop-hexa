package com.global.eshophexa.controllers;

import com.global.eshophexa.dtos.category.CategoryRequest;
import com.global.eshophexa.dtos.category.CategoryResponse;
import com.global.eshophexa.mappers.CategoryMapper;
import com.global.eshophexa.ports.incoming.CategoryUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryUseCases categoryUseCases;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public List<CategoryResponse> findAll() {
        return categoryMapper.categoriesToCategoriesResponse(categoryUseCases.findAll());
    }

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable("id") Long id) {
        return categoryMapper.categoryToCategoryResponse(categoryUseCases.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        categoryUseCases.delete(id);
    }

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest categoryRequest) {
        return categoryMapper.categoryToCategoryResponse(categoryUseCases.create(
                categoryMapper.categoryRequestToCategory(categoryRequest)));
    }
}
