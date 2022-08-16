package com.projetinho.backend.models.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    @NotBlank
    private String brand;
    @NotBlank
    private String anime;
    @NotBlank
    private String character;
    @NotBlank @NotNull
    private Double value;
    @NotBlank @NotNull
    private Double weight; // peso
    @NotBlank @NotNull
    private Integer height;
    @NotBlank @NotNull
    private Integer width;
    @NotBlank @NotNull
    private Integer length;

}
