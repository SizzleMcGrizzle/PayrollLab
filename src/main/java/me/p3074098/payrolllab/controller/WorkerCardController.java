package me.p3074098.payrolllab.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import me.p3074098.payrolllab.workers.Worker;

public class WorkerCardController {
    
    @FXML
    private HBox nameHBox;
    
    @FXML
    private Label nameLabel;
    
    @FXML
    private Label salaryLabel;
    
    @FXML
    private Label jobLabel;
    
    @FXML
    private AnchorPane anchor;
    
    private Worker worker;
    
    public void initialize(Worker worker) {
        this.worker = worker;
        
        nameHBox.setStyle("-fx-background-color: rgb(" + worker.getColor() + ")");
        nameLabel.setText(worker.getFirstName() + " " + worker.getLastName());
        salaryLabel.setText("$" + (int) worker.earnings());
        jobLabel.setText(worker.getJobTitle());
    }
    
    public Worker getWorker() {
        return worker;
    }
    
    public AnchorPane getAnchor() {
        return anchor;
    }
}
