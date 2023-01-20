package com.george.productcrud.mapper;

import com.george.productcrud.dtos.ProductDto;
import com.george.productcrud.model.Product;
import com.george.productcrud.model.Rating;

public class productMapper {


    private ProductDto productDto;
    private Product product;

    private Rating rating;


    public productMapper(ProductDto productDto, Product product, Rating rating) {
        this.productDto = productDto;
        this.product = product;
        this.rating = rating;
    }





}
