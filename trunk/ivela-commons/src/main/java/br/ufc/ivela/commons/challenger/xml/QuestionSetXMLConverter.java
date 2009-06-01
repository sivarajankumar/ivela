package br.ufc.ivela.commons.challenger.xml;

import br.ufc.ivela.commons.challenger.config.Constants;
import br.ufc.ivela.commons.challenger.dataobject.ChallengeDesc;
import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;
import br.ufc.ivela.commons.challenger.dataobject.Question;
import br.ufc.ivela.commons.challenger.dataobject.QuestionOption;
import br.ufc.ivela.commons.challenger.dataobject.XMLInterface;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;


import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Logic of {@link Converter2XMLFactory} to
 * {@link ChallengeDesc} which contains a Question set
 * @see Question
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public class QuestionSetXMLConverter implements XMLInterface {

	private IvelaObj ivelaObj = null;
	
	public QuestionSetXMLConverter(IvelaObj ivelaObj) {
		this.ivelaObj = ivelaObj;
	}

	public String getXML() throws TransformerConfigurationException, SAXException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(); 
		StreamResult streamResult = new StreamResult(out);
		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		
		TransformerHandler hd;
		hd = tf.newTransformerHandler();
		Transformer serializer = hd.getTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
		serializer.setOutputProperty(OutputKeys.INDENT,"yes");
		hd.setResult(streamResult);
		hd.startDocument();
		AttributesImpl atts = new AttributesImpl();
		
		hd.startElement("","",Constants.TAG_IVELA,atts);
		atts.clear();
		atts.addAttribute("","",Constants.DOCTYPE,"CDATA",String.valueOf(ivelaObj.getIvelaHeader().getDocType()));
		atts.addAttribute("","",Constants.DOCSUBTYPE,"CDATA",String.valueOf(ivelaObj.getIvelaHeader().getDocSubType()));
		hd.startElement("","",Constants.TAG_HEADER,atts);
		hd.endElement("","",Constants.TAG_HEADER);
		atts.clear();
		
		hd.startElement("","",Constants.TAG_PAYLOAD,atts);
		
		atts.addAttribute("","",Constants.CHALLENGEDESC_NAME,"CDATA",ivelaObj.getIvelaPayload().getChallengeDesc().getName());
		atts.addAttribute("","",Constants.CHALLENGEDESC_STYLE,"CDATA",ivelaObj.getIvelaPayload().getChallengeDesc().getStyle());
		atts.addAttribute("","",Constants.CHALLENGEDESC_TOTALVALUE,"CDATA",String.valueOf(ivelaObj.getIvelaPayload().getChallengeDesc().getTotalValue()));
		atts.addAttribute("","",Constants.CHALLENGEDESC_MAXTRIES,"CDATA",String.valueOf(ivelaObj.getIvelaPayload().getChallengeDesc().getMaxTries()));
		atts.addAttribute("","",Constants.CHALLENGEDESC_SHORTDESC,"CDATA",ivelaObj.getIvelaPayload().getChallengeDesc().getShortDesc());
		hd.startElement("","",Constants.TAG_CHALLENGEDESC,atts);
		atts.clear();
		
		
		
		if(ivelaObj.getIvelaPayload().getChallengeDesc().getQuestionList().size() > 0){
			for(int i=0; i<ivelaObj.getIvelaPayload().getChallengeDesc().getQuestionList().size(); i++){
				atts.addAttribute("","",Constants.QUESTION_NAME,"CDATA",ivelaObj.getIvelaPayload().getChallengeDesc().getQuestionList().get(i).getName());
				//atts.addAttribute("","",Constants.QUESTION_REFERENCE,"CDATA",ivelaObj.getIvelaPayload().getChallengeDesc().getQuestionList().get(i).getReference());
				atts.addAttribute("","",Constants.QUESTION_VALUE,"CDATA",String.valueOf(ivelaObj.getIvelaPayload().getChallengeDesc().getQuestionList().get(i).getValue()));
				hd.startElement("","",Constants.TAG_QUESTION,atts);
				atts.clear();
				hd.startElement("","",Constants.TAG_QUESTIONDESC,atts);
				try{
					hd.characters(ivelaObj.getIvelaPayload().getChallengeDesc().getQuestionList().get(i).getDescription().toCharArray(), 0, ivelaObj.getIvelaPayload().getChallengeDesc().getQuestionList().get(i).getDescription().length());	
				} catch(NullPointerException npe){
					//npe.printStackTrace();
				}
				
				hd.endElement("","",Constants.TAG_QUESTIONDESC);
				
				List<QuestionOption> optionList = ivelaObj.getIvelaPayload().getChallengeDesc().getQuestionList().get(i).getOptionList();
				
				if(optionList.size()>0){
					for(int j=0; j<optionList.size(); j++){
						atts.addAttribute("","",Constants.OPTION_NAME,"CDATA", optionList.get(j).getName());
						//atts.addAttribute("","",Constants.OPTION_DESC,"CDATA", optionList.get(j).getDesc());
						//atts.addAttribute("","",Constants.OPTION_CORRECT,"CDATA", String.valueOf(optionList.get(j).isCorrect()));
						hd.startElement("","",Constants.TAG_QUESTIONOPTION, atts);
						atts.clear();
						hd.endElement("","",Constants.TAG_QUESTIONOPTION);
					}
				}
				
				hd.endElement("","",Constants.TAG_QUESTION);
			}
		}
		
		hd.endElement("","",Constants.TAG_CHALLENGEDESC);
		hd.endElement("","",Constants.TAG_PAYLOAD);
		hd.endElement("","",Constants.TAG_IVELA);
		hd.endDocument();
		
		return out.toString();
	}

}
