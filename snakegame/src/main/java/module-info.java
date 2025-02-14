module com.example.snakegame {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    //requires javafx.graphics;
    requires jlayer;
    requires javafx.swing;
    requires javafx.media;
    requires org.testng;


    opens com.example.snakegame to javafx.fxml;
    exports com.example.snakegame;
    exports view;
    exports util;
    exports model;
    exports interfaces;
}