package com.group17.inventoryease.inventory.models;

import com.group17.inventoryease.inventory.models.Supplier;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "can_expire")
    private Boolean canExpire;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "threshold_min")
    private Integer thresholdMin;

    @ManyToMany
    @JoinTable(
            name="product_supplier",
            joinColumns=@JoinColumn(name="product_id"),
            inverseJoinColumns=@JoinColumn(name="supplier_id")
    )
    private Set<Supplier> suppliers;
}
