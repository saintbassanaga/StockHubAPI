package tech.saintbassanaga.stockhubapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.saintbassanaga.stockhubapi.models.embedded.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sale")
public class Sale extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    private int quantity;
    private BigDecimal totalAmount;  // Total price of the sale (quantity * product price)


    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "users_uuid", nullable = false)
    private Users users;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "product_uuid", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}