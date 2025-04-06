package com.group17.inventoryease.inventory.repositories;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findAll();
}