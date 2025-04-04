package com.group17.inventoryease.ums.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductSupplierId implements Serializable {
    private Long product;
    private Long supplier;
}
