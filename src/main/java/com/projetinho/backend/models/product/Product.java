package com.projetinho.backend.models.product;

import com.projetinho.backend.dtos.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String producer;
    private String anime;
    private String character;
    private String material;
    private Integer height;
    private Integer weight;
    private Double price;
    private String image;

    public Product(String producer, String anime, String character, String material, Integer height, Integer weight, Double price, String image) {
        this.producer = producer;
        this.anime = anime;
        this.character = character;
        this.material = material;
        this.height = height;
        this.weight = weight;
        this.price = price;
        this.image = image;
    }

    public ProductDTO convertEntityToDto() {
        return new ProductDTO(producer, anime, character, material, height, weight, price, image);
    }
}
