package tech.saintbassanaga.stockhubapi.services.impls;

import tech.saintbassanaga.stockhubapi.config.exceptions.ErrorCode;
import tech.saintbassanaga.stockhubapi.config.exceptions.ErrorStatus;
import tech.saintbassanaga.stockhubapi.config.exceptions.GeneralException;
import tech.saintbassanaga.stockhubapi.dtos.DtoMappers;
import tech.saintbassanaga.stockhubapi.dtos.ProductDetailsDto;
import tech.saintbassanaga.stockhubapi.dtos.ProductDto;
import tech.saintbassanaga.stockhubapi.models.Product;
import tech.saintbassanaga.stockhubapi.repositories.ProductRepository;
import tech.saintbassanaga.stockhubapi.services.ProductService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project StockHubAPI at Thu - 9/19/24
 */
public class ProductServiceImpls implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpls(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * @param productDto dto object holding data to persisted
     * @return a created product data
     */
    @Override
    public Product createProduct(ProductDto productDto) {
        return productRepository.save(DtoMappers.toProduct(productDto));
    }

    /**
     * Retrieves all products from the database and converts them to a list of {@code ProductDto}.
     * <p>
     * This method fetches all {@code Product} entities from the database, streams them,
     * and maps each entity to a {@code ProductDto} using the {@code DtoMappers::toProductDto} method.
     * The final result is a filtered and converted list of {@code ProductDto} objects.
     * </p>
     *
     * @return a {@code List<ProductDto>} containing all products present in the database,
     * with each {@code Product} entity converted to a {@code ProductDto}.
     * @implNote The result is a list of filtered data where each {@code Product} is transformed
     * into a {@code ProductDto} using the mapper function. The data is retrieved from
     * the database, streamed, mapped, and collected into a list.
     * Example usage:
     * <pre>{@code
     *  List<ProductDto> products = productService.getAllProduct();
     * }</pre>
     */
    @Override
    public List<ProductDto> getAllProduct() {
        return productRepository
                .findAll() // Retrieve all products from the database
                .stream() // Stream the list of products
                .map(DtoMappers::toProductDto) // Convert each Product entity to a ProductDto
                .collect(Collectors.toList()); // Collect and return as a list of ProductDto
    }


    /**
     * Retrieves a {@code ProductDto} by its unique identifier (UUID).
     * <p>
     * This method queries the database for a {@code Product} entity using the provided UUID.
     * If the product is found, it is converted to a {@code ProductDto} and returned.
     * If the product is not found, a {@code GeneralException} is thrown with detailed error information.
     * </p>
     *
     * @param productUuid the unique identifier of the {@code Product} to retrieve.
     * @return a {@code ProductDto} containing the product's data.
     * @throws GeneralException if no product is found with the specified UUID.
     *                          Example usage:
     *                          <pre>{@code
     *                           ProductDto productDto = productService.getProductByUuid(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
     *                          }</pre>
     */
    @Override
    public ProductDto getProductByUuid(UUID productUuid) {
        Product product = productRepository.findById(productUuid).orElseThrow(
                () -> new GeneralException("Product not Found with UUID :" + productUuid,
                        ErrorCode.PRODUCT_NOT_FOUND, ErrorStatus.NOT_FOUND_ENTITY
                ));
        return DtoMappers.toProductDto(product);
    }

    /**
     * Deletes a product based on its unique identifier (UUID).
     *
     * @param productId the UUID of the product to be deleted
     * @return a confirmation message indicating the product has been deleted successfully
     */
    @Override
    public String deleteProduct(UUID productId) {
        // Check if the product exists; throws an exception if not found
        productRepository.findById(productId).orElseThrow(() -> new GeneralException("Product not found", ErrorCode.PRODUCT_NOT_FOUND, ErrorStatus.NOT_FOUND_ENTITY));

        // Delete the product by its ID
        productRepository.deleteById(productId);

        // Return a confirmation message
        return "Product Deleted Successfully";
    }
    /**
     * Deletes multiple products based on a list of UUIDs.
     * Logs and skips any UUIDs that do not correspond to existing products.
     *
     * @param uuidList a list of UUIDs of the products to be deleted
     * @return a confirmation message indicating the number of successfully deleted products
     */
    @Override
    public String multipleDelete(List<UUID> uuidList) {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        // Identify UUIDs that do not exist in the repository
        List<UUID> missingProductIds = uuidList.stream()
                .filter(uuid -> !productRepository.existsById(uuid))
                .collect(Collectors.toList());

        // Log the UUIDs of missing products
        if (!missingProductIds.isEmpty()) {
            logger.warn("The following product IDs were not found and will be skipped: {}", missingProductIds);
        }
        // Filter the list to include only the products that actually exist
        List<UUID> existingProductIds = uuidList.stream()
                .filter(productRepository::existsById)
                .collect(Collectors.toList());
        // Delete all products that are found in the repository
        productRepository.deleteAllById(existingProductIds);

        // Return a confirmation message with the count of deleted products
        return existingProductIds.size() + " Products Deleted Successfully";
    }
    /**
     * Updates an existing product with new details.
     *
     * @param product   the product entity containing updated details
     * @param productId the UUID of the product to be updated
     * @return a ProductDetailsDto containing the updated product details
     */
    @Override
    public ProductDetailsDto updateProduct(Product product, UUID productId) {
        // Retrieve and validate the product; throws an exception if not found
        Product existingProduct = productRepository
                .findById(productId)
                .orElseThrow(
                        () -> new GeneralException("Product not found", ErrorCode.PRODUCT_NOT_FOUND, ErrorStatus.NOT_FOUND_ENTITY)
                );
        // Update the existing product's details with those provided in the input product entity
        existingProduct.setName(product.getName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());

        // Save the updated product back to the repository
        Product updatedProduct = productRepository.save(existingProduct);

        // Map the updated product to a ProductDetailsDto and return it
        return DtoMappers.toProductDetailsDto(updatedProduct);
    }

}
