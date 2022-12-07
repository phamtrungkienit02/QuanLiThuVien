/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.ptk.services;

import com.ptk.pojo.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author Kien
 */
public class UserDaoTest {
    private static Connection conn;
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void afterAll() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Test
    public void testCheckLoginEmpty() throws Exception {
        String username = "";
        String password = "";
        UserDao instance = new UserDao();
        User expResult = null;
        User result = instance.checkLogin(username, password);
        Assertions.assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckLoginPassInvalid() throws Exception {
        String username = "admin123";
        String password = "adminfdfdf";
        UserDao instance = new UserDao();
        User expResult = null;
        User result = instance.checkLogin(username, password);
        Assertions.assertEquals(expResult, result);
    }
    @Test
    public void testCheckLoginInvalid() throws Exception {
        String username = "ad123";
        String password = "addfdf";
        UserDao instance = new UserDao();
        User expResult = null;
        User result = instance.checkLogin(username, password);
        Assertions.assertEquals(expResult, result);
    }
    @Test
    public void testCheckLoginUserInvalid() throws Exception {
        String username = "dfdfd";
        String password = "admin";
        UserDao instance = new UserDao();
        User expResult = null;
        User result = instance.checkLogin(username, password);
        Assertions.assertEquals(expResult, result);
    }
   
}
