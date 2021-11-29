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
    
    private Calculator memory = new Calculator();
    
    /** Observable list that will contain all the elements in <code>memory</code> making them observable*/
    private ObservableList<Complex>  list;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // If the textfield is empty it is not possible to press the button
        SimpleBooleanProperty check  = new SimpleBooleanProperty();
        check.bind(Bindings.when(text.textProperty().isEmpty()).then(true).otherwise(false));
        insertButton.disableProperty().bind(check);
        variablesButton.disableProperty().bind(check);
        
        list = FXCollections.observableArrayList();
        clmHistory.setCellValueFactory(new PropertyValueFactory<Complex,String>("complex"));

        historyTab.setItems(list);
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
    private void insetEvent(ActionEvent event) {
        try{
         
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
        
        checkValidInput(insertText.trim());
        
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
        c = new Complex(real, imaginary);
        memory.insert(c);
        list.add(0, memory.lastElement());
        text.setText("");
            
        }catch(WrongInputException ex){
            wrongOperation("Input is not in correct form:\n a + j b");
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
            list.remove(0);
            list.remove(0);  
            memory.divide();
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
            text.setText(memory.lastElement().toString());
            memory.drop();
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
            checkValidInputForVariables(text.getText().toLowerCase());
            char key= text.getText().toLowerCase().charAt(1);
            if(text.getText().charAt(0)=='>'){
                memory.getVariables().saveVariable(key, memory.drop());
                list.remove(0);
                text.setText("");
            }else if(text.getText().charAt(0)=='<'){
                memory.insert(memory.getVariables().pushVariable(key));
                list.add(0, memory.lastElement());
                text.setText("");
            }
        } catch (KeyNotAlphabeticException ex){
            wrongOperation("The calculator supports 26 variables:\n from \"a\" to \"z\"");
        }catch (WrongInputException ex){
            wrongOperation("Invalid Input");
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
        if(s.length()>2 || !s.matches("^[a-z+-><]+$"))
            throw new WrongInputException();
    }
    
    
    /**
     * The method checks if the string <code>s</code>  is in the form a + j b. a and b must be real numbers.
     * @param s {@code String}
     * @return <code>true</code>  if the string is correct
     * @throws WrongInputException 
     */
    private boolean checkValidInput(String s) throws WrongInputException{
        if(!s.contains("j")){
            if(!s.matches("^[0-9. ]+$"))
            throw new WrongInputException();
        }else{
            if(!s.matches("^[0-9+-.j ]+$")){
                throw new WrongInputException();
            }else{
                if(s.indexOf("j")==0){
                    if(!s.substring(1).trim().matches("^[0-9.]+$") && s.length()>1)
                        throw new WrongInputException();
                    return true;
                }else{
                    String[] parseText= s.split("[+-]");
                    String s1 = parseText[0].trim();
                    String s2 = parseText[1].trim();
                    if(!s1.matches("^[0-9.]+$"))
                        throw new WrongInputException();
                    if(s2.lastIndexOf("j")>0){
                        throw new WrongInputException();
                    }else{
                        if(s2.length()==1)
                            return true;
                        s2=s2.substring(1).trim();
                        if(!s2.matches("^[0-9.]+$"))
                            throw new WrongInputException();
                    }
                } 
            } 
        }
            
        return true;
    }
}
