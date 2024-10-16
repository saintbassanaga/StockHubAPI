package tech.saintbassanaga.stockhubapi.config.exceptions;

import lombok.Getter;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project StockHubAPI at Thu - 9/19/24
 */

@Getter
public enum ErrorCode {
    MISSING_FIELD("Missing Field"),
    INVALID_FORMAT("Invalid Format"),
    UNAUTHORIZED_ACCESS("Unauthorized Access"),
    PRODUCT_NOT_FOUND("Product Not Found"),
    RESOURCE_NOT_FOUND("Resource Not Found"),
    CATEGORY_NOT_FOUND("Category Not Found"),
    LOW_STOCK("Low Stock"), REPORT_GENERATION_ERROR("Report Generation Error"),;
    ErrorCode(String s) {

    }
}
