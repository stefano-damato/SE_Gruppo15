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
public class KeyNotPresentInOperations extends RuntimeException {

    /**
     * Creates a new instance of <code>KeyNotPresentInOperations</code> without
     * detail message.
     */
    public KeyNotPresentInOperations() {
    }

    /**
     * Constructs an instance of <code>KeyNotPresentInOperations</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public KeyNotPresentInOperations(String msg) {
        super(msg);
    }
}
