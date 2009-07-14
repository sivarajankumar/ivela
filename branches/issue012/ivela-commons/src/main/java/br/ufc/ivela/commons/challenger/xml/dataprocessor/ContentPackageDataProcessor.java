package br.ufc.ivela.commons.challenger.xml.dataprocessor;

import br.ufc.ivela.commons.challenger.dataobject.FileSystem;
import br.ufc.ivela.commons.challenger.dataobject.ContentPackage;
import br.ufc.ivela.commons.challenger.config.Constants;
import br.ufc.ivela.commons.challenger.dataobject.ChallengeDesc;
import br.ufc.ivela.commons.challenger.dataobject.IvelaHeader;
import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;
import br.ufc.ivela.commons.challenger.dataobject.IvelaPayload;
import br.ufc.ivela.commons.challenger.dataobject.Question;
import br.ufc.ivela.commons.challenger.dataobject.QuestionOption;
import br.ufc.ivela.commons.challenger.xml.Parser;
import br.ufc.ivela.commons.challenger.xml.QuestionSetTransformer2Object;
import br.ufc.ivela.commons.challenger.xml.TransformFactory;
import java.util.ArrayList;
import java.util.List;


import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX data processor implementation that scans a xml string
 * to build an {@link IvelaObj} which contains a {@link ContentPackage} 
 * @see Parser
 * @see ContentPackageTransformer2Object
 * @see TransformFactory
 * @author Leonardo Moreira - leoomoreira@gmail.com (23Sep2008)
 *
 */
public class ContentPackageDataProcessor extends DefaultHandler {

    private IvelaObj ivelaObj = new IvelaObj();
    private IvelaHeader ivelaHeader = new IvelaHeader();
    private IvelaPayload ivelaPayLoad = new IvelaPayload();
    private ContentPackage contentPackage = new ContentPackage();
    private FileSystem fileSystem = null;
    private List<FileSystem> fileSystemList = null;
    private StringBuffer buffer = null;
    private boolean isTagActive = false;
    private String activeTag = null;

    public void startElement(String namespaceUri, String localName, String qualifiedName, Attributes attributes) {

        if (qualifiedName.equals(Constants.TAG_HEADER)) {
            activeTag = Constants.TAG_HEADER;
            buffer = new StringBuffer();
            ivelaHeader.setDocType(attributes.getValue(Constants.DOCTYPE));
            ivelaHeader.setDocSubType(attributes.getValue(Constants.DOCSUBTYPE));

            isTagActive = true;

        } else if (qualifiedName.equals(Constants.TAG_CONTENTPACKAGE)) {
            activeTag = Constants.TAG_CONTENTPACKAGE;
            buffer = new StringBuffer();
            isTagActive = true;
            contentPackage.setCourse(attributes.getValue(Constants.CONTENTPACKAGE_COURSE));
            contentPackage.setDiscipline(attributes.getValue(Constants.CONTENTPACKAGE_DISCIPLINE));
            contentPackage.setUnit(attributes.getValue(Constants.CONTENTPACKAGE_UNIT));
            contentPackage.setUnitContent(attributes.getValue(Constants.CONTENTPACKAGE_UNITCONTENT));
            fileSystemList = new ArrayList<FileSystem>();

        } else if (qualifiedName.equals(Constants.TAG_FILESYSTEM)) {
            activeTag = Constants.TAG_FILESYSTEM;
            buffer = new StringBuffer();
            isTagActive = true;
            fileSystem = new FileSystem();
            fileSystem.setValue(attributes.getValue(Constants.FILESYSTEM_VALUE));
            fileSystemList.add(fileSystem);

        } else {
            isTagActive = false;
            buffer = null;
        }

    }

    public void characters(char[] chars, int start, int length) {
        /*
        if (isTagActive && activeTag.equals(Constants.TAG_QUESTIONDESC)) {
            if (buffer == null) {
                buffer = new StringBuffer();
            }
            buffer.append(chars, start, length);
        }
        */
    }

    public void endElement(String uri, String name, String qualifiedName) {
        if (qualifiedName.equals(Constants.TAG_CONTENTPACKAGE)) {
            contentPackage.setFileSystemList(fileSystemList);
        }
    }

    public IvelaObj getIvelaObj() {
        ivelaPayLoad.setContentPackage(contentPackage);
        ivelaObj.setIvelaHeader(ivelaHeader);
        ivelaObj.setIvelaPayload(ivelaPayLoad);
        return ivelaObj;
    }
    
}
