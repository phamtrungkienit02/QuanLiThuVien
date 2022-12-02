package com.ptk.quanlithuvienapp;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToLoginForm() throws IOException {
        App.setRoot("login");
    }
    @FXML
    private void switchToEditBookForm() throws IOException {
        App.setRoot("editBook");
    }
}
