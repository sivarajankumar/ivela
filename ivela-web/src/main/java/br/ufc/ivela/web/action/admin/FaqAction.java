/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action.admin;

import br.ufc.ivela.commons.model.Faq;
import br.ufc.ivela.web.action.*;
import br.ufc.ivela.ejb.interfaces.FaqRemote;
import java.util.Date;
import java.util.List;
import org.springframework.util.StringUtils;

/**
 *
 * @author Leonardo Oliveira Moreira
 */
public class FaqAction extends GenericAction {

    private Faq faq;
    private List<Faq> faqList;
    private FaqRemote faqRemote;
   
    /**
     * Add a new faq, perform a validation.
     * @return if hasn't error return a list of faqs
     */
    public String add() {
        faq.setCreatedBy(getAuthenticatedUser());
        faq.setDate(new Date());
        performValidateAdd();
        if (!hasActionErrors()) {
            Long result = faqRemote.add(faq);
            if (result != null) {
                return list();
            }else{
                return input();
            }
        }else{
            return input();
        }
    }

    /**
     * Remove a faq, perform a validation.
     * @return if hasn't error return a list of faq
     */
    public String remove() {
        performValidateRemove();
        if (!hasActionErrors()) {
            boolean result = faqRemote.remove(faq.getId());
            if (result) {
                return list();
            }
        }
        return list();
    }

    /**
     * Edit a faq
     * @return a string
     */
    public String edit() {
        setFaq(faqRemote.get(faq.getId()));
        return "edit";
    }

    /**
     * 
     * @return
     */
    public String update() {
        faq.setCreatedBy(getAuthenticatedUser());
        faq.setDate(new Date());
        performValidateEdit();
        if (hasActionErrors()) {
            return edit();
        }
        if (faqRemote.update(faq)) {
            return list();
        } else {
            return ERROR;
        }
    }

    /**
     * Sets the variables to be used on the input form
     */
    public String input() {
        return INPUT;
    }

    /**
     * List all faq
     */
    public String list() {
        faqList = faqRemote.getAll();
        return "list";
    }

    /**
     * 
     */
    private void performValidateAdd() {
        if (faq == null) {
            addActionError(getText("faq.input.validation.required"));
        }
        // verifies if the question is empty
        if (!StringUtils.hasText(faq.getQuestion())) {
            addActionError(getText("faq.input.validation.question"));
        }
        // verifies if the answer is empty
        if (!StringUtils.hasText(faq.getAnswer())) {
            addActionError(getText("faq.input.validation.answer"));
        }
    }
    
    /**
     * 
     */
    private void performValidateEdit() {
        performValidateAdd();
    }

    /**
     * 
     */
    private void performValidateRemove() {
        // verifies if there is an id
        if (faq.getId() == null) {
            addActionError(getText("faq.input.validation.requiredId"));
        } else {
            // verifies if this id is valid
            if (faqRemote.get(faq.getId()) == null) {
                addActionError(getText("faq.input.validation.invalidId"));
            }
        }
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

    public FaqRemote getFaqRemote() {
        return faqRemote;
    }

    public void setFaqRemote(FaqRemote faqRemote) {
        this.faqRemote = faqRemote;
    }

}
