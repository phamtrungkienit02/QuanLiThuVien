/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ptk.quanlithuvienapp;

import com.ptk.services.DataValidator;
import com.ptk.services.MessageLogin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class LoginController implements Initializable {
    @FXML private TextField txtUser;
    @FXML private PasswordField txtPass;
  
    @FXML private void switchToLogin() throws IOException {
        StringBuilder sb = new StringBuilder();
        String err1 = "Tên đăng nhập không được để trống hoặc có khoảng trắng";
        String err2 = "Mật khẩu không được để trống hoặc có khoảng trắng";

        DataValidator.validateEmpty(txtUser, err1, sb);
        DataValidator.passEmpty(txtPass, err2, sb);
        //co thong bao
        if (sb.length() > 0){
            MessageLogin.showErrorLogin("", sb.toString());
            return;
        }
        else App.setRoot("primary");
        
        
        
//        if( txtUser.getText().contains(" ") ||  txtUser.getText().equals("")){
////            App.setRoot("secondary");
//            MessageLogin.showErrorLogin("trong", "khoang trang");
//            txtUser.requestFocus();
            
        
        //else App.setRoot("primary");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
