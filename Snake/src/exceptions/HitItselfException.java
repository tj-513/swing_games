/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 *
 * @author TJR
 */
public class HitItselfException extends RuntimeException {

    /**
     * Creates a new instance of <code>HitItselfException</code> without detail
     * message.
     */
    public HitItselfException() {
    }

    /**
     * Constructs an instance of <code>HitItselfException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public HitItselfException(String msg) {
        super(msg);
    }
}
