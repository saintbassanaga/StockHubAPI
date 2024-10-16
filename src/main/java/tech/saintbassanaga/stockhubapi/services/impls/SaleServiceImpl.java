package tech.saintbassanaga.stockhubapi.services.impls;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.saintbassanaga.stockhubapi.config.exceptions.ErrorCode;
import tech.saintbassanaga.stockhubapi.config.exceptions.ErrorStatus;
import tech.saintbassanaga.stockhubapi.dtos.SaleDto;
import tech.saintbassanaga.stockhubapi.config.exceptions.GeneralException;
import tech.saintbassanaga.stockhubapi.dtos.DtoMappers;
import tech.saintbassanaga.stockhubapi.models.Sale;
import tech.saintbassanaga.stockhubapi.repositories.ProductRepository;
import tech.saintbassanaga.stockhubapi.repositories.SaleRepository;
import tech.saintbassanaga.stockhubapi.repositories.UsersRepository;
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
    private final UsersRepository usersRepository;
    private final StockService stockService;
    private static final Logger logger = LoggerFactory.getLogger(SaleServiceImpl.class);
    private final ProductRepository productRepository;

    public SaleServiceImpl(SaleRepository saleRepository, UsersRepository usersRepository, StockService stockService, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.usersRepository = usersRepository;
        this.stockService = stockService;
        this.productRepository = productRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public SaleDto createSale(UUID productId, UUID userId, int quantity) {
        // Adjust stock quantity using StockService
        stockService.adjustProductQuantity(productId, -quantity);
        Sale sale = new Sale();
        sale.setProduct(productRepository.getReferenceById(productId));
        sale.setQuantity(quantity);
        sale.setUsers(usersRepository.getReferenceById(userId));
        return DtoMappers.toSaleDto(saleRepository.save(sale));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SaleDto getSaleById(UUID saleId) {
        return saleRepository.findById(saleId)
                .map(DtoMappers::toSaleDto)
                .orElseThrow(() -> new GeneralException("Sale not found with ID: " + saleId, ErrorCode.RESOURCE_NOT_FOUND, ErrorStatus.NOT_FOUND_ENTITY));
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
        return saleRepository
                .findSalesByUsers(usersRepository.getReferenceById(userId))
                .stream()
                .map(DtoMappers::toSaleDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SaleDto> getSalesByProductId(UUID productId) {
        return saleRepository
                .findSalesByProducts(productRepository.getReferenceById(productId))
                .stream()
                .map(DtoMappers::toSaleDto)
                .collect(Collectors.toList());
    }
}
