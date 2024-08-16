package com.main.rest.controller;

import com.main.rest.entities.Category;
import com.main.rest.entities.Marker;
import com.main.rest.entities.Product;
import com.main.rest.services.ServicesProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/product")
public class ControllerProduct {
    @Value("${file.upload-dir}")
    private String uploadDir;
    @Autowired
    private ServicesProduct serviceProduct;
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestParam("nombre")String name, @RequestParam
                                        ("precio") BigDecimal price, @RequestParam("descripcion")String description, @RequestParam("imagen") MultipartFile image, @RequestParam("category_id") Category category_id, @RequestParam("marker_id") Marker marker_id){
        if(image.isEmpty()){
            return ResponseEntity.badRequest().body("La imagen es requerida");
        }
        System.out.println(name);

        String nameFile = UUID.randomUUID().toString() +"_"+ Objects.requireNonNull(image.getOriginalFilename()).replace(" ","_");
        String filePath = Paths.get(uploadDir, nameFile).toString();
        try {
            image.transferTo(Paths.get(filePath));
            String urlImage = "/"+uploadDir+"/"+nameFile;
            String fullUrlImage = "http://173.27.0.3:8060"+urlImage;
            Product  product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setImage(fullUrlImage);
            product.setCategory(category_id);
            product.setMarker(marker_id);
            serviceProduct.createProduct(product);
            return ResponseEntity.ok("OK");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error al subir la imagen: "+e.getMessage());
        }

    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllProducts(){
       try {
           return ResponseEntity.ok(serviceProduct.readProduct());
       }catch (Exception e){
           return ResponseEntity.status(500).body("Error al obtener los productos");
       }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(serviceProduct.findProductById(id));
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error al obtener el producto");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestParam(value = "nombre",required = false)String name, @RequestParam
            (value = "precio",required = false) BigDecimal price, @RequestParam(value = "descripcion", required = false)String description, @RequestParam(value = "imagen",required = false) MultipartFile image, @RequestParam(value = "category_id", required = false) Category category_id, @RequestParam(value = "marker_id",required = false) Marker marker_id){
        try {
            Product productUpdate = serviceProduct.findProductById(id);
            if(image.isEmpty()){
                return ResponseEntity.badRequest().body("La imagen es requerida");
            }
            image.transferTo(Paths.get("upload", image.getOriginalFilename()));
            String urlImage = "/"+uploadDir+"/"+image.getOriginalFilename();
            String fullUrlImage = "http://173.27.0.3:8060"+urlImage;
            System.out.println(fullUrlImage);
            productUpdate.setName(name);
            productUpdate.setPrice(price);
            productUpdate.setDescription(description);
            productUpdate.setImage(fullUrlImage);
            productUpdate.setCategory(category_id);
            productUpdate.setMarker(marker_id);
            serviceProduct.updateProduct(id,productUpdate);
            return ResponseEntity.ok("OK");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error al actualizar el producto: "+e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try {
            serviceProduct.deleteProduct(id);
            return ResponseEntity.ok("OK");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error al eliminar el producto");
        }
    }
    @GetMapping("/precio/{precio}")
    public ResponseEntity<?> findProductByPrice(@PathVariable BigDecimal precio){
        try {
            return ResponseEntity.ok(serviceProduct.findProductByPrice(precio));
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error al obtener los productos");
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> findProductByName(@PathVariable String name){
        try {
            return ResponseEntity.ok(serviceProduct.findProductByName(name));
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error al obtener los productos");
        }
    }
    @GetMapping("/between/{minPrice}/{maxPrice}")
    public ResponseEntity<?> findProductBetweenPrice(@PathVariable BigDecimal minPrice, @PathVariable BigDecimal maxPrice){
        try {
            return ResponseEntity.ok(serviceProduct.findProductBetweenPrice(minPrice, maxPrice));
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error al obtener los productos");
        }
    }

}
