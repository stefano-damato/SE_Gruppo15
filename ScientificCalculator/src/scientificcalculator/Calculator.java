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
public class Calculator {
    
    private Stack<Complex> complexStack;
    
    public Calculator(){
        complexStack = new Stack<>();
    }
    
    public void add() throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.pop();
        Complex b = complexStack.pop();
        
        complexStack.push(a.add(b));
    }
    
    public void sub() throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.pop();
        Complex b = complexStack.pop();
        
        complexStack.push(a.sub(b));
    }
    
    public void multiply() throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.pop();
        Complex b = complexStack.pop();
        
        complexStack.push(a.multiply(b));
    }
    
    public void divide() throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.pop();
        Complex b = complexStack.pop();
        
        complexStack.push(a.divide(b));
    }
    
    public void square(){
        if(complexStack.size()<1)
            throw new EmptyStackException();
        Complex a = complexStack.pop();
        
        complexStack.push(a.square());
    }
}
