package tech.saintbassanaga.stockhubapi.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link tech.saintbassanaga.stockhubapi.models.Product}
 */
public record ProductDto(String name, CategoryDto category, @NotNull BigDecimal price,
                         String description) implements Serializable {
}