/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action.admin;

import br.ufc.ivela.commons.ContentPackageUtils;
import br.ufc.ivela.commons.challenger.config.Constants;
import br.ufc.ivela.commons.challenger.dataobject.FileSystem;
import br.ufc.ivela.commons.challenger.dataobject.IvelaObj;
import br.ufc.ivela.commons.challenger.dataobject.IvelaPayload;
import br.ufc.ivela.commons.challenger.dataobject.IvelaStringObj;
import br.ufc.ivela.commons.challenger.dataobject.TransformerInterface;
import br.ufc.ivela.commons.challenger.util.DebugLogger;
import br.ufc.ivela.commons.challenger.xml.TransformFactory;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.Exam;
import br.ufc.ivela.commons.model.Exercise;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import br.ufc.ivela.ejb.interfaces.ExamRemote;
import br.ufc.ivela.ejb.interfaces.ExerciseRemote;
import br.ufc.ivela.ejb.interfaces.UnitContentRemote;
import br.ufc.ivela.ejb.interfaces.UnitRemote;
import br.ufc.ivela.web.action.GenericAction;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import org.xml.sax.SAXException;

/**
 *
 * @author rodrigo
 */
public class UnitContentAction extends GenericAction {

    private UnitContentRemote unitContentRemote;
    private UnitRemote unitRemote;
    private DisciplineRemote disciplineRemote;
    private java.io.File upload;
    private String uploadContentType;
    private String uploadFileName;
    private InputStream inputStream;
    private UnitContent unitContent;
    private XStream xStream = new XStream(new JettisonMappedXmlDriver());
    private String message;
    private ExerciseRemote exerciseRemote;
    private ExamRemote examRemote;
    private Unit unit;
    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

     
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
    public DisciplineRemote getDisciplineRemote() {
        return disciplineRemote;
    }

    public void setDisciplineRemote(DisciplineRemote disciplineRemote) {
        this.disciplineRemote = disciplineRemote;
    }

    public UnitRemote getUnitRemote() {
        return unitRemote;
    }

    public void setUnitRemote(UnitRemote unitRemote) {
        this.unitRemote = unitRemote;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UnitContent getUnitContent() {
        return unitContent;
    }

    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    /**
     * Sets the variables to be used on the input Unit
     * @return 
     */
    @Override
    public String input() {

        return INPUT;
    }
    
    
    public String show(){
        return "show";
    }

    /**
     * Upload the content of a unit
     * @return a string
     */
    public String upload() {
        System.out.println(uploadFileName);
        System.out.println(uploadContentType);
        //message = "Package successfully uploaded!";

        Unit u = unitRemote.get(this.unitContent.getUnitId());
        Discipline d = disciplineRemote.get(u.getDisciplineId());

        String path = ContentPackageUtils.PROCESS_PATH;

        Long id = null;
        if (this.unitContent != null && this.unitContent.getId() != null) {
            id = this.unitContent.getId();
            
            List<UnitContent> unitContents = unitContentRemote.getByUnit(this.unitContent.getUnitId());
            int order_n = this.unitContent.getOrderN();
            for (UnitContent uc : unitContents) {
                if (uc.getOrderN() >= order_n) {
                    uc.setOrderN(uc.getOrderN() + 1);
                    unitContentRemote.update(uc);
                }
            }
            
            unitContentRemote.update(this.unitContent);
        } else {
            id = unitContentRemote.add(this.unitContent);
        }

        this.unitContent = unitContentRemote.get(id);

        if (upload != null && uploadFileName != null && uploadFileName.trim().length() > 0) {
            boolean result = unitContentRemote.uploadPackage(upload, uploadFileName, uploadContentType);
            System.out.println("result: " + result);
            if (result) {

                try {
                    String unitContent = String.valueOf(this.unitContent.getId());
                    String unit = String.valueOf(u.getId());
                    String discipline = String.valueOf(d.getId());
                    String course = String.valueOf(d.getCourseId());

                    ContentPackageUtils.createDir(new File(path), course);
                    path += System.getProperty("file.separator") + course;
                    ContentPackageUtils.createDir(new File(path), discipline);
                    path += System.getProperty("file.separator") + discipline;
                    ContentPackageUtils.createDir(new File(path), unit);
                    path += System.getProperty("file.separator") + unit;
                    ContentPackageUtils.createDir(new File(path), unitContent);
                    path += System.getProperty("file.separator") + unitContent;


                    String deployFile = ContentPackageUtils.unzip(new File(ContentPackageUtils.UPLOAD_PATH + uploadFileName), new File(path));
                    String xml = ContentPackageUtils.getStringXML(new File(deployFile));

                    IvelaStringObj ivelaStringObj = new IvelaStringObj();
                    ivelaStringObj.setType(Constants.CONTENTPACKAGE);
                    ivelaStringObj.setXml(xml);
                    TransformerInterface tInter = TransformFactory.getTransformer(ivelaStringObj);

                    IvelaObj ivelaObj = null;

                    try {
                        ivelaObj = tInter.getTransformerType();
                        IvelaPayload p = ivelaObj.getIvelaPayload();

                        //System.out.println("File Systems: ");
                        for (FileSystem f : p.getContentPackage().getFileSystemList()) {
                            if (!new File(path + f.getValue()).isDirectory()) {

                                String contentPackageStr = "";
                                String line = "";
                                boolean find = false;
                                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path + f.getValue()))));
                                while ((line = br.readLine()) != null) {
                                    if (line.toLowerCase().indexOf("@path") > -1) {
                                        find = true;
                                        line = line.replaceAll("@path", course + File.separator + discipline + File.separator + unit + File.separator + unitContent);
                                    }
                                    contentPackageStr += line + "\n";
                                }
                                br.close();
                                if (find) {
                                    PrintWriter pw = new PrintWriter(new File(path + f.getValue()));
                                    pw.print(contentPackageStr);
                                    pw.close();
                                }

                            }
                        //ContentPackageUtils.moveFile(new File(ContentPackageUtils.UPLOAD_PATH + f.getValue()), new File(path + System.getProperty("file.separator") + f.getValue().substring(0, f.getValue().lastIndexOf(System.getProperty("file.separator")))));
                        //System.out.println("Movendo: De: " + ContentPackageUtils.UPLOAD_PATH + f.getValue() + " Para: " + path + System.getProperty("file.separator") + f.getValue().substring(0, f.getValue().lastIndexOf(System.getProperty("file.separator"))));
                        //System.out.println("- Value: " + f.getValue());
                        }
                        setMessage("Content package uploaded");
                    } catch (SAXException e) {
                        setMessage(e.getMessage());
                        DebugLogger.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
                    } catch (IOException e) {
                        setMessage(e.getMessage());
                        DebugLogger.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
                    }
                } catch (IOException e) {
                    setMessage(e.getMessage());
                    DebugLogger.getInstance().logAtExceptionTime(this.getClass().getName(), e.getMessage());
                }

            }
            setMessage("Content package uploaded");
        }
        //setInputStream(new ByteArrayInputStream(message.getBytes()));
        //return "json";
        return "courses";
    }

    public String getUnitContentOrdersJson() {
        StringBuilder json = new StringBuilder();
        List<UnitContent> unitContents = unitContentRemote.getByUnit(unit.getId());
        json.append("{");
        json.append("\"info\":[");
        int max = 0;
        for (UnitContent uc : unitContents) {
            if (uc.getOrderN() > max)
                max = uc.getOrderN();
        }
        max++;
        for (int i = 0; i < (max + 1); i++) {
            json.append("{ \"order_n\"" + ":\"" + i + "\" },");
        }
        if (json.length() > 0 && json.substring(json.length() - 1, json.length()).equalsIgnoreCase(",")) {
            json = new StringBuilder(json.substring(0, json.length() - 1));
        }
        json.append("]");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }
    
    public String getExercisesInfo() {
        xStream.alias("exercises", Exercise.class);
        xStream.omitField(Exercise.class, "createdBy");
        xStream.omitField(Exercise.class, "requisites");
        List<Exercise> results = exerciseRemote.getListExerciseByUnitContent(unitContent.getId());
        setInputStream(new ByteArrayInputStream(xStream.toXML(results).getBytes()));
        return "json";
    }

    public String getExamsInfo() {
        xStream.alias("exams", Exam.class);
        xStream.omitField(Exam.class, "createdBy");
        xStream.omitField(Exam.class, "requisites");
        //List<Exam> results = examRemote.getListExamByUnitContent(unitContent.getId());
        List<Exam> results = examRemote.getListAllExamByUnitContent(unitContent.getId());
        //System.out.println("%%%%%%%%"+results);
        setInputStream(new ByteArrayInputStream(xStream.toXML(results).getBytes()));

        return "json";
    }

    /**
     * Retrieves a upload
     * @return upload
     */
    public File getUpload() {
        return upload;
    }

    /**
     * Sets a upload
     * @param upload
     */
    public void setUpload(File upload) {
        this.upload = upload;
    }

    /**
     * Retrieves a upload to a content type
     * @return uploadContentType
     */
    public String getUploadContentType() {
        return uploadContentType;
    }

    /**
     * Retrieves a upload to a content type
     * @param uploadContentType
     */
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    /**
     * Retrieves a upload file name
     * @return uploadFileName
     */
    public String getUploadFileName() {
        return uploadFileName;
    }

    /**
     * Sets a upload file name
     * @param uploadFileName
     */
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    /**
     * Retrieves a remote content of a unit
     * @return unitContentRemote
     */
    public UnitContentRemote getUnitContentRemote() {
        return unitContentRemote;
    }

    /**
     * Sets a remote content of a unit
     * @param unitContentRemote
     */
    public void setUnitContentRemote(UnitContentRemote unitContentRemote) {
        this.unitContentRemote = unitContentRemote;
    }

    /**
     * Retrieves a input stream
     * @return inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Sets a input Stream
     * @param inputStream
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String removeUnitContent() {
        unitContent = unitContentRemote.get(unitContent.getId());
        //unit.setUnitContents(unitContentRemote.getByUnit(unit.getId()));
        performValidationRemove();
        if (!hasActionErrors()) {
            boolean result = unitContentRemote.remove(unitContent);
            xStream.alias("boolean", Boolean.class);
            String json = xStream.toXML(new Boolean(result));
            json = json.replaceAll("boolean", "result");
            setInputStream(new ByteArrayInputStream(json.getBytes()));
            String path = "/opt/ivela/";
            java.io.File directory = new java.io.File(path);
            File[] courses = directory.listFiles();
            for (File c : courses) {
                if (c.isDirectory()) {
                    File[] disciplines = c.listFiles();
                    for (File d : disciplines) {
                        if (d.isDirectory()) {
                            File[] units = d.listFiles();
                            for (File u : units) {
                                String unit = u.getAbsolutePath().substring(u.getAbsolutePath().lastIndexOf(File.separator) + 1);
                                //System.out.println("Unit: " + unit);
                                //System.out.println("Unit Id: " + unitContent.getUnitId());
                                if (u.isDirectory() && unit.equalsIgnoreCase(String.valueOf(unitContent.getUnitId()))) {
                                    File[] unitContents = u.listFiles();
                                    for (File uc : unitContents) {
                                        String strUnitContent = uc.getAbsolutePath().substring(uc.getAbsolutePath().lastIndexOf(File.separator) + 1);
                                        if (uc.isDirectory() && strUnitContent.equalsIgnoreCase(String.valueOf(unitContent.getId()))) {
                                            // System.out.println("Removendo conteudo da aula: " + unitContent.getId());
                                            deleteDir(uc);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            xStream.alias("boolean", Boolean.class);
            String json = xStream.toXML(new Boolean(false));
            json = json.replaceAll("boolean", "result");
            setInputStream(new ByteArrayInputStream(json.getBytes()));
        }
        return "json";
    }

    public static boolean deleteDir(File dir) {
        File candir;
        try {
            candir = dir.getCanonicalFile();
        } catch (IOException e) {
            return false;
        }
        if (!candir.equals(dir.getAbsoluteFile())) {
            return false;
        }
        File[] files = candir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                boolean deleted = file.delete();
                if (!deleted) {
                    if (file.isDirectory()) {
                        deleteDir(file);
                    }
                }
            }
        }
        return dir.delete();
    }

    /**
     * validates the data of the method remove
     */
    public void performValidationRemove() {

        logger.log("\n\nValidating remove");

        // verifies if the unit is null
        if (unitContent == null || unitContent.getId() == null) {
            addActionError(getText("unitContent.validation.requiredUnitContent"));
        } else {
            // verifies if this id is valid
            if (unitContentRemote.get(unitContent.getId()) == null) {
                addActionError(getText("unitContent.validation.invalidUnitContent"));
            }
        }
    }

    public ExerciseRemote getExerciseRemote() {
        return exerciseRemote;
    }

    public void setExerciseRemote(ExerciseRemote exerciseRemote) {
        this.exerciseRemote = exerciseRemote;
    }

    public XStream getXStream() {
        return xStream;
    }

    public void setXStream(XStream xStream) {
        this.xStream = xStream;
    }

    public ExamRemote getExamRemote() {
        return examRemote;
    }

    public void setExamRemote(ExamRemote examRemote) {
        this.examRemote = examRemote;
    }

    public String getContentPackageJson() {
        unitContent = unitContentRemote.get(unitContent.getId());
        Unit u = unitRemote.get(unitContent.getUnitId());
        Discipline d = disciplineRemote.get(u.getDisciplineId());
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"info\":{");
        json.append("\"course\":\"" + d.getCourseId() + "\",");
        json.append("\"discipline\":\"" + d.getId() + "\",");
        json.append("\"unit\":\"" + u.getId() + "\",");
        json.append("\"unitContent\":\"" + unitContent.getId() + "\",");
        json.append("\"unitContentWidth\":\"" + ((unitContent.getWidth() == null) ? "600" : unitContent.getWidth().intValue()) + "\",");
        json.append("\"unitContentHeight\":\"" + ((unitContent.getHeight() == null) ? "1000" : unitContent.getHeight().intValue()) + "\"");
        json.append("}");
        json.append("}");
        setInputStream(new ByteArrayInputStream(json.toString().getBytes()));
        return "json";
    }
}