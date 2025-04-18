package com.group17.inventoryease.inventory.models;

import com.group17.inventoryease.inventory.models.Locator;
import com.group17.inventoryease.inventory.models.Supplier;
import com.group17.inventoryease.inventory.models.Product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_quantity")
    private Integer itemQuantity;

    @Column(name = "received_date")
    private LocalDateTime receivedDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "locator_id")
    private Locator locator;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
