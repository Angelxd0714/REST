package com.main.rest.services;

import com.main.rest.entities.Category;
import com.main.rest.interfaces.CrudCategory;
import com.main.rest.repository.RepositoryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesCategory implements CrudCategory {
    @Autowired
    private RepositoryCategory repositoryCategory;

    @Override
    public void createCategory(Category category) {
        repositoryCategory.save(category);
    }

    @Override
    public Category readCategory(Long id) {
        return repositoryCategory.findById(id).orElse(null);
    }

    @Override
    public void updateCategory(Long id, Category category) {
        Category existingCategory = repositoryCategory.findById(id).orElse(null);
        if (existingCategory != null) {
            existingCategory.setName(category.getName());
            repositoryCategory.save(existingCategory);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        repositoryCategory.deleteById(id);
    }

    @Override
    public Iterable<Category> getAllCategories() {
        return repositoryCategory.findAll();
    }
}

