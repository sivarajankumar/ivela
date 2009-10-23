/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action.admin;

import br.ufc.ivela.commons.ReplaceModelString;
import br.ufc.ivela.commons.model.ChallengeItems;
import br.ufc.ivela.commons.model.Course;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.ejb.interfaces.ChallengeItemsRemote;
import br.ufc.ivela.web.action.GenericAction;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;


/**
 *
 * @author emanuelle Vieira
 */
public class ChallengeAction extends GenericAction {

    ChallengeItems challengeItems;
    Unit unit;       
    InputStream inputStream;
    List<ChallengeItems> listChallengeItems;
    ChallengeItemsRemote challengeItemsRemote;
    
    public String add(){
        String json = "{\"challengeItem\":";
        challengeItems.setTimestamp(new Date());
        challengeItems.setName(challengeItems.getName().trim());
        ReplaceModelString.replace(challengeItems);
        json += challengeItemsRemote.add(challengeItems) + "}";
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    
    }
    
    public String getByName(){
        this.challengeItems = challengeItemsRemote.get(challengeItems.getName());
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("challengeItems", ChallengeItems.class);
        xStream.omitField(Course.class, "course");
        xStream.omitField(Discipline.class, "unit");
        if (this.challengeItems!=null){
            setInputStream(new ByteArrayInputStream( xStream.toXML(this.challengeItems).getBytes()));
        }else{
            setInputStream(new ByteArrayInputStream( "{\"challengeItems\":\"\"}".getBytes()));
        }
        return "json";
    }
    
    public String getByUnit(){
        StringBuilder json = new StringBuilder("{\"list\":[");
        listChallengeItems = new ArrayList<ChallengeItems>(); 
        listChallengeItems = challengeItemsRemote.getByUnit(unit.getId());
        Collections.sort(listChallengeItems);
        for (ChallengeItems challengeIt : listChallengeItems) {
            json.append("{");
            json.append("\"id\":\"" + challengeIt.getId() + "\",");
            json.append("\"name\":\"" + challengeIt.getName() + "\"");
            json.append("},");
        }
        if(listChallengeItems!=null && listChallengeItems.size()>0){
            json = new StringBuilder(json.substring(0, json.length()-1));
        } 
        json.append("]}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }
    
    public String remove(){
        String json = "{\"challengeItem\":";
        json += challengeItemsRemote.remove(challengeItems) + "}";
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }
    
    public String update(){
        String json = "{\"challengeItem\":";
        ReplaceModelString.replace(challengeItems);
        json += challengeItemsRemote.update(challengeItems) + "}";
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }


    public ChallengeItems getChallengeItems() {
        return challengeItems;
    }

    public void setChallengeItems(ChallengeItems challengeItems) {
        this.challengeItems = challengeItems;
    }

    public ChallengeItemsRemote getChallengeItemsRemote() {
        return challengeItemsRemote;
    }

    public void setChallengeItemsRemote(ChallengeItemsRemote challengeItemsRemote) {
        this.challengeItemsRemote = challengeItemsRemote;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<ChallengeItems> getListChallengeItems() {
        return listChallengeItems;
    }

    public void setListChallengeItems(List<ChallengeItems> listChallengeItems) {
        this.listChallengeItems = listChallengeItems;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
    private void performValidateAdd() {
        if (challengeItems == null) {
            addActionError(getText("challenge.input.validation"));
        }

        if (!StringUtils.hasText(challengeItems.getName())) {
            addActionError(getText("challenge.input.validation.name"));
        }
    }
    
    
    
    
}