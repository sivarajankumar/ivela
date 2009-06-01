package br.ufc.ivela.commons.challenger.dataobject;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores entire Question data for a given Challenge
 * @see {@link ChallengeDesc}
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public class Question {
	
	private String name = null;
	private String reference = null;
	private int value = 0;
	private String description = null;
	private List<QuestionOption> optionList = new ArrayList<QuestionOption>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<QuestionOption> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<QuestionOption> optionList) {
		this.optionList = optionList;
	}
}
