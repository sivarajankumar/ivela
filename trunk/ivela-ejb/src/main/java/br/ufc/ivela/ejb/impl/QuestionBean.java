/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.ejb.impl;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.dao.DaoFactory;
import br.ufc.ivela.commons.dao.GenericDao;
import java.io.File;
import br.ufc.ivela.ejb.interfaces.QuestionRemote;
import br.ufc.ivela.ejb.*;
import br.ufc.ivela.commons.model.Question;
import br.ufc.ivela.commons.model.QuestionText;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author emanuelle
 * 
 * Class of ejb which implements the interface QuestionLocal
 */
@Stateless(mappedName="QuestionBean")
public class QuestionBean implements QuestionRemote {

    private GenericDao<Question> daoQuestion = DaoFactory.getInstance(Question.class);
    private GenericDao<QuestionText> daoQuestionText = DaoFactory.getInstance(QuestionText.class);

    public Long add(Question question) {
        question.setCreatedAt(new Date());
        return (Long) daoQuestion.save(question);

    }

    public Long addQuestionText(QuestionText questionText, File file, String dir) {
        InputStream data = null;
        if (file != null) {
            try {
                File f1 = new File(Constants.AUDIO_UPLOAD_PATH+dir);
                if (!f1.exists()) {
                    f1.mkdirs();
                }
                java.io.File f = new java.io.File(Constants.AUDIO_UPLOAD_PATH+questionText.getAudio());
                data = new FileInputStream(file);
                OutputStream out = new FileOutputStream(f);
                byte[] buf = new byte[1024];
                int len;
                while ((len = data.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                data.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return (Long) daoQuestionText.save(questionText);
    }

    public Question get(Long id) {
        if (id == null) {
            return null;
        }

        return daoQuestion.get(id);
    }

    public boolean remove(Long id) {
        return daoQuestion.remove(id);
    }

    public boolean edit(Question question) {
        Question questionObj = daoQuestion.get(question.getId());
        questionObj.setQuestion(question.getQuestion());
        return daoQuestion.update(questionObj);
    }

    public List<Question> getByType(int type) {
        Question questionObj = new Question();
        questionObj.setType(type);
        return daoQuestion.getByExample(questionObj);

    }

    public QuestionText getQuestionTextByQuestion(Long questionId) {

        List list = daoQuestionText.find("select q.questionText from Question q where q.id = ?", new Object[]{questionId});
        if (list != null && list.size() > 0) {
            return (QuestionText) list.get(0);
        } else {
            return null;
        }
    }

    public QuestionText getQuestionText(Long questionTextId) {
        return daoQuestionText.get(questionTextId);
    }

    public List<Question> getAll() {
        return daoQuestion.getAll();
    }

    public boolean update(Question question) {

        return daoQuestion.update(question);
    }
}
