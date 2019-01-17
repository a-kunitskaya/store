package com.kunitskaya;

import com.kunitskaya.service.configuration.AppConfiguration;
import com.kunitskaya.service.database.UserDatabaseOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = AppConfiguration.class)
public class BaseTest {
    @Autowired
    protected UserDatabaseOperations userDatabaseOperations;

//    @BeforeClass
//    public void setUp() throws Exception {
//        TestContextManager contextManager = new TestContextManager(getClass());
//        contextManager.prepareTestInstance(this);
//    }
}
