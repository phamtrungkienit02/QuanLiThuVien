/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ptk.services;


import java.util.Calendar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//import javafx.scene.layout.Background;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.Paint;
//import javafx.​scene.​layout.​Region;



/**
 *
 * @author Kien
 */
public class DataValidator {
   public static void validateEmptyAndSpace(TextField field, String errorMessage, StringBuilder sb){       
       if (field.getText().contains(" ") ||  field.getText().equals("")){
           //field.setBackground(Background b.Color.red);
           sb.append(errorMessage).append("\n");
            // tro con tro chuot den vung loi
           field.requestFocus();
       }
    }
   public static void passEmpty(PasswordField field, String errorMessage, StringBuilder sb){
    //   String password = new String(field.getText());
       if (field.getText().contains(" ") ||  field.getText().equals("")){
           //field.setBackground(Background b.Color.red);
           sb.append(errorMessage).append("\n");
           field.requestFocus();
       }
    }
   public static void checkLengthPass(PasswordField field, String errorMessage, StringBuilder sb, int n){
    //   String password = new String(field.getText(Pass));
       if (field.getText().length() > n){
           //field.setBackground(Background b.Color.red);
           sb.append(errorMessage).append("\n");
           field.requestFocus();
       }
    }
   public static void checkSpecialChar(TextField field1,PasswordField field2, String errorMessage, StringBuilder sb){
        String u = field1.getText();
        String p = field2.getText();
        for(int i = 0; i < u.length(); i++){
            if (((int)u.charAt(i) < 48 && (int)u.charAt(i) > 32) || 
                ((int)u.charAt(i) > 57 && (int)u.charAt(i) < 65) ||
                ((int)u.charAt(i) > 90 && (int)u.charAt(i) < 97) ||
                  (int)u.charAt(i) > 123){
                sb.append(errorMessage).append("\n");          
                return;
            }
        }
        for(int i = 0; i < p.length(); i++){
            if (((int)p.charAt(i) < 48 && (int)p.charAt(i) > 32) || 
                ((int)p.charAt(i) > 57 && (int)p.charAt(i) < 65) ||
                ((int)p.charAt(i) > 90 && (int)p.charAt(i) < 97) ||
                 (int)p.charAt(i) > 123){
                sb.append(errorMessage).append("\n");          
                return;
            }
        }
    }
   public static void validateEmpty(TextField field, String errorMessage, StringBuilder sb){       
       if (field.getText().equals("")){
           //field.setBackground(Background b.Color.red);
           sb.append(errorMessage).append("\n");
            // tro con tro chuot den vung loi
           field.requestFocus();
       }
    } 
    public static void checkYearValid(TextField field, String errorMessage, StringBuilder sb){
       Calendar instance = Calendar.getInstance();
       int year = instance.get(Calendar.YEAR);
       if (field.getText().length() > 4 || Integer.parseInt(field.getText()) < 1900 || Integer.parseInt(field.getText()) > year){
           sb.append(errorMessage).append("\n");
           field.requestFocus();
       }
    }
    public static void checkNumber(TextField field, String errorMessage, StringBuilder sb){
        if (Integer.parseInt(field.getText()) < 1 || Integer.parseInt(field.getText()) > 50){
           sb.append(errorMessage).append("\n");
           field.requestFocus();
       }
    }
}