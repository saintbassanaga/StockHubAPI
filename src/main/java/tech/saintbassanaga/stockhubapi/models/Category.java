package tech.saintbassanaga.stockhubapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @NotNull
    private String name;

    private String description;

    // Many subcategories can have one parent category
    @ManyToOne
    @JoinColumn(name = "parent_category_id")  // Foreign key in the subcategory table
    private Category parentCategory;

    // One category can have many subcategories
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> subCategories = new ArrayList<>();

    // Constructors, getters, setters
}

