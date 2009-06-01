/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.exception;

/**
 *
 * @author marcus
 */
public class IvelaException extends Exception{
    
    public IvelaException() {
    }

    public IvelaException(Throwable cause) {
        super(cause);
    }

    public IvelaException(String message, Throwable cause) {
        super(message, cause);
    }

    public IvelaException(String message) {
        super(message);
    }    
}
