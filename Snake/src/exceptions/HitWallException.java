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
public class HitWallException extends RuntimeException {

    /**
     * Creates a new instance of <code>HitWallException</code> without detail
     * message.
     */
    public HitWallException() {
    }

    /**
     * Constructs an instance of <code>HitWallException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public HitWallException(String msg) {
        super(msg);
    }
}
