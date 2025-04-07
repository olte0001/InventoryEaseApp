package com.group17.inventoryease.inventory.repositories;

import com.group17.inventoryease.inventory.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findAll();
}