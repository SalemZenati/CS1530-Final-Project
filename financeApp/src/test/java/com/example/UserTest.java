package com.example;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.finance.Account;
import com.finance.User;

public class UserTest {

    User user;

    @Before
    public void setUp() {
        user = new User(0, "fname", "lname", "test@gmail.com", "1234", 0, 0, 0);
    }

    @Test
    public void testSetUuid() {
        assertEquals(0, user.getUuid());
        user.setUuid(1);
        assertEquals(1, user.getUuid());
    }

    @Test
    public void testSetFirstName() {
        assertEquals("fname", user.getFirstName());
        user.setFirstName("testName");
        assertEquals("testName", user.getFirstName());
    }

    @Test
    public void testSetLastName() {
        assertEquals("lname", user.getLastName());
        user.setLastName("testName");
        assertEquals("testName", user.getLastName());
    }

    @Test
    public void testSetEmail() {
        assertEquals("test@gmail.com", user.getEmail());
        user.setEmail("test2@gmail.com");
        assertEquals("test2@gmail.com", user.getEmail());
    }

    @Test
    public void testSetPassword() {
        assertEquals("1234", user.getPassword());
        user.setPassword("4321");
        assertEquals("4321", user.getPassword());
    }

    @Test
    public void testAddAccount() {

        List<Account> accounts = user.getAccounts();
        assertEquals(0, accounts.size());

        user.addAccount(0, "fake type", 0);

        accounts = user.getAccounts();

        assertEquals("fake type", accounts.get(0).getAccountType());
        assertEquals(1, user.getNumAccounts());
        assertEquals(user.getNumAccounts(), accounts.size());
    }

}
