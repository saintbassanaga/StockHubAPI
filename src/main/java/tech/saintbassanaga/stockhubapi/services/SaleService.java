package tech.saintbassanaga.stockhubapi.services;

import tech.saintbassanaga.stockhubapi.config.exceptions.GeneralException;
import tech.saintbassanaga.stockhubapi.dtos.SaleDto;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing sales operations, including creating,
 * retrieving, and listing sales.
 *
 * @created by saintbassanaga {saintbassanaga}
 * @date Wed - 10/16/24
 */
public interface SaleService {

    /**
     * Creates a sale for a specific product and user.
     *
     * @param productId the UUID of the product being sold
     * @param userId    the UUID of the user making the purchase
     * @param quantity  the quantity of the product being sold
     * @return a SaleDto containing the details of the created sale
     * @throws GeneralException if there is an issue with stock availability or other business constraints
     */
    SaleDto createSale(UUID productId, UUID userId, int quantity);

    /**
     * Updates the details of an existing sale, including the quantity and payment status.
     *
     * @param saleId      the UUID of the sale to update
     * @param saleUpdate  the SaleDto object containing updated sale details
     * @return SaleDto    the updated sale details as a data transfer object
     * @throws GeneralException if the sale is not found with the given ID
     */
    public SaleDto updateSale(UUID saleId, SaleDto saleUpdate);

    /**
     * Retrieves the details of a sale by its ID.
     *
     * @param saleId the UUID of the sale to retrieve
     * @return a SaleDto containing the details of the sale
     * @throws GeneralException if the sale is not found
     */
    SaleDto getSaleById(UUID saleId);

    /**
     * Retrieves all sales made.
     *
     * @return a list of SaleDto representing all sales
     */
    List<SaleDto> getAllSales();

    /**
     * Retrieves all sales made by a specific user.
     *
     * @param userId the UUID of the user whose sales are being retrieved
     * @return a list of SaleDto representing the sales made by the user
     */
    List<SaleDto> getSalesByUserId(UUID userId);

    /**
     * Retrieves all sales of a specific product.
     *
     * @param productId the UUID of the product whose sales are being retrieved
     * @return a list of SaleDto representing the sales of the product
     */
    List<SaleDto> getSalesByProductId(UUID productId);
}
