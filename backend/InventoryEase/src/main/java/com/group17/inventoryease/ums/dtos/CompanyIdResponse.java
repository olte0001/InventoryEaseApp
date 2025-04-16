package com.group17.inventoryease.ums.dtos;

public class CompanyIdResponse {
    private boolean valid;
    private String companyName;
    private String schemaName;

    public CompanyIdResponse(boolean valid, String companyName, String schemaName) {
        this.valid = valid;
        this.companyName = companyName;
        this.schemaName = schemaName;
    }

    public boolean isValid() {return valid;}

    public String getCompanyName() {return companyName;}

    public String getSchemaName() {return schemaName;}

}