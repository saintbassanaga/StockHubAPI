package tech.saintbassanaga.stockhubapi.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link tech.saintbassanaga.stockhubapi.models.Product}
 */
public record ProductDetailsDto(LocalDateTime createAt, LocalDateTime updateAt, UUID uuid, String name,
                                @NotNull String sku, CategoryDto category, @NotNull BigDecimal price,
                                String description) implements Serializable {
}