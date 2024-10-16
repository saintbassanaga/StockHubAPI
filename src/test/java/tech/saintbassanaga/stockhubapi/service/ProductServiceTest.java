package tech.saintbassanaga.stockhubapi.service;


import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.saintbassanaga.stockhubapi.dtos.ProductDto;
import tech.saintbassanaga.stockhubapi.models.Product;
import tech.saintbassanaga.stockhubapi.config.exceptions.GeneralException;
import tech.saintbassanaga.stockhubapi.repositories.ProductRepository;
import tech.saintbassanaga.stockhubapi.services.ProductService;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project StockHubAPI at Wed - 10/16/24
 */

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private ProductDto productDto;
    private Product product;

    @BeforeEach
    public void setUp() {
        productDto = new ProductDto();
        productDto.setName("Test Product");
        productDto.setSku("TEST-001");
        productDto.setPrice(BigDecimal.valueOf(19.99));
        productDto.setDescription("Test Description");

        product = new Product();
        product.setUuid(UUID.randomUUID());
        product.setName(productDto.getName());
        product.setSku(productDto.getSku());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
    }

    @Test
    public void testAddProduct() {

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product productF = productService.createProduct(productDto);

        assertNotNull(productF);
        assertEquals(product, productF);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testUpdateProduct() {
        UUID productId = product.getUuid();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDto updatedDto= new ProductDto("Updated Product",BigDecimal.valueOf(29.99),"Updated Description") ;
        Product updatedProduct = productService.updateProduct(updatedDto, productId);

        assertEquals("Updated Product", updatedProduct.getName());
        assertEquals(BigDecimal.valueOf(29.99), updatedProduct.getPrice());
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testGetProductByIdNotFound() {
        UUID productId = UUID.randomUUID(); // Non-existent product ID
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(GeneralException.class, () -> productService.getProductByUuid(productId));
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    public void testDeleteProduct() {
        UUID productId = product.getUuid();
        doNothing().when(productRepository).deleteById(productId);

        String result = productService.deleteProduct(productId);

        assertEquals("Product Deleted Successfully", result);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testDeleteProductNotFound() {
        UUID productId = UUID.randomUUID(); // Non-existent product ID
        doThrow(new GeneralException("Product not found", null, null)).when(productRepository).deleteById(productId);

        assertThrows(GeneralException.class, () -> productService.deleteProduct(productId));
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testAddProductWithInvalidData() {
        productDto.name(null); // Invalid name

        assertThrows(GeneralException.class, () -> productService.createProduct(productDto));
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    public void testUpdateProductWithInvalidData() {
        UUID productId = product.getUuid();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ProductDto invalidDto = new ProductDto();
        invalidDto.sku(null); // Invalid SKU

        assertThrows(GeneralException.class, () -> productService.updateProduct(productId, invalidDto));
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    public void testAddDuplicateProduct() {
        when(productRepository.save(any(Product.class))).thenThrow(new GeneralException("Product already exists", null, null));

        assertThrows(GeneralException.class, () -> productService.addProduct(productDto));
        verify(productRepository, times(1)).save(any(Product.class));
    }
}

