package com.group17.inventoryease.ums.beans;

/*
* This class is directly used by Hibernate, so it needs to indicate it how our multitenancy is set up with the use of TenantContext.
*
* Source: https://spring.io/blog/2022/07/31/how-to-integrate-hibernates-multitenant-feature-with-spring-data-jpa-in-a-spring-boot-application
*         https://dzone.com/articles/spring-boot-hibernate-multitenancy-implementation
* */


import com.group17.inventoryease.ums.context.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = TenantContext.getCurrentTenant();
        if (tenantId != null) {
            return tenantId;
        }
        return "public";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

}