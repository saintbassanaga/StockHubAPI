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
     *         with each {@code Product} entity converted to a {@code ProductDto}.
     * @implNote The result is a list of filtered data where each {@code Product} is transformed
     *           into a {@code ProductDto} using the mapper function. The data is retrieved from
     *           the database, streamed, mapped, and collected into a list.
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
     * Example usage:
     * <pre>{@code
     *  ProductDto productDto = productService.getProductByUuid(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
     * }</pre>
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
     * @param productId
     * @return
     */
    @Override
    public String deleteProduct(UUID productId) {
        productRepository.findById(productId);
        productRepository.deleteById(productId);
        return "Product Deleted Successfully";
    }

    /**
     * @param uuidList
     * @return
     */
    @Override
    public String multipleDelete(List<UUID> uuidList) {
        return "";
    }

    /**
     * @param product
     * @param productId
     * @return
     */
    @Override
    public ProductDetailsDto updateProduct(Product product, UUID productId) {
        return null;
    }
}
