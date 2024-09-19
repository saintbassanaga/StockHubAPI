package tech.saintbassanaga.stockhubapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import tech.saintbassanaga.stockhubapi.models.embedded.Role;

import java.io.Serializable;

/**
 * DTO for {@link tech.saintbassanaga.stockhubapi.models.Users}
 */
public record UsersDto(String username, String password, @NotNull Role role,
                       @Email(message = "Enter a correct mail") String email) implements Serializable {
}