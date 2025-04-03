package com.group17.inventoryease.dtos;

public class CompanyIdResponse {
    private boolean valid;
    private String companyName;

    public CompanyIdResponse(boolean valid, String companyName) {
        this.valid = valid;
        this.companyName = companyName;
    }

    public boolean isValid() {return valid;}

    public String getCompanyName() {return companyName;}

}
