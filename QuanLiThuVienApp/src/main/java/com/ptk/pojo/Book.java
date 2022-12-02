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
    private String name, surname_author, name_author, category;
    private int year, number, status;

    public Book() {
    }

    public Book(String name, String surname_author, String name_author, int status, String category, int year, int number) {
        this.name = name;
        this.surname_author = surname_author;
        this.name_author = name_author;
        this.status = status;
        this.category = category;
        this.year = year;
        this.number = number;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
