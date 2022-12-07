/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.ptk.pojo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Kien
 */
public class UserClassTester {
    
    public UserClassTester() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }


    @Test
    public void testGetUserName() {
        User instance = new User();
        String expResult = null;
        String result = instance.getUserName();
        assertEquals(expResult, result);  
    }

  
    @Test
    public void testSetUserName() {
        String userName = "abc123";
        String expResult = "abc123";
        User instance = new User();
        instance.setUserName(userName);
        assertEquals(expResult, instance.getUserName());
    }

  
    @Test
    public void testGetPassWord() {
        User instance = new User();
        String expResult = null;
        String result = instance.getPassWord();
        assertEquals(expResult, result);
    }

 
    @Test
    public void testSetPassWord() {
        String passWord = "abc123";
        String expResult = "abc123";
        User instance = new User();
        instance.setPassWord(passWord);
        assertEquals(expResult, instance.getPassWord());
    }
    
    @Test
    public void testGetActive() {
        User instance = new User();
        String expResult = null;
        String result = instance.getActive();
        assertEquals(expResult, result);
    }

   
    @Test
    public void testSetActive() {
        String active = "1";
        String expResult = "1";
        User instance = new User();
        instance.setActive(active);
        assertEquals(expResult, instance.getActive());
    }
    
}
