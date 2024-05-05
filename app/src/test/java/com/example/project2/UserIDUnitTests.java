package com.example.project2;

import com.example.project2.database.entities.UserID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserIDUnitTests {

    UserID testUser;
    String testUsername = "testUsername";
    String testPassword = "testPassword";

    @Before
    public void setup() {
        testUser = new UserID(testUsername, testPassword, false);
    }

    @Test
    public void setUsername(){
        String newUsername = "newUsername";
        Assert.assertNotEquals(newUsername, testUser.getUsername());
        testUser.setUsername(newUsername);
        Assert.assertEquals(newUsername, testUser.getUsername());
    }

    @Test
    public void getUsername(){
        Assert.assertEquals(testUsername, testUser.getUsername());
    }

    @Test
    public void setPassword(){
        String newPassword = "newPassword";
        Assert.assertNotEquals(newPassword, testUser.getPassword());
        testUser.setPassword(newPassword);
        Assert.assertEquals(newPassword, testUser.getPassword());
    }

    @Test
    public void getPassword(){
        Assert.assertEquals(testPassword, testUser.getPassword());
    }
}
