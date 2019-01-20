package com.kunitskaya;

import com.kunitskaya.service.configuration.BeanConfiguration;
import com.kunitskaya.service.database.OrderDatabaseOperations;
import com.kunitskaya.service.database.ProductDatabaseOperations;
import com.kunitskaya.service.database.UserDatabaseOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.testng.annotations.BeforeClass;

@ContextConfiguration(classes = BeanConfiguration.class)
public class BaseTest {
    @Autowired
    protected UserDatabaseOperations userDatabaseOperations;
    @Autowired
    protected ProductDatabaseOperations productDatabaseOperations;
    @Autowired
    protected OrderDatabaseOperations orderDatabaseOperations;

    @BeforeClass
    public void setUp() throws Exception {
        TestContextManager contextManager = new TestContextManager(getClass());
        contextManager.prepareTestInstance(this);
    }
}
