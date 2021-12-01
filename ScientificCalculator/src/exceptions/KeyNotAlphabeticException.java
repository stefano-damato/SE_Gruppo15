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
public class KeyNotAlphabeticException extends RuntimeException{

    /**
     * Creates a new instance of <code>KeyNotAlphabetic</code> without detail
     * message.
     */
    public KeyNotAlphabeticException()  {
    }

    /**
     * Constructs an instance of <code>KeyNotAlphabetic</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public KeyNotAlphabeticException(String msg) {
        super(msg);
    }
}
