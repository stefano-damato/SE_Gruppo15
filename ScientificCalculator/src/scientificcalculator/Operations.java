/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import exceptions.KeyNotPresentInOperations;
import exceptions.KeyAlreadyPresentInOperations;
import exceptions.WrongInputException;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 * The <em>Operations</em> class represents a user-defined operation characterized by a name and the sequence of operations to be performed.
 * 
 * @author group15
 */

public class Operations {
    /**
     * Keeps the key,value correspondence:
     * The key is a String that represents the name of the user-defined operation.
     * The value is a String that represents the sequence of operations of the user-defined operation.
     */
    private ObservableMap<String,String> operationsMap;
    
    /**
     * Initialize object of the class <em>Operations</em> 
     */
    public Operations() {
        operationsMap = FXCollections.observableHashMap();
    }
    
    /**
     * The method adds a user-defined operation.
     * @param name {@code String}
     * @param sequence {@code String}
     * @throws KeyAlreadyPresentInOperations  if the operation to be added has the same name as an operation already present in the map.
     */
    public void addOperation(String name, String sequence) throws KeyAlreadyPresentInOperations, WrongInputException{
        checkUserDefinedOpration(name);
        if (!operationsMap.containsKey(name)) 
            operationsMap.put(name, sequence);
        else throw new KeyAlreadyPresentInOperations();
    }
    
    /**
     * The method modifies the sequence of operations of a user-defined operation already present in the map.
     * @param name {@code String}
     * @param sequence {@code String}
     * @throws KeyNotPresentInOperations if the operation to be modified is not present in the map.
     */
    public void modify(String name, String sequence) throws KeyNotPresentInOperations, WrongInputException{
        checkUserDefinedOpration(name);
        if (operationsMap.containsKey(name))
            operationsMap.replace(name, sequence);
        else throw new KeyNotPresentInOperations();
    }
    
    /**
     * The method deletes from the map the user-defined operation that has the name corresponding to the one passed as parameter.
     * @param name {@code String}
     * @throws KeyNotPresentInOperations if the operation to be deleted is not present in the map.
     */
    public void delete(String name) throws KeyNotPresentInOperations{
        if(operationsMap.containsKey(name))
            operationsMap.remove(name);
        else throw new KeyNotPresentInOperations();
    }
    
    /**
     * The method checks whether there is an operation in the map that has the name that matches the name passed as a parameter.
     * @param name {@code String}
     * @return boolean value, true if it exists, false otherwise.
     */
    public boolean containName(String name) {
        return operationsMap.containsKey(name);
    }
    
    /**
     * The method returns the sequence of operations associated with the user-defined operation whose name is the same as the one passed as parameter.
     * @param name {@code String}
     * @return sequence {@code String}
     */
    public String getSequence(String name) {
        return operationsMap.get(name);
    }
    
    /**
     * The method returns the reference to the map.
     * @return operations {@code HashMap<String,String>}
     */
    public ObservableMap<String, String> getOperationsMap() {
        return operationsMap;
    }
    
    public void checkUserDefinedOpration(String string) throws WrongInputException{
        if(!string.matches("^[a-zA-Z]+$")){
            throw new WrongInputException();
        }
    }
    
    
}

