package com.group17.inventoryease.transaction.models;

import com.group17.inventoryease.inventory.models.Product;
import com.group17.inventoryease.inventory.models.Supplier;
import com.group17.inventoryease.inventory.models.Locator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id")
    private Long transactionId;

    @Column(name = "trans_type")
    private String transactionType;

    @Column(name = "trans_quantity")
    private Integer transactionQuantity;

    @Column(name = "item_sn")
    private String itemSn;

    @Column(name = "trans_date")
    private LocalDateTime transactionDate;

    private String comments;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "locator_id")
    private Locator locator;
}
