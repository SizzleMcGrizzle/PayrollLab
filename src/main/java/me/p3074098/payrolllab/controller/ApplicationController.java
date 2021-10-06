package me.p3074098.payrolllab.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import me.p3074098.payrolllab.workers.Boss;
import me.p3074098.payrolllab.workers.FactoryWorker;
import me.p3074098.payrolllab.workers.Manager;
import me.p3074098.payrolllab.workers.Worker;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {
    
    @FXML
    private AnchorPane employeeAdder;
    
    @FXML
    private AnchorPane employeeGrid;
    
    @FXML
    private Label avgSalaryLabel;
    
    @FXML
    private Label employeesLabel;
    
    private List<Worker> workers;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        deserialize();
        loadAdder();
        loadGrid();
        updateLabels();
    }
    
    public void updateLabels() {
        employeesLabel.setText(String.valueOf(workers.size()));
        avgSalaryLabel.setText("$" + (int) getAverageSalary());
    }
    
    private double getAverageSalary() {
        double sum = 0;
        
        for (Worker worker : workers)
            sum += worker.earnings();
        
        return sum / workers.size();
    }
    
    private void deserialize() {
        workers = Arrays.asList(
                new Boss("Peyton", "Peck", 210000),
                new FactoryWorker("Clint", "McKenzie", 1, 1000),
                new Manager("Adrian", "Janner", 20, 50)
        );
    }
    
    private void loadGrid() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workerGrid.fxml"));
        AnchorPane anchorPane = null;
        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        WorkerGridController workerGridController = fxmlLoader.getController();
        workerGridController.initialize(workers);
        
        employeeGrid.getChildren().add(anchorPane);
    }
    
    private void loadAdder() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("workerAdder.fxml"));
        AnchorPane anchorPane = null;
        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        WorkerAdderController workerAdderController = fxmlLoader.getController();
        workerAdderController.initialize(this);
        
        employeeAdder.getChildren().add(anchorPane);
    }
}
