package tech.saintbassanaga.stockhubapi.dtos;

import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link tech.saintbassanaga.stockhubapi.models.Category}
 */
public record CategoryDto(@NotNull String name, @NotNull String description, @Nullable UUID parentUuid) implements Serializable {
}