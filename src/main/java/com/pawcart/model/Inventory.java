package com.pawcart.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter @Setter
public class Inventory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private int reorderLevel;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
    @ManyToOne private Item item;
    @ManyToOne private DistributionCentre centre;
}
