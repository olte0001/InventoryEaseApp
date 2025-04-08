package com.group17.inventoryease.dtos;

public class LocationItem {
    private String id;
    private String name;

    public LocationItem(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId(){ return id; }
    public String getName(){ return name; }

    @Override
    public String toString() {
        return name;
    }
}
