package tech.saintbassanaga.stockhubapi.dtos;

import tech.saintbassanaga.stockhubapi.models.embedded.PaymentStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link tech.saintbassanaga.stockhubapi.models.Sale}
 */
public record SaleDto(int quantity, BigDecimal totalAmount,
                      PaymentStatus paymentStatus) implements Serializable {
}