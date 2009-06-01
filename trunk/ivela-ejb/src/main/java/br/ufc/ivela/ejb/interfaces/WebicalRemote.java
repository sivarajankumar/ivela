/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.ejb.interfaces;

import javax.ejb.Remote;

/**
 *
 * @author marcus
 */

@Remote
public interface WebicalRemote {
    boolean add(String username);
}
