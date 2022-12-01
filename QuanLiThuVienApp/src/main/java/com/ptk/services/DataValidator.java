/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ptk.services;


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
   public static void validateEmpty(TextField field, String errorMessage, StringBuilder sb){       
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
    public static void checkLength(PasswordField field, String errorMessage, StringBuilder sb){
    //   String password = new String(field.getText());
       if (field.getText().length() > 9){
           //field.setBackground(Background b.Color.red);
           sb.append(errorMessage).append("\n");
           field.requestFocus();
       }
    }
}
