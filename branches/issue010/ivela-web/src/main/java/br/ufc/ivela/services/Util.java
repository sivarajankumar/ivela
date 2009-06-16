/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.services;

/**
 *
 * @author jefferson
 */
public class Util {
    
    public static Object treatValue(Object obj) {
    	if (obj == null)
    		return "";
    	if (obj instanceof String) {
    		String str = (String) obj;
    		str = str.replaceAll("\n+", " ");
    		str = str.replaceAll("\t+", " ");
    		return str;
    	}
    	return obj;
    }

}
