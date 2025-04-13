package com.group17.inventoryease.ums.beans;

/*
 * The TenantConnectionProvider class manages the connection with the database to set the current schema dynamically.
 *
 * Source: https://spring.io/blog/2022/07/31/how-to-integrate-hibernates-multitenant-feature-with-spring-data-jpa-in-a-spring-boot-application
 * */

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;
import org.checkerframework.checker.initialization.qual.Initialized;

@Component
public class TenantConnectionProvider implements MultiTenantConnectionProvider<String>, HibernatePropertiesCustomizer {

    @Autowired
    private DataSource dataSource;

    @Override
    public Connection getAnyConnection() throws SQLException {
        return getConnection("PUBLIC");
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.createStatement().execute("SET search_path TO " + tenantIdentifier);
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        connection.createStatement().execute("SET search_path TO public");
        connection.close();
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, this);
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