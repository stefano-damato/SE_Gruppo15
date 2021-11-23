/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

/**
 *
 * @author stefa
 */
public class LessOf2ElementsException extends RuntimeException {

    /**
     * Creates a new instance of <code>LessOf2Elements</code> without detail
     * message.
     */
    public LessOf2ElementsException() {
    }

    /**
     * Constructs an instance of <code>LessOf2Elements</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public LessOf2ElementsException(String msg) {
        super(msg);
    }
}
