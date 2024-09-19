package tech.saintbassanaga.stockhubapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.saintbassanaga.stockhubapi.models.Stock;

import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> {
}