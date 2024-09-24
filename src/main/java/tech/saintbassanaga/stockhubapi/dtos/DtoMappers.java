package tech.saintbassanaga.stockhubapi.dtos;

import tech.saintbassanaga.stockhubapi.models.*;

import java.util.stream.Collectors;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project StockHubAPI at Thu - 9/19/24
 */

public class DtoMappers {

    // Mapping from Category to CategoryDto
    public static CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(
                category.getName(),
                category.getDescription(),
                category.getParentCategory() != null ? category.getParentCategory().getUuid() : null
        );
    }

    public static Category toCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.name());
        category.setDescription(categoryDto.description());
        // Handle parent category based on UUID (fetch from database if needed)
        return category;
    }

    // Mapping from Product to ProductDto (Simple Version for referencing)
    public static ProductDto toProductDto(Product product) {
        return new ProductDto(
                product.getName(),
                toCategoryDto(product.getCategory()),
                product.getPrice(),
                product.getDescription()
        );
    }

    public static Product toProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.name());
        product.setCategory(toCategory(productDto.category()));
        product.setPrice(productDto.price());
        product.setDescription(productDto.description());
        return product;
    }

    // Mapping from Product to ProductDetailsDto (Detailed Version for viewing product details)
    public static ProductDetailsDto toProductDetailsDto(Product product) {
        return new ProductDetailsDto(
                product.getCreateAt(),
                product.getUpdateAt(),
                product.getUuid(),
                product.getName(),
                product.getSku(),
                toCategoryDto(product.getCategory()),
                product.getPrice(),
                product.getDescription()
        );
    }

    // Mapping from Sale to SaleDetailsDto (Detailed)
    public static SaleDetailsDto toSaleDetailsDto(Sale sale) {
        return new SaleDetailsDto(
                sale.getCreateAt(),
                sale.getUpdateAt(),
                sale.getUuid(),
                sale.getQuantity(),
                sale.getTotalAmount(), sale.getUsers(), // Using simple UserDto instead of full user details
                toProductDto(sale.getProduct()),  // Using simple ProductDto for sale
                sale.getPaymentStatus()
        );
    }

    // Mapping from Sale to SaleDto (Simple)
    public static SaleDto toSaleDto(Sale sale) {
        return new SaleDto(
                sale.getQuantity(),
                sale.getTotalAmount(),
                sale.getPaymentStatus()
        );
    }

    public static Sale toSale(SaleDetailsDto saleDetailsDto) {
        Sale sale = new Sale();
        sale.setQuantity(saleDetailsDto.quantity());
        sale.setTotalAmount(saleDetailsDto.totalAmount());
        sale.setProduct(toProduct(saleDetailsDto.product()));
        sale.setPaymentStatus(saleDetailsDto.paymentStatus());
        return sale;
    }

    // Mapping from Stock to StockDetailsDto
    public static StockDetailsDto toStockDetailsDto(Stock stock) {
        return new StockDetailsDto(
                stock.getCreateAt(),
                stock.getUpdateAt(),
                stock.getUuid(),
                stock.getQuantity(),
                stock.getReservedQuantity(),
                toProductDto(stock.getProduct())  // Using simple ProductDto for stock
        );
    }

    public static Stock toStock(StockDetailsDto stockDetailsDto) {
        Stock stock = new Stock();
        stock.setQuantity(stockDetailsDto.quantity());
        stock.setReservedQuantity(stockDetailsDto.reservedQuantity());
        stock.setProduct(toProduct(stockDetailsDto.product()));
        return stock;
    }

    // Mapping from Users to UserDto (Simple)
    public static UserDto toUserDto(Users user) {
        return new UserDto(
                user.getUsername(),
                user.getRole()
        );
    }

    // Mapping from Users to UserDetailsDto (Detailed with sales)
    public static UserDetailsDto toUserDetailsDto(Users user) {
        return new UserDetailsDto(
                user.getUsername(),
                user.getRole(),
                user.getEmail(),
                user.getSales() != null ?
                        user.getSales().stream()
                                .map(DtoMappers::toSaleDto)  // Using simple SaleDto in UserDetailsDto
                                .collect(Collectors.toList())
                        : null
        );
    }

    public static Users toUser(UsersDto usersDto) {
        Users user = new Users();
        user.setUsername(usersDto.username());
        user.setRole(usersDto.role());
        user.setEmail(usersDto.email());
        user.setPassword(usersDto.password());
        // Handle sales separately if needed
        return user;
    }
}
