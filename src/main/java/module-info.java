module com.example.demo1605 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo1605 to javafx.fxml;
    exports com.example.demo1605;
    exports com.example.demo1605.view;
    opens com.example.demo1605.view to javafx.fxml;
    exports com.example.demo1605.controller;
    opens com.example.demo1605.controller to javafx.fxml;
}