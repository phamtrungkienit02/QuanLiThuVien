/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ptk.services;

import com.ptk.pojo.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Kien
 */
public class BookDao {
    
    public boolean insertBook(Book b) throws Exception{
        String sql = "INSERT INTO book(name, year, surname_author, name_author, number, category, status)"
                + " values(?,?,?,?,?,?,?)";
        try(
                Connection conn = JdbcUtils.getConn();
                PreparedStatement pstmt = conn.prepareStatement(sql);
           ){
            pstmt.setString(1, b.getName());
            pstmt.setInt(2, b.getYear());
            pstmt.setString(3, b.getSurname_author());
            pstmt.setString(4, b.getName_author());
            pstmt.setInt(5, b.getNumber());            
            pstmt.setString(6, b.getCategory());
            pstmt.setInt(7, b.getStatus());
        
            return pstmt.executeUpdate()>0;
            // tra ve <= 0: loi hoac khong co du lieu
            }
            
     }
}
