package com.global.eshophexa.services;

import com.global.eshophexa.models.Category;
import com.global.eshophexa.ports.incoming.CategoryUseCases;
import com.global.eshophexa.ports.outgoing.CategoryRepositoryPort;

import java.util.List;

public class CategoryService implements CategoryUseCases {

    private final CategoryRepositoryPort categoryRepositoryPort;

    public CategoryService(CategoryRepositoryPort categoryRepositoryPort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
    }

    @Override
    public Category create(Category category) {
        return categoryRepositoryPort.createCategory(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepositoryPort.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepositoryPort.findById(id);
    }

    @Override
    public void delete(Long id) {
        categoryRepositoryPort.delete(id);
    }
}
