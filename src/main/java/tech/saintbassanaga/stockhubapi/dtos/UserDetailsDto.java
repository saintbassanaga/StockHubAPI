package tech.saintbassanaga.stockhubapi.dtos;

import tech.saintbassanaga.stockhubapi.models.embedded.Role;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link tech.saintbassanaga.stockhubapi.models.Users}
 */
public record UserDetailsDto(String username, Role role, String email, List<SaleDto> sales) implements Serializable {
}