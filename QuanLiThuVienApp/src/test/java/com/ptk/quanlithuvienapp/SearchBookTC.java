package com.ptk.quanlithuvienapp;

import com.ptk.quanlithuvienapp.SearchBookController;
import com.ptk.services.BookTester;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Nguyen Linh Nhi
 */
public class SearchBookTC {
    private static Connection conn;
    
    
    @Test
    public void testGetAll() throws SQLException
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
