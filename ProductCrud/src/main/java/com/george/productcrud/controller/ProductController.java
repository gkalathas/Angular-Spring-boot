package com.george.productcrud.controller;

import com.george.productcrud.model.Product;
import com.george.productcrud.repository.ProductRepository;
import com.george.productcrud.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200/")
public class ProductController {


    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService,
                             ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(name = "page", defaultValue = "0") Integer pageNo,
                                                        @RequestParam(name = "size", defaultValue = "10") Integer pageSize) {
        return new ResponseEntity<>(productService.getAll(pageNo, pageSize), HttpStatus.OK);
    }



    @PostMapping("/saveAll")
    public ResponseEntity<String> saveMany(@RequestBody List<Product> productList) {
        productService.saveAll(productList);
        return new ResponseEntity<>("saved", HttpStatus.CREATED);
    }


    @PostMapping("/saveProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct( @PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }
}
