/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets.challenge;

/**
 *
 * @author jdamico
 */
public class Converter2ObjectFactory {
   
    public static ObjectInterface getConverter(XMLObject xo) {
        switch(xo.getIntType()) {
            case Constants.CHALLENGE_TYPE:;
                return new ChallengeConverter2Object(xo.getEntireXml());
            case Constants.SCORE_TYPE:;
                return new ScoreConverter2Object(xo.getEntireXml());
            default:
                return null;
        }
    }
}
