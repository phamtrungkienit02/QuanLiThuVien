package com.ptk.services;


import com.ptk.quanlithuvienapp.Book;
import com.ptk.services.BookDao;
import com.ptk.services.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kien
 */
public class BookTester {
    private static Connection conn;
    private static final BookDao dao = new BookDao();
    private static Book b1, b2;
    
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
     //TEST HAM THEM SACH
    @Test
    public void testInsertBook() throws Exception{
        Boolean flag;
        try {
            b1 = new Book("TEST1", "Java", "Trần Văn", "Be", "Lập trình","Sách cũ", 2000, 3);
            flag = dao.insertBook(b1);
            Assertions.assertTrue(flag);
//            Assertions.assertEquals(b1.getBook_id(), "TEST1");
//            Assertions.assertEquals(b1.getName(), "Java");
//            Assertions.assertEquals(b1.getSurname_author(), "Trần Văn");
//            Assertions.assertEquals(b1.getName_author(), "Bê");
//            Assertions.assertEquals(b1.getCategory(), "Lập trình");
//            Assertions.assertEquals(b1.getStatus(), "Sách cũ");
//            Assertions.assertEquals(b1.getYear(), 2000);
//            Assertions.assertEquals(b1.getNumber(), 3);
            
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    @Test
    public void testInsertBookInvalid() throws Exception{
        Boolean flag;
        try {
            b1 = new Book("IT03", "Java", "Trần Văn", "Bê", "Lập trình","Sách cũ", 2000, 3);
            flag = dao.insertBook(b1);
            Assertions.assertFalse(flag);     
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    //TEST HAM UPDATE SACH DUA
    @Test
    public void testUpdateBook() throws Exception{
        Boolean flag;
        b2 = new Book("IT03", "JavaFX", "Trần Văn", "Cê", "Lập trình","Sách cũ", 2001, 1);
        try {
            flag = dao.updateBook(b2);
            Assertions.assertTrue(flag);
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    @Test
    public void testUpdateBookInvalid() throws Exception{
        Boolean flag;
        b2 = new Book("TEST2", "JavaFX", "Trần Văn", "Cê", "Lập trình","Sách cũ", 2001, 1);
        try {
            flag = dao.updateBook(b2);
            Assertions.assertFalse(flag);
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
      //TEST HAM XOA SACH DUA VAO ID SACH
    @Test
    public void testDeleteBookByInvalidId() throws Exception{
        Boolean flag;
        try {
            flag = dao.deleteBook("TEST2");
            Assertions.assertFalse(flag);
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    @Test
    public void testDeleteBookByvalidId() throws Exception{
        Boolean flag;
        try {
            flag = dao.deleteBook("TEST1");
            Assertions.assertTrue(flag);   
        } catch (Exception ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
   
   // TEST HÀM LOAD BOOK
    /*@Test
    public void testLoadBook() throws Exception{
        try {
            ObservableList<Book> list = dao.loadBook1();
            Assertions.assertNotNull(list);   
                
        } catch (Exception ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    */
}