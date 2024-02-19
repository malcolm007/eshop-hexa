package com.global.eshophexa.infrastructure.adapters;

import com.global.eshophexa.infrastructure.aop.annotations.Audit;
import com.global.eshophexa.infrastructure.databse.mapper.CategoryPersistenceMapper;
import com.global.eshophexa.infrastructure.exceptions.ResourceNotFoundException;
import com.global.eshophexa.ports.outgoing.CategoryRepositoryPort;
import com.global.eshophexa.infrastructure.databse.repositories.CategoryRepository;
import com.global.eshophexa.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CategoryRepositoryAdapter implements CategoryRepositoryPort {

    private final CategoryRepository categoryRepository;
    private final CategoryPersistenceMapper categoryPersistenceMapper;

    @Override
    @Audit
    public Category createCategory(Category category) {
        return categoryPersistenceMapper.toCategory(categoryRepository
                .save(categoryPersistenceMapper.toCategoryEntity(category)));
    }

    @Override
    @Audit
    public List<Category> findAll() {
        return categoryPersistenceMapper.toCategories(categoryRepository.findAll());
    }

    @Override
    @Audit
    public Category findById(Long id) {
        return categoryPersistenceMapper.toCategory(categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(400, "error.resource.notFound",
                        "CAT_001", new Object[]{id})));
    }

    @Override
    @Audit
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException(400, "error.resource.notFound",
                    "CAT_001", new Object[]{id});
        }
        categoryRepository.deleteById(id);
    }
}
