package com.projetinho.backend.dtos.product;

import com.projetinho.backend.models.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ProductCreationDTO {

    @NotNull @NotBlank
    private String producer;
    @NotNull @NotBlank
    private String anime;
    @NotNull @NotBlank
    private String character;
    @NotNull @NotBlank
    private String material;
    @NotNull
    private Integer height;
    @NotNull
    private Integer weight;
    @NotNull
    private Double price;
    @NotNull @NotBlank
    private String image;

    public Product convertCreationDtoToEntity() {
        return new Product(producer, anime, character, material, height, weight, price, image);
    }
}
