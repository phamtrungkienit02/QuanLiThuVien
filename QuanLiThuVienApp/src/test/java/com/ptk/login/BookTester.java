package com.ptk.login;


import com.ptk.quanlithuvienapp.Book;
import com.ptk.services.BookDao;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kien
 */
public class BookTester {
    private static BookDao dao = new BookDao();
    private static Book b1, b2;
    
    @BeforeAll
    public static void init(){
      //  dao = new BookDao();
//                            rs.getString("name"),
//                            rs.getString("surname_author"),
//                            rs.getString("name_author"),
//                            rs.getString("category"),
//                            rs.getString("status"), 
//                            rs.getInt("year"), 
//                            rs.getInt("number")));
        b1 = new Book("TEST1", "Java", "Trần Văn", "Bê", "Lập trình","Sách cũ", 2000, 3);
        
        
    }
    
    //TEST HAM XOA SACH DUA VAO ID SACH
    @Test
    public void testDeleteBookByInvalidId() throws Exception{
        Boolean flag;
        try {
            flag = dao.deleteBook("1");
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
}
