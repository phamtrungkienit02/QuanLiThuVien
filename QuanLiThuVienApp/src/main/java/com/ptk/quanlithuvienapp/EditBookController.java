/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ptk.quanlithuvienapp;

import com.ptk.pojo.Book;
import com.ptk.services.BookDao;
import com.ptk.services.MessageLogin;
import com.ptk.services.DataValidator;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Kien
 */
public class EditBookController implements Initializable {
    @FXML private TextField txtBookId;
    @FXML private TextField txtBook;
    @FXML private TextField txtYear;
    @FXML private TextField txtSurname;
    @FXML private TextField txtName;
    @FXML private TextField txtNumber;
    @FXML private TextField txtCategory;
    @FXML private RadioButton rdbStatus1;
    @FXML private RadioButton rdbStatus2;
    @FXML private TableView tbvBook;
   
    
    @FXML private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML private void newBook() throws IOException {
    txtBookId.setText("");
    txtBook.setText("");
    txtYear.setText("");
    txtSurname.setText("");
    txtName.setText("");
    txtNumber.setText("");
    txtCategory.setText("");
    }
    
    @FXML private void saveBook() throws IOException {
        StringBuilder sb = new StringBuilder();
        DataValidator.validateEmpty(txtBookId, "Mã sách không được để trống", sb);
       // DataValidator.validateEmpty(txtBook, "Tên sách không được để trống", sb);
       // DataValidator.validateEmpty(txtYear, "Năm xuất bản không được để trống", sb);
        //DataValidator.validateEmpty(txtSurname, "Họ tác giả không được để trống", sb);
        DataValidator.validateEmpty(txtName, "Tên tác giả không được để trống", sb);
        DataValidator.validateEmpty(txtNumber, "Số lượng sách không được để trống", sb);
        //DataValidator.validateEmpty(txtCategory, "Thể loại sách không được để trống", sb);  
        
        if(sb.length() > 0){
            MessageLogin.showErrorLogin("ERROR", sb.toString());
            return;
        }
        //kiem tra viec nhap du lieu
        try {
            Book b = new Book();
            b.setBook_id(txtBookId.getText());
            b.setName(txtBook.getText());
            b.setYear(Integer.valueOf(txtYear.getText()));
            b.setSurname_author(txtSurname.getText());
            b.setName_author(txtName.getText());
            b.setNumber(Integer.valueOf(txtNumber.getText()));
            b.setCategory(txtCategory.getText());
            b.setStatus(rdbStatus1.isSelected()?"Sách mới":"Sách cũ");
            
            BookDao dao = new BookDao();
            //True: co du lieu
            if(dao.insertBook(b)) {
                MessageLogin.showMessageLogin("Thông báo", "Sách đã được thêm thành công!!!");
            }else {
                MessageLogin.showErrorLogin("ERROR", "Sách không không được thêm do lỗi");
            }
        }catch (Exception e){
            //in thong tin loiERROR
            e.printStackTrace();
            MessageLogin.showErrorLogin("", e.getMessage());
        }
    }
    
    @FXML private void updateBook() throws IOException {
        StringBuilder sb = new StringBuilder();
        DataValidator.validateEmpty(txtBookId, "Mã sách không được để trống", sb);
        //DataValidator.validateEmpty(txtBook, "Tên sách không được để trống", sb);
        //DataValidator.validateEmpty(txtYear, "Năm xuất bản không được để trống", sb);
        //DataValidator.validateEmpty(txtSurname, "Họ tác giả không được để trống", sb);
        DataValidator.validateEmpty(txtName, "Tên tác giả không được để trống", sb);
        DataValidator.validateEmpty(txtNumber, "Số lượng sách không được để trống", sb);
        //DataValidator.validateEmpty(txtCategory, "Thể loại sách không được để trống", sb);  
        
        if(sb.length() > 0){
            MessageLogin.showErrorLogin("ERROR", sb.toString());
            return;
        }
        //kiem tra viec nhap du lieu
        try {
            Book b = new Book();
            b.setBook_id(txtBookId.getText());
            b.setName(txtBook.getText());
            b.setYear(Integer.valueOf(txtYear.getText()));
            b.setSurname_author(txtSurname.getText());
            b.setName_author(txtName.getText());
            b.setNumber(Integer.valueOf(txtNumber.getText()));
            b.setCategory(txtCategory.getText());
            b.setStatus(rdbStatus1.isSelected()?"Sách mới":"Sách cũ");
            
            BookDao dao = new BookDao();
            //True: co du lieu
            if(dao.updateBook(b)) {
                MessageLogin.showMessageLogin("Thông báo", "Cập nhập sách thành công!!!");
            }else {
                MessageLogin.showErrorLogin("ERROR", "Không cập nhập được thêm do lỗi");
            }
        }catch (Exception e){
            //in thong tin loiERROR
            e.printStackTrace();
            MessageLogin.showErrorLogin("", e.getMessage());
        }
    }
    
    @FXML  private void deleteBook() throws IOException {
        StringBuilder sb = new StringBuilder();
        DataValidator.validateEmpty(txtBookId, "Mã sách không được để trống", sb);
        if(sb.length() > 0){
            MessageLogin.showErrorLogin("ERROR", sb.toString());
            return;
        }
        
        //kiem tra viec nhap du lieu
        try {
            BookDao dao = new BookDao();
            //True: co du lieu
            if(dao.deleteBook(txtBookId.getText())) {
                MessageLogin.showMessageLogin("Thông báo", "Xóa sách thành công!!!");
            }else {
                MessageLogin.showErrorLogin("ERROR", "Không Xóa được thêm do lỗi");
            }
        }catch (Exception e){
            //in thong tin loiERROR
            e.printStackTrace();
            MessageLogin.showErrorLogin("", e.getMessage());
        }
    }  
    
    @FXML private TableColumn<?,?> colId;
    @FXML private TableColumn<?,?> colBook;
    @FXML private TableColumn<?,?> colName;
    @FXML private TableColumn<?,?> colSurname;
    @FXML private TableColumn<?,?> colNumber;
    @FXML private TableColumn<?, ?> colCategory;
    @FXML private TableColumn<?, ?> colStatus;
    @FXML private TableColumn<?,?> colYear;
    
    private void loadBook(){
        try{
            BookDao dao = new BookDao();
            List<Book> list = dao.loadBook();
            
            for (Book b:list){
                
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            MessageLogin.showErrorLogin("", e.getMessage());

        }
    }
            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
