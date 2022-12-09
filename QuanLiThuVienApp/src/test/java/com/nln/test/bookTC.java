package com.nln.test;

import com.ptk.services.JdbcUtils;
import org.junit.jupiter.api.BeforeAll;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Nguyen Linh Nhi
 */
public class bookTC {
    private static Connection conn;
    
    @BeforeAll
    public static void beforeAll() throws SQLException
    {
        conn = JdbcUtils.getConn();
    }
    
    @AfterAll
    public static void afterAll() throws SQLException
    {
        if(conn != null)
        {
            conn.close();
        }
    }
    
    @Test
    public void testQuantity() throws SQLException
    {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM book");
        
        while (rs.next())
        {
            String name = rs.getString("name");
            System.out.println(name);
        }
    }
}
