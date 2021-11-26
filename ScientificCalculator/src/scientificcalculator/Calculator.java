/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * The <em>Calculator</em> class contains all useful methods implemented for the correct 
 * functioning of the stack
 * 
 * @author group15
 */
public class Calculator {
    /*** Contains all the complex that will be inserted*/
    private Stack<Complex> complexStack;
    /*** */
    private Stack<VariableMap> variables;
    
    /**
     * Initialize new objects from the class Stack
     */
    public Calculator(){
        complexStack = new Stack<>();
        variables = new Stack<>();
        /**Push the new object {@code variables} into a VariableMap*/
        variables.push(new VariableMap());
    }
    
    /**
     * The method returns the stack
     * @return complexStack
     */
    public Stack<Complex> getComplexStack() {
        return complexStack;
    }

    /**
     * The method returns the last element of the variables whitin the VariableMap
     * @return variables
     */
    public VariableMap getVariables() {
        return variables.lastElement();
    }
    
    /**
     * The method insert a complex number into the stack
     * @param c {@code Complex}
     */
    public void insert(Complex c){
        complexStack.push(c);
    }
    /**
     * The method returns the last element within the stack
     * @return complexStack.lastElement
     */
    public Complex lastElement(){
        return complexStack.lastElement();
    }
    /**
     * The method removes the last two elements from the stack, adds them up
     * and adds the sum to the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void add() throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.pop();
        Complex b = complexStack.pop();
        
        insert(a.add(b));
    }
    
    /**
     * The method removes the last two elements from the stack, subs them up
     * and adds the sub to the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void sub() throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.pop();
        Complex b = complexStack.pop();
        
        insert(a.sub(b));
    }
    
    /**
     * The method removes the last two elements from the stack, multiplies them 
     * and adds the multiplication to the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void multiply() throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.pop();
        Complex b = complexStack.pop();
        
        insert(a.multiply(b));
    }
    
    /**
     * The method removes the last two elements from the stack, divides them 
     * and adds the division to the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void divide() throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.pop();
        Complex b = complexStack.pop();
        
        insert(a.divide(b));
    }
    
    /**
     * The method checks if there is at least one element in the stack,
     * if so it removes the element from the stack, executes the square root and
     * puts the result on the stack
     * @throws EmptyStackException if the stack is empty
     */
    public void square() throws EmptyStackException{
        if(complexStack.size()<1)
            throw new EmptyStackException();
        Complex a = complexStack.pop();
        
        insert(a.square());
    }
    
    /**
     * The method checks if there is at least one element in the stack,
     * if so it removes the element from the stack, reverses its sign and
     * puts the result on the stack
     * @throws EmptyStackException if the stack is empty
     */
    public void invert()throws EmptyStackException{
        if(complexStack.size()<1)
            throw new EmptyStackException();
        Complex a = complexStack.pop();
        
        insert(a.invert());
    }
    
    /**
     * The method checks if there's at least one element in the stack, 
     * if so it empties the stack
     * @throws EmptyStackException if the stack is empty
     */
    public void clear() throws EmptyStackException{
        if (complexStack.size() < 1)
            throw new EmptyStackException();
        complexStack.clear();
    }
    
    /**
     * The method checks if there's at least one element in the stack, 
     * if so it removes the stack
     * @throws EmptyStackException if the stack is empty
     */
    public void drop() throws EmptyStackException{
        if (complexStack.size() < 1)
            throw new EmptyStackException();
        complexStack.pop();
    }
   
    /**
     * The method checks if there's at least one element in the stack, if so
     * it initialize the current object like the last element and insert it 
     * 
     * @throws EmptyStackException if the stack is empty
     */
    public void dup() throws EmptyStackException{
        if (complexStack.size() < 1)
            throw new EmptyStackException();
        Complex a = complexStack.lastElement();
        
        insert(a);
    }
    
    /**
     * The method checks if there are at least two elements in the stack, if so
     * it removes them from the stack and insert them
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void swap() throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.pop();
        Complex b = complexStack.pop();
        
        insert(a);
        insert(b);
    } 
    /**
     * 
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void over()throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.get(complexStack.size() - 2);
        
        insert(a);
    }
}
