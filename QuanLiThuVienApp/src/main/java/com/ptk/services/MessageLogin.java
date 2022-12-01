/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ptk.services;

import javafx.scene.control.Alert;




/**
 *
 * @author Kien
 */
public class MessageLogin {
    public static void showMessageLogin(String content, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText("INFORMATION MESSAGE");
        alert.showAndWait();
    }
    public static void showErrorLogin(String content, String title){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText("ERROR MESSAGE");
        alert.showAndWait();
    }
    public static void showConfirmLogin(String content, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        //alert.setHeaderText("INFORMATION MESSAGE");
//        if(result.isPresent() && result.get() == ButtonType.OK){
//            
//        }
        alert.showAndWait();
    }
}
