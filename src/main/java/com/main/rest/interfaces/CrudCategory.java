package com.main.rest.interfaces;

import com.main.rest.entities.Category;

public interface CrudCategory {
    public void createCategory(Category category);
    public Category  readCategory(Long id);
    public void updateCategory(Long id, Category category);
    public void deleteCategory(Long id);
    public Iterable<Category> getAllCategories();
}
