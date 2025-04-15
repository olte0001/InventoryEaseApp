package com.group17.inventoryease.ums.beans;

/*
 * The TenantConnectionProvider class manages the connection with the database to set the current schema dynamically.
 *
 * Source: https://spring.io/blog/2022/07/31/how-to-integrate-hibernates-multitenant-feature-with-spring-data-jpa-in-a-spring-boot-application
 *         https://dzone.com/articles/spring-boot-hibernate-multitenancy-implementation
 * */

import com.group17.inventoryease.ums.context.TenantContext;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class TenantConnectionProvider implements MultiTenantConnectionProvider<String> {

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
        final Connection connection = getAnyConnection();
        try {
            if (tenantIdentifier != null) {
                connection.createStatement().execute("SET search_path TO " + tenantIdentifier);
            } else {
                connection.createStatement().execute("SET search_path TO public");
            }
        }
        catch ( SQLException e ) {
            throw new HibernateException(
                    "Problem setting schema to " + tenantIdentifier,
                    e
            );
        }
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try {
            connection.createStatement().execute( "SET search_path TO public");
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