package com.group17.inventoryease.inventory.repositories;

import com.group17.inventoryease.inventory.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}