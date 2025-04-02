package com.group17.inventoryease.ums.beans;

/*
 * The TenantConnectionProvider class manages the connecion with the database to set the current schema dynamically.
 *
 * Source: https://spring.io/blog/2022/07/31/how-to-integrate-hibernates-multitenant-feature-with-spring-data-jpa-in-a-spring-boot-application
 * */

@Component
class ExampleConnectionProvider implements MultiTenantConnectionProvider, HibernatePropertiesCustomizer {

    @Autowired
    DataSource dataSource;

    @Override
    public Connection getAnyConnection() throws SQLException {
        return getConnection("PUBLIC");
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String schema) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.createStatement().execute("SET search_path TO " + schema);
        return connection;
    }

    @Override
    public void releaseConnection(String s, Connection connection) throws SQLException {
        connection.createStatement().execute("SET search_path TO PUBLIC");
        connection.close();
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, this);
    }

}