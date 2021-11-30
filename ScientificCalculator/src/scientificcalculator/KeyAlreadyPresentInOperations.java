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
public class KeyAlreadyPresentInOperations extends RuntimeException {

    /**
     * Creates a new instance of <code>KeyAlreadyPresentInOperations</code>
     * without detail message.
     */
    public KeyAlreadyPresentInOperations() {
    }

    /**
     * Constructs an instance of <code>KeyAlreadyPresentInOperations</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public KeyAlreadyPresentInOperations(String msg) {
        super(msg);
    }
}
