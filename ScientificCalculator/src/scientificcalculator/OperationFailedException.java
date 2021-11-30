/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

/**
 *
 * @author nicol
 */
public class OperationFailedException extends RuntimeException {

    /**
     * Creates a new instance of <code>OperationFailedException</code> without
     * detail message.
     */
    public OperationFailedException() {
    }

    /**
     * Constructs an instance of <code>OperationFailedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public OperationFailedException(String msg) {
        super(msg);
    }
}
