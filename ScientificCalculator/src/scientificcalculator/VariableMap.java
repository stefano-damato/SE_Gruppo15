/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author benedettocirillo
 */
public class VariableMap {
    
    private HashMap<Character,Complex> variables;

    public VariableMap() {
        variables = new HashMap<>();
    }

    public HashMap<Character, Complex> getVariables() {
        return variables;
    }
    
    public void saveVariable(char key, Complex value) throws KeyNotAlphabeticException{
        //decide if lowerCase==UpperCase for the key
        if(!Character.isAlphabetic(key))
            throw new KeyNotAlphabeticException();
        if(variables.containsKey(key))
            variables.replace(key, value);
        else variables.put(key, value);
    }
    
    public Complex pushVariable(char key){
        //return null in case of there are no matches for the key 
        return variables.get(key);
    }
    
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
