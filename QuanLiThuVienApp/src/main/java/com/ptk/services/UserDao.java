/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ptk.services;

import com.ptk.pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Kien
 */
public class UserDao {

  
    public User checkLogin(String userName, String passWord) throws Exception{
        String sql = "SELECT userName, passWord, active FROM user"
                + "WHERE userName=? AND matKhau=?";
        try(
                Connection conn = JdbcUtils.getConn();
                PreparedStatement pstmt = conn.prepareStatement(sql);
           ){
            pstmt.setString(1, userName);
            pstmt.setString(2, userName);
            // thuc hien cau truy van ben tren
            try(ResultSet rs = pstmt.executeQuery();){
                //co thong tin
                if (rs.next()) {
                    User u1 = new User();
                    u1.setUserName(userName);
                    u1.setActive(rs.getString("active"));
                    //khong thiet lap mat khau de dam bao an toan
                    return u1;
                }
            }
        }
        // dang nhap that bai tra ve null
        return null;
    }
}
