/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.net.URL;
import java.util.EmptyStackException;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    @FXML
    private TableView<Complex> historyTab;
    @FXML
    private TableColumn<Complex, String> clmHistory;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    private ObservableList<Complex>  list;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // If the textfield is empty it is not possible to press the button
        SimpleBooleanProperty check  = new SimpleBooleanProperty();
        check.bind(Bindings.when(text.textProperty().isEmpty()).then(true).otherwise(false));
        insertButton.disableProperty().bind(check);
        
        list = FXCollections.observableArrayList();
        clmHistory.setCellValueFactory(new PropertyValueFactory<Complex,String>("complex"));
        
        historyTab.setItems(list);
    }    

    @FXML
    private void wrongOperation(String msg){
        Stage primaryStage=new Stage();
        Label lbl = new Label(msg);

        VBox root = new VBox(20);
        root.getChildren().addAll(lbl);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 220, 150);

        primaryStage.setTitle("Error");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    @FXML
    private void insetEvent(ActionEvent event) {
        double real=0;
        double imaginary=0;
        int sign=1;
        Complex c;
        String insertText= text.getText().trim();
        
        //check string
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
                if(insertText.length()==1)
                    imaginary = sign*1;
                else
                    imaginary=sign*Double.parseDouble(insertText.substring(j+1));
            }else{
                ////the number has both real and imaginary part
                String[] parseText= insertText.split("[+-]");
                real=sign*Double.parseDouble(parseText[0]);
                if(parseText[1].substring(parseText[1].indexOf("j")).length()==1)
                    imaginary=1;
                else
                    imaginary=Double.parseDouble(parseText[1].substring(parseText[1].indexOf("j")+1));
                if(insertText.contains("-"))
                    imaginary=-imaginary;
            }
            
        }
        c = new Complex(real,imaginary);
        memory.getComplexStack().push(c);
        list.add(0, memory.getComplexStack().lastElement());
        text.setText("");
    }

    @FXML
    private void addEvent(ActionEvent event) {
        try{
            memory.add();
            list.remove(0);
            list.remove(0);
            list.add(0, memory.getComplexStack().lastElement());
            text.setText(memory.getComplexStack().lastElement().toString());
        } catch(LessOf2ElementsException ex){
            wrongOperation("There must be at least two elements!");
        }
    }

    @FXML
    private void subEvent(ActionEvent event) {
        try{
            memory.sub();
            list.remove(0);
            list.remove(0);
            list.add(0, memory.getComplexStack().lastElement());
            text.setText(memory.getComplexStack().lastElement().toString());
        } catch(LessOf2ElementsException ex){
            wrongOperation("There must be at least two elements!");
        }
        
    }

    @FXML
    private void multiplyEvent(ActionEvent event) {
        try{    
            memory.multiply();
            list.remove(0);
            list.remove(0);
            list.add(0, memory.getComplexStack().lastElement());
            text.setText(memory.getComplexStack().lastElement().toString());
        } catch(LessOf2ElementsException ex){
            wrongOperation("There must be at least two elements!");
        }
    }

    @FXML
    private void divideEvent(ActionEvent event) {
        try{    
            memory.divide();
            list.remove(0);
            list.remove(0);
            list.add(0, memory.getComplexStack().lastElement());
            text.setText(memory.getComplexStack().lastElement().toString());
        } catch(LessOf2ElementsException ex){
            wrongOperation("There must be at least two elements!");
        }
    }

    @FXML
    private void sqrtEvent(ActionEvent event) {
        try{    
            memory.square();
            list.remove(0);
            list.add(0, memory.getComplexStack().lastElement());
            text.setText(memory.getComplexStack().lastElement().toString());
        } catch(EmptyStackException ex){
            wrongOperation("There must be at least one element!");
        }
    }

    @FXML
    private void invertEvent(ActionEvent event) {
        try{    
            memory.invert();
            list.remove(0);
            list.add(0, memory.getComplexStack().lastElement());
            text.setText(memory.getComplexStack().lastElement().toString());
        } catch(EmptyStackException ex){
            wrongOperation("There must be at least one element!");
        }    
    }

    @FXML
    private void clearEvent(ActionEvent event) {
        try{
            memory.clear();
            list.clear();
        }catch (EmptyStackException ex){
            wrongOperation("There must be at least one element!");
        }
    }

    @FXML
    private void dropEvent(ActionEvent event) {
        try{
            list.remove(0);
            memory.drop();
        } catch (EmptyStackException ex){
            wrongOperation("There must be at least one element!");
        }
    }

    @FXML
    private void dupEvent(ActionEvent event) {
        try{
            list.add(0, list.get(0));
            memory.dup();
        } catch(EmptyStackException ex){
            wrongOperation("There must be at least one element!");
        }
    }

    @FXML
    private void swapEvent(ActionEvent event) {
        try{
            list.add(0, list.get(1));
            list.remove(2);
            memory.swap();
        } catch(LessOf2ElementsException ex){
            wrongOperation("There must be at least two elements!");
        }
    }

    @FXML
    private void overEvent(ActionEvent event) {
        try{
            list.add(0, list.get(1));
            memory.over();
        } catch (LessOf2ElementsException ex){
            wrongOperation("There must be at least two elements!");
        }
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
