//package com.kunitskaya;
//
//import com.kunitskaya.entity.User;
//import com.kunitskaya.service.database.UserDatabaseOperations;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class DatabaseTest extends BaseTest {
//
//    @Test
//    public void addUser() {
//       String username = RandomStringUtils.randomAlphabetic(1, 10);
//       String password = RandomStringUtils.randomAlphanumeric(1, 7);
//
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//
//        UserDatabaseOperations userDatabaseOperations = new UserDatabaseOperations();
//        userDatabaseOperations.addUser(user);
//        User userFromDB = userDatabaseOperations.getUser(username, password);
//
//        Assert.assertEquals(username, userFromDB.getUsername());
//        Assert.assertEquals(password, userFromDB.getPassword());
//    }
//}
