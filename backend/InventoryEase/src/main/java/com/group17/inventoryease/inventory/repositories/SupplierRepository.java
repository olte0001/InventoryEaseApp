package com.group17.inventoryease.inventory.repositories;

import com.group17.inventoryease.inventory.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{

}