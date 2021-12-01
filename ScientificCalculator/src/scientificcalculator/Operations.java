/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import exceptions.KeyNotPresentInOperations;
import exceptions.KeyAlreadyPresentInOperations;
import java.util.HashMap;

/**
 *
 * @author nicol
 */
public class Operations {
    private HashMap<String,String> operations;

    public Operations() {
        operations = new HashMap<>();
    }
    
    public void addOperation(String name, String sequence) throws KeyAlreadyPresentInOperations{
        if (!operations.containsKey(name)) 
            operations.put(name, sequence);
        else throw new KeyAlreadyPresentInOperations();
    }
    
    public void modify(String name, String sequence) throws KeyNotPresentInOperations{
        if (operations.containsKey(name))
            operations.replace(name, sequence);
        else throw new KeyNotPresentInOperations();
    }
    
    public void delete(String name) throws KeyNotPresentInOperations{
        if(operations.containsKey(name))
            operations.remove(name);
        else throw new KeyNotPresentInOperations();
    }
    
    public boolean containName(String name) {
        return operations.containsKey(name);
    }
    
    public String getOperation(String name) {
        return operations.get(name);
    }
}

