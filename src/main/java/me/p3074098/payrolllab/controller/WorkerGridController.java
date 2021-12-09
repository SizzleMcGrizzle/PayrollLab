package me.p3074098.payrolllab.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private HBox removeBox;
    
    private List<WorkerCardController> workerCards;
    private Button selectedButton;
    private WorkerCardController selectedCard;
    private ApplicationController applicationController;
    
    public void initialize(ApplicationController applicationController, List<Worker> workers) {
        this.applicationController = applicationController;

        selectedButton = allButton;

        scrollPane.setFitToWidth(true);

        createWorkerCards(workers);
        fillGrid();
        displayRemoveButton(false);
    }

    private void displayRemoveButton(boolean show) {
        removeBox.setManaged(show);
        removeBox.setVisible(show);
    }

    public List<WorkerCardController> getWorkerCards() {
        return workerCards;
    }

    public void add(Worker worker) {
        addWorkerCard(worker);
        fillGrid();
    }

    @FXML
    public void onRemoveButtonClick(ActionEvent event) {
        if (selectedCard == null)
            return;

        workerCards.remove(selectedCard);
        applicationController.removeWorker(selectedCard.getWorker());
        
        setSelectedCard(null);
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
        
        for (Worker worker : workers)
            addWorkerCard(worker);
    }

    private void addWorkerCard(Worker worker) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("workerCard.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        WorkerCardController workerCardController = fxmlLoader.getController();
        workerCardController.initialize(worker, this);

        workerCards.add(workerCardController);
    }

    public WorkerCardController getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(WorkerCardController newCard) {
        if (selectedCard != null)
            selectedCard.getAnchor().getStyleClass().remove("label-box-selected");

        this.selectedCard = newCard;

        displayRemoveButton(newCard != null);
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
            grid.setAlignment(Pos.CENTER);
            GridPane.setMargin(anchorPane, new Insets(10));
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
        
        if (selectedButton.equals(hourlyWorkerButton) && worker instanceof HourlyWorker && !(worker instanceof Manager))
            return true;
        
        return selectedButton.equals(factoryWorkerButton) && worker instanceof FactoryWorker;
    }
}
