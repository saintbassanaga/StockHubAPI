package tech.saintbassanaga.stockhubapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "stock")
public class Stock extends AbstractAuditingEntity {
    @Id
    @Column(name = "uuid", nullable = false)
    private UUID uuid;
    private int quantity;
    private int reservedQuantity;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "product_uuid", nullable = false)
    private Product product;

}