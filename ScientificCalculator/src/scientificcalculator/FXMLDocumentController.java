/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import exceptions.WrongInputException;
import exceptions.KeyAlreadyPresentInOperations;
import exceptions.OperationFailedException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

/**
 *The <em>FXMLDocumentController</em> class represents controller class in the Model-View-Controller architecture
 * @author group15
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
    @FXML
    private TableView<Complex> historyTab;
    @FXML
    private TableColumn<Complex, String> clmHistory;
    @FXML
    private TableView<Variable> variablesTab;
    @FXML
    private TableColumn<Variable, String> clmNameVariables;
    @FXML
    private TableColumn<Variable, String> clmValuesVariables;
    @FXML
    private TableView<String> operationsTab;
    @FXML
    private TableColumn<String, String> clmOperation;
    @FXML
    private TableColumn<String, String> clmNameOperation;
    @FXML
    private Button saveOperationButton;
    @FXML
    private TextField operationName;
    @FXML
    private TextField operationSequence;
    
    private Calculator memory = new Calculator();
    
    private Operations operation = memory.getOperations();
    
    /** Observable list that will contain all the name of the user defined operations*/
    private ObservableList<String> keys;
    
    /** Observable list that will contain all the Complex number of the calculator making them observable*/
    private ObservableList<Complex>  list;
    
    /** Observable list that will contain all the Variables of the calculator making them observable*/
    private ObservableList<Variable>  listVariables;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // If the textfield is empty it is not possible to press the button
        SimpleBooleanProperty check  = new SimpleBooleanProperty();
        check.bind(Bindings.when(text.textProperty().isEmpty()).then(true).otherwise(false));
        insertButton.disableProperty().bind(check);
        variablesButton.disableProperty().bind(check);
        
        SimpleBooleanProperty checkOperation  = new SimpleBooleanProperty();
        checkOperation.bind(Bindings.when(operationName.textProperty().isEmpty().or(operationSequence.textProperty().isEmpty())).then(true).otherwise(false));
        saveOperationButton.disableProperty().bind(checkOperation);
        
        list = FXCollections.observableList(memory.getComplexStack());
        clmHistory.setCellValueFactory(new PropertyValueFactory<Complex,String>("complex"));
        historyTab.setItems(list);
        
        
        memory.getVariables().getVariables().addListener((MapChangeListener.Change<? extends Character, ? extends Complex> change) -> {
            boolean removed = change.wasRemoved();
            if (removed != change.wasAdded()) {
                // no put for existing key
                if (removed) {
                    listVariables.remove(new Variable(change.getKey(),change.getValueRemoved()));
                } else {
                    Variable var = new Variable(change.getKey(),change.getValueAdded());
                    listVariables.add(new Variable(change.getKey(),change.getValueAdded()));
                }
            }else{
                Variable var = new Variable(change.getKey(),change.getValueAdded());
                if(listVariables.contains(var)){
                    listVariables.set(listVariables.indexOf(var), var);
                }else listVariables.add(new Variable(change.getKey(),change.getValueAdded()));
            }
        });
        
        listVariables = FXCollections.observableArrayList();
        listVariables.sort((Variable a,Variable b)->a.compareTo(b));
        clmNameVariables.setCellValueFactory(new PropertyValueFactory<Variable,String>("name"));
        clmValuesVariables.setCellValueFactory(new PropertyValueFactory<Variable,String>("value"));
        variablesTab.setItems(listVariables);
        
        keys = FXCollections.observableArrayList();
        operation.getOperationsMap().addListener((MapChangeListener.Change<? extends String, ? extends String> change) -> {
            boolean removed = change.wasRemoved();
            if (removed != change.wasAdded()) {
                // no put for existing key
                if (removed) {
                    keys.remove(change.getKey());
                } else {
                    keys.add(change.getKey());
                }
            }
        });
        
        
        
        clmNameOperation.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue()));
        clmOperation.setCellValueFactory(cd -> Bindings.valueAt ( operation.getOperationsMap(), cd.getValue()));
        clmOperation.setCellFactory(TextFieldTableCell.forTableColumn());
        operationsTab.setItems(keys);
        operationsTab.getColumns().setAll(clmNameOperation, clmOperation);
    }    
    
    /**
     * This method creates a second window showing the string <code>msg</code> as text
     * @param msg {@code String} The error message to show
     */
    private void wrongOperation(String msg){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(msg);
        alert.showAndWait();
    }
    
    /**
     * This method execute the insertion of a complex number or a variable in the calculator
     * after pressing the insertButton or the return key on the keyboard.
     * @param event {@code ActionEvent}
     */
    @FXML
    private void insertEvent(ActionEvent event) {
         
        String insertedText= text.getText().trim();
        
        if(insertedText.length() == 2 && !Character.isDigit(insertedText.charAt(0)) 
                && Character.isAlphabetic(insertedText.charAt(1))){
            variablesEvent(event);
            text.setText("");
        }
        else{
            try{
            memory.selectOperationToInvoke(insertedText);
            historyTab.refresh();
            }catch(OperationFailedException ex){
                wrongOperation("Input is not in correct form:\n a + bj");
            }finally{
                text.setText("");
            }
        }
    }
    
    /**
     * The method deletes the last two saved numbers and inserts their sum in the calculator
     * @param event {@code ActionEvent}
     */
    @FXML
    private void addEvent(ActionEvent event) {
        try{
            memory.selectOperationToInvoke("+");
            historyTab.refresh();
        } catch(OperationFailedException ex){
            wrongOperation("There must be at least two elements!");
        }
    }
    
    /**
     * The method deletes the last two saved numbers and inserts the difference between the last and the second last in the calculator
     * after pressing the button subButton
     * @param event {@code ActionEvent}
     */
    @FXML
    private void subEvent(ActionEvent event) {
        try{
            memory.selectOperationToInvoke("-");
            historyTab.refresh();
        } catch(OperationFailedException ex){
            wrongOperation("There must be at least two elements!");
        }
        
    }
    
    /**
     * The method deletes the last two saved numbers and inserts their product in the calculator
     * after pressing the button multiplyButton
     * @param event {@code ActionEvent}
     */
    @FXML
    private void multiplyEvent(ActionEvent event) {
        try{    
            memory.selectOperationToInvoke("*");
            historyTab.refresh();
        } catch(OperationFailedException ex){
            wrongOperation("There must be at least two elements!");
        }
    }
    
    /**
     * The method deletes the last two saved numbers and inserts the division between the last and the second last in the calculator
     * after pressing the button divideButton 
     * @param event {@code ActionEvent}
     */
    @FXML
    private void divideEvent(ActionEvent event) {
        try{  
            memory.selectOperationToInvoke("/");
            historyTab.refresh();
        }catch(OperationFailedException ex){
            wrongOperation("Division not possible!");
        }
    }
    
    /**
     * The method deletes the last number entered and inserts its square root in the calculator
     * after pressing the button sqrtButton 
     * @param event {@code ActionEvent}
     */
    @FXML
    private void sqrtEvent(ActionEvent event) {
        try{    
            memory.selectOperationToInvoke("sqrt");
            historyTab.refresh();
        } catch(OperationFailedException ex){
            wrongOperation("There must be at least one element!");
        }
    }
    
    /**
     * The method deletes the last number entered, invert its sign,and inserts it the calculator
     * after pressing the button invertButton  
     * @param event {@code ActionEvent}
     */
    @FXML
    private void invertEvent(ActionEvent event) {
        try{    
            memory.selectOperationToInvoke("+-");
            historyTab.refresh();
        } catch(OperationFailedException ex){
            wrongOperation("There must be at least one element!");
        }    
    }
    
    /**
     * The method erases all numbers present in the calculator
     * after pressing the button clearButton.
     * @param event {@code ActionEvent}
     */
    @FXML
    private void clearEvent(ActionEvent event) {
        try{
            memory.selectOperationToInvoke("clear");
            historyTab.refresh();
        }catch (OperationFailedException ex){
            wrongOperation("There must be at least one element!");
        }
    }
    
    /**
     * The method deletes the last number entered in the calculator
     * after pressing the button dropButton 
     * @param event {@code ActionEvent}
     */
    @FXML
    private void dropEvent(ActionEvent event) {
        try{
            memory.selectOperationToInvoke("drop");
            historyTab.refresh();
        } catch (OperationFailedException ex){
            wrongOperation("There must be at least one element!");
        }
    }
    
    /**
     * The method duplicates the last element in the calculator
     * after pressing the button dupButton  
     * @param event {@code ActionEvent}
     */
    @FXML
    private void dupEvent(ActionEvent event) {
        try{
            memory.selectOperationToInvoke("dup");
            historyTab.refresh();
        } catch(OperationFailedException ex){
            wrongOperation("There must be at least one element!");
        }
    }
    
    /**
     * The method swap the last two elements in the calculator
     * after pressing the button swapButton 
     * @param event {@code ActionEvent}
     */
    @FXML
    private void swapEvent(ActionEvent event) {
        try{
            memory.selectOperationToInvoke("swap");
            historyTab.refresh();
        } catch(OperationFailedException ex){
            wrongOperation("There must be at least two elements!");
        }
    }
    
    /**
     * The method inserts a copy of the second last element in the calculator
     * after pressing the button overButton  
     * @param event {@code ActionEvent}
     */
    @FXML
    private void overEvent(ActionEvent event) {
        try{
            memory.selectOperationToInvoke("over");
            historyTab.refresh();
        } catch (OperationFailedException ex){
            wrongOperation("There must be at least two elements!");
        }
    }
    
    /**
     * This method executes the operation on the variables according to what is 
     * written in the text field after pressing the button insert or var
     * @param event {@code ActionEvent}
     */
    @FXML
    private void variablesEvent(ActionEvent event) {
        try{
            String input=text.getText().toLowerCase().trim();
            memory.selectOperationVariableToInvoke(input);
            historyTab.refresh();
        } catch (WrongInputException | OperationFailedException ex){
            wrongOperation("Invalid operation on variables");
        }
    }
    
    /**
     * The method saves the variables in the calculator
     * @param event 
     */
    @FXML
    private void saveVarEvent(ActionEvent event) {
        memory.selectOperationVariableStackToInvoke("save");
    }
    
    /**
     * The method restore the last saved variables in the calculator
     * @param event 
     */
    @FXML
    private void restoreVarEvent(ActionEvent event) {
        try {
            memory.selectOperationVariableStackToInvoke("restore");
            variablesTab.refresh();
        }catch (OperationFailedException ex){
            wrongOperation("There are no variables to restore");
        }          
    }
    
    /**
     * The method save a user defined operation in the calculator
     * @param event 
     */
    @FXML
    private void saveOperationEvent(ActionEvent event) {
        try{
            String name = operationName.getText().trim();
            String sequence = operationSequence.getText().trim();
            
            if(operationName.isDisabled()){
                operation.modify(name, sequence);
                operationName.setDisable(false);
            }else{
                operation.addOperation(name, sequence);
            }
            operationName.setText("");
            operationSequence.setText(""); 
        }catch(KeyAlreadyPresentInOperations ex){
            wrongOperation("Operation name already present");
        }catch(WrongInputException ex){
            wrongOperation("Operation name invalid");
        }
        
    }
    
    /**
     * The method execute the sequence of operations defined by the selected user defined operation
     * @param event 
     */
    @FXML
    private void invokeEvent(ActionEvent event) {
        try {
            memory.invokeOperation(operationsTab.getSelectionModel().getSelectedItem());
        }catch (NullPointerException ex){
            wrongOperation("Sono gay");
        }catch (OperationFailedException ex) {
            wrongOperation("Operation failed");
        }finally{
            historyTab.refresh();
        }
    }
    
    /**
     * The method delete the selected user defined operation
     * @param event 
     */
    @FXML
    private void deleteOperationEvent(ActionEvent event) {
        String operationSequence = operationsTab.getSelectionModel().getSelectedItem();
        operation.delete(operationSequence);
    }
    
    /**
     * The method sets the text field of operationName and operationSequence respectively with
     *  the name and the sequence of the selected user defined operation, disabling the operationName textField.
     * @param event 
     */
    @FXML
    private void modifyOperationEvent(ActionEvent event) {
        operationName.setText(operationsTab.getSelectionModel().getSelectedItem());
        operationName.setDisable(true);
        operationSequence.setText(operation.getOperationsMap().get(operationsTab.getSelectionModel().getSelectedItem()));
    }
    
    /**
     * The method after double-clicking the sequence of operation of the selected user defined operation,
     * enables the sequence editing.
     * @param event 
     */
    @FXML
    private void editOperationEvent(TableColumn.CellEditEvent<String, String> event) {
        String operationSequence = operationsTab.getSelectionModel().getSelectedItem();
        operation.modify(operationSequence, event.getNewValue());
    }
    
    /**
     * The method saves the user defined operation in a .txt file. The name of the file is chosen by the user.
     * @param event 
     */
    @FXML
    private void saveOperations(ActionEvent event) {
        final FileChooser fc = new FileChooser();
        fc.setTitle("Scegli file");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt", "*.txt"));
        File file = fc.showSaveDialog(null);
        
        if(file != null){
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))){
                
                for(String name : keys){
                    
                    out.print(name);
                    out.print('=');
                    out.print(operation.getSequence(name).replace('=', '|'));
                    out.print('\n');
                }
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * The method loads operations from a txt file chosen by the user and saves them.
     * @param event 
     */
    @FXML
    private void loadOperations(ActionEvent event) {
        final FileChooser fc = new FileChooser();
        fc.setTitle("Scegli file");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt", "*.txt"));
        File file = fc.showOpenDialog(null);
        
        if(file != null){
            try(Scanner i = new Scanner(new BufferedReader(new FileReader(file)))){
                
                i.useDelimiter("=|\\n");
                operation.getOperationsMap().clear();
                try{
                
                while(i.hasNext()){
                    String name = i.next();
                    String expression = i.next();
                    operation.addOperation(name, expression);
                }
                }catch(WrongInputException ex){
                    wrongOperation("Wrong Format for the user defiend operations");
                }
                
            } catch (FileNotFoundException ex) {
                wrongOperation("File not found");
            }
        }
    }

}
