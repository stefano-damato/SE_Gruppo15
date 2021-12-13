/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import exceptions.KeyNotPresentInOperations;
import exceptions.KeyAlreadyPresentInOperations;
import exceptions.WrongInputException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 * The <em>Operations</em> class represents the set of user-defined operation characterized by a name and the sequence of operations to be performed.
 * 
 * @author group15
 */

public class Operations {
    /**
     * ObservableMap that keeps the key,value correspondence:
     * The <b>key</b> is a {@code String} that represents the name of the user-defined operation.
     * The <b>value</b> is a {@code String} that represents the sequence of operations of the user-defined operation.
     */
    private ObservableMap<String,String> operationsMap;
    
    /**
     * Initialize object of the class {@link #Operations Operations}  by initializing {@link #operationsMap operationsMap}
     * to a {@link javafx.collections.FXCollections#observableHashMap() ObservableHashMap}.
     */
    public Operations() {
        operationsMap = FXCollections.observableHashMap();
    }
    
    /**
     * The method adds a user-defined operation to the {@link #operationsMap operationsMap}.
     * @param name {@code String} the name of the user-defined operation that you want to add
     * @param sequence {@code String} the sequence of operations of the new user-defined operation
     * @throws KeyAlreadyPresentInOperations  if the operation to be added has the same name as an operation already present in the map.
     */
    public void addOperation(String name, String sequence) throws KeyAlreadyPresentInOperations{
        checkUserDefinedOpration(name);
        if (!operationsMap.containsKey(name)) 
            operationsMap.put(name, sequence);
        else throw new KeyAlreadyPresentInOperations();
    }
    
    /**
     * The method modifies the sequence of operations of a user-defined operation already present in the {@link #operationsMap operationsMap}.
     * @param name {@code String} the name of the user-defined operation that you want to change
     * @param sequence {@code String} the new sequence of operations of the user-defined operation
     * @throws KeyNotPresentInOperations if the operation to be modified is not present in the {@link #operationsMap operationsMap}.
     */
    public void modify(String name, String sequence) throws KeyNotPresentInOperations{
        if (operationsMap.containsKey(name))
            operationsMap.replace(name, sequence);
        else throw new KeyNotPresentInOperations();
    }
    
    /**
     * The method deletes from the {@link #operationsMap operationsMap} the user-defined operation that has the same name of the {@code String} passed as a parameter.
     * @param name {@code String} the name of the sequence of operations
     * @throws KeyNotPresentInOperations if the operation to be deleted is not present in the map.
     */
    public void delete(String name) throws KeyNotPresentInOperations{
        if(operationsMap.containsKey(name))
            operationsMap.remove(name);
        else throw new KeyNotPresentInOperations();
    }
    
    /**
     * The method checks whether there is an operation in the {@link #operationsMap operationsMap} that has the name that matches the {@code String} passed as a parameter.
     * @param name {@code String} the name of the sequence of operations
     * @return {@code boolean} - {@code true} if it exists, {@code false} otherwise.
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
     * The method returns the reference to the ObservableMap {@link #operationsMap operationsMap}.
     * @return operations {@code ObservableMap<String,String>}
     */
    public ObservableMap<String, String> getOperationsMap() {
        return operationsMap;
    }
    
    /**
     * The method check if the name of the user defined is formed only by letters.
     * @param string {@code String} the name of the user defined operation to check
     * @throws WrongInputException if the name is not in the correct form
     */
    public void checkUserDefinedOpration(String string) throws WrongInputException{
        if(!string.matches("^[a-zA-Z]+$")){
            throw new WrongInputException();
        }
    }
    
    
}

