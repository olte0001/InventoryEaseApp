package com.group17.inventoryease.ums.repositories;

import com.group17.inventoryease.ums.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyId(Long companyId);
}