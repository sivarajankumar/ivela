/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action.admin;

import br.ufc.ivela.commons.ReplaceModelString;
import br.ufc.ivela.web.action.*;
import br.ufc.ivela.commons.model.Dictionary;
import br.ufc.ivela.ejb.interfaces.DictionaryRemote;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 *
 * @author nelson
 */
public class DictionaryAction extends GenericAction {

    private Dictionary dictionary;
    private DictionaryRemote dictionaryRemote;
    private XStream xStream = new XStream(new JettisonMappedXmlDriver());
    private InputStream inputStream;

    public String add() {
        ReplaceModelString.replace(dictionary);
        if (dictionaryRemote.add(dictionary) != null) {
            addActionMessage(getText("dictionary.input.success"));
            dictionary = null;
        } else {
            addActionMessage(getText("dictionary.input.fail"));
        }

        return "input";
    }

    public String addJson() {
        
        ReplaceModelString.replace(dictionary);
        if (dictionaryRemote.get(dictionary.getTitle()) == null) {
            xStream.alias("id", long.class);
            String json = xStream.toXML(dictionaryRemote.add(dictionary));
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        } else {
            String json = "{\"dictionary\":\"exists\"}";
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }

        return "json";

    }
    
    public String deleteJson(){
        ReplaceModelString.replace(dictionary);
        boolean resul = dictionaryRemote.delete(dictionary);
        xStream.alias("del", boolean.class);
        String json = xStream.toXML(resul);
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String getJson() {

        xStream.alias("dictionary", Dictionary.class);
        ReplaceModelString.replace(dictionary);
        Dictionary d = dictionaryRemote.get(dictionary.getTitle());
        if (d != null) {
            String json = xStream.toXML(d);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }
        else{
            
            String json = "{\"dictionary\":{\"id\":null,\"title\":null,\"description\":null,\"createdAt\":null}}";
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }
        return "json";
    }

    public String updateJson() {

        ReplaceModelString.replace(dictionary);
        
        Dictionary d = dictionaryRemote.get(dictionary.getTitle());

        if (dictionary.getId().equals(d.getId())) {

            dictionary.setCreatedAt(d.getCreatedAt());

            xStream.alias("update", boolean.class);
            String json = xStream.toXML(dictionaryRemote.update(dictionary));
            setInputStream(new ByteArrayInputStream(json.getBytes()));

        } else {
            String json = "{\"dictionary\":\"exists\"}";
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }

        return "json";
    }

    public String input() {

        return "input";
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public DictionaryRemote getDictionaryRemote() {
        return dictionaryRemote;
    }

    public void setDictionaryRemote(DictionaryRemote dictionaryRemote) {
        this.dictionaryRemote = dictionaryRemote;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public XStream getXStream() {
        return xStream;
    }

    public void setXStream(XStream xStream) {
        this.xStream = xStream;
    }
}
