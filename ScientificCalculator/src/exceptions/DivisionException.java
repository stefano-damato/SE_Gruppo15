/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author stefa
 */
public class DivisionException extends RuntimeException {

    /**
     * Creates a new instance of <code>DivisionException</code> without detail
     * message.
     */
    public DivisionException() {
    }

    /**
     * Constructs an instance of <code>DivisionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DivisionException(String msg) {
        super(msg);
    }
}
