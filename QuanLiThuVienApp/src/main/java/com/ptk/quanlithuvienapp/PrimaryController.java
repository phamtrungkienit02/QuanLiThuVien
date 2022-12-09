package com.ptk.quanlithuvienapp;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private void switchToLoginForm() throws IOException {
        App.setRoot("login");
    }
    private static Scene scene;

//    @Override
//    public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("primary"), 640, 480);
//        stage.setScene(scene);
//        stage.show();
//    }
    @FXML
    private void switchToEditBookForm() throws IOException {
       
  
        App.setRoot("editBook");
    }
    
    @FXML private void switchToSearchBook() throws IOException
    {
        App.setRoot("searchBook");
    }
    
    @FXML private void switchToIssue_Return() throws IOException
    {
        App.setRoot("Issue_Return");
    }
}
