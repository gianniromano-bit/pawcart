package com.pawcart.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter @Setter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 500)
    private String description;
    private String sku;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private PetType petType;
    private String imageUrl;
    @ManyToOne private Brand brand;
    @ManyToOne private Category category;
    @CreationTimestamp @Column(updatable = false)
    private LocalDateTime createdAt;
}
