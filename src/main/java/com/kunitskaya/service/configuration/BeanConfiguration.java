package com.kunitskaya.service.configuration;

import com.kunitskaya.service.database.UserDatabaseOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class BeanConfiguration {

    @Bean
    public ConfigProvider configProvider() {
        return ConfigProvider.getInstance();
    }

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(configProvider().getDBUrl());
        dataSource.setUsername(configProvider().getDBUsername());
        dataSource.setPassword(configProvider().getDBPassword());

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(mysqlDataSource());
        return jdbcTemplate;
    }

    @Bean
    public UserDatabaseOperations userDatabaseOperations() {
        return new UserDatabaseOperations();
    }

    @Bean
    public Logger logger() {
        return LogManager.getLogger(this);
    }
}
