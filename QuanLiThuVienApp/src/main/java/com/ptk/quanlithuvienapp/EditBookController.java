/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ptk.quanlithuvienapp;

import com.ptk.services.BookDao;
import com.ptk.services.MessageLogin;
import com.ptk.services.DataValidator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;

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
    
    @FXML private void saveBook() throws IOException, Exception {
        StringBuilder sb = new StringBuilder();
        DataValidator.validateEmptyAndSpace(txtBookId, "Mã sách không được để trống", sb);
        DataValidator.validateEmpty(txtBook, "Tên sách không được để trống", sb);
        DataValidator.validateEmpty(txtYear, "Năm xuất bản không được để trống", sb);
        DataValidator.validateEmpty(txtSurname, "Họ tác giả không được để trống", sb);
        DataValidator.validateEmptyAndSpace(txtName, "Tên tác giả không được để trống", sb);
        DataValidator.validateEmptyAndSpace(txtNumber, "Số lượng sách không được để trống", sb);
        DataValidator.validateEmpty(txtCategory, "Thể loại sách không được để trống", sb);  
        
        if(sb.length() > 0){
            MessageLogin.showErrorLogin("ERROR", sb.toString());
            return;
        }
        Book b = new Book();
        if(rdbStatus1.isSelected() && rdbStatus2.isSelected()){
            MessageLogin.showErrorLogin("ERROR", "Không được chọn 2 trường một lúc");
        }
        else if (rdbStatus1.isSelected()){
           b.setStatus("Sách mới");
        }
            else  {
            b.setStatus("Sách cũ");
        }
                        
            
        //kiem tra viec nhap du lieu
        try {
            b.setBook_id(txtBookId.getText());
            b.setName(txtBook.getText());
            b.setYear(Integer.parseInt(txtYear.getText()));
            b.setSurname_author(txtSurname.getText());
            b.setName_author(txtName.getText());
            b.setNumber(Integer.parseInt(txtNumber.getText()));
            b.setCategory(txtCategory.getText());
            
            BookDao dao = new BookDao();
            //True: co du lieu
            if(dao.insertBook(b)) {
                MessageLogin.showMessageLogin("Thông báo", "Sách đã được thêm thành công!!!");
                showBooks();
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
        DataValidator.validateEmptyAndSpace(txtBookId, "Mã sách không được để trống", sb);
        DataValidator.validateEmpty(txtBook, "Tên sách không được để trống", sb);
        DataValidator.validateEmpty(txtYear, "Năm xuất bản không được để trống", sb);
        DataValidator.validateEmpty(txtSurname, "Họ tác giả không được để trống", sb);
        DataValidator.validateEmptyAndSpace(txtName, "Tên tác giả không được để trống", sb);
        DataValidator.validateEmptyAndSpace(txtNumber, "Số lượng sách không được để trống", sb);
        DataValidator.validateEmpty(txtCategory, "Thể loại sách không được để trống", sb);  
        
        if(sb.length() > 0){
            MessageLogin.showErrorLogin("ERROR", sb.toString());
            return;
        }
        Book b = new Book();
        if(rdbStatus1.isSelected() && rdbStatus2.isSelected()){
            MessageLogin.showErrorLogin("ERROR", "Không được chọn 2 trường một lúc");
        }
        else if (rdbStatus1.isSelected()){
           b.setStatus("Sách mới");
        }
            else  {
            b.setStatus("Sách cũ");
        }
                        
            
        //kiem tra viec nhap du lieu
        try {
            b.setBook_id(txtBookId.getText());
            b.setName(txtBook.getText());
            b.setYear(Integer.parseInt(txtYear.getText()));
            b.setSurname_author(txtSurname.getText());
            b.setName_author(txtName.getText());
            b.setNumber(Integer.parseInt(txtNumber.getText()));
            b.setCategory(txtCategory.getText());
            
            
            BookDao dao = new BookDao();
            //True: co du lieu
            if(dao.updateBook(b)) {
                MessageLogin.showMessageLogin("Thông báo", "Cập nhập sách thành công!!!");
                showBooks();
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
        DataValidator.validateEmptyAndSpace(txtBookId, "Mã sách không được để trống", sb);
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
                showBooks();
            }else {
                MessageLogin.showErrorLogin("ERROR", "Không Xóa được sách do lỗi");
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
    
    
    
    public void showBooks() throws Exception  {
        BookDao dao = new BookDao();
        ObservableList<Book> list = dao.loadBook1();
        
        
        colId.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        colBook.setCellValueFactory(new PropertyValueFactory<>("name"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("surname_author"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name_author"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));      
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
      
        tbvBook.setItems(list);
//       // tbvBook.a
//        
    }
            //    // xu li nap thong tin len textField khi an vao tung hang
    @FXML private void getItem(){
        Integer index = tbvBook.getSelectionModel().getSelectedIndex();
        if (index < 0){
            return;
        }
       
        txtBookId.setText(colId.getCellData(index).toString());
        txtBook.setText(colBook.getCellData(index).toString());
        txtYear.setText(colYear.getCellData(index).toString());
        txtSurname.setText(colSurname.getCellData(index).toString());
        txtName.setText(colName.getCellData(index).toString());
        txtNumber.setText(colNumber.getCellData(index).toString());
        txtCategory.setText(colCategory.getCellData(index).toString());
       //
       //outPut.setText(colName.getCellData(index).toString());
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
              showBooks();
          } catch (Exception ex) {
              Logger.getLogger(EditBookController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }    
    
}
