package com.main.rest.repository;

import com.main.rest.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RepositoryCategory extends CrudRepository<Category, Long> {
    @Override
    Optional<Category> findById(Long id);
    Category findByName(String name);
    Iterable<Category> findAllByName(String name);
}
