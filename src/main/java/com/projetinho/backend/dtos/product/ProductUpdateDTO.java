package com.projetinho.backend.dtos.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductUpdateDTO {

    private String material;
    private Integer height;
    private Integer weight;
    private Double price;

    public ProductUpdateDTO(String material, Integer height, Integer weight, Double price) {
        this.material = material;
        this.height = height;
        this.weight = weight;
        this.price = price;
    }
}
