/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.util.HashMap;
import java.util.Objects;

/**
 *  The <em>VariableMap</em> class represents the set of all variables that the user can use
 * @author group15
 */
public class VariableMap {
    
    /**
     * Keeps the key,value correspondence:
     * Character is the Key
     * Complex is the Value
     */
    private HashMap<Character,Complex> variables;

    /**
     * Initialize object of the class <em>VariableMap</em> 
     */
    public VariableMap() {
        variables = new HashMap<>();
    }
    
    /**
     * The method returns the reference to the map
     * @return variables {@code HashMap<Character,Complex>}
     */
    public HashMap<Character, Complex> getVariables() {
        return variables;
    }
    /**
     * The method saves the value in variables by specifying the key.
     * If the key is already present, it overwrites the value.
     * If the key is not alphabetic throw an exception.
     * @param key {@code Character}
     * @param value {@code Complex}
     * @throws KeyNotAlphabeticException 
     */
    public void saveVariable(char key, Complex value) throws KeyNotAlphabeticException{
        if(!Character.isAlphabetic(key))
            throw new KeyNotAlphabeticException();
        if(variables.containsKey(key))
            variables.replace(key, value);
        else variables.put(key, value);
    }
    
    /**
     * The method returns the value contained in the indicated key.
     * @param key {@code char}
     * @return value {@code Complex}
     */
    public Complex pushVariable(char key){
        //return null in case of there are no matches for the key 
        Complex value= variables.get(key);
        return value;
    }
    /**
     * The method saves the sum of value and current value of variable 
     * specified by the key in the variable.
     * @param key {@code char}
     * @param value {@code Complex}
     * @throws KeyNotAlphabeticException
     */
    public void addVariable(char key, Complex value) throws KeyNotAlphabeticException{
       if(!Character.isAlphabetic(key))
            throw new KeyNotAlphabeticException();
        if(variables.containsKey(key))
            variables.replace(key, variables.get(key).add(value));
        else variables.put(key, value);
       
    }
    /**
     * The method saves the subtraction of value and current value of variable 
     * specified by the key in the variable.
     * @param key {@code char}
     * @param value {@code Complex}
     * @throws KeyNotAlphabeticException
     */
    public void subVariable(char key, Complex value) throws KeyNotAlphabeticException{
       if(!Character.isAlphabetic(key))
            throw new KeyNotAlphabeticException();
        if(variables.containsKey(key))
            variables.replace(key, variables.get(key).sub(value));
        else variables.put(key, value);
       
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
