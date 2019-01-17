package com.kunitskaya.service.configuration;

import com.kunitskaya.service.MainController;
import com.kunitskaya.service.database.DatabaseOperations;
import com.kunitskaya.service.database.UserDatabaseOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan("com.kunitskaya")
public class AppConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/pages/**").addResourceLocations("/pages/");
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

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
    public DatabaseOperations databaseOperations() {
        return new DatabaseOperations();
    }

    @Bean
    public Logger logger() {
        return LogManager.getLogger(this);
    }

    @Bean
    public MainController mainController() {
        return new MainController();
    }
}
