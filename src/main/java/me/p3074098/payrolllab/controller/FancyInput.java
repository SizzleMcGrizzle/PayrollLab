package me.p3074098.payrolllab.controller;

import javafx.beans.NamedArg;
import javafx.beans.value.ObservableBooleanValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;

public class FancyInput extends Pane {

    @FXML
    private Pane anchor;

    @FXML
    private HBox fieldBox;

    @FXML
    private Label label;

    @FXML
    private TextField textField;

    public FancyInput(@NamedArg("labelDefault") String labelDefault,
                      @NamedArg("prefText") String preferredText) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fancyInput.fxml"));

            loader.setController(this);

            Pane pane = loader.load();

            this.getChildren().add(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }

        setLabelText(labelDefault);

        textField.setPromptText(preferredText);

        // Set color to blue if the text field is selected
        textField.focusedProperty().addListener(object -> {
            if(((ObservableBooleanValue) object).get())
                select();
            else
                clearColor();
        });
    }

    protected void select() {
        setColor("#3636fc");
    }

    protected void clearColor() {
        setColor("black");
    }

    protected void clearText() {
        textField.clear();
    }

    protected void warn() {
        setColor("red");
    }

    private void setColor(String color) {
        label.setStyle("-fx-text-fill: " + color);
        fieldBox.setStyle("-fx-border-color: " + color);
    }

    protected String validateString() {
        if (textField.getText().isEmpty()) {
            warn();
            return null;
        }

        return textField.getText();
    }

    protected void setLabelText(String text) {
        label.setText(text);
        label.setPrefWidth(Region.USE_COMPUTED_SIZE);
    }
    
    protected void adjustVisibility(boolean show) {
        fieldBox.setVisible(show);
        fieldBox.setManaged(show);
        label.setVisible(show);
        label.setManaged(show);
    }

    protected Double validateDouble() {
        if (textField.getText().isEmpty()) {
            warn();
            return null;
        }

        try {
            return Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            warn();
            return null;
        }
    }
}
