package auction.service;

import auctionclient.Auction;
import auctionclient.Registration;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

//Webservices
import web.User;

public class JPARegistrationMgrTest {

    private Registration registrationMgr;
    private Auction auction;

    @Before
    public void setUp() throws Exception {
        registrationMgr = new Registration();
        auction = new Auction();
    }

    @After
    public void tearDown() throws Exception {
        auction.databaseClean();
    }

    @Test
    public void registerUser() {
        User user1 = registrationMgr.registerUser("xxx1@yyy");
        assertTrue(user1.getEmail().equals("xxx1@yyy"));
        User user2 = registrationMgr.registerUser("xxx2@yyy2");
        assertTrue(user2.getEmail().equals("xxx2@yyy2"));
        User user2bis = registrationMgr.registerUser("xxx2@yyy2");
        //geen @ in het adres
        assertNull(registrationMgr.registerUser("abc"));
    }

    @Test
    public void getUser() {
        User user1 = registrationMgr.registerUser("xxx5@yyy5");
        User userGet = registrationMgr.getUser("xxx5@yyy5");
        assertNull(registrationMgr.getUser("aaa4@bb5"));
        registrationMgr.registerUser("abc");
        assertNull(registrationMgr.getUser("abc"));
    }
}
