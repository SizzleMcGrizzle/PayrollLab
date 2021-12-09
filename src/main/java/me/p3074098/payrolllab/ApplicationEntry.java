package me.p3074098.payrolllab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.p3074098.payrolllab.controller.ApplicationController;
import me.p3074098.payrolllab.util.ConfigurationSerialization;
import me.p3074098.payrolllab.workers.Boss;
import me.p3074098.payrolllab.workers.CommissionWorker;
import me.p3074098.payrolllab.workers.FactoryWorker;
import me.p3074098.payrolllab.workers.HourlyWorker;
import me.p3074098.payrolllab.workers.Manager;

import java.io.IOException;

public class ApplicationEntry extends Application {
    
    public static Stage mainStage;
    
    private ApplicationController applicationController;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("controller/application.fxml"));
        
        primaryStage.setTitle("Payroll");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setResizable(false);
        primaryStage.show();
    
        applicationController = loader.getController();
    }
    
    @Override
    public void stop() throws Exception {
        super.stop();
        
        applicationController.serialize();
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        ConfigurationSerialization.registerClass(CommissionWorker.class);
        ConfigurationSerialization.registerClass(HourlyWorker.class);
        ConfigurationSerialization.registerClass(FactoryWorker.class);
        ConfigurationSerialization.registerClass(Boss.class);
        ConfigurationSerialization.registerClass(Manager.class);
        
        launch();
    }
}