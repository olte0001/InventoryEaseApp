package com.group17.inventoryease.ums.beans;

/*
 * The TenantConnectionProvider class manages the connection with the database to set the current schema dynamically.
 *
 * Source: https://spring.io/blog/2022/07/31/how-to-integrate-hibernates-multitenant-feature-with-spring-data-jpa-in-a-spring-boot-application
 *         https://dzone.com/articles/spring-boot-hibernate-multitenancy-implementation
 * */

import com.group17.inventoryease.ums.context.TenantContext;
import com.group17.inventoryease.ums.controllers.UmsController;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class TenantConnectionProvider implements MultiTenantConnectionProvider<String> {

    private static final Logger log = LoggerFactory.getLogger(TenantConnectionProvider.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        tenantIdentifier = TenantContext.getCurrentTenant();
        log.debug("Current tenant identifier: {}", tenantIdentifier);

        final Connection connection = getAnyConnection();
        log.debug("Connection established to database: {}", connection.getMetaData().getURL());
        log.debug("Connection user: {}", connection.getMetaData().getUserName());
        try {
            if (tenantIdentifier != null) {
                log.debug("Setting schema search_path to tenant: {}", tenantIdentifier);
                connection.createStatement().execute("SET search_path TO " + tenantIdentifier);
            } else {
                log.debug("Setting schema search_path to public (default schema)");
                connection.createStatement().execute("SET search_path TO public");
            }
        }
        catch ( SQLException e ) {
            log.error("Error setting schema to {}", tenantIdentifier, e);
            throw new HibernateException(
                    "Problem setting schema to " + tenantIdentifier,
                    e
            );
        }
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SET search_path TO " + tenantIdentifier);
        }
        catch ( SQLException e ) {
            throw new HibernateException(
                    "Problem setting schema to " + tenantIdentifier,
                    e
            );
        }
        connection.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    @org.checkerframework.checker.nullness.qual.UnknownKeyFor
    @org.checkerframework.checker.nullness.qual.NonNull
    @org.checkerframework.checker.initialization.qual.Initialized
    public boolean isUnwrappableAs(@org.checkerframework.checker.nullness.qual.UnknownKeyFor @org.checkerframework.checker.nullness.qual.NonNull @org.checkerframework.checker.initialization.qual.Initialized Class<@org.checkerframework.checker.nullness.qual.UnknownKeyFor @org.checkerframework.checker.nullness.qual.NonNull @org.checkerframework.checker.initialization.qual.Initialized ?> aClass) {
        return false;
    }

    @Override
    public <T> T unwrap(@org.checkerframework.checker.nullness.qual.UnknownKeyFor @org.checkerframework.checker.nullness.qual.NonNull @org.checkerframework.checker.initialization.qual.Initialized Class<T> aClass) {
        return null;
    }
}