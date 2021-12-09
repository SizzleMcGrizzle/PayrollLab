package me.p3074098.payrolllab.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import me.p3074098.payrolllab.util.ConfigurationSerialization;
import me.p3074098.payrolllab.workers.Boss;
import me.p3074098.payrolllab.workers.CommissionWorker;
import me.p3074098.payrolllab.workers.FactoryWorker;
import me.p3074098.payrolllab.workers.HourlyWorker;
import me.p3074098.payrolllab.workers.Manager;
import me.p3074098.payrolllab.workers.Worker;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private WorkerGridController gridController;
    private WorkerAdderController adderController;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        deserialize();
        loadGrid();
        loadAdder();
        updateLabels();
    }
    
    public void updateLabels() {
        List<WorkerCardController> cards = gridController.getWorkerCards();

        employeesLabel.setText(String.valueOf(gridController.getWorkerCards().size()));
        avgSalaryLabel.setText("$" + (int) getAverageSalary(cards));
    }
    
    private double getAverageSalary(List<WorkerCardController> cards) {
        double sum = 0;
        
        for (WorkerCardController card : cards)
            sum += card.getWorker().earnings();
        
        return sum / cards.size();
    }
    
    private void deserialize() {
        File file = new File(ConfigurationSerialization.getApplicationDirectory(), "workers");
    
        if (!file.exists()) {
            workers = Stream.of(
                    new HourlyWorker("Clara", "Dawson", 22.35, 40),
                    new HourlyWorker("Edward", "Fitz", 20.5, 45),
                    new HourlyWorker("Garren","Howard",24.15,32),
                    new FactoryWorker("Quin", "Raney",0.52,1240),
                    new CommissionWorker("Inez","Johnson",500,20,15),
                    new Boss("Abby","Barton",102000),
                    new CommissionWorker("Karen","Lowen",400,22,25),
                    new FactoryWorker("Mary","Norquist",0.52,1000),
                    new FactoryWorker("Opal","Pearson",0.52,902),
                    new HourlyWorker("Steve","Trimmer",31.15,0),
                    new Manager("Peyton", "Peck", 20, 45)
            ).collect(Collectors.toList());
        } else {
            workers = ConfigurationSerialization.deserializeDirectory(file);
        }
    }
    
    public void serialize() {
        ConfigurationSerialization.serialize(workers);
    }
    
    public void removeWorker(Worker worker) {
        worker.deleteFile();
        workers.remove(worker);
        updateLabels();
    }
    
    public void addWorker(Worker worker) {
        workers.add(worker);
        worker.serializeToFile();
        updateLabels();
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
        workerGridController.initialize(this, workers);
        
        employeeGrid.getChildren().add(anchorPane);

        this.gridController = workerGridController;
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
        workerAdderController.initialize(this, gridController);
        
        employeeAdder.getChildren().add(anchorPane);

        this.adderController = workerAdderController;
    }
}
