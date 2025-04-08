package com.group17.inventoryease.dtos;

public class Company {

    private Long id;
    private String name;
    private  String schema;

    public Company(Long id, String name, String schema) {
        this.id = id;
        this.name = name;
        this.schema = schema;
    }

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
