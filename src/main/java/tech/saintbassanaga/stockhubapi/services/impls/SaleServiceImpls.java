package tech.saintbassanaga.stockhubapi.services.impls;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.saintbassanaga.stockhubapi.dtos.SaleDto;
import tech.saintbassanaga.stockhubapi.config.exceptions.GeneralException;
import tech.saintbassanaga.stockhubapi.dtos.DtoMappers;
import tech.saintbassanaga.stockhubapi.models.Sale;
import tech.saintbassanaga.stockhubapi.repositories.SaleRepository;
import tech.saintbassanaga.stockhubapi.repositories.StockRepository;
import tech.saintbassanaga.stockhubapi.services.SaleService;
import tech.saintbassanaga.stockhubapi.services.StockService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of SaleService for managing sales operations.
 */
@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final StockService stockService;
    private static final Logger logger = LoggerFactory.getLogger(SaleServiceImpl.class);

    public SaleServiceImpl(SaleRepository saleRepository, StockService stockService) {
        this.saleRepository = saleRepository;
        this.stockService = stockService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public SaleDto createSale(UUID productId, UUID userId, int quantity) {
        // Adjust stock quantity using StockService
        stockService.adjustProductQuantity(productId, -quantity);

        // Proceed with sale creation
        Sale sale = saleRepository.save(new Sale(productId, userId, quantity));
        logger.info("Sale created with ID: {}", sale.getUuid().toString());

        return DtoMappers.toSaleDto(sale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SaleDto getSaleById(UUID saleId) {
        return saleRepository.findById(saleId)
                .map(DtoMappers::toSaleDto)
                .orElseThrow(() -> new GeneralException("Sale not found with ID: " + saleId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SaleDto> getAllSales() {
        return saleRepository.findAll().stream()
                .map(DtoMappers::toSaleDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SaleDto> getSalesByUserId(UUID userId) {
        return saleRepository.findByUserId(userId).stream()
                .map(DtoMappers::toSaleDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SaleDto> getSalesByProductId(UUID productId) {
        return saleRepository.findByProductId(productId).stream()
                .map(DtoMappers::toSaleDto)
                .collect(Collectors.toList());
    }
}
