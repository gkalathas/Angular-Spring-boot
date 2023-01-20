package com.george.productcrud.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {


    private String productDescription;

    private String productTitle;

    private float productPrice;

    private float rating;

    private String productCategory;
    private String productImage;



}
