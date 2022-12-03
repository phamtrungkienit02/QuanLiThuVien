/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ptk.pojo;

/**
 *
 * @author Kien
 */
public class Book {
    private String book_id ,name, surname_author, name_author, category, status;
    private int year, number;

    public Book() {
    }

    public Book(String book_id, String name, String surname_author, String name_author, String category, String status, int year, int number) {
        this.book_id = book_id;
        this.name = name;
        this.surname_author = surname_author;
        this.name_author = name_author;
        this.category = category;
        this.status = status;
        this.year = year;
        this.number = number;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname_author() {
        return surname_author;
    }

    public void setSurname_author(String surname_author) {
        this.surname_author = surname_author;
    }

    public String getName_author() {
        return name_author;
    }

    public void setName_author(String name_author) {
        this.name_author = name_author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    
    
}
