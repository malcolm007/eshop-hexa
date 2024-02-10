package com.global.eshophexa.infrastructure.databse.mapper;

import com.global.eshophexa.infrastructure.databse.entities.CategoryEntity;
import com.global.eshophexa.models.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryPersistenceMapper {

    CategoryEntity toCategoryEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);

    List<CategoryEntity> toCategoryEntities(List<Category> categories);
    List<Category> toCategories(List<CategoryEntity> categoryEntities);
}
