package com.group17.inventoryease.ums.context;

/* Each thread has its own schema that it stores and sets.
* Source: https://dzone.com/articles/spring-boot-hibernate-multitenancy-implementation
* */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantContext {
    private static Logger logger = LoggerFactory.getLogger(TenantContext.class);

    private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static void setCurrentTenant(String tenant) {
        logger.debug("Setting tenant to: " + tenant);
        currentTenant.set(tenant);
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.set(null);
    }
}
