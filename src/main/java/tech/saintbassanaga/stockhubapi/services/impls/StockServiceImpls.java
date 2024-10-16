package tech.saintbassanaga.stockhubapi.services.impls;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import tech.saintbassanaga.stockhubapi.config.exceptions.ErrorCode;
import tech.saintbassanaga.stockhubapi.config.exceptions.ErrorStatus;
import tech.saintbassanaga.stockhubapi.config.exceptions.GeneralException;
import tech.saintbassanaga.stockhubapi.dtos.DtoMappers;
import tech.saintbassanaga.stockhubapi.dtos.ProductDetailsDto;
import tech.saintbassanaga.stockhubapi.dtos.ProductDto;
import tech.saintbassanaga.stockhubapi.dtos.SaleDto;
import tech.saintbassanaga.stockhubapi.models.Sale;
import tech.saintbassanaga.stockhubapi.models.Stock;
import tech.saintbassanaga.stockhubapi.repositories.ProductRepository;
import tech.saintbassanaga.stockhubapi.repositories.StockRepository;
import tech.saintbassanaga.stockhubapi.repositories.SaleRepository;

import tech.saintbassanaga.stockhubapi.services.StockService;
import tech.saintbassanaga.stockhubapi.services.PdfGeneratorService;

@Service
public class StockServiceImpls implements StockService {

    private final StockRepository stockRepository;

    private static final Logger logger = Logger.getLogger(StockServiceImpls.class.getName());

    public StockServiceImpls(StockRepository stockRepository,
                             SaleRepository saleRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public int adjustProductQuantity(UUID productId, int quantity) {
        return stockRepository.findByProductUuid(productId)
                .map(stock -> {
                    // Calculate the new quantity
                    int newQuantity = stock.getQuantity() + quantity;
                    // Validate that new quantity is not negative
                    if (newQuantity < 0) {
                        throw new GeneralException("Stock quantity cannot be negative after adjustment", ErrorCode.INVALID_FORMAT, ErrorStatus.VALIDATION_ERROR);
                    }
                    // Update the stock quantity
                    stock.setQuantity(newQuantity);
                    // Log the adjustment
                    logger.info("Adjusted stock quantity for product ID" + productId + " to :" + newQuantity);
                    // Save the updated stock and return the new quantity
                    return stockRepository.save(stock).getQuantity();
                })
                .orElseThrow(() -> new GeneralException("Stock not found for product ID: " + productId,
                        ErrorCode.MISSING_FIELD, ErrorStatus.NOT_FOUND_ENTITY));
    }

}
