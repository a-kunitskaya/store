package com.kunitskaya.service.configuration;

import com.kunitskaya.entity.Product;
import com.kunitskaya.entity.User;
import com.kunitskaya.service.TradeService;
import com.kunitskaya.service.database.OrderDatabaseOperations;
import com.kunitskaya.service.database.ProductDatabaseOperations;
import com.kunitskaya.service.database.UserDatabaseOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
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
        return new JdbcTemplate(mysqlDataSource());
    }

    @Bean
    public UserDatabaseOperations userDatabaseOperations() {
        return new UserDatabaseOperations();
    }

    @Bean
    public ProductDatabaseOperations productDatabaseOperations() {
        return new ProductDatabaseOperations();
    }

    @Bean
    public OrderDatabaseOperations orderDatabaseOperations() {
        return new OrderDatabaseOperations();
    }

    @Bean
    public Logger logger() {
        return LogManager.getLogger(this);
    }

    @Bean
    @Scope("prototype")
    public Product product() {
        return new Product();
    }

    @Bean
    @Scope("prototype")
    public User user() {
        return new User();
    }

    @Bean
    public TablesInitializer tablesInitializer() {
        return new TablesInitializer();
    }

    @Bean
    public TradeService tradeService() {
        return new TradeService();
    }
}
