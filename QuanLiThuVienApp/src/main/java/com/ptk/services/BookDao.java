/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ptk.services;

import com.ptk.quanlithuvienapp.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;


import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Kien
 */
public class BookDao {

    public boolean insertBook(Book b) throws Exception {
        String sql = "INSERT INTO book(book_id, name, year, surname_author, name_author, number, category, status)"
                + " values(?,?,?,?,?,?,?,?)";
        try (
                 Connection conn = JdbcUtils.getConn();  PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, b.getBook_id());
            pstmt.setString(2, b.getName());
            pstmt.setInt(3, b.getYear());
            pstmt.setString(4, b.getSurname_author());
            pstmt.setString(5, b.getName_author());
            pstmt.setInt(6, b.getNumber());
            pstmt.setString(7, b.getCategory());
            pstmt.setString(8, b.getStatus());

            return pstmt.executeUpdate() > 0;
            // tra ve <= 0: loi hoac khong co du lieu
        }
    }

    public boolean updateBook(Book b) throws Exception {
        String sql = "UPDATE book SET name=?, year=?, surname_author=?, name_author=?, number=?, category=?, status=?"
                + " WHERE book_id=?";
        try (
                 Connection conn = JdbcUtils.getConn();  PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(8, b.getBook_id());
            pstmt.setString(1, b.getName());
            pstmt.setInt(2, b.getYear());
            pstmt.setString(3, b.getSurname_author());
            pstmt.setString(4, b.getName_author());
            pstmt.setInt(5, b.getNumber());
            pstmt.setString(6, b.getCategory());
            pstmt.setString(7, b.getStatus());

            return pstmt.executeUpdate() > 0;
            // tra ve <= 0: loi hoac khong co du lieu
        }
    }

    public boolean deleteBook(String bookId) throws Exception {
        String sql = "DELETE FROM book WHERE book_id=?";

        try (Connection conn = JdbcUtils.getConn();  PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, bookId);
            return pstmt.executeUpdate() > 0;
            // tra ve <= 0: loi hoac khong co du lieu
        }

    }
  
    public ObservableList<Book> loadBook1() throws Exception {
        String sql = "SELECT * FROM book";
        try ( Connection conn = JdbcUtils.getConn();  
                //Statement st = conn.createStatement();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {

            try ( ResultSet rs = pstmt.executeQuery(sql);) {
                ObservableList<Book> list = FXCollections.observableArrayList();
//                Book b = new Book();
                while (rs.next()) {
//                    b.setBook_id(rs.getString("book_id"));
//                    b.setName(rs.getString("name"));
//                    b.setYear(rs.getInt("year"));
//                    b.setSurname_author(rs.getString("surname_author"));
//                    b.setName_author(rs.getString("name_author"));
//                    b.setNumber(rs.getInt("number"));
//                    b.setCategory(rs.getString("category"));
//                    b.setStatus(rs.getString("status"));
//                    list.add(b);

                    list.add(new Book(
                            rs.getString("book_id"),
                            rs.getString("name"),
                            rs.getString("surname_author"),
                            rs.getString("name_author"),
                            rs.getString("category"),
                            rs.getString("status"), 
                            rs.getInt("year"), 
                            rs.getInt("number")));
                }
                return list;

        }
    }
        
         

    public List<Book> getBook(String kw) throws SQLException {
        List<Book> book = new ArrayList<>();

        String sql = "SELECT * FROM book";
        if (kw != null && !kw.isEmpty()) {
            sql += " WHERE name like ?";
        }

        try ( Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, '%' + kw + '%');
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Book b;
                b = new Book(rs.getString("book_id"), rs.getString("name"),
                        rs.getString("surname_author"), rs.getString("name_author"),
                        rs.getString("category"), rs.getString("status"),
                        rs.getShort("year"), rs.getShort("number"));
                book.add(b);
            }
        }
        return book;

        }

    }
    
}
