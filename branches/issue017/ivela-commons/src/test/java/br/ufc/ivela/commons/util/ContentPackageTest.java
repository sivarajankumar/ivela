/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.util;

import br.ufc.ivela.commons.challenger.config.Constants;
import br.ufc.ivela.commons.challenger.dataobject.FileSystem;
import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;
import br.ufc.ivela.commons.challenger.dataobject.IvelaPayload;
import br.ufc.ivela.commons.challenger.dataobject.IvelaStringObj;
import br.ufc.ivela.commons.challenger.dataobject.TransformerInterface;
import br.ufc.ivela.commons.challenger.util.DebugLogger;
import br.ufc.ivela.commons.challenger.xml.TransformFactory;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * 
 */
public class ContentPackageTest {

    private String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                        "<ivela>" +		
                                "<header doctype=\"plugin\" docsubtype=\"contentpackage\"/>" +
                                "<payload>" +
                                        "<contentpackage grade=\"1\" course=\"2\" discipline=\"3\" unit=\"4\" unitcontent=\"5\">" +
                                                "<filesystem value=\"/index.html\" />" +
                                                "<filesystem value=\"/index1.html\" />" +
                                        "</contentpackage>" +
                                "</payload>" +
                        "</ivela>";
    
    public ContentPackageTest() {
        
    }
    
    /*
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test() {
        // XML to Object convertion
        
        IvelaStringObj ivelaStringObj = new IvelaStringObj();
        ivelaStringObj.setType(Constants.CONTENTPACKAGE);
        ivelaStringObj.setXml(xml);
        TransformerInterface tInter = TransformFactory.getTransformer(ivelaStringObj);

        IvelaObj ivelaObj = null;

        try {
                ivelaObj = tInter.getTransformerType();
                IvelaPayload p = ivelaObj.getIvelaPayload();
                System.out.println("Grade: " + p.getContentPackage().getGrade());
                System.out.println("Course: " + p.getContentPackage().getCourse());
                System.out.println("Discipline: " + p.getContentPackage().getDiscipline());
                System.out.println("Unit: " + p.getContentPackage().getUnit());
                System.out.println("UnitContent: " + p.getContentPackage().getUnitContent());
                System.out.println("File Systems: ");
                for (FileSystem f : p.getContentPackage().getFileSystemList()) {
                    System.out.println("- Value: " + f.getValue());
                }
        } catch (SAXException e) {
                DebugLogger.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
                //fail();
        } catch (IOException e) {
                DebugLogger.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
                //fail();
        }        
    }
    */
}
