package tech.saintbassanaga.stockhubapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.saintbassanaga.stockhubapi.models.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}