/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
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
    
    private Calculator memory = new Calculator();
    
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
        double real=0;
        double imaginary=0;
        int sign=1;
        Complex c;
        String insertText= text.getText().trim();
        if(insertText.indexOf("-")==0 || insertText.indexOf("+")==0){
            if(insertText.indexOf("-")==0)
                sign=-1;
            insertText=insertText.substring(1);
        }
        //The number has only the real part 
        if(!insertText.contains("j")){
                real=sign*Double.parseDouble(insertText);
        }else{
            insertText = insertText.trim();
            int j=insertText.indexOf("j");
            ////The number has only the imaginary part 
            if(j==0){
                imaginary=sign*Double.parseDouble(insertText.substring(j+1));
            }else{
                ////the number has both real and imaginary part
                String[] parseText= insertText.split("[+-]");
                real=sign*Double.parseDouble(parseText[0]);
                imaginary=Double.parseDouble(parseText[1].substring(parseText[1].indexOf("j")+1));
                if(insertText.contains("-"))
                    imaginary=-imaginary;
            }
            
        }
        c = new Complex(real,imaginary);
        memory.getComplexStack().push(c);
        text.setText(memory.getComplexStack().lastElement().toString());
                
    }

    @FXML
    private void addEvent(ActionEvent event) {
        memory.add();
        text.setText(memory.getComplexStack().lastElement().toString());
    }

    @FXML
    private void subEvent(ActionEvent event) {
        memory.sub();
        text.setText(memory.getComplexStack().lastElement().toString());
        
    }

    @FXML
    private void multiplyEvent(ActionEvent event) {
        memory.multiply();
        text.setText(memory.getComplexStack().lastElement().toString());
    }

    @FXML
    private void divideEvent(ActionEvent event) {
        memory.divide();
        text.setText(memory.getComplexStack().lastElement().toString());
    }

    @FXML
    private void sqrtEvent(ActionEvent event) {
        memory.square();
        text.setText(memory.getComplexStack().lastElement().toString());
    }

    @FXML
    private void invertEvent(ActionEvent event) {
        memory.invert();
        text.setText(memory.getComplexStack().lastElement().toString());
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
