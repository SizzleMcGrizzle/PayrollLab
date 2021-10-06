package me.p3074098.payrolllab.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

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
    private TextField firstNameField;
    
    @FXML
    private Label firstNameLabel;
    
    @FXML
    private TextField lastNameField;
    
    @FXML
    private Label lastNameLabel;
    
    @FXML
    private TextField parameter1Field;
    
    @FXML
    private Label parameter1Label;
    
    @FXML
    private TextField parameter2Field;
    
    @FXML
    private Label parameter2Label;
    
    @FXML
    private TextField parameter3Field;
    
    @FXML
    private Label parameter3Label;
    
    @FXML
    private Button addButton;
    
    @FXML
    private HBox parameterBox1;
    
    @FXML
    private HBox parameterBox2;
    
    @FXML
    private HBox parameterBox3;
    
    @FXML
    private HBox firstNameBox;
    
    @FXML
    private HBox lastNameBox;
    
    private Button selectedPositionButton;
    private Label[] parameterLabels;
    private TextField[] allTextFields;
    private TextField[] parameterTextFields;
    private HBox[] parameterBoxes;
    private ApplicationController applicationController;
    
    public void initialize(ApplicationController controller) {
        applicationController = controller;
        selectedPositionButton = bossButton;
        parameterLabels = new Label[]{parameter1Label, parameter2Label, parameter3Label};
        allTextFields = new TextField[]{firstNameField, lastNameField, parameter1Field, parameter2Field, parameter3Field};
        parameterTextFields = new TextField[]{parameter1Field, parameter2Field, parameter3Field};
        parameterBoxes = new HBox[]{firstNameBox, lastNameBox, parameterBox1, parameterBox2, parameterBox3};
        
        setTextFields();
    }
    
    @FXML
    public void positionButtonClick(ActionEvent event) {
        Button source = (Button) event.getSource();
        
        selectedPositionButton.getStyleClass().remove("job-button-selected");
        
        selectedPositionButton = source;
        selectedPositionButton.getStyleClass().add("job-button-selected");
        
        resetBordersForFields();
        clearTextFields();
        setTextFields();
    }
    
    @FXML
    public void addButtonClick(ActionEvent event) {
        String[] parameters = getParameters();
        
        String firstName = "";
        String lastName = "";
        
        List<Double> entries = new ArrayList<>();
        
        for (int i = 0; i < parameters.length; i++) {
            if (i == 0)
                firstName = allTextFields[i].getText();
            else if (i == 1)
                lastName = allTextFields[i].getText();
            else
                try {
                    entries.add(Double.parseDouble(parameterLabels[i - 2].getText()));
                } catch (NumberFormatException e) {
                    redBorder(parameterBoxes[i]);
                }
        }
        
        if (firstName.isEmpty()) {
            redBorder(firstNameBox);
            return;
        }
        
        if (lastName.isEmpty()) {
            redBorder(lastNameBox);
            return;
        }
        
        createWorker(firstName, lastName, entries);
        clearTextFields();
    }
    
    private void clearTextFields() {
        for (TextField field : parameterTextFields)
            field.clear();
    }
    
    private void createWorker(String first, String last, List<Double> entries) {
    
    }
    
    private void redBorder(Node node) {
        node.getStyleClass().add("red-border");
    }
    
    private void resetBordersForFields() {
        for (Node node : parameterBoxes)
            node.getStyleClass().remove("red-border");
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
            Label label = parameterLabels[i];
            HBox box = parameterBoxes[i + 2];
            
            if (parameters.length > i) {
                String s = parameters[i];
                
                label.setText(s);
                label.setPrefWidth(Region.USE_COMPUTED_SIZE);
                
                show = true;
            }
            
            box.setVisible(show);
            box.setManaged(show);
            label.setVisible(show);
            label.setManaged(show);
        }
    }
}
