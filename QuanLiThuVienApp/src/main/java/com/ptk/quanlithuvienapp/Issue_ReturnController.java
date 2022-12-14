/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ptk.quanlithuvienapp;

import com.ptk.services.JdbcUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Nguyen Linh Nhi
 */
public class Issue_ReturnController implements Initializable {
    @FXML private Text IDBook;
    @FXML private Text BookName;
    @FXML private Text Author;
    @FXML private Text Year;
    @FXML private Text BookStatus;
    @FXML private Text Num;
    @FXML private Text IDStudent;
    @FXML private Text StudentName;
    @FXML private Text Address;
    @FXML private Text Phone;
    @FXML private Text Email;
    @FXML private TextField bookIDInput;
    @FXML private TextField studentIDInput;
    @FXML private DatePicker ReturnDate;
    @FXML private Text borrowBook_num;
    @FXML private TextField CardID;
    @FXML private ListView<String> returnDataList;
    @FXML private ComboBox<String> cbb;
    @FXML private Text Money;
    @FXML private Text oldStat;
    @FXML private Text rBookID;
    @FXML private Text end_date;
    
    Boolean isReadyForReturn = false;
    
    /**
     * Initializes the controller class.
     */
    ObservableList<String> cbbList = FXCollections.observableArrayList("S??ch m???i","S??ch c??", "S??ch c?? h??n", "S??ch h???ng");
    
    @FXML private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbb.setItems(cbbList);
    }    
    
    @FXML 
    private void LoadBookInfo(ActionEvent ev) throws SQLException
    {
        String id = bookIDInput.getText();
        
        String sql = "SELECT * FROM book WHERE book_id like ?";
        
        try (Connection conn = JdbcUtils.getConn())
        {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + id + "%" );
            
            Boolean flag = false;
            
            ResultSet rs = stm.executeQuery();
            while (rs.next())
            {
                IDBook.setText(rs.getString("book_id"));
                BookName.setText(rs.getString("name"));
                Author.setText(rs.getString("surname_author") + " " + rs.getString("name_author"));
                Year.setText(rs.getString("year"));
                Num.setText(rs.getString("number"));
                BookStatus.setText(rs.getString("status"));
                flag = true;
            }
            if (!flag)
            {
                IDBook.setText("ID kh??ng t???n t???i.");
                BookName.setText("S??ch n??y kh??ng t???n t???i.");
                Year.setText(" ");
                Author.setText(" ");
                BookStatus.setText(" ");
                Num.setText(" ");
            }
        }
    }
    
    @FXML 
    private void LoadStudentInfo(ActionEvent ev) throws SQLException
    {
        String id = studentIDInput.getText();
        
        String sql = "SELECT * FROM student WHERE student_id like ?";
        
        try (Connection conn = JdbcUtils.getConn())
        {
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + id + "%" );
            
            Boolean flag = false;
            
            ResultSet rs = stm.executeQuery();
            while (rs.next())
            {
                IDStudent.setText(rs.getString("student_id"));
                StudentName.setText(rs.getString("surname") + " " + rs.getString("name"));
                Address.setText(rs.getString("address"));
                Phone.setText(rs.getString("phone"));
                Email.setText(rs.getString("email"));
                flag = true;
            }
            if (!flag)
            {
                IDStudent.setText("ID kh??ng t???n t???i.");
                StudentName.setText("Sinh vi??n n??y kh??ng t???n t???i.");
                Email.setText(" ");
                Address.setText(" ");
                Phone.setText(" ");
            }
        }
    }

    @FXML 
    private void LoadBorrowFunc(ActionEvent ev) throws SQLException, Exception
    {
        String bookID = IDBook.getText();
        String studentID = IDStudent.getText();
        LocalDate date = ReturnDate.getValue();
        LocalDate curdate = LocalDate.now();
        String status = BookStatus.getText();

        if(curdate.plusDays(6).isBefore(date) && curdate.plusMonths(3).plusDays(1).isAfter(date))
        {
            String format = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String sql = "SELECT * FROM borrow WHERE student_id = " + studentID;
            
            try (Connection conn = JdbcUtils.getConn())
            {
                PreparedStatement stm = conn.prepareStatement(sql);
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next())
                    borrowBook_num.setText(rs.getString("borrowBook_num"));

            }
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("C???nh b??o!!!");
            alert.setHeaderText(null);
            alert.setContentText("B???n c?? ch???c ch???n mu???n cho sinh vi??n " + StudentName.getText() + "\n m?????n s??ch " + BookName.getText() + " ?" ) ;

            Optional<ButtonType> respond = alert.showAndWait();
            int storedBookNumber = Integer.parseInt(Num.getText());
            int borrow_num = Integer.parseInt(borrowBook_num.getText());
            if(respond.get() == ButtonType.OK)
            {
                if (storedBookNumber <= 0 )
                {
                    Alert err = new Alert(Alert.AlertType.ERROR);
                    err.setTitle("Th???t b???i!");
                    err.setHeaderText(null);
                    err.setContentText("Kh??ng th??? m?????n s??ch do kh??ng ????? s??? l?????ng.");
                    err.showAndWait();
                }
                else if(borrow_num >= 5)
                {
                    Alert err = new Alert(Alert.AlertType.ERROR);
                    err.setTitle("Th???t b???i!");
                    err.setHeaderText(null);
                    err.setContentText("Kh??ng th??? m?????n s??ch. Vui l??ng tr??? l???i s??ch tr?????c khi m?????n th??m.");
                    err.showAndWait();
                }
                else 
                {
                    try (Connection conn = JdbcUtils.getConn())
                    {
                        Statement stm = (Statement) conn.createStatement();
                        String str = "INSERT INTO borrow(student_id, book_id,create_date,end_date, borrow_status) VALUES ( "+ "'" + studentID + "'," +" '" + bookID + "'," + "current_timestamp()" + ",'" + format + "'," + status + "')" ;
                        stm.executeUpdate(str);
                        String str2 = "update borrow set borrowBook_num = " + (borrow_num + 1) + " where student_id = " + "'" + studentID + "' order by id desc limit 1";
                        stm.executeUpdate(str2);
                        String str1 = "UPDATE book SET number = (number - 1) WHERE book_id = '" + bookID + "'";
                        stm.executeUpdate(str1);

                    }
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert1.setTitle("Th??nh c??ng!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("M?????n s??ch th??nh c??ng!");

                    alert1.showAndWait();
                }
            }
        }
        else if (!(curdate.plusDays(6).isBefore(date) && curdate.plusMonths(3).plusDays(1).isAfter(date)))
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("C???nh b??o!");
            alert1.setHeaderText(null);
            alert1.setContentText("Ph???i ch???n ng??y tr??? s??ch sau ng??y m?????n 1 tu???n v?? kh??ng ???????c qu?? 3 th??ng.");
                
            alert1.showAndWait();
        }
        
        else
        {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Tho??t");
            alert1.setHeaderText(null);
            alert1.setContentText("H???y m?????n s??ch.");
                
            alert1.showAndWait();
        }
    }
    
    @FXML
    private void LoadBookForReturn(ActionEvent ev) throws SQLException
    {
        ObservableList<String> borrowData = FXCollections.observableArrayList();
        
        isReadyForReturn = false;
        String id = CardID.getText();
        
        String sql = "SELECT * FROM borrow WHERE id = '" + id + "'";
        try (Connection conn = JdbcUtils.getConn())
        {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery(sql);
            
            while (rs.next())
            {
                String rCardID = id;
                String rStudentID = rs.getString("student_ID");
                String rbookID = rs.getString("book_id");
                Timestamp rBorrowTime = rs.getTimestamp("create_date");
                
                borrowData.add("Ng??y m?????n s??ch: " +rBorrowTime.toGMTString());
                
                borrowData.add("==========Th??ng tin m?????n s??ch==========");
                sql = "SELECT * FROM borrow WHERE id = '" + rCardID + "'";
                rs = stm.executeQuery(sql);
                while(rs.next())
                {
                    borrowData.add("Ng??y tr??? ???? ch???n: " + rs.getString(("end_date")));
                    borrowData.add("S??? s??ch ???? m?????n: " + rs.getString("borrowBook_num")); 
                    borrowData.add("Status: " + rs.getString("borrow_status"));
                    
                }
                
                sql = "SELECT * FROM book WHERE book_id = '" + rbookID + "'";
                rs = stm.executeQuery(sql);
                borrowData.add("==========Th??ng tin s??ch==========");
                while(rs.next())
                {
                    borrowData.add("M?? s??ch: " + rs.getString("book_id"));
                    borrowData.add("T??n s??ch: " + rs.getString("name"));
                    borrowData.add("Lo???i s??ch: " + rs.getString("category"));
                    
                }
                
                sql = "SELECT * FROM student WHERE student_id = '" + rStudentID + "'";
                rs = stm.executeQuery(sql);
                borrowData.add("==========Th??ng tin sinh vi??n==========");
                while(rs.next())
                {
                    borrowData.add("M?? sinh vi??n: " + rs.getString("student_id"));
                    borrowData.add("H??? t??n sinh vi??n: " + rs.getString("surname") + " " + rs.getString("name"));
                    borrowData.add("Khoa: " + rs.getString("faculty"));
                    borrowData.add("Email: " + rs.getString("email"));
                    borrowData.add("S??? ??i???n tho???i: " + rs.getString("phone"));
                }  
                isReadyForReturn = true;
            } 
        }
        returnDataList.getItems().setAll(borrowData);
    }
    
    @FXML
    private void LoadReturnFunc(ActionEvent ev) throws SQLException
    {
        if(!isReadyForReturn)
            return;
        
        int moneyTorn = 0, moneyLate = 0;
        String id = CardID.getText();
        String newStatus = cbb.getValue();
        
        LocalDate returnDate = LocalDate.now();
        
        String sql = "SELECT * FROM borrow WHERE id = " + id ;     
        try (Connection conn = JdbcUtils.getConn())
        {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next())
            {
                oldStat.setText(rs.getString("borrow_status"));
                rBookID.setText(rs.getString("book_id"));
            }
        }
        String returnBookID = rBookID.getText();
        String oldStatus = oldStat.getText();
       
        if(!newStatus.isEmpty() && !newStatus.equals(oldStatus) && newStatus.equals("S??ch c??"))
        {
            try (Connection conn = JdbcUtils.getConn())
                {
                    Statement stm = (Statement) conn.createStatement();
                    String str = "UPDATE borrow SET return_status =  'S??ch c??' WHERE id = " + id ;
                    stm.executeUpdate(str);
                }
            moneyTorn = 10000;
        }
        else if (!newStatus.isEmpty() && !newStatus.equals(oldStatus) && newStatus.equals("S??ch c?? h??n"))
        {
            try (Connection conn = JdbcUtils.getConn())
                {
                    Statement stm = (Statement) conn.createStatement();
                    String str = "UPDATE borrow SET return_status =  'S??ch c?? h??n' WHERE id = " + id ;
                    stm.executeUpdate(str);
                }
            moneyTorn = 10000;
        }
        
        else if(!newStatus.isEmpty() && !newStatus.equals(oldStatus) && newStatus.equals("S??ch h???ng"))
        {
            try (Connection conn = JdbcUtils.getConn())
                {
                    Statement stm = (Statement) conn.createStatement();
                    String str = "UPDATE borrow SET return_status =  'S??ch h???ng' WHERE id = " + id;
                    stm.executeUpdate(str);
                }
            moneyTorn = 15000;
        }
        else if(!newStatus.equals(oldStatus))
            moneyTorn = 0;
        
        String sql1 = "SELECT * FROM borrow WHERE id = " + id ;     
        try (Connection conn = JdbcUtils.getConn())
        {
            PreparedStatement stm = conn.prepareStatement(sql1);
            ResultSet rs = stm.executeQuery(sql1);
            while (rs.next())
                end_date.setText(rs.getString("end_date"));
            
        }
        String e_date = end_date.getText();
        LocalDate eDate = LocalDate.parse(e_date);
        
        if(!returnDate.equals(e_date))
        {
            Period p = Period.between(returnDate, eDate);
            moneyLate = p.getDays() * 5000;
        }
        else 
            moneyLate = 0;
        
        String MoneyFinal = Integer.toString(moneyTorn + moneyLate);
        Money.setText(MoneyFinal);
        
        try (Connection conn = JdbcUtils.getConn())
            {
                Statement stm = (Statement) conn.createStatement();
                String str = "UPDATE borrow SET money = '" + MoneyFinal + "', return_date = current_timestamp(), borrowBook_num = borrowBook_num - 1 WHERE id = " + id ;
                stm.executeUpdate(str);
                String str1 = "UPDATE book SET number = number + 1 where book_id = '" + returnBookID + "'"; 
                stm.executeUpdate(str1);
            }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Optional<ButtonType> respond = alert.showAndWait();
        if(respond.get() == ButtonType.OK)
        {
            alert.setTitle("Th??ng b??o!!");
            alert.setHeaderText(null);
            alert.setContentText("???? c???p nh???t tr??? s??ch th??nh c??ng");
                
            alert.showAndWait();
        }
    }
}
