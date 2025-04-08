package com.group17.inventoryease.ums.services;

import com.group17.inventoryease.ums.models.Company;
import com.group17.inventoryease.ums.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchemaService {
    @Autowired
    private CompanyRepository companyRepository;

    public Optional<String> getSchemaByCompanyId(String companyId) {
        return companyRepository.findByCompanyId(companyId).map(Company::getCompanySchema);
    }

    public Optional<String> getCompanyNameByCompanyId(String companyId) {
        return companyRepository.findByCompanyId(companyId).map(Company::getCompanyName);
    }
}