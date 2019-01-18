//package com.kunitskaya;
//
//import com.kunitskaya.service.database.UserDatabaseOperations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestContextManager;
//import org.testng.annotations.BeforeClass;
//
//@ContextConfiguration(classes = TestConfiguration.class)
//public class BaseTest {
//    @Autowired
//    protected UserDatabaseOperations userDatabaseOperations;
//
//    @BeforeClass
//    public void setUp() throws Exception {
//        TestContextManager contextManager = new TestContextManager(getClass());
//        contextManager.prepareTestInstance(this);
//    }
//}
