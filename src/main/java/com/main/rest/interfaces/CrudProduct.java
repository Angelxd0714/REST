package com.main.rest.interfaces;

import com.main.rest.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface CrudProduct {
    public void createProduct(Product product);
    public List<Product> readProduct();
    public void updateProduct(Long id, Product product);
    public void deleteProduct(Long id);
    public Product findProductById(Long id);
    public List<Product> findProductByName(String name);
    public Product findProductByPrice(BigDecimal price);
    public List<Product> findProductBetweenPrice(BigDecimal minPrice, BigDecimal maxPrice);
}
