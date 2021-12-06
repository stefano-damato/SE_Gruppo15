/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import exceptions.WrongInputException;
import exceptions.LessOf2ElementsException;
import exceptions.KeyNotAlphabeticException;
import exceptions.DivisionException;
import exceptions.KeyAlreadyPresentInOperations;
import exceptions.OperationFailedException;
import exceptions.VariableNotFoundException;
import java.net.URL;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *The <em>FXMLDocumentController</em> class represents controller class in the Model-View-Controller architecture
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
    @FXML
    private TableView<Complex> historyTab;
    @FXML
    private TableColumn<Complex, String> clmHistory;
    @FXML
    private TableView<Variable> variablesTab;
    @FXML
    private TableColumn<Variable, String> clmNameVariables;
    
    private Calculator memory = new Calculator();
    
    /** Observable list that will contain all the elements in <code>memory</code> making them observable*/
    private ObservableList<Complex>  list;
    
    /** Observable list that will contain all the variables in <code>memory</code> making them observable*/
    private ObservableList<Variable>  listVariables;
    @FXML
    private TableView<String> operationsTab;
    @FXML
    private TableColumn<String, String> clmOperation;
    @FXML
    private TableColumn<Variable, String> clmValuesVariables;
    @FXML
    private TableColumn<String, String> clmNameOperation;
    
    private Operations operation = memory.getOperations();
    
    private ObservableList<String> keys;
    @FXML
    private Button saveOperationButton;
    @FXML
    private TextField operationName;
    @FXML
    private TextField operationSequence;

        
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // If the textfield is empty it is not possible to press the button
        SimpleBooleanProperty check  = new SimpleBooleanProperty();
        check.bind(Bindings.when(text.textProperty().isEmpty()).then(true).otherwise(false));
        insertButton.disableProperty().bind(check);
        variablesButton.disableProperty().bind(check);
        
        SimpleBooleanProperty checkOperation  = new SimpleBooleanProperty();
        checkOperation.bind(Bindings.when(operationName.textProperty().isEmpty().and(operationSequence.textProperty().isEmpty())).then(true).otherwise(false));
        saveOperationButton.disableProperty().bind(checkOperation);
        
        list = FXCollections.observableArrayList();
        clmHistory.setCellValueFactory(new PropertyValueFactory<Complex,String>("complex"));
        historyTab.setItems(list);
        
        listVariables = FXCollections.observableArrayList();
        clmNameVariables.setCellValueFactory(new PropertyValueFactory<Variable,String>("name"));
        clmValuesVariables.setCellValueFactory(new PropertyValueFactory<Variable,String>("value"));
        variablesTab.setItems(listVariables);
        
        keys = FXCollections.observableArrayList();
        operation.getOperations().addListener((MapChangeListener.Change<? extends String, ? extends String> change) -> {
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
        clmOperation.setCellValueFactory(cd -> Bindings.valueAt ( operation.getOperations(), cd.getValue()));
        clmOperation.setCellFactory(TextFieldTableCell.forTableColumn());
        operationsTab.setItems(keys);
        operationsTab.getColumns().setAll(clmNameOperation, clmOperation);
    }    
    
    /**
     * This method creates a second window showing the string <code>msg</code> as text
     * @param msg {@code String}
     */
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
    
    /**
     * This method execute the <code>insert</code> method of <code>memory</code>
     * and inserts the element in the head of <code>list</code>
     * after pressing the button addButton
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
            memory.insert(insertedText);
            list.add(0, memory.lastElement());

            }catch(WrongInputException ex){
                wrongOperation("Input is not in correct form:\n a + bj");
            }catch(IndexOutOfBoundsException ex){
                wrongOperation("Input is not in correct form:\n a + bj");
            }finally{
                text.setText("");
            }
        }
    }
    
    /**
     * This method execute the <code>add</code> method of <code>memory</code>
     * and inserts the result in the head of <code>list</code> in place of the first two elements
     * after pressing the button addButton
     * @param event {@code ActionEvent}
     */
    @FXML
    private void addEvent(ActionEvent event) {
        try{
            memory.add();
            list.remove(0);
            list.remove(0);
            list.add(0, memory.lastElement());
        } catch(LessOf2ElementsException ex){
            wrongOperation("There must be at least two elements!");
        }
    }
    
    /**
     * This method execute the <code>sub</code> method of <code>memory</code>
     * and inserts the result in the head of <code>list</code> in place of the first two elements
     * after pressing the button subButton
     * @param event {@code ActionEvent}
     */
    @FXML
    private void subEvent(ActionEvent event) {
        try{
            memory.sub();
            list.remove(0);
            list.remove(0);
            list.add(0, memory.lastElement());
        } catch(LessOf2ElementsException ex){
            wrongOperation("There must be at least two elements!");
        }
        
    }
    
    /**
     * This method execute the <code>multiply</code> method of <code>memory</code>
     * and inserts the result in the head of <code>list</code> in place of the first two elements
     * after pressing the button multiplyButton
     * @param event {@code ActionEvent}
     */
    @FXML
    private void multiplyEvent(ActionEvent event) {
        try{    
            memory.multiply();
            list.remove(0);
            list.remove(0);
            list.add(0, memory.lastElement());
        } catch(LessOf2ElementsException ex){
            wrongOperation("There must be at least two elements!");
        }
    }
    
    /**
     * This method execute the <code>divde</code> method of <code>memory</code>
     * and inserts the result in the head of <code>list</code> in place of the first two elements
     * after pressing the button divideButton 
     * @param event {@code ActionEvent}
     */
    @FXML
    private void divideEvent(ActionEvent event) {
        try{  
            memory.divide();
            list.remove(0);
            list.remove(0);  
            list.add(0, memory.lastElement());
        } catch(LessOf2ElementsException ex){
            wrongOperation("There must be at least two elements!");
        } catch(DivisionException ex){
            wrongOperation("Division not possible");
        }
    }
    
    /**
     * This method execute the <code>square</code> method of <code>memory</code>
     * and inserts the result in the head of <code>list</code> in place of the first element
     * after pressing the button sqrtButton 
     * @param event {@code ActionEvent}
     */
    @FXML
    private void sqrtEvent(ActionEvent event) {
        try{    
            memory.square();
            list.remove(0);
            list.add(0, memory.lastElement());
        } catch(EmptyStackException ex){
            wrongOperation("There must be at least one element!");
        }
    }
    
    /**
     * This method execute the <code>invert</code> method of <code>memory</code>
     * and inserts the result in the head of <code>list</code> in place of the first element
     * after pressing the button invertButton  
     * @param event {@code ActionEvent}
     */
    @FXML
    private void invertEvent(ActionEvent event) {
        try{    
            memory.invert();
            list.remove(0);
            list.add(0, memory.lastElement());
        } catch(EmptyStackException ex){
            wrongOperation("There must be at least one element!");
        }    
    }
    
    /**
     * This method execute the <code>clear</code> method of <code>memory</code>
     * and delete all the elements in <code>list</code>
     * after pressing the button clearButton
     * @param event {@code ActionEvent}
     */
    @FXML
    private void clearEvent(ActionEvent event) {
        try{
            memory.clear();
            list.clear();
        }catch (EmptyStackException ex){
            wrongOperation("There must be at least one element!");
        }
    }
    
    /**
     * This method execute the <code>drop</code> method of <code>memory</code>
     * and delete the last element in <code>list</code> 
     * and write it in the text field after pressing the button dropButton 
     * @param event {@code ActionEvent}
     */
    @FXML
    private void dropEvent(ActionEvent event) {
        try{
            memory.drop();
            text.setText(memory.lastElement().toString());
            list.remove(0);
        } catch (EmptyStackException ex){
            wrongOperation("There must be at least one element!");
        }
    }
    
    /**
     * This method execute the <code>dup</code> method <code>memory</code>
     * and duplicates the last element in <code>list</code>
     * after pressing the button dupButton  
     * @param event {@code ActionEvent}
     */
    @FXML
    private void dupEvent(ActionEvent event) {
        try{
            memory.dup();
            list.add(0, list.get(0));
        } catch(EmptyStackException ex){
            wrongOperation("There must be at least one element!");
        }
    }
    
    /**
     * This method execute the <code>swap</code> method <code>memory</code>
     * and swap the last two elements in <code>list</code>
     * after pressing the button swapButton 
     * @param event {@code ActionEvent}
     */
    @FXML
    private void swapEvent(ActionEvent event) {
        try{
            memory.swap();
            list.add(0, list.get(1));
            list.remove(2);
        } catch(LessOf2ElementsException ex){
            wrongOperation("There must be at least two elements!");
        }
    }
    
    /**
     * This method execute the <code>over</code> method <code>memory</code> 
     * and inserts a copy of the second last element in the head of the <code>list</code> 
     * after pressing the button overButton  
     * @param event {@code ActionEvent}
     */
    @FXML
    private void overEvent(ActionEvent event) {
        try{
            memory.over();
            list.add(0, list.get(1));
        } catch (LessOf2ElementsException ex){
            wrongOperation("There must be at least two elements!");
        }
    }
    
    /**
     * This method executes the operation on the variables according to what is 
     * written in the text field after pressing the button 
     * @param event {@code ActionEvent}
     */
    @FXML
    private void variablesEvent(ActionEvent event) {
        try{
            String input=text.getText().toLowerCase().trim();
            checkValidInputForVariables(input);
            char key=input.charAt(1);
            switch(input.charAt(0)){
                case '>':{
                    Complex c= memory.drop();
                    Variable var = new Variable(key,c);
                    memory.getVariables().saveVariable(var);
                    list.remove(0);

                    if(listVariables.contains(var))
                        listVariables.set(listVariables.indexOf(var), var);
                    else listVariables.add(var);
                    Collections.sort(listVariables);
                    
                    break;
                }
                case '<':{
                    memory.insert(memory.getVariables().pushVariable(new Variable(key, null)));
                    list.add(0, memory.lastElement());
                    
                    break;
                }
                case '+':{
                    Complex c= memory.drop();
                    Variable var = new Variable(key,c);
                    
                    memory.getVariables().addVariable(var);
                    list.remove(0);
                    
                    if(listVariables.contains(var)){
                        Complex oldVar= listVariables.get(listVariables.indexOf(var)).getValue();
                        listVariables.set(listVariables.indexOf(var), new Variable(key,oldVar.add(c)));
                    }
                    else{
                        listVariables.add(var);
                    }
                    
                    break;
                }
                case '-':{
                    Complex c= memory.drop();
                    Variable var = new Variable(key,c);
                    
                    memory.getVariables().subVariable(var);
                    list.remove(0);
                    
                    if(listVariables.contains(var)){
                        Complex oldVar= listVariables.get(listVariables.indexOf(var)).getValue();
                        listVariables.set(listVariables.indexOf(var), new Variable(key,oldVar.sub(c)));
                    }
                    else{
                        listVariables.add(var);
                    }
                    
                    break;
                }
                default:{
                    wrongOperation("Invalid Input");
                }
            }
        } catch (KeyNotAlphabeticException ex){
            wrongOperation("The calculator supports 26 variables:\n from \"a\" to \"z\"");
        }catch (WrongInputException ex){
            wrongOperation("Invalid Input");
        }catch (EmptyStackException ex){
            wrongOperation("No numbers in memory");
        }catch (VariableNotFoundException ex){
            wrongOperation("Variable not available");
        }
    }

    @FXML
    private void saveVarEvent(ActionEvent event) {
    }
    
    
    @FXML
    private void restoreVarEvent(ActionEvent event) {
    }
    
    /**
     * The method checks if the string <code>s</code> is in 
     * the correct form to do operations with variables
     * @param s {@code String}
     * @throws WrongInputException 
     */
    private void checkValidInputForVariables(String s) throws WrongInputException{
        if(s.length()!=2 || !s.matches("^[a-z+-><]+$"))
            throw new WrongInputException();
    }
    
    
    /*@FXML
    private void editNameOperationEvent(TableColumn.CellEditEvent<S, T> event) {
    }
*/

    @FXML
    private void saveOperationEvent(ActionEvent event) {
        try{
            checkUserDefinedOpration(text.getText().trim());
            if(operationName.isDisabled()){
                operation.modify(operationName.getText().trim(), operationSequence.getText().trim());
                operationName.setDisable(false);
            }else{
                operation.addOperation(operationName.getText().trim(), operationSequence.getText().trim());
            }
            operationName.setText("");
            operationSequence.setText(""); 
        }catch(KeyAlreadyPresentInOperations ex){
            wrongOperation("Operation name already present");
        }catch(WrongInputException ex){
            wrongOperation("Operation name invalid");
        }
        
    }

    @FXML
    private void invokeEvent(ActionEvent event) {
        try {
            memory.invokeOperation(operationsTab.getSelectionModel().getSelectedItem());
        }catch (OperationFailedException ex) {
            wrongOperation("Operation failed");
        }finally{
            list.clear();
            for(Complex c: memory.getComplexStack()){
                list.add(0,c);
            }
        }
    }

    @FXML
    private void deleteOperationEvent(ActionEvent event) {
        String operationSequence = operationsTab.getSelectionModel().getSelectedItem();
        operation.delete(operationSequence);
    }

    @FXML
    private void modifyOperationEvent(ActionEvent event) {
        operationName.setText(operationsTab.getSelectionModel().getSelectedItem());
        operationName.setDisable(true);
        operationSequence.setText(operation.getOperations().get(operationsTab.getSelectionModel().getSelectedItem()));
    }
    
    @FXML
    private void editOperationEvent(TableColumn.CellEditEvent<String, String> event) {
        String operationSequence = operationsTab.getSelectionModel().getSelectedItem();
        operation.modify(operationSequence, event.getNewValue());
    }
    
    public void checkUserDefinedOpration(String string) throws WrongInputException{
        if(string.matches("^[a-zA-Z]+$")){
                throw new WrongInputException();
            }
    }

    @FXML
    private void saveOperations(ActionEvent event) {
    }

    @FXML
    private void restoreOperations(ActionEvent event) {
    }

}
