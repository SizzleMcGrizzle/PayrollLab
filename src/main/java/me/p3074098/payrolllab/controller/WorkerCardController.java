package me.p3074098.payrolllab.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
    private WorkerGridController controller;
    
    public void initialize(Worker worker, WorkerGridController gridController) {
        this.controller = gridController;
        this.worker = worker;
        
        nameHBox.setStyle("-fx-background-color: rgb(" + worker.getColor() + ")");
        nameLabel.setText(worker.getFirstName() + " " + worker.getLastName());
        salaryLabel.setText("$" + String.format("%.2f", worker.earnings()));
        jobLabel.setText(worker.getJobTitle());
    }

    @FXML
    public void onClick(MouseEvent event) {
        if (controller.getSelectedCard() != null && controller.getSelectedCard().equals(this)) {
            anchor.getStyleClass().remove("label-box-selected");
            controller.setSelectedCard(null);
        } else {
            anchor.getStyleClass().add("label-box-selected");
            controller.setSelectedCard(this);
        }
    }
    
    public Worker getWorker() {
        return worker;
    }
    
    public AnchorPane getAnchor() {
        return anchor;
    }
}
