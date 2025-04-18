package com.group17.inventoryease.ums.dtos;

public class LoginRequest {
    private String username;
    private String password;
    private String schemaName;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSchemaName() {return schemaName;}
}