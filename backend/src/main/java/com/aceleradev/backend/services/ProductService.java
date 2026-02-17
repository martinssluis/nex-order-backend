package com.aceleradev.backend.services;

import com.aceleradev.backend.commons.dto.ProductDto;
import com.aceleradev.backend.entities.Product;
import com.aceleradev.backend.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(product -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setName(product.getName());
                    productDto.setPrice(product.getPrice());
                    productDto.setCategory(product.getCategory());
                    productDto.setId(product.getId());
                    return productDto;
                })
                .toList();
    }

    public ProductDto findById(Long id) {
        ProductDto productDto = new ProductDto();
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found."));

        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        productDto.setId(product.getId());

        return productDto;

    }


    //TODO -- RETORNAR O PRODUTO CRIADO
    public void createProduct(ProductDto productDto) {
        Product productEntity = new Product();
        
        productEntity.setName(productDto.getName());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setCategory(productDto.getCategory());

        productRepository.save(productEntity);
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {

            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setCategory(productDto.getCategory());
            productRepository.save(product);

            productDto.setName(optionalProduct.get().getName());
            productDto.setCategory(optionalProduct.get().getCategory());
            product.setPrice(optionalProduct.get().getPrice());
            product.setId(optionalProduct.get().getId());

            return productDto;

        } else {
            throw new RuntimeException("Product not found!");
        }

    }

    //TODO -- ANTES DE REALIZAR A EXCLUSÃO,VALIDAR SE O PRODUTO EXISTE MESMO OU NÃO | CONFIGURAR UMA EXCEPTION ESPECÍFICA PRA ISSO
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
