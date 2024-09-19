package tech.saintbassanaga.stockhubapi.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link tech.saintbassanaga.stockhubapi.models.Stock}
 */
public record StockDetailsDto(LocalDateTime createAt, LocalDateTime updateAt, UUID uuid, int quantity,
                              int reservedQuantity, ProductDto product) implements Serializable {
}