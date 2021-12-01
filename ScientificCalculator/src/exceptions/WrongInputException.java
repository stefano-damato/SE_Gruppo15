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
public class WrongInputException extends RuntimeException{

    /**
     * Creates a new instance of <code>WrongInputExceptiony</code> without
     * detail message.
     */
    public WrongInputException() {
    }

    /**
     * Constructs an instance of <code>WrongInputExceptiony</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public WrongInputException(String msg) {
        super(msg);
    }
}
