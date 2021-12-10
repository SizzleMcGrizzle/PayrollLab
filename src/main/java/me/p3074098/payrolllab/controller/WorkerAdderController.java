package me.p3074098.payrolllab.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import me.p3074098.payrolllab.workers.*;

import java.util.ArrayList;
import java.util.List;

public class WorkerAdderController {
    
    @FXML
    private Button bossButton;
    
    @FXML
    private Button managerButton;
    
    @FXML
    private Button commissionedWorkerButton;
    
    @FXML
    private Button hourlyWorkerButton;
    
    @FXML
    private Button factoryWorkerButton;

    @FXML
    private Button addButton;

    @FXML
    private VBox parameterBox;
    
    private Button selectedPositionButton;
    private ApplicationController applicationController;
    private WorkerGridController gridController;

    private FancyInput[] inputs;
    private int numParameters = 1;
    
    public void initialize(ApplicationController controller, WorkerGridController gridController) {
        applicationController = controller;
        this.gridController = gridController;
        selectedPositionButton = bossButton;

        inputs = new FancyInput[]{
                new FancyInput("First Name", "John"),
                new FancyInput("Last Name", "Smith"),
                new FancyInput("Parameter 1", "1000"),
                new FancyInput("Parameter 2", "1000"),
                new FancyInput("Parameter 3", "1000")
        };

        for (FancyInput input : inputs) {
            parameterBox.getChildren().add(input);
        }


        setTextFields();
    }

    @FXML
    public void positionButtonClick(ActionEvent event) {
        Button source = (Button) event.getSource();
        
        selectedPositionButton.getStyleClass().remove("job-button-selected");
        
        selectedPositionButton = source;
        selectedPositionButton.getStyleClass().add("job-button-selected");
        
        clearBorders();
        clearTextFields();
        setTextFields();
    }
    
    @FXML
    public void addButtonClick(ActionEvent event) {
        clearBorders();

        String firstName = inputs[0].validateString();

        if (firstName == null)
            return;

        String lastName = inputs[1].validateString();

        if (lastName == null)
            return;

        List<Double> entries = new ArrayList<>();
        
        for (int i = 0; i < numParameters; i++) {
            FancyInput input = inputs[i+2];
            Double d = input.validateDouble();

            if (d == null)
                return;

            entries.add(d);
        }
        
        createWorker(firstName, lastName, entries);
        clearTextFields();
        clearBorders();
    }

    private void clearTextFields() {
        for (FancyInput input : inputs)
            input.clearText();
    }
    
    private void createWorker(String first, String last, List<Double> entries) {
        Worker worker;

        if (selectedPositionButton.equals(bossButton))
            worker = new Boss(first, last, entries.get(0));
        else if (selectedPositionButton.equals(managerButton))
            worker = new Manager(first, last, entries.get(0), entries.get(1));
        else if (selectedPositionButton.equals(commissionedWorkerButton))
            worker = new CommissionWorker(first, last, entries.get(0), entries.get(1), entries.get(2).intValue());
        else if (selectedPositionButton.equals(factoryWorkerButton))
            worker = new FactoryWorker(first, last, entries.get(0), entries.get(1).intValue());
        else
            worker = new HourlyWorker(first, last, entries.get(0), entries.get(1));

        gridController.add(worker);
        applicationController.addWorker(worker);
    }
    
    private void red(Node border, Node text) {
        border.getStyleClass().add("red-border");
        text.getStyleClass().add("red-text");
    }
    
    private void clearBorders() {
        for (FancyInput input : inputs)
            input.clearColor();
    }
    
    private String[] getParameters() {
        String[] parameters;
        
        if (selectedPositionButton.equals(bossButton))
            parameters = new String[]{"Yearly Salary"};
        else if (selectedPositionButton.equals(managerButton) || selectedPositionButton.equals(hourlyWorkerButton))
            parameters = new String[]{"Wage", "Hours"};
        else if (selectedPositionButton.equals(factoryWorkerButton))
            parameters = new String[]{"Wage per Part", "Output"};
        else {
            parameters = new String[]{"Base Salary", "Commission per Part", "Output"};
        }
        
        return parameters;
    }
    
    public void setTextFields() {
        String[] parameters = getParameters();
        
        for (int i = 0; i < 3; i++) {
            boolean show = false;

            FancyInput input = inputs[i+2];
            
            if (parameters.length > i) {
                String s = parameters[i];
                
                input.setLabelText(s);
                
                show = true;
            }
            
            input.adjustVisibility(show);
        }
    }
}
