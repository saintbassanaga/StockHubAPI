package tech.saintbassanaga.stockhubapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.saintbassanaga.stockhubapi.models.Users;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
}