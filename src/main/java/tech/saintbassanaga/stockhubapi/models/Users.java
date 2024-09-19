package tech.saintbassanaga.stockhubapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.saintbassanaga.stockhubapi.models.embedded.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;  // admin, salesperson, etc.

    private String email;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sale> sales = new ArrayList<>();

}