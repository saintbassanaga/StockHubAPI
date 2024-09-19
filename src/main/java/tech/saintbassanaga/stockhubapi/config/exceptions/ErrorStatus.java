package tech.saintbassanaga.stockhubapi.config.exceptions;

import lombok.Getter;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project StockHubAPI at Thu - 9/19/24
 */
@Getter
public enum ErrorStatus {
    VALIDATION_ERROR("Validation Error"),
    AUTHENTICATION_ERROR("Authentication Error"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    NOT_FOUND_ENTITY("Entity Not Found"),
    FILE_ERROR("Missing File");

    ErrorStatus(String s) {
    }
}
