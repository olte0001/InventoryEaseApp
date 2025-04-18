package com.group17.inventoryease.ums.services;

import com.group17.inventoryease.ums.repositories.CompanyRepository;
import com.group17.inventoryease.ums.models.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SchemaService {
    @Autowired
    private CompanyRepository companyRepository;

    private static final Logger log = LoggerFactory.getLogger(SchemaService.class);

    public Optional<String> getSchemaByCompanyId(Long companyId) {
        log.info("get schema by company id {}", companyId);
        log.info("Connected to database: {}", companyRepository.getCurrentDatabase());
        log.info("Connected to schema: {}", companyRepository.getCurrentSchema());
        return companyRepository.findById(companyId).map(Company::getCompanySchema);
        /*
        log.info("get schema by company id {}", companyId);
        Optional<String> schema = companyRepository.findById(companyId).map(Company::getCompanySchema);
        log.info("get schema by company id {}: {}", companyId, schema.orElse("Not found"));
        return schema;
         */
    }

    public Optional<String> getCompanyNameByCompanyId(Long companyId) {
        log.info("get company name by company id {}", companyId);
        Optional<String> companyName = companyRepository.findById(companyId).map(Company::getCompanyName);
        log.info("get company name by company id {}: {}", companyId, companyName.orElse("Not found"));
        return companyName;
    }
}