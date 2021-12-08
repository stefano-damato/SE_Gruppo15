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
        variables = new Stack<VariableMap>();
    }

    /**
     * The method creates a new instance of the VariableMap object and inserts it onto stack.
     */
    public void save(VariableMap vm) {
        variables.push(vm);
    }

    /**
     * The method deletes and returns the last inserted VariableMap object and restores the previous one.
     * @return 
     * @throws EmptyStackException if there are less than two elements
     */
    public VariableMap restore() throws EmptyStackException{
        if (variables.size() <= 1)
            throw new EmptyStackException();
        return variables.pop();
    }
}