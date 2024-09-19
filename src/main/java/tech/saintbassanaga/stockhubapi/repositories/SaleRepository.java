package tech.saintbassanaga.stockhubapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.saintbassanaga.stockhubapi.models.Sale;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
}