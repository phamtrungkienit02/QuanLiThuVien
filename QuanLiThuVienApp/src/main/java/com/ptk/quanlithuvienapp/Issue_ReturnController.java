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
    ObservableList<String> cbbList = FXCollections.observableArrayList("Sách mới","Sách cũ", "Sách cũ hơn", "Sách hỏng");
    
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
                IDBook.setText("ID không tồn tại.");
                BookName.setText("Sách này không tồn tại.");
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
                IDStudent.setText("ID không tồn tại.");
                StudentName.setText("Sinh viên này không tồn tại.");
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
            alert.setTitle("Cảnh báo!!!");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn cho sinh viên " + StudentName.getText() + "\n mượn sách " + BookName.getText() + " ?" ) ;

            Optional<ButtonType> respond = alert.showAndWait();
            int storedBookNumber = Integer.parseInt(Num.getText());
            int borrow_num = Integer.parseInt(borrowBook_num.getText());
            if(respond.get() == ButtonType.OK)
            {
                if (storedBookNumber <= 0 )
                {
                    Alert err = new Alert(Alert.AlertType.ERROR);
                    err.setTitle("Thất bại!");
                    err.setHeaderText(null);
                    err.setContentText("Không thể mượn sách do không đủ số lượng.");
                    err.showAndWait();
                }
                else if(borrow_num >= 5)
                {
                    Alert err = new Alert(Alert.AlertType.ERROR);
                    err.setTitle("Thất bại!");
                    err.setHeaderText(null);
                    err.setContentText("Không thể mượn sách. Vui lòng trả lại sách trước khi mượn thêm.");
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
                    alert1.setTitle("Thành công!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Mượn sách thành công!");

                    alert1.showAndWait();
                }
            }
        }
        else if (!(curdate.plusDays(6).isBefore(date) && curdate.plusMonths(3).plusDays(1).isAfter(date)))
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Cảnh báo!");
            alert1.setHeaderText(null);
            alert1.setContentText("Phải chọn ngày trả sách sau ngày mượn 1 tuần và không được quá 3 tháng.");
                
            alert1.showAndWait();
        }
        
        else
        {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Thoát");
            alert1.setHeaderText(null);
            alert1.setContentText("Hủy mượn sách.");
                
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
                Timestamp rBorrowTime = rs.getTimestamp("create_date");
                
                borrowData.add("Ngày mượn sách: " +rBorrowTime.toGMTString());
                
                borrowData.add("==========Thông tin mượn sách==========");
                sql = "SELECT * FROM borrow WHERE id = '" + rCardID + "'";
                rs = stm.executeQuery(sql);
                while(rs.next())
                {
                    borrowData.add("Ngày trả đã chọn: " + rs.getString(("end_date")));
                    borrowData.add("Số sách đã mượn: " + rs.getString("borrowBook_num")); 
                    borrowData.add("Status: " + rs.getString("borrow_status"));
                }
                
                sql = "SELECT * FROM book WHERE book_id = '" + rBookID + "'";
                rs = stm.executeQuery(sql);
                borrowData.add("==========Thông tin sách==========");
                while(rs.next())
                {
                    borrowData.add("Mã sách: " + rs.getString("book_id"));
                    borrowData.add("Tên sách: " + rs.getString("name"));
                    borrowData.add("Loại sách: " + rs.getString("category"));
                    
                }
                
                sql = "SELECT * FROM student WHERE student_id = '" + rStudentID + "'";
                rs = stm.executeQuery(sql);
                borrowData.add("==========Thông tin sinh viên==========");
                while(rs.next())
                {
                    borrowData.add("Mã sinh viên: " + rs.getString("student_id"));
                    borrowData.add("Họ tên sinh viên: " + rs.getString("surname") + " " + rs.getString("name"));
                    borrowData.add("Khoa: " + rs.getString("faculty"));
                    borrowData.add("Email: " + rs.getString("email"));
                    borrowData.add("Số điện thoại: " + rs.getString("phone"));
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
       
        if(!newStatus.isEmpty() && !newStatus.equals(oldStatus) && newStatus.equals("Sách cũ"))
        {
            try (Connection conn = JdbcUtils.getConn())
                {
                    Statement stm = (Statement) conn.createStatement();
                    String str = "UPDATE borrow SET return_status =  'Sách cũ' WHERE id = " + id ;
                    stm.executeUpdate(str);
                }
            moneyTorn = 10000;
        }
        else if (!newStatus.isEmpty() && !newStatus.equals(oldStatus) && newStatus.equals("Sách cũ hơn"))
        {
            try (Connection conn = JdbcUtils.getConn())
                {
                    Statement stm = (Statement) conn.createStatement();
                    String str = "UPDATE borrow SET return_status =  'Sách cũ hơn' WHERE id = " + id ;
                    stm.executeUpdate(str);
                }
            moneyTorn = 10000;
        }
        
        else if(!newStatus.isEmpty() && !newStatus.equals(oldStatus) && newStatus.equals("Sách hỏng"))
        {
            try (Connection conn = JdbcUtils.getConn())
                {
                    Statement stm = (Statement) conn.createStatement();
                    String str = "UPDATE borrow SET return_status =  'Sách hỏng' WHERE id = " + id;
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
            alert.setTitle("Thông báo!!");
            alert.setHeaderText(null);
            alert.setContentText("Đã cập nhật trả sách thành công");
                
            alert.showAndWait();
        }
    }
}
