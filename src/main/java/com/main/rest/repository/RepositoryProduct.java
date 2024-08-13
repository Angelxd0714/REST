package com.main.rest.repository;

import com.main.rest.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RepositoryProduct extends CrudRepository<Product, Long> {
    Product findByName(String name);
    Product findByPrice(BigDecimal price);
    List<Product> findAllByName(String name);
    @Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice")
    List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
}
