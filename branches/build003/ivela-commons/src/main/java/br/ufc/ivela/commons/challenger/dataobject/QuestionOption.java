package br.ufc.ivela.commons.challenger.dataobject;

/**
 * Stores an option data for a given {@link Question}
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public class QuestionOption {
	
	private String name = null;
	private String desc = null;
	private boolean correct = false;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
}
