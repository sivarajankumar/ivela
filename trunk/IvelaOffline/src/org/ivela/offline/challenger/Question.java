/**
 * @(#)Question.java
 *
 * Eldorado Confidential and Proprietary Information
 * Copyright (C)2010 Eldorado, All Rights Reserved
 *
 * Hist�rico de Modifica��es
 *
 * Data           Quem              Descri��o
 * ------------------------------------------------------------------------------------------------
 * 07 May 2010    Rafael Lagoa      (3626)Importa��o de estrutura do Ivela para Challenges.  
 */
package org.ivela.offline.challenger;

import java.util.ArrayList;
import java.util.List;

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