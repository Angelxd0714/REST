package com.main.rest.controller;

import com.main.rest.entities.Category;
import com.main.rest.services.ServicesCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/category")
public class ControllerCategory {
    @Autowired
    private ServicesCategory servicesCategory;

    @GetMapping("/allCategories")
    public ResponseEntity<?> getAllCategories() {
        try {
          return ResponseEntity.ok(servicesCategory.getAllCategories());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/categoryById/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(servicesCategory.readCategory(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
   @PostMapping("/createCategory")
    public ResponseEntity<?> createCategory(@RequestBody  Category category) {
        try {
            servicesCategory.createCategory(category);
            return ResponseEntity.ok("ok");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable  Long id,@RequestBody  Category category) {
        try {
            servicesCategory.updateCategory(id,category);
            return ResponseEntity.ok("ok");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            servicesCategory.deleteCategory(id);
            return ResponseEntity.ok("ok");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
