package com.kunitskaya.service.database;

import com.kunitskaya.service.configuration.ConfigProvider;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class DatabaseOperations {
    @Autowired
    protected Logger logger;
    @Autowired
    protected ConfigProvider configProvider;
    @Autowired
    protected JdbcTemplate jdbcTemplate;
}
