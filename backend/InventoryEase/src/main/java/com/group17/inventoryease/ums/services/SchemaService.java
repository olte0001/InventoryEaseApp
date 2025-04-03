package com.group17.inventoryease.ums.services;

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