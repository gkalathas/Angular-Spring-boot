package com.george.productcrud.service;


import com.george.productcrud.exception.ResourceNotFoundException;
import com.george.productcrud.model.Product;
import com.george.productcrud.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void saveAll(List<Product> productList) {
        productRepository.saveAll(productList);
    }


    public Page<Product> getAll(Integer page, Integer size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product retrievedProduct = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product does not exist with id: " + id));

        retrievedProduct.setTitle(product.getTitle());
        retrievedProduct.setDescription(product.getDescription());
        retrievedProduct.setPrice(product.getPrice());
        retrievedProduct.setImage(product.getImage());
        retrievedProduct.setPcategory(product.getPcategory());
        return productRepository.save(retrievedProduct);
    }

    public void deleteProduct(Long id) {
        Product retrievedProduct = productRepository.findById(id).orElseThrow(() ->
           new ResourceNotFoundException("Product with id: " + id + " not found."));

        productRepository.delete(retrievedProduct);
    }

}
