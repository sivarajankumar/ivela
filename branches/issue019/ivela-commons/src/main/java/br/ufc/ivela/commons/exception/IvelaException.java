/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.exception;

/**
 *
 * @author marcus
 * @author jdamico
 */
public class IvelaException extends Exception{
   
	private static final long serialVersionUID = 9053910920694644057L;
	
	private StackTraceElement[] stackTraceElements;
	
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
    
    public IvelaException(StackTraceElement[] stackTraceElements) {
		this.stackTraceElements = stackTraceElements;
	}
	
	public IvelaException(StackTraceElement[] stackTraceElements, String rootMessage) {
		super(rootMessage);
		this.stackTraceElements = stackTraceElements;
		
	}
}
