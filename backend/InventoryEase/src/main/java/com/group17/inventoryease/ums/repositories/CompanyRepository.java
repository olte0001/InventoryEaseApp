package com.group17.inventoryease.ums.repositories;

import com.group17.inventoryease.ums.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "SELECT current_database()", nativeQuery = true)
    String getCurrentDatabase();
    @Query(value = "SELECT current_schema()", nativeQuery = true)
    String getCurrentSchema();
    Optional<Company> findById(Long companyId);
}