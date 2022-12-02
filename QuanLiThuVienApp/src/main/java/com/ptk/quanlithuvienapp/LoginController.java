/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ptk.quanlithuvienapp;

import com.ptk.pojo.User;
import com.ptk.services.DataValidator;
import com.ptk.services.MessageLogin;
import com.ptk.services.UserDao;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class LoginController implements Initializable {
    @FXML private TextField txtUser;
    @FXML private PasswordField txtPass;
    @FXML private Label lbError;
  
  
    @FXML private void switchToPrimary() throws IOException {
        StringBuilder sb = new StringBuilder();
        String err1 = "username không được để trống hoặc có khoảng trắng";
        String err2 = "Password không được để trống hoặc có khoảng trắng";
        String err3 = "Username hay password sai";
        String err4 = "Password tối đa 9 kí tự";
        String err5 = "Không nhập kí tự đặc biệt";
        
        DataValidator.validateEmpty(txtUser, err1, sb);
        DataValidator.passEmpty(txtPass, err2, sb);
        DataValidator.checkLength(txtPass, err4, sb);
        DataValidator.checkSpecialChar(txtUser, txtPass, err5, sb);
        //co thong bao
        if (sb.length() > 0){
            MessageLogin.showErrorLogin("ERROR", sb.toString());
            lbError.setTextFill(Color.RED);
            lbError.setText(sb.toString());
            return;
        }
        else 
            lbError.setText("");
       
        UserDao dao = new UserDao();
        try {
            User u1 = dao.checkLogin(txtUser.getText(), txtPass.getText());
            if (u1 == null){
                MessageLogin.showErrorLogin("ERROR", err3);
            }
            else{
                App.setRoot("primary");
                MessageLogin.showIndex("Đăng nhập thành công", "WELCOME TO LIBRARY MANAGER ");
            }
                
        } catch(Exception e){
            e.printStackTrace();
            MessageLogin.showErrorLogin("ERROR", e.getMessage());
        }
     
        
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
