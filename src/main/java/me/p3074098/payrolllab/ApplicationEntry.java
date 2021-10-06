package me.p3074098.payrolllab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationEntry extends Application {
    
    public static Stage mainStage;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("controller/application.fxml"));
        
        mainStage = primaryStage;
        
        primaryStage.setTitle("Payroll");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
//        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("app-icon.png")));
    }
    
    public static void main(String[] args) {
        launch();
    }
}