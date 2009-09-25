package br.ufc.ivela.commons.challenger.xml.dataprocessor;


import br.ufc.ivela.commons.challenger.config.Constants;
import br.ufc.ivela.commons.challenger.dataobject.ChallengeDesc;
import br.ufc.ivela.commons.challenger.dataobject.IvelaHeader;
import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;
import br.ufc.ivela.commons.challenger.dataobject.IvelaPayload;
import br.ufc.ivela.commons.challenger.dataobject.Question;
import br.ufc.ivela.commons.challenger.dataobject.QuestionOption;
import br.ufc.ivela.commons.challenger.xml.QuestionSetTransformer2Object;
import br.ufc.ivela.commons.challenger.xml.TransformFactory;
import br.ufc.ivela.commons.model.xml.Parser;

import java.util.ArrayList;
import java.util.List;


import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX data processor implementation that scans a xml string
 * to build an {@link IvelaObj} which contains a {@link ChallengeDesc} 
 * @see Parser
 * @see QuestionSetTransformer2Object
 * @see TransformFactory
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public class QuestionSetDataProcessor extends DefaultHandler{
	
	private IvelaObj ivelaObj = new IvelaObj();
	private IvelaHeader ivelaHeader = new IvelaHeader();
	private IvelaPayload ivelaPayLoad = new IvelaPayload();
	private ChallengeDesc challengeDesc = new ChallengeDesc();	
	private Question question = null;
	private QuestionOption option = null;
	private List<Question> questionList = null;
	private List<QuestionOption> optionList = null;
	
	private StringBuffer buffer = null;
	private boolean isTagActive = false;
	private String activeTag = null;
	private StringBuffer textArea = null;
	private StringBuffer binArea = null;
	
	public void startElement (String namespaceUri, String localName, String qualifiedName, Attributes attributes) {
		
		if(qualifiedName.equals(Constants.TAG_HEADER)){
			activeTag = Constants.TAG_HEADER;
			buffer = new StringBuffer();
			ivelaHeader.setDocType(attributes.getValue(Constants.DOCTYPE));
			ivelaHeader.setDocSubType(attributes.getValue(Constants.DOCSUBTYPE));
			
			isTagActive = true;
			
		}else if(qualifiedName.equals(Constants.TAG_CHALLENGEDESC)){
			activeTag = Constants.TAG_CHALLENGEDESC;
			buffer = new StringBuffer();
			isTagActive = true;
			challengeDesc.setMaxTries(Integer.parseInt(attributes.getValue(Constants.CHALLENGEDESC_MAXTRIES)));
			challengeDesc.setName(attributes.getValue(Constants.CHALLENGEDESC_NAME));
			challengeDesc.setShortDesc(attributes.getValue(Constants.CHALLENGEDESC_SHORTDESC));
			challengeDesc.setStyle(attributes.getValue(Constants.CHALLENGEDESC_STYLE));
			challengeDesc.setTotalValue(Integer.parseInt(attributes.getValue(Constants.CHALLENGEDESC_TOTALVALUE)));
			questionList = new ArrayList<Question>();
			
		}else if(qualifiedName.equals(Constants.TAG_QUESTION)){	
			
			optionList = new ArrayList<QuestionOption>();
			question = new Question();
			activeTag = Constants.TAG_QUESTION;
			buffer = new StringBuffer();
			isTagActive = true;
			question.setName(attributes.getValue(Constants.QUESTION_NAME));
			question.setReference(attributes.getValue(Constants.QUESTION_REFERENCE));
			question.setValue(Integer.parseInt(attributes.getValue(Constants.QUESTION_VALUE)));
			
		}else if(qualifiedName.equals(Constants.TAG_QUESTIONDESC)){
			activeTag = Constants.TAG_QUESTIONDESC;
			buffer = new StringBuffer();
			isTagActive = true;
			
		}else if(qualifiedName.equals(Constants.TAG_QUESTIONOPTION)){

			activeTag = Constants.TAG_QUESTIONOPTION;
			buffer = new StringBuffer();
			isTagActive = true;
			option = new QuestionOption();
			
			option.setCorrect(Boolean.parseBoolean(attributes.getValue(Constants.OPTION_CORRECT)));
			option.setDesc(attributes.getValue(Constants.OPTION_DESC));
			option.setName(attributes.getValue(Constants.OPTION_NAME));
			
			optionList.add(option);
			
		}else{
			isTagActive = false;
			buffer = null;
		}

	}
	
	public void characters(char[] chars,int start,int length){
		
       if(isTagActive && activeTag.equals(Constants.TAG_QUESTIONDESC)){
    	   if(buffer==null) buffer = new StringBuffer();
           buffer.append(chars,start,length);
       }
  
    }
	
	public void endElement(String uri, String name, String qualifiedName){

		if(qualifiedName.equals(Constants.TAG_QUESTIONDESC)){ 
			isTagActive = true;
			if(buffer!=null){
				question.setDescription(buffer.toString());
			}
			buffer = null;
			
			
		}else if(qualifiedName.equals(Constants.TAG_QUESTION)){ 
			question.setOptionList(optionList);
			questionList.add(question);
			challengeDesc.setQuestionList(questionList);
		}
	}
	
	

	public StringBuffer getTextArea() {
		return textArea;
	}

	public void setTextArea(StringBuffer textArea) {
		this.textArea = textArea;
	}

	public StringBuffer getBinArea() {
		return binArea;
	}

	

	public IvelaObj getIvelaObj(){
		ivelaPayLoad.setChallengeDesc(challengeDesc);
		ivelaObj.setIvelaHeader(ivelaHeader);
		ivelaObj.setIvelaPayload(ivelaPayLoad);
		return ivelaObj;
	}

}
