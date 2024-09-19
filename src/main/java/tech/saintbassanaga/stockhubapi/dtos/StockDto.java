package tech.saintbassanaga.stockhubapi.dtos;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link tech.saintbassanaga.stockhubapi.models.Stock}
 */
public record StockDto(int quantity, int reservedQuantity, LinkProduct product) implements Serializable {
    /**
     * DTO for {@link tech.saintbassanaga.stockhubapi.models.Product}
     */
    public record LinkProduct(UUID uuid) implements Serializable {
    }
}