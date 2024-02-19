package com.global.eshophexa.infrastructure.adapters;

import com.global.eshophexa.infrastructure.databse.entities.CategoryEntity;
import com.global.eshophexa.infrastructure.databse.mapper.CategoryPersistenceMapper;
import com.global.eshophexa.infrastructure.databse.mapper.CategoryPersistenceMapperImpl;
import com.global.eshophexa.infrastructure.databse.repositories.CategoryRepository;
import com.global.eshophexa.infrastructure.exceptions.ResourceNotFoundException;
import com.global.eshophexa.models.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CategoryRepositoryAdapterTest {

    @Mock
    private CategoryRepository categoryRepository;
    private CategoryRepositoryAdapter categoryAdapter;

    CategoryPersistenceMapper categoryPersistenceMapper;

    @BeforeEach
    void setUp() {
        categoryPersistenceMapper = new CategoryPersistenceMapperImpl();
        categoryAdapter = new CategoryRepositoryAdapter(categoryRepository, categoryPersistenceMapper);
    }

    @Test
    void findAllShouldReturnEmptyList() {
        Mockito.when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        List<Category> result = categoryAdapter.findAll();

        Assertions.assertTrue(result.isEmpty());
    }


    @Test
    void findAllShouldReturnAllCategories() {
        Mockito.when(categoryRepository.findAll()).thenReturn(getCategoryEntities());

        List<Category> result = categoryAdapter.findAll();

        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void findByIdShouldReturnException() {
        Mockito.when(categoryRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> categoryAdapter.findById(2L));
    }

    @Test
    void findBuIdShouldReturnOK() {
        Mockito.when(categoryRepository.findById(ArgumentMatchers.anyLong())).thenReturn(
                Optional.of(getCategoryEntities().get(0)));

        Category category = categoryAdapter.findById(1L);
        Assertions.assertEquals(1L, category.getId());
        Assertions.assertEquals("cat-1", category.getName());
    }


    @Test
    void create() {
        Mockito.when(categoryRepository.save(ArgumentMatchers.any())).thenReturn(getCategoryEntities().get(0));

        Category result = categoryAdapter.createCategory(getCategories().get(0));

        Assertions.assertEquals(getCategories().get(0).getId(), result.getId());
        Assertions.assertEquals(getCategories().get(0).getName(), result.getName());
    }

    @Test
    void deleteShouldThrowException() {
        Mockito.when(categoryRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(false);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> categoryAdapter.delete(1L));
    }

    @Test
    void deleteShouldWorkCorrectly() {
        Mockito.when(categoryRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(true);
        Mockito.doNothing().when(categoryRepository).deleteById(ArgumentMatchers.anyLong());

        categoryAdapter.delete(1L);

        Mockito.verify(categoryRepository, Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
    }



    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "cat-1"));
        categories.add(new Category(2L, "cat-2"));
        return categories;
    }

    private List<CategoryEntity> getCategoryEntities() {
        return categoryPersistenceMapper.toCategoryEntities(getCategories());
    }
}
