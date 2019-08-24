/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import javafx.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilian
 */
public class LoginTest {
    
    public LoginTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUser method, of class Login.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Login instance = new Login("iliana", "iliana");
        String expResult = "";
        String result = instance.getUser();
        assertNotEquals(expResult, result);

    }



    /**
     * Test of getPassword method, of class Login.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Login instance = new Login("iliana", "iliana");
        String result = instance.getPassword();
        assertNotNull(result);
    }

    /**
     * Test of setPassword method, of class Login.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        Login instance = new Login();
        instance.setPassword("ILI");
        String result = instance.getPassword();
        assertNotNull(result);
    }

   
    
}
