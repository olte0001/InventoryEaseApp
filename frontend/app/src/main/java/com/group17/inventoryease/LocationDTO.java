package com.group17.inventoryease.dtos;

public class LocationDTO {
    private String locationId;
    private String locationName;

    public LocationDTO(String locationId, String locationName) {
        this.locationId = locationId;
        this.locationName = locationName;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getLocationName() {
        return locationName;
    }
}