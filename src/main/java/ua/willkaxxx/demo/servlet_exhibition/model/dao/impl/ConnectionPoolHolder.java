package ua.willkaxxx.demo.servlet_exhibition.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;
import ua.willkaxxx.demo.servlet_exhibition.model.ConfigReader;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    private ConnectionPoolHolder(){}
    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(ConfigReader.getProperties().getProperty("database.connectionUrl"));
                    ds.setUsername(ConfigReader.getProperties().getProperty("database.username"));
                    ds.setPassword(ConfigReader.getProperties().getProperty("database.password"));
                    ds.setMinIdle(Integer.parseInt(ConfigReader.getProperties().getProperty("database.minIdle")));
                    ds.setMaxIdle(Integer.parseInt(ConfigReader.getProperties().getProperty("database.maxIdle")));
                    ds.setMaxOpenPreparedStatements(Integer.parseInt(ConfigReader.getProperties().getProperty("database.maxOpenPreparedStatements")));
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
