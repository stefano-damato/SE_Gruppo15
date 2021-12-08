/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import exceptions.KeyNotAlphabeticException;
import exceptions.VariableNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 *  The <em>VariableMap</em> class represents the set of all variables that the user can use
 * @author group15
 */
public class VariableMap{
    
    /**
     * Keeps the key,value correspondence:
     * Character is the Key
     * Complex is the Value
     */
    private ObservableMap<Character,Complex> variables;

    /**
     * Initialize object of the class <em>VariableMap</em> 
     */
    public VariableMap() {
        variables = FXCollections.observableHashMap();
    }
    
    /**
     * The method returns the reference to the map
     * @return variables {@code HashMap<Character,Complex>}
     */
    public ObservableMap<Character, Complex> getVariables() {
        return variables;
    }
    /**
     * The method saves the value in variables by specifying the Variable.
     * If the key of Variable is already present, it overwrites the value.
     * If the key of Variable is not alphabetic throw an exception.
     * @param var {@code Variable}
     * @throws KeyNotAlphabeticException 
     */
    public void saveVariable(Variable var) throws KeyNotAlphabeticException{
        char key = var.getName();
        Complex value = var.getValue();
        if(!Character.isAlphabetic(key))
            throw new KeyNotAlphabeticException();
        if(variables.containsKey(key))
            variables.replace(key, value);
        else variables.put(key, value);
    }
    
    /**
     * The method returns the value of the variable which has the same key as the variable passed as a parameter.
     * @param var {@code Variable}
     * @return value {@code Complex}
     */
    public Complex pushVariable(Variable var) throws VariableNotFoundException{
        //return null in case of there are no matches for the key 
        if(!variables.containsKey(var.getName()))
            throw new VariableNotFoundException();
        Complex value= variables.get(var.getName());
        return value;
    }
    /**
     * The method saves the sum of the value of the variable passed as a parameter with the current value of the variable 
     * that corresponds to the key of the variable passed as a parameter.
     * @param var {@code Variable}
     * @throws KeyNotAlphabeticException
     */
    public void addVariable(Variable var) throws KeyNotAlphabeticException{
        char key = var.getName();
        Complex value = var.getValue();
       if(!Character.isAlphabetic(key))
            throw new KeyNotAlphabeticException();
        if(variables.containsKey(key))
            variables.replace(key, variables.get(key).add(value));
        else saveVariable(var);
       
    }
    /**
     * The method saves the subtraction of the value of the variable passed as a parameter with the current value of the variable 
     * that corresponds to the key of the variable passed as a parameter.
     * @param var {@code Variable}
     * @throws KeyNotAlphabeticException
     */
    public void subVariable(Variable var) throws KeyNotAlphabeticException{
        char key = var.getName();
        Complex value = var.getValue();
       if(!Character.isAlphabetic(key))
            throw new KeyNotAlphabeticException();
        if(variables.containsKey(key))
            variables.replace(key, variables.get(key).sub(value));
        else saveVariable(var);
       
    }
    
    /**
     * The method overrides the {@link #equals(java.lang.Object) } of the Object {@link java.lang.Object}
     * @param obj
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VariableMap other = (VariableMap) obj;
        if (!Objects.equals(this.variables, other.variables)) {
            return false;
        }
        return true;
    }
    
     
}
