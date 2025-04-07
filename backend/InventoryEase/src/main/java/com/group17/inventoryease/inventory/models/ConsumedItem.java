package com.group17.inventoryease.inventory.models;

import com.group17.inventoryease.ums.models.Locator;
import com.group17.inventoryease.ums.models.Supplier;
import com.group17.inventoryease.ums.models.Product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "consumeditem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumedItem {

    @Id
    @Column(name = "consumed_item_id")
    private String consumedItemId;

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
