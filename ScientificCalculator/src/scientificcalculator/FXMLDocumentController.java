/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author stefa
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button insertButton;
    @FXML
    private TextField text;
    @FXML
    private Button addButton;
    @FXML
    private Button subButton;
    @FXML
    private Button multiplyButton;
    @FXML
    private Button divideButton;
    @FXML
    private Button sqrtButton;
    @FXML
    private Button invertButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button dropButton;
    @FXML
    private Button dupButton;
    @FXML
    private Button swapButton;
    @FXML
    private Button overButton;
    @FXML
    private Button variablesButton;
    @FXML
    private Button saveVarButton;
    @FXML
    private Button restoreVarButton;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void insetEvent(ActionEvent event) {
    }

    @FXML
    private void addEvent(ActionEvent event) {
    }

    @FXML
    private void subEvent(ActionEvent event) {
    }

    @FXML
    private void multiplyEvent(ActionEvent event) {
    }

    @FXML
    private void divideEvent(ActionEvent event) {
    }

    @FXML
    private void sqrtEvent(ActionEvent event) {
    }

    @FXML
    private void invertEvent(ActionEvent event) {
    }

    @FXML
    private void clearEvent(ActionEvent event) {
    }

    @FXML
    private void dropEvent(ActionEvent event) {
    }

    @FXML
    private void dupEvent(ActionEvent event) {
    }

    @FXML
    private void swapEvent(ActionEvent event) {
    }

    @FXML
    private void overEvent(ActionEvent event) {
    }

    @FXML
    private void variablesEvent(ActionEvent event) {
    }

    @FXML
    private void saveVarEvent(ActionEvent event) {
    }

    @FXML
    private void restoreVarEvent(ActionEvent event) {
    }
    
}
