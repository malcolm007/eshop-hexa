package com.global.eshophexa.ports.outgoing;

import com.global.eshophexa.models.Category;

import java.util.List;

public interface CategoryRepositoryPort {

    Category createCategory(Category category);
    List<Category> findAll();

    Category findById(Long id);

    void delete(Long id);
}
