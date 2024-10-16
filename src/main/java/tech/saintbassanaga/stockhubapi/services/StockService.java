package tech.saintbassanaga.stockhubapi.services;

import tech.saintbassanaga.stockhubapi.dtos.ProductDetailsDto;
import tech.saintbassanaga.stockhubapi.dtos.ProductDto;
import tech.saintbassanaga.stockhubapi.dtos.SaleDto;

import java.util.List;
import java.util.UUID;

/**
 * Interface for stock management operations within the StockHubAPI project.
 * Defines methods for handling stock-related functionalities such as
 * managing inventory, products, and their related data.
 *
 * <p>This interface should be implemented by any class that aims to
 * provide concrete stock management features in the StockHubAPI.</p>
 *
 * Created by saintbassanaga (saintbassanaga)
 * Project: StockHubAPI
 * Date: Wednesday, October 16, 2024
 */
public interface StockService {
    /**
     * Adjusts the inventory quantity for a specific product.
     *
     * @param productId the UUID of the product for which to adjust the quantity
     * @param quantity  the quantity to add or subtract from the existing stock
     * @return an updated inventory count for the product
     */
    int adjustProductQuantity(UUID productId, int quantity);

}
