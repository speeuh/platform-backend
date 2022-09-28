package com.projetinho.backend.controllers.product;

import com.projetinho.backend.dtos.product.ProductCreationDTO;
import com.projetinho.backend.dtos.product.ProductDTO;
import com.projetinho.backend.dtos.product.ProductUpdateDTO;
import com.projetinho.backend.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@ModelAttribute @Valid ProductCreationDTO productCreationDTO, MultipartFile file) throws IOException {
        String path = productService.getFilePath(file);

        ProductDTO productDTO = productService.createProduct(productCreationDTO, path);

        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getAllProducts(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<ProductDTO> productDTO = productService.getAllProducts(pageable);

        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable String id){
        ProductDTO productDTO = productService.getById(id);
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable String id){
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProductById(@RequestBody @Valid ProductUpdateDTO productUpdateDto, @PathVariable String id, BindingResult result) {

        try {
            if(result.hasErrors()){
                throw new IllegalArgumentException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            }

            ProductDTO productDTO = productService.updateById(productUpdateDto, id);

            return new ResponseEntity<>(productDTO, HttpStatus.OK);

        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
