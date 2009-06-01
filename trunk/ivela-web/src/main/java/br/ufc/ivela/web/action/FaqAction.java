/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.model.Faq;
import br.ufc.ivela.ejb.interfaces.FaqRemote;
import java.util.List;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Action of the FAQ's
 */
public class FaqAction extends GenericAction {

    private Faq faq;
    private List<Faq> faqList;
    private FaqRemote faqRemote;

    public FaqRemote getFaqRemote() {
        return faqRemote;
    }

    public void setFaqRemote(FaqRemote faqRemote) {
        this.faqRemote = faqRemote;
    }
    
    public String list() {
        faqList = faqRemote.getAll();
        return "list";
    }

    public Faq getFaq() {
        return faq;
    }

    public void setFaq(Faq faq) {
        this.faq = faq;
    }

    public List<Faq> getFaqList() {
        return faqList;
    }

    public void setFaqList(List<Faq> faqList) {
        this.faqList = faqList;
    }
        
}