package tech.saintbassanaga.stockhubapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.saintbassanaga.stockhubapi.models.Product;
import tech.saintbassanaga.stockhubapi.models.Sale;
import tech.saintbassanaga.stockhubapi.models.Users;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
    Optional<Sale> findSalesByUsers(Users users);

    Optional<Sale> findSalesByProducts(Product products);
}