/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * The <em>VariableMapStack</em> represents a stack of {@link scientificcalculator.VariableMap VariableMap}
 * @author group15
 */
public class VariableMapStack {
    /** Contains all the {@link scientificcalculator.VariableMap VariableMap} that will be saved*/
    private Stack<VariableMap> variables;
    
    /** Initialize {@link #variables variables} to a empty Stack */
    public VariableMapStack() {
        variables = new Stack<VariableMap>();
    }
    
    /**
     * The method push into {@link #variables variables} a {@link scientificcalculator.VariableMap VariableMap}.
     * @param vm {@code VariableMap} the VariableMap to save.
     */
    public void save(VariableMap vm) {
        variables.push(vm);
    }

    /**
     * The method deletes and returns the last inserted {@link scientificcalculator.VariableMap VariableMap} object.
     * @return {@code VariableMap} the last element of {@link #variables variables}
     * @throws EmptyStackException if there are less than one element
     */
    public VariableMap restore() throws EmptyStackException{
        if (variables.size() < 1)
            throw new EmptyStackException();
        return variables.pop();
    }
}