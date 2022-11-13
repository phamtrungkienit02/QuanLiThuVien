module com.ptk.quanlithuvienapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ptk.quanlithuvienapp to javafx.fxml;
    exports com.ptk.quanlithuvienapp;
}
