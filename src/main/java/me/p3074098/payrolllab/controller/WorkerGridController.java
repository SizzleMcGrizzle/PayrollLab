package me.p3074098.payrolllab.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import me.p3074098.payrolllab.workers.Boss;
import me.p3074098.payrolllab.workers.CommissionWorker;
import me.p3074098.payrolllab.workers.FactoryWorker;
import me.p3074098.payrolllab.workers.HourlyWorker;
import me.p3074098.payrolllab.workers.Manager;
import me.p3074098.payrolllab.workers.Worker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkerGridController {
    
    @FXML
    private Button allButton;
    
    @FXML
    private Button bossButton;
    
    @FXML
    private Button managerButton;
    
    @FXML
    private Button commissionWorkerButton;
    
    @FXML
    private Button factoryWorkerButton;
    
    @FXML
    private Button hourlyWorkerButton;
    
    @FXML
    private GridPane grid;
    
    private List<WorkerCardController> workerCards;
    private Button selectedButton;
    
    
    public void initialize(List<Worker> workers) {
        selectedButton = allButton;
        
        createWorkerCards(workers);
        fillGrid();
    }
    
    @FXML
    public void positionButtonClick(ActionEvent event) {
        Button source = (Button) event.getSource();
        
        selectedButton.getStyleClass().remove("search-label-selected");
        
        selectedButton = source;
        selectedButton.getStyleClass().add("search-label-selected");
        
        fillGrid();
    }
    
    private void createWorkerCards(List<Worker> workers) {
        workerCards = new ArrayList<>();
        
        for (Worker worker : workers) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("workerCard.fxml"));
            
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            WorkerCardController workerCardController = fxmlLoader.getController();
            workerCardController.initialize(worker);
            
            workerCards.add(workerCardController);
        }
    }
    
    private void fillGrid() {
        grid.getChildren().clear();
        
        List<WorkerCardController> cards = workerCards.stream().filter(c -> satisfiesGridCondition(c.getWorker())).collect(Collectors.toList());
        
        int col = 0, row = 1;
        for (int i = 0; i < cards.size(); i++) {
            WorkerCardController card = cards.get(i);
            
            if (col == 3) {
                col = 0;
                row++;
            }
            
            AnchorPane anchorPane = card.getAnchor();
            
            anchorPane.setMinWidth(anchorPane.getPrefWidth());
            anchorPane.setMinHeight(anchorPane.getPrefHeight());
            
            grid.add(anchorPane, col++, row);
            GridPane.setMargin(anchorPane, new Insets(15));
        }
    }
    
    private boolean satisfiesGridCondition(Worker worker) {
        if (selectedButton.equals(allButton))
            return true;
        
        if (selectedButton.equals(bossButton) && worker instanceof Boss)
            return true;
        
        if (selectedButton.equals(managerButton) && worker instanceof Manager)
            return true;
        
        if (selectedButton.equals(commissionWorkerButton) && worker instanceof CommissionWorker)
            return true;
        
        if (selectedButton.equals(hourlyWorkerButton) && worker instanceof HourlyWorker)
            return true;
        
        return selectedButton.equals(factoryWorkerButton) && worker instanceof FactoryWorker;
    }
}
