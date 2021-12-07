/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 *
 * @author stefa
 */
public class VariableMapStack {
    
    private Stack<VariableMap> variables;

    public VariableMapStack() {
        variables.push(new VariableMap());
    }
    
    /**
     * The method allows to modify the last set of variables saved in the stack
     * @return variables
     */
    public VariableMap getLast() {
        return variables.lastElement();
    }
    
    /**
     * The method creates a new instance of the VariableMap object and inserts it onto stack.
     */
    public void save() {
        VariableMap newMap = new VariableMap();
        newMap.getVariables().putAll(getLast().getVariables());
        variables.push(newMap);
    }
    
    /**
     * The method deletes the last inserted VariableMap object and restores the previous one.
     * @throws EmptyStackException if there are less than two elements
     */
    public void restore() throws EmptyStackException{
        if (variables.size() <= 1)
            throw new EmptyStackException();
        variables.pop();
    }
}
