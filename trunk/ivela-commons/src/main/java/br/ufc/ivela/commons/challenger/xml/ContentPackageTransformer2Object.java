/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.challenger.xml;

import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;
import br.ufc.ivela.commons.challenger.dataobject.TransformerInterface;
import br.ufc.ivela.commons.challenger.xml.dataprocessor.ContentPackageDataProcessor;
import java.io.IOException;
import org.xml.sax.SAXException;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe responsável por transformar o content package do XML em objeto
 */
public class ContentPackageTransformer2Object implements TransformerInterface {

    private String xml = null;
	
    public ContentPackageTransformer2Object(String xml) {
        //DebugLogger.getInstance().logAtDebugTime(this.getClass().getName(), "Calling QuestionSetTransformer2Object...");
        this.xml = xml;
    }

    public IvelaObj getTransformerType() throws SAXException, IOException {
        Parser parser = new Parser(xml);
        //System.out.println(xml);
        ContentPackageDataProcessor cPkgDp = parser.getRawContentPackage();
        return cPkgDp.getIvelaObj();
    }

}
