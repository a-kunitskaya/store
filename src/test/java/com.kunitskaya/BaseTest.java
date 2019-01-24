package com.kunitskaya;

import com.kunitskaya.entity.User;
import com.kunitskaya.service.configuration.BeanConfiguration;
import com.kunitskaya.service.database.DatabaseOperations;
import com.kunitskaya.service.database.OrderDatabaseOperations;
import com.kunitskaya.service.database.ProductDatabaseOperations;
import com.kunitskaya.service.database.UserDatabaseOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.testng.annotations.BeforeClass;

@ContextConfiguration(classes = BeanConfiguration.class)
public class BaseTest {
    @Autowired
    protected UserDatabaseOperations userDatabaseOperations;
    @Autowired
    protected ProductDatabaseOperations productDatabaseOperations;
    @Autowired
    protected OrderDatabaseOperations orderDatabaseOperations;
    @Autowired
    protected User user;

    @BeforeClass
    public void setUp() throws Exception {
        TestContextManager contextManager = new TestContextManager(getClass());
        contextManager.prepareTestInstance(this);
    }
}
