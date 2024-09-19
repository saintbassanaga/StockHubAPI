package tech.saintbassanaga.stockhubapi.models.embedded;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project StockHubAPI at Thu - 9/19/24
 */
public enum PaymentStatus {
    PENDING,    // Payment is pending
    PAID,       // Payment is completed successfully
    FAILED      // Payment failed or was rejected
}
