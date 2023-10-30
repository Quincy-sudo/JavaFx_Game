module com.example.group_projectv2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.group_projectv2 to javafx.fxml;
    exports com.example.group_projectv2;
}