package com.aceleradev.nexorder.services;

import com.aceleradev.nexorder.commons.enums.ProductCategory;
import com.aceleradev.nexorder.dtos.product.ProductDto;
import com.aceleradev.nexorder.entities.Product;
import com.aceleradev.nexorder.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void findAllShouldReturnMappedProducts() {
        Product p1 = product(1L, "Book A", 15.0, ProductCategory.BOOK);
        Product p2 = product(2L, "Game A", 200.0, ProductCategory.GAMES);

        when(productRepository.findAll()).thenReturn(List.of(p1, p2));

        List<ProductDto> result = productService.findAll();

        assertEquals(2, result.size());
        assertEquals("Book A", result.get(0).getName());
        assertEquals(15.0, result.get(0).getPrice());
        assertEquals(ProductCategory.BOOK, result.get(0).getCategory());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Game A", result.get(1).getName());
        assertEquals(2L, result.get(1).getId());
    }

    @Test
    void findByIdShouldReturnMappedProduct() {
        Product product = product(3L, "Other A", 30.0, ProductCategory.OTHER);
        when(productRepository.findById(3L)).thenReturn(Optional.of(product));

        ProductDto result = productService.findById(3L);

        assertEquals("Other A", result.getName());
        assertEquals(30.0, result.getPrice());
        assertEquals(ProductCategory.OTHER, result.getCategory());
        assertEquals(3L, result.getId());
    }

    @Test
    void findByIdShouldThrowWhenProductNotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> productService.findById(99L));
    }

    @Test
    void createProductShouldSaveEntity() {
        ProductDto dto = new ProductDto();
        dto.setName("Book B");
        dto.setPrice(25.0);
        dto.setCategory(ProductCategory.BOOK);

        productService.createProduct(dto);

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(captor.capture());
        Product saved = captor.getValue();
        assertEquals("Book B", saved.getName());
        assertEquals(25.0, saved.getPrice());
        assertEquals(ProductCategory.BOOK, saved.getCategory());
    }

    @Test
    void updateProductShouldUpdateWhenProductExists() {
        Product existing = product(7L, "Old", 10.0, ProductCategory.OTHER);
        when(productRepository.findById(7L)).thenReturn(Optional.of(existing));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProductDto update = new ProductDto();
        update.setName("New");
        update.setPrice(50.0);
        update.setCategory(ProductCategory.GAMES);

        ProductDto result = productService.updateProduct(7L, update);

        assertEquals("New", result.getName());
        assertEquals(50.0, existing.getPrice());
        assertEquals(ProductCategory.GAMES, existing.getCategory());
        verify(productRepository).save(existing);
    }

    @Test
    void updateProductShouldThrowWhenProductDoesNotExist() {
        when(productRepository.findById(7L)).thenReturn(Optional.empty());

        ProductDto update = new ProductDto();
        update.setName("New");
        update.setPrice(50.0);
        update.setCategory(ProductCategory.GAMES);

        assertThrows(RuntimeException.class, () -> productService.updateProduct(7L, update));
    }

    @Test
    void deleteProductShouldCallRepository() {
        productService.deleteProduct(5L);
        verify(productRepository).deleteById(5L);
    }

    private Product product(Long id, String name, Double price, ProductCategory category) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        return product;
    }
}
