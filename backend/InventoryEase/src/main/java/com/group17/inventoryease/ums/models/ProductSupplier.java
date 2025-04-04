package com.group17.inventoryease.ums.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products_supplier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProductSupplierId.class)
public class ProductSupplier {

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
