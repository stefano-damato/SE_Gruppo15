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
     * ObservableMap that keeps the key,value correspondence:
     * The <b>key</b> is a {@code Character} that represents the name of the {@link scientificcalculator.Variable Variable}.
     * The <b>value</b> is a {@code {@link scientificcalculator.Complex Complex}} that represents the value of the {@link scientificcalculator.Variable Variable}.
     */
    private ObservableMap<Character,Complex> variables;

    /**
     * Initialize object of the class {@link #VariableMap VariableMap} by initializing {@link #variables variables}
     * to a {@link javafx.collections.FXCollections#observableHashMap() ObservableHashMap}.
     */
    public VariableMap() {
        variables = FXCollections.observableHashMap();
    }
    
    /**
     * The method returns the reference to the {@link #variables variables}
     * @return variables {@code ObservableMap<Character,Complex>}
     */
    public ObservableMap<Character, Complex> getVariables() {
        return variables;
    }
    /**
     * The method saves a {@link scientificcalculator.Variable Variable} in {@link #variables variables}.
     * If the name of the object Variable is already present, it overwrites the value.
     * @param var {@code Variable}
     * @throws KeyNotAlphabeticException if the name of Variable is not alphabetic.
     */
    public void save(Variable var) throws KeyNotAlphabeticException{
        char key = var.getName();
        Complex value = var.getValue();
        if(!Character.isAlphabetic(key))
            throw new KeyNotAlphabeticException();
        if(variables.containsKey(key))
            variables.replace(key, value);
        else variables.put(key, value);
    }
    
    /**
     * The method returns the value of the {@link scientificcalculator.Variable Variable} which has the same name as the variable passed as a parameter.
     * @param var {@code Variable} the variable whose value I want to return
     * @return value {@code {@link scientificcalculator.Variable Variable}}
     */
    public Complex push(Variable var) throws VariableNotFoundException{
        //return null in case of there are no matches for the key 
        if(!variables.containsKey(var.getName()))
            throw new VariableNotFoundException();
        Complex value= variables.get(var.getName());
        return value;
    }
    /**
     * The method saves the sum of the value of the {@link scientificcalculator.Variable Variable} passed as a parameter with the current value of the variable 
     * that have the same name of the variable passed as a parameter. If the name of the var is not present, it will be saved into {@link #variables variables}.
     * @param var {@code Variable} the variable whose value I want to add
     * @throws KeyNotAlphabeticException if the name of Variable is not alphabetic.
     */
    public void add(Variable var) throws KeyNotAlphabeticException{
        char key = var.getName();
        Complex value = var.getValue();
       if(!Character.isAlphabetic(key))
            throw new KeyNotAlphabeticException();
        if(variables.containsKey(key))
            variables.replace(key, variables.get(key).add(value));
        else save(var);
       
    }
    /**
     * The method saves the subtraction of the value of the variable passed as a parameter with the current value of the variable 
     * that corresponds to the key of the variable passed as a parameter.
     * @param var {@code Variable}
     * @throws KeyNotAlphabeticException
     */
    public void sub(Variable var) throws KeyNotAlphabeticException{
        char key = var.getName();
        Complex value = var.getValue();
       if(!Character.isAlphabetic(key))
            throw new KeyNotAlphabeticException();
        if(variables.containsKey(key))
            variables.replace(key, variables.get(key).sub(value));
        else save(var);
       
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
