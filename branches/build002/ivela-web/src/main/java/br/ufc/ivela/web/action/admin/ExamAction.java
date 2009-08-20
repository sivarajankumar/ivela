/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action.admin;

import br.ufc.ivela.commons.ReplaceModelString;
import br.ufc.ivela.commons.model.Exam;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.ExamRemote;
import br.ufc.ivela.web.action.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.collection.PersistentSet;

/**
 *
 * @author Emanuelle Vieira
 */
public class ExamAction extends GenericAction {

    private Exam exam;
    private ExamRemote examRemote;
    private InputStream inputStream;
    private XStream xStream = new XStream(new JettisonMappedXmlDriver());
    private UnitContent unitContent;
    private String dateStartTime;
    private String dateEndTime;
    private String requisitesString;
    private Long[] examsCheckeds;

    public String add() {
        SimpleDateFormat dateStart = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat dateEnd = new SimpleDateFormat("MM/dd/yyyy");

        try {
            exam.setStartDatetime(dateStart.parse(dateStartTime));
            exam.setEndDatetime(dateEnd.parse(dateEndTime));
        } catch (ParseException ex) {
            ex.printStackTrace();

        }


        // performValidates the add
        //performValidateAdd();
        if (!hasActionErrors()) {

            exam.setActive(true);
            exam.setCreatedBy(getAuthenticatedUser());
            exam.setOrder(examRemote.getMaxOrderNByUnitContent(exam.getUnitContentId()) + 1);

            ReplaceModelString.replace(exam);
            Long idExam = examRemote.add(exam);


            exam = examRemote.get(idExam);
            xStream.alias("exam", Exam.class);
            xStream.omitField(Exam.class, "createdBy");
            String json = xStream.toXML(exam);
            setInputStream(new ByteArrayInputStream(json.getBytes()));
            logger.log("entrei3");
            return "json";
        }
        return ERROR;
    }

    public String getExamUnitContentJson() {
        xStream.alias("examArray", Exam.class);
        xStream.omitField(Set.class, "requisites");
        xStream.omitField(Exam.class, "createdBy");
        xStream.omitField(Exam.class, "createdAt");
        String json = xStream.toXML(examRemote.getListExamByUnitContent(unitContent.getId()));
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String getExamJson() {

        xStream.alias("exam", Exam.class);
        xStream.omitField(Exam.class, "requisites");
        xStream.omitField(Exam.class, "createdBy");

        Exam examTemp = examRemote.get(exam.getId());

        String json = xStream.toXML(examTemp);

        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String requisitesByExam() {
        xStream.alias("list", PersistentSet.class);
        xStream.alias("exam", Exam.class);
        xStream.omitField(Exam.class, "requisites");
        xStream.omitField(Exam.class, "createdBy");
        xStream.omitField(Exam.class, "startDatetime");
        xStream.omitField(Exam.class, "endDatetime");

        Exam examTemp = examRemote.get(exam.getId());

        Set<Exam> requTemp = examTemp.getRequisites();

        String json = xStream.toXML(requTemp);
        logger.log(json);
        setInputStream(new ByteArrayInputStream(json.getBytes()));

        return "json";
    }

    public String update() {
        // performValidates the add
        //performValidateAdd();
        if (!hasActionErrors()) {

            Exam ex = examRemote.get(exam.getId());
            
            ex.setTitle(exam.getTitle());
            ex.setNavigable(exam.getNavigable());
            ex.setWeight(exam.getWeight());
            ex.setDuration(exam.getDuration());
            ReplaceModelString.replace(ex);
            SimpleDateFormat dateStart = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat dateEnd = new SimpleDateFormat("MM/dd/yyyy");

            try {
                ex.setStartDatetime(dateStart.parse(dateStartTime));
                ex.setEndDatetime(dateEnd.parse(dateEndTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            xStream.alias("update", boolean.class);

            String json = xStream.toXML(examRemote.update(ex));
            setInputStream(new ByteArrayInputStream(json.getBytes()));

            return "json";
        }
        return ERROR;
    }
    
    public String updateActive() {

        for (Long examId : examsCheckeds) {
            Exam ex = examRemote.get(examId);
            ex.setActive(false);
            examRemote.updateJson(ex);
        }
        String json = "ok";
        
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String addRequisitesWithJason() {
        if (this.requisitesString != null && !this.requisitesString.equals("")) {
            requisitesString = requisitesString.trim();
            String[] major = requisitesString.split("a");

            Long examId = Long.parseLong(major[0]);

            if (major.length == 2) {
                String[] checkedExam = major[1].trim().split("b");

                for (int i = 0; i < checkedExam.length; i++) {

                    if (checkedExam[i] != null && !checkedExam[i].equals("") && !checkedExam[i].equals(" ")) {
                        Long reqId = Long.parseLong(checkedExam[i]);
                        examRemote.addRequisite(examId, reqId);

                    }

                }
            }

        }
        String json = "ok";

        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String updateRequisitesWithJason() {
        if (this.requisitesString != null && !this.requisitesString.equals("")) {

            Set<Exam> newRequisites = new HashSet<Exam>();

            requisitesString = requisitesString.trim();


            String[] major = requisitesString.split("a");

            Long examId = Long.parseLong(major[0]);
            Exam updatedExam = examRemote.get(examId);



            if (major.length == 2) {
                String[] checkedExam = major[1].trim().split("b");

                for (int i = 0; i < checkedExam.length; i++) {
                    if (checkedExam[i] != null && !checkedExam[i].equals("") && !checkedExam[i].equals(" ") && !checkedExam[i].equals(major[0])) {
                        Long reqId = Long.parseLong(checkedExam[i]);
                        Exam requisiteExam = examRemote.get(reqId);
                        newRequisites.add(requisiteExam);

                    }
                }

                updatedExam.setRequisites(newRequisites);
            }
            if (major.length == 1) {
                updatedExam.setRequisites(new HashSet<Exam>());
            }

            examRemote.update(updatedExam);
        }

        String json = "ok";

        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getRequisitesString() {
        return requisitesString;
    }

    public void setRequisitesString(String requisitesString) {
        this.requisitesString = requisitesString;
    }

    public ExamRemote getExamRemote() {
        return examRemote;
    }

    public void setExamRemote(ExamRemote examRemote) {
        this.examRemote = examRemote;
    }

    public XStream getXStream() {
        return xStream;
    }

    public void setXStream(XStream xStream) {
        this.xStream = xStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public UnitContent getUnitContent() {
        return unitContent;
    }

    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    public String getDateEndTime() {
        return dateEndTime;
    }

    public void setDateEndTime(String dateEndTime) {
        this.dateEndTime = dateEndTime;
    }

    public String getDateStartTime() {
        return dateStartTime;
    }

    public void setDateStartTime(String dateStartTime) {
        this.dateStartTime = dateStartTime;
    }

    public Long[] getExamsCheckeds() {
        return examsCheckeds;
    }

    public void setExamsCheckeds(Long[] examsCheckeds) {
        this.examsCheckeds = examsCheckeds;
    }
    
}
