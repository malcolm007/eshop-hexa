package com.global.eshophexa.ports.incoming;

import com.global.eshophexa.models.Category;

import java.util.List;

public interface CategoryUseCases {

    Category create(Category category);
    List<Category> findAll();

    Category findById(Long id);

    void delete(Long id);
}
