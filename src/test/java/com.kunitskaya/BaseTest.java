//package com.kunitskaya;
//
//import com.kunitskaya.service.configuration.AppConfiguration;
//import UserDatabaseOperations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestContextManager;
//import org.testng.annotations.BeforeClass;
//
//@ContextConfiguration(classes = AppConfiguration.class)
//public class BaseTest {
//    @Autowired
//    protected UserDatabaseOperations userDatabaseOperations;
//
//    protected TestContextManager contextManager;
//
//    @BeforeClass
//    public void setUp() throws Exception {
//        contextManager = new TestContextManager(getClass());
//        contextManager.prepareTestInstance(this);
//    }
//}
