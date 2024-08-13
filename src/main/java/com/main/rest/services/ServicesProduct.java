package com.main.rest.services;

import com.main.rest.entities.Product;
import com.main.rest.interfaces.CrudProduct;
import com.main.rest.repository.RepositoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ServicesProduct implements CrudProduct {
    @Autowired
    private RepositoryProduct repositoryProduct;

    @Override
    public void createProduct(Product product) {
        repositoryProduct.save(product);
    }

    @Override
    public List<Product> readProduct() {
        return (List<Product>) repositoryProduct.findAll();
    }

    @Override
    public void updateProduct(Long id, Product product) {
        Product productToUpdate = repositoryProduct.findById(id).orElse(null);
        if (productToUpdate != null) {
            productToUpdate.setName(product.getName());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setDescription(product.getDescription());
            productToUpdate.setImage(product.getImage());
            productToUpdate.setCategory(product.getCategory());
            repositoryProduct.save(productToUpdate);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        repositoryProduct.deleteById(id);
    }

    @Override
    public Product findProductById(Long id) {
        return repositoryProduct.findById(id).orElse(null);
    }

    @Override
    public List<Product> findProductByName(String name) {
        return (List<Product>) repositoryProduct.findAllByName(name);
    }

    @Override
    public Product findProductByPrice(BigDecimal price) {
        return repositoryProduct.findByPrice(price);
    }

    @Override
    public List<Product> findProductBetweenPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        return (List<Product>) repositoryProduct.findByPriceRange(minPrice, maxPrice);
    }
}
