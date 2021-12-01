/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author benedettocirillo
 */
public class VariableNotFoundException extends RuntimeException {

    /**
     * Creates a new instance of <code>EmptyStack</code> without detail message.
     */
    public VariableNotFoundException() {
    }

    /**
     * Constructs an instance of <code>EmptyStack</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public VariableNotFoundException(String msg) {
        super(msg);
    }
}
