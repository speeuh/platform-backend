package com.projetinho.backend.dtos.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {

    private String producer;
    private String anime;
    private String character;
    private String material;
    private Integer height;
    private Integer weight;
    private Double price;
    private String image;

    public ProductDTO(String producer, String anime, String character, String material, Integer height, Integer weight, Double price, String image) {
        this.producer = producer;
        this.anime = anime;
        this.character = character;
        this.material = material;
        this.height = height;
        this.weight = weight;
        this.price = price;
        this.image = image;
    }
}
