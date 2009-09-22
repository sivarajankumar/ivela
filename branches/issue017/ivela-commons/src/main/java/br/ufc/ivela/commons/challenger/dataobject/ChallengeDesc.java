package br.ufc.ivela.commons.challenger.dataobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Inner element of {@link IvelaPayload}
 * responsible to store a challenge
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public class ChallengeDesc {
	
	private String name = null;
	private String style = null;
	private String shortDesc = null;
	private int totalValue = 0;
	private int maxTries = 0;
	private Date expires = null;
	
	public Date getExpires() {
		return expires;
	}
	public void setExpires(Date expires) {
		this.expires = expires;
	}
	private List<Question> questionList = new ArrayList<Question>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public int getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}
	public int getMaxTries() {
		return maxTries;
	}
	public void setMaxTries(int maxTries) {
		this.maxTries = maxTries;
	}
	public List<Question> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}
	
	
}
