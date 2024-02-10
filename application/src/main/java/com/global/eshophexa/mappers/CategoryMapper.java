package com.global.eshophexa.mappers;

import com.global.eshophexa.dtos.category.CategoryRequest;
import com.global.eshophexa.dtos.category.CategoryResponse;
import com.global.eshophexa.models.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category categoryRequestToCategory(CategoryRequest categoryRequest);
    CategoryResponse categoryToCategoryResponse(Category category);

    List<CategoryResponse> categoriesToCategoriesResponse(List<Category> categories);
}
