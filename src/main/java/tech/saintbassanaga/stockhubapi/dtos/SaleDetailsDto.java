package tech.saintbassanaga.stockhubapi.dtos;

import tech.saintbassanaga.stockhubapi.models.Users;
import tech.saintbassanaga.stockhubapi.models.embedded.PaymentStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link tech.saintbassanaga.stockhubapi.models.Sale}
 */
public record SaleDetailsDto(LocalDateTime createAt, LocalDateTime updateAt, UUID uuid, int quantity, Users users, ProductDto product,
                             PaymentStatus paymentStatus) implements Serializable {
}