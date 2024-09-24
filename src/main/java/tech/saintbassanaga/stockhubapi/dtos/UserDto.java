package tech.saintbassanaga.stockhubapi.dtos;

import tech.saintbassanaga.stockhubapi.models.embedded.Role;

import java.io.Serializable;

/**
 * DTO for {@link tech.saintbassanaga.stockhubapi.models.Users}
 */
public record UserDto(String username, Role role) implements Serializable {
}