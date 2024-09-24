package tech.saintbassanaga.stockhubapi.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * DTO for {@link tech.saintbassanaga.stockhubapi.models.Product}
 */
@Schema(description = "Data Transfer Object for detailed Product entity information")
public record ProductDetailsDto(

        @Schema(description = "Creation timestamp of the product", example = "2023-09-14T12:34:56")
        LocalDateTime createAt,

        @Schema(description = "Last update timestamp of the product", example = "2024-01-01T09:00:00")
        LocalDateTime updateAt,

        @Schema(description = "Unique identifier of the product", example = "a8098c1a-f86e-11da-bd1a-00112444be1e")
        UUID uuid,

        @Schema(description = "Name of the product", example = "Smartphone")
        String name,

        @NotNull
        @Schema(description = "Stock Keeping Unit (SKU) of the product", example = "SKU123456")
        String sku,

        @Schema(description = "Category details of the product")
        CategoryDto category,

        @NotNull
        @Schema(description = "Price of the product", example = "499.99")
        BigDecimal price,

        @Schema(description = "Description of the product", example = "Latest model with 64GB storage and 6GB RAM")
        String description

) implements Serializable {
}
