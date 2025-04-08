package com.group17.inventoryease.ums.beans;

/*
* "Since the tenant ID is to be set when storing an entity and added to where clauses when loading an entity, we need something that provides a value for it.
*  For this purpose, Hibernate requires a CurrentTenantIdentifierResolver to be implemented."
*
* Source: https://spring.io/blog/2022/07/31/how-to-integrate-hibernates-multitenant-feature-with-spring-data-jpa-in-a-spring-boot-application
* */


import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver, HibernatePropertiesCustomizer {

    private String currentTenant = "unknown";

    public void setCurrentTenant(String tenant) {
        currentTenant = tenant;
    }

    public String getCurrentTenant() {return currentTenant;}

    @Override
    public String resolveCurrentTenantIdentifier() {
        return currentTenant;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
    }
}