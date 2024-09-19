package tech.saintbassanaga.stockhubapi.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link tech.saintbassanaga.stockhubapi.models.Category}
 */
public record CategoryDto(@NotNull String name, @NotNull String description) implements Serializable {
}