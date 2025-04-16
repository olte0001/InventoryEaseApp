package com.group17.inventoryease.dtos;

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

    public LoginRequest(String username, String password, String schemaName) {
        this.username = username;
        this.password = password;
        this.schemaName = schemaName;
    }
}
