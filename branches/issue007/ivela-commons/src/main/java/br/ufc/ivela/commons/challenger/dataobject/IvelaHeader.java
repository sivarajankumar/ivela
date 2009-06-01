package br.ufc.ivela.commons.challenger.dataobject;

import java.util.Date;

/**
 * Fundamental element of {@link IvelaObj}
 * Stores types of document, author, date, etc 
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public class IvelaHeader {
	private String docType = null;
	private String docSubType = null;
	private String author = null;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	private Date date = null;
	
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getDocSubType() {
		return docSubType;
	}
	public void setDocSubType(String docSubType) {
		this.docSubType = docSubType;
	}
	
	
}
