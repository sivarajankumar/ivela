/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author rodrigo
 */
public class Hash {
    
    /**
     * generates a hash string
     * @param value
     * @return
     */
    public static final String generateHash(String value) {
        return generateHash(value, "MD5");
    }
    
    /**
     * generates a hash string
     * @param value
     * @param alghritm
     * @return
     */
    public static final String generateHash(String value, String alghoritm) {
        try {
            MessageDigest md = MessageDigest.getInstance(alghoritm);
            md.update(value.getBytes());
            return hexaToString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }   
    
    /**
     * converts an array of bytes in string
     * @param bytes
     * @return
     */
    public static String hexaToString(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int highPart = ((bytes[i] >> 4) & 0xf) << 4;
            int lowPart = bytes[i] & 0xf;
            if (highPart == 0) {
                s.append('0');
            }
            s.append(Integer.toHexString(highPart | lowPart));
        }
        return s.toString();
    }
}
