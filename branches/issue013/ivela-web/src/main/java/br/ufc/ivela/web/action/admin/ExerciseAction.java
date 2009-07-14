/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action.admin;

import br.ufc.ivela.commons.ReplaceModelString;
import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.ExerciseRemote;
import br.ufc.ivela.web.action.GenericAction;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.collection.PersistentSet;
import org.springframework.util.StringUtils;

/**
 *
 * @author nelson
 */
public class ExerciseAction extends GenericAction {

    private Exercise exercise;
    private ExerciseRemote exerciseRemote;
    private InputStream inputStream;
    private UnitContent unitContent;
    private XStream xStream = new XStream(new JettisonMappedXmlDriver());
    private String requisitesString;
    private Long[] exercisesCheckeds;

    public String add() {
        // performValidates the add
        //performValidateAdd();
        if (!hasActionErrors()) {

            ReplaceModelString.replace(exercise);
            
            exercise.setActive(true);
            exercise.setCreatedBy(getAuthenticatedUser());
            exercise.setOrder(exerciseRemote.getMaxOrderNByUnitContent(exercise.getUnitContentId()) + 1);

            Long idExercise = exerciseRemote.add(exercise);


            exercise = exerciseRemote.get(idExercise);
            xStream.alias("exercise", Exercise.class);
            xStream.omitField(Exercise.class, "createdBy");
            
            String json = xStream.toXML(exercise);
            setInputStream(new ByteArrayInputStream(json.getBytes()));

            return "json";
        }
        return ERROR;
    }

    public String update() {
        // performValidates the add
        //performValidateAdd();
        if (!hasActionErrors()) {

            Exercise exe = exerciseRemote.get(exercise.getId());
            
            
            exe.setTitle(exercise.getTitle());
            exe.setDescription(exercise.getDescription());
            exe.setNavigable(exercise.getNavigable());
            exe.setWeight(exercise.getWeight());
            exe.setChances(exercise.getChances());
            
            ReplaceModelString.replace(exe);
            xStream.alias("update", boolean.class);
            
            String json = xStream.toXML(exerciseRemote.update(exe));
            setInputStream(new ByteArrayInputStream(json.getBytes()));

            return "json";
        }
        return ERROR;
    }

    private void performValidateAdd() {
        // verifies if the exercise is null
        if (exercise == null) {
            addActionError(getText("exercise.input.validation.requiredExercise"));
        }
        // verifies if the title is empty
        if (!StringUtils.hasText(exercise.getTitle())) {
            addActionError(getText("exercise.input.validation.requiredTitle"));
        }

    }

    public String getExerciseUnitContentJson() {
        xStream.alias("exerciseArray", Exercise.class);
        xStream.omitField(Exercise.class, "requisites");
        xStream.omitField(Exercise.class, "createdBy");
        xStream.omitField(Exercise.class, "createdAt");
        String json = xStream.toXML(exerciseRemote.getListExerciseByUnitContent(unitContent.getId()));
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String getExerciseJson() {

        xStream.alias("exercise", Exercise.class);
        xStream.omitField(Exercise.class, "requisites");
        xStream.omitField(Exercise.class, "createdBy");

        Exercise exeTemp = exerciseRemote.get(exercise.getId());

        String json = xStream.toXML(exeTemp);

        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String requisitesByExercise() {
        xStream.alias("list", PersistentSet.class);
        xStream.alias("exercise", Exercise.class);
        xStream.omitField(Exercise.class, "requisites");
        xStream.omitField(Exercise.class, "createdBy");

        Exercise exeTemp = exerciseRemote.get(exercise.getId());

        Set<Exercise> requTemp = exeTemp.getRequisites();

        String json = xStream.toXML(requTemp);

        setInputStream(new ByteArrayInputStream(json.getBytes()));

        return "json";
    }

    //requisitesString = <idExercise>a<rq1>b<rq2>b<rqN>
    // idExercise = number
    // rqN = number
    public String addRequisitesWithJason() {
        if (this.requisitesString != null && !this.requisitesString.equals("")) {
            requisitesString = requisitesString.trim();

            String[] major = requisitesString.split("a");

            Long exeId = Long.parseLong(major[0]);

            if (major.length == 2) {
                String[] checkedExe = major[1].trim().split("b");

                for (int i = 0; i < checkedExe.length; i++) {

                    if (checkedExe[i] != null && !checkedExe[i].equals("") && !checkedExe[i].equals(" ")) {
                        Long reqId = Long.parseLong(checkedExe[i]);
                        exerciseRemote.addRequisite(exeId, reqId);

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

            Set<Exercise> newRequisites = new HashSet<Exercise>();

            requisitesString = requisitesString.trim();


            String[] major = requisitesString.split("a");

            Long exeId = Long.parseLong(major[0]);
            Exercise updatedExe = exerciseRemote.get(exeId);



            if (major.length == 2) {
                String[] checkedExe = major[1].trim().split("b");

                for (int i = 0; i < checkedExe.length; i++) {

                    if (checkedExe[i] != null && !checkedExe[i].equals("") && !checkedExe[i].equals(" ") && !checkedExe[i].equals(major[0])) {
                        Long reqId = Long.parseLong(checkedExe[i]);
                        Exercise requisiteExe = exerciseRemote.get(reqId);
                        newRequisites.add(requisiteExe);
                    }

                }

                updatedExe.setRequisites(newRequisites);
            }
            if (major.length == 1) {
                updatedExe.setRequisites(new HashSet<Exercise>());
            }

            exerciseRemote.update(updatedExe);
        }

        String json = "ok";

        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public String updateActive() {

        for (Long exerciseId : exercisesCheckeds) {
            Exercise exe = exerciseRemote.get(exerciseId);
            exe.setActive(false);
            exerciseRemote.update(exe);
        }
        String json = "ok";
        
        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public ExerciseRemote getExerciseRemote() {
        return exerciseRemote;
    }

    public void setExerciseRemote(ExerciseRemote exerciseRemote) {
        this.exerciseRemote = exerciseRemote;
    }

    public String getRequisitesString() {
        return requisitesString;
    }

    public void setRequisitesString(String requisitesString) {
        this.requisitesString = requisitesString;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public XStream getXStream() {
        return xStream;
    }

    public void setXStream(XStream xStream) {
        this.xStream = xStream;
    }

    public UnitContent getUnitContent() {
        return unitContent;
    }

    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    public Long[] getExercisesCheckeds() {
        return exercisesCheckeds;
    }

    public void setExercisesCheckeds(Long[] exercisesCheckeds) {
        this.exercisesCheckeds = exercisesCheckeds;
    }
    
}
