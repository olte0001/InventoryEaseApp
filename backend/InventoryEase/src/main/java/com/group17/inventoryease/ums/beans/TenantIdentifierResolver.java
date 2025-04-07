package com.group17.inventoryease.ums.beans;

/*
* "Since the tenant ID is to be set when storing an entity and added to where clauses when loading an entity, we need something that provides a value for it.
*  For this purpose, Hibernate requires a CurrentTenantIdentifierResolver to be implemented."
*
* Source: https://spring.io/blog/2022/07/31/how-to-integrate-hibernates-multitenant-feature-with-spring-data-jpa-in-a-spring-boot-application
* */

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.spi.EventSource;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.internal.util.StringHelper;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.orm.hibernate5.HibernatePropertiesCustomizer;

import java.util.Map;

@Component
class TenantIdentifierResolver implements CurrentTenantIdentifierResolver, HibernatePropertiesCustomizer {

    private String currentTenant = "unknown";

    public void setCurrentTenant(String tenant) {
        currentTenant = tenant;
    }

    @Override
    public String resolveCurrentTenantIdentifier() {
        return currentTenant;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
    }
}