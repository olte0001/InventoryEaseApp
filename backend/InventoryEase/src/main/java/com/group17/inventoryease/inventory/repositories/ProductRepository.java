package com.group17.inventoryease.inventory.repositories;

import com.group17.inventoryease.inventory.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
}