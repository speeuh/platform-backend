package com.projetinho.backend.services.product;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.projetinho.backend.config.validation.exception.ProductNotFoundException;
import com.projetinho.backend.dtos.product.ProductCreationDTO;
import com.projetinho.backend.dtos.product.ProductDTO;
import com.projetinho.backend.dtos.product.ProductUpdateDTO;
import com.projetinho.backend.models.product.Product;
import com.projetinho.backend.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AmazonS3 amazonS3;

    private static final String BUCKET = "learning-aws-lcsvieira";

    public ProductDTO createProduct(ProductCreationDTO productCreationDTO, String path) {
        Product product = productCreationDTO.convertCreationDtoToEntity();

        product.setImage(path);

        Product productDto = productRepository.save(product);

        return productDto.convertEntityToDto();
    }

    public String getFilePath(MultipartFile file) throws IOException {
        amazonS3.putObject(new PutObjectRequest(BUCKET,
                file.getOriginalFilename(),
                file.getInputStream(),
                null)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        String path = "http://s3.amazonaws.com/" + BUCKET + "/" + file.getOriginalFilename();

        return path;
    }

    public Page<ProductDTO> getAllProducts(Pageable pageable){
        return productRepository.findAll(pageable).map(Product::convertEntityToDto);
    }

    public ProductDTO getById(String id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Not found product with id " + id));

        return product.convertEntityToDto();
    }

    public void deleteById(String id){
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ProductNotFoundException("Not found product with id " + id);
        }
    }

    public ProductDTO updateById(ProductUpdateDTO productUpdateDto, String id){

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Not found product with id " + id));

        if(productUpdateDto.getMaterial() != null){
            product.setMaterial(productUpdateDto.getMaterial());
        }

        if(productUpdateDto.getHeight() != null){
            product.setHeight(productUpdateDto.getHeight());
        }

        if(productUpdateDto.getWeight() != null){
            product.setWeight(productUpdateDto.getWeight());
        }

        if(productUpdateDto.getPrice() != null){
            product.setPrice(productUpdateDto.getPrice());
        }

        Product productResponse = productRepository.save(product);

        return productResponse.convertEntityToDto();
    }
}
