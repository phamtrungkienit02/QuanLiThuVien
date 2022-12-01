module com.ptk.quanlithuvienapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.ptk.quanlithuvienapp to javafx.fxml;
    exports com.ptk.quanlithuvienapp;
}
