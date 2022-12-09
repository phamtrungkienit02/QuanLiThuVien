/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ptk.quanlithuvienapp;

import com.ptk.services.BookDao;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Nguyen Linh Nhi
 */
public class SearchBookController implements Initializable {
    @FXML private TableView<Book> listbook;
    @FXML private TextField txtKeyWord;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.LoadTableView();
        try {
            this.LoadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(SearchBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.txtKeyWord.textProperty().addListener((ev) ->{
            try 
            {
                this.LoadTableData(this.txtKeyWord.getText());
            } catch (SQLException ex)
            {
                Logger.getLogger(SearchBookController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private void LoadTableView()
    {
        TableColumn colID = new TableColumn("Id");
        colID.setCellValueFactory(new PropertyValueFactory("book_id"));
        colID.setPrefWidth(105);
        
        TableColumn colName = new TableColumn("Book name");
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colName.setPrefWidth(105);
        
        TableColumn colSurname = new TableColumn("Author surname");
        colSurname.setCellValueFactory(new PropertyValueFactory("surname_author"));
        colSurname.setPrefWidth(105);
        
        TableColumn colAuthor = new TableColumn("Author name");
        colAuthor.setCellValueFactory(new PropertyValueFactory("name_author"));
        colAuthor.setPrefWidth(105);
        
        TableColumn colCate = new TableColumn("Category");
        colCate.setCellValueFactory(new PropertyValueFactory("category"));
        colCate.setPrefWidth(105);
        
        TableColumn colStatus = new TableColumn("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory("status"));
        colStatus.setPrefWidth(105);
        
        TableColumn colYear = new TableColumn("Year");
        colYear.setCellValueFactory(new PropertyValueFactory("year"));
        colYear.setPrefWidth(105);
        
        TableColumn colNum = new TableColumn("Number");
        colNum.setCellValueFactory(new PropertyValueFactory("number"));
        colNum.setPrefWidth(105);
        
        this.listbook.getColumns().addAll(colID, colName, colSurname, colAuthor, colCate, colStatus, colYear, colNum);
    }
    
    public void LoadTableData (String kw) throws SQLException 
    {
        BookDao b = new BookDao();
        
        this.listbook.setItems(FXCollections.observableList(b.getBook(kw)));
    }
    
    @FXML private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
