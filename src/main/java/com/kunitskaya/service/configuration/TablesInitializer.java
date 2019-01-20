package com.kunitskaya.service.configuration;

import com.kunitskaya.service.database.ProductDatabaseOperations;
import com.kunitskaya.service.database.UserDatabaseOperations;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class TablesInitializer implements InitializingBean {
    @Autowired
    private UserDatabaseOperations userDatabaseOperations;
    @Autowired
    private ProductDatabaseOperations productDatabaseOperations;
    @Autowired
    private Logger logger;

    @Override
    public void afterPropertiesSet() {
        logger.info("Initializing tables");

        userDatabaseOperations.createTable();
        productDatabaseOperations.createTable();

    }
}
