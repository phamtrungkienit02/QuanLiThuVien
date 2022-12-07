/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.ptk.quanlithuvienapp;

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
public class BookClassTester {
    
    public BookClassTester() {
    }

 
     

    @Test
    public void testGetBook_id() {
        Book instance = new Book();
        String expResult = null;
        String result = instance.getBook_id();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetBook_id() {
        String book_id = "JAVA1";
        String expected = "JAVA1";
        Book instance = new Book();
        instance.setBook_id(book_id);
        assertEquals(expected, instance.getBook_id());
    }


    @Test
    public void testGetName() {
        Book instance = new Book();
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetName() {
        String name = "Sách dạy lập trình Java";
        String expected = "Sách dạy lập trình Java";
        Book instance = new Book();
        instance.setName(name);
        assertEquals(expected, instance.getName());
    }

    @Test
    public void testGetSurname_author() {
        Book instance = new Book();
        String expResult = null;
        String result = instance.getSurname_author();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetSurname_author() {
        String surname_author = "Trần Văn";
        String expected = "Trần Văn";
        Book instance = new Book();
        instance.setSurname_author(surname_author);
        assertEquals(expected, instance.getSurname_author());
    }

    @Test
    public void testGetName_author() {
        Book instance = new Book();
        String expResult = null;
        String result = instance.getName_author();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetName_author() {
        String name_author = "An";
        String expected = "An";
        Book instance = new Book();
        instance.setName_author(name_author);
        assertEquals(expected, instance.getName_author());
    }

    @Test
    public void testGetCategory() {
        Book instance = new Book();
        String expResult = null;
        String result = instance.getCategory();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetCategory() { 
        String category = "Lập trình";
        String expected = "Lập trình";
        Book instance = new Book();
        instance.setCategory(category);
        assertEquals(expected, instance.getCategory());
    }

    @Test
    public void testGetStatus() {
        Book instance = new Book();
        String expResult = null;
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }
  
   @Test
    public void testSetStatus() {
        String status = "Sách cũ";
        String expected = "Sách cũ";
        Book instance = new Book();
        instance.setStatus(status);
        assertEquals(expected, instance.getStatus());
    }

    
    @Test
    public void testGetYear() {
        Book instance = new Book();
        int expResult = 0;
        int result = instance.getYear();
        assertEquals(expResult, result);
      
    }

    @Test
    public void testSetYear() {
        int year = 2000;
        int expResult = 2000;
        Book instance = new Book();
        instance.setYear(year);
        int result = instance.getYear();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNumber() {
        Book instance = new Book();
        int expResult = 0;
        int result = instance.getNumber();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetNumber() {
        int number = 5;
        int expResult = 5;
        Book instance = new Book();
        instance.setNumber(number);
        int result = instance.getNumber();
        assertEquals(expResult, result);
        
    }
    
}
