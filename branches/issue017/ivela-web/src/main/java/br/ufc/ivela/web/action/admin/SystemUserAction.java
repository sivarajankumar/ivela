/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action.admin;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.model.Authentication;
import br.ufc.ivela.commons.model.Profile;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.ProfileRemote;
import br.ufc.ivela.web.action.*;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Action of the EAD's home
 */
public class SystemUserAction extends GenericAction {

    private SystemUserRemote systemUserRemote;
    private ProfileRemote profileRemote;
    private SystemUser systemUser;
    private CourseRemote courseRemote;
    private Integer type;
    private Long gradeId;
    private Long courseId;
    private Long studentId;
    private String stIds;
    
    private List<SystemUser> listAdmin;
    private List<SystemUser> listCoord;
    private List<SystemUser> listTutor;
    private List<SystemUser> listUser;
    private List<SystemUser> listProf;
    
    private Set<SystemUser> allUsers;
        
    private List<SystemUser> systemUsers;
    private String systemUserIds;
    private Long[] userIds;
    private InputStream inputStream;   
    
    private String usernameAdmin;
    private String usernameTutor;
    private String usernameCoordinator;
    private String username;

    public String show() {
     
        listAdmin = systemUserRemote.getByAuthentication(Constants.ROLE_ADMIN);
        listCoord = systemUserRemote.getByAuthentication(Constants.ROLE_COORD);
        listTutor = systemUserRemote.getByAuthentication(Constants.ROLE_TUTOR);
        listUser  = systemUserRemote.getByAuthentication(Constants.ROLE_USER);
        listProf  = systemUserRemote.getByAuthentication(Constants.ROLE_PROFESSOR);
        
        systemUsers = systemUserRemote.getAll();
        
        allUsers = new HashSet<SystemUser>();
        
        allUsers.addAll(listAdmin);
        allUsers.addAll(listCoord);
        allUsers.addAll(listTutor);
        allUsers.addAll(listUser);
        allUsers.addAll(listProf);
                        
        return "show";
    }
    
    public String deleteUser(){
        boolean resul = true;
        String[] ids = getSystemUserIds().split(";");
        if (ids == null) {
            ids = new String[]{getSystemUserIds()};
        }
        for (int i = 0; i < ids.length; i++) {
            SystemUser su = systemUserRemote.get(new Long(ids[i]));
            su.setEnabled(false);
            resul = resul && systemUserRemote.update(su);
        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("result", boolean.class);
        setInputStream(new ByteArrayInputStream(xStream.toXML(resul).getBytes()));
        
        return "json";
    }

    public String getInfo(){
        
        systemUser = systemUserRemote.get(systemUser.getId());
        
        if(systemUser.getProfileId() != null) {
            systemUser.setProfile(profileRemote.get(systemUser.getProfileId()));
        }
        
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("systemUser", SystemUser.class);
        xStream.alias("profile", Profile.class);
        
        setInputStream(new ByteArrayInputStream(xStream.toXML(systemUser).getBytes()));
        
        return "json";
    }
    
    public String getReport(){
                
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
                
        int res = courseRemote.isFinishedCourse(studentId, courseId, gradeId);
        String json = xStream.toXML(res);
        
        json = json.replaceAll("int", "count");

        setInputStream(new ByteArrayInputStream(json.getBytes()));
        return "json";
         
    }
    
    public String getReportForMany(){
        
        //GregorianCalendar gc = new GregorianCalendar();
        //long start = gc.getTimeInMillis();
        //System.out.println("start: " + start);
                
                
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        
        String myIds[] = stIds.split("A");
        int percentage[] = new int[4];
        
        ThreadToResolve[] workers = new ThreadToResolve[myIds.length];
        for(int i=0;i<myIds.length;i++){
            Long tempId = new Long(myIds[i]);
            workers[i] = new ThreadToResolve();
            workers[i].courseId = courseId;
            workers[i].gradeId = gradeId;
            workers[i].studentId = tempId;
            workers[i].courseRemote = courseRemote;
            workers[i].setPriority(Thread.MAX_PRIORITY);
            workers[i].start();
            
            //serial
            //int res = courseRemote.isFinishedCourse(tempId, courseId, gradeId);
           // System.out.println(res);
            //finished += res;
        }
        
        for(int i=0;i<myIds.length;i++){
            try {
                workers[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(SystemUserAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for(int i=0;i<myIds.length;i++){
           
           //System.out.println("W"+workers[i].res);
           //System.out.println("-f "+finished);
           //System.out.println("-t "+total);
           
            double pTemp = workers[i].res;
            //System.out.println("--"+pTemp);
            if(pTemp>= 0 && pTemp<=2.5){
                percentage[0]++;
            }else if(pTemp> 2.5 && pTemp<=5.0){
                percentage[1]++;
            }else if(pTemp>= 5.1 && pTemp<=7.5){
                percentage[2]++;
            }else
                percentage[3]++;
        }
        //System.out.println("-r "+resd);
        String stRes="";
        for(int i=0;i<percentage.length;i++){
           stRes += percentage[i]+"A"; 
        }
        
        String json = xStream.toXML(stRes);
        
        json = json.replaceAll("string", "count");

        setInputStream(new ByteArrayInputStream(json.getBytes()));
        
        GregorianCalendar gc2 = new GregorianCalendar();
        //long end = gc2.getTimeInMillis();
        //System.out.println("end: " + end);
        //System.out.println("overall: " + (end-start));
        return "json";
         
    }
    
    public String getReportForManyTeste(){
        
        //GregorianCalendar gc = new GregorianCalendar();
        //long start = gc.getTimeInMillis();
        //System.out.println("start: " + start);
                
                
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        
        ThreadToResolve[] workers = new ThreadToResolve[userIds.length];
        for(int i=0;i<userIds.length;i++){
            workers[i] = new ThreadToResolve();
            workers[i].courseId = courseId;
            workers[i].gradeId = gradeId;
            workers[i].studentId = userIds[i];
            workers[i].courseRemote = courseRemote;
            workers[i].setPriority(Thread.MAX_PRIORITY);
            workers[i].start();
        }
        
        for(int i=0;i<userIds.length;i++){
            try {
                workers[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(SystemUserAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Map<Double, Integer> map = new HashMap<Double, Integer>();
        
        for(int i=0;i<userIds.length;i++){
            double pTemp = workers[i].res;
            if(map.containsKey(pTemp)){
                map.put(pTemp, map.get(pTemp) + 1);
            }else{
                map.put(pTemp, 1);
            }
        }
        
        xStream.alias("percent", double.class);
        xStream.alias("qtd", int.class);
        String json = xStream.toXML(map);
        
        //json = json.replaceAll("string", "count");

        setInputStream(new ByteArrayInputStream(json.getBytes()));
        
        return "json";
         
    }
    
    public String add(){
        
        systemUser = systemUserRemote.get(systemUser.getId());
        systemUser.setAuthentication(new Authentication(new Long(type)));
        
        XStream xStream = new XStream(new JettisonMappedXmlDriver());        
        Boolean result = systemUserRemote.update(systemUser);        
        setInputStream(new ByteArrayInputStream(xStream.toXML(result).getBytes()));
        
        return "json";
    }
    
    public String change(){
        boolean result = true;
        String[] ids = getSystemUserIds().split(";");
        
        for (int i = 0; ids != null && i < ids.length; i++) {
            systemUser = systemUserRemote.get(new Long(ids[i]));
            
            systemUser.setAuthentication(new Authentication(new Long(type)));
            
            if (!systemUserRemote.update(systemUser)){
                result = false;
            }
        }
        
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        String json = xStream.toXML(new Boolean(result));
        setInputStream(new ByteArrayInputStream(json.getBytes()));   
        
        return "json";
    }

    public String searchUsersProfessor() {
        logger.log("nam:"+username);
        List<SystemUser> systemUserList = systemUserRemote.getByUsernameByAuthentication(username, Constants.ROLE_PROFESSOR);
        logger.log("fsdfds:"+systemUserList.size());
        String html = "";
        html += "<ul>";
        if (!systemUserList.isEmpty()) {
            for (SystemUser su : systemUserList) {
                html += "<li id=\"" + su.getId() + "\">" + su.getUsername() + "</li>";
            }
        }
        html += "</ul>";        
        setInputStream(new ByteArrayInputStream(html.getBytes()));
        return "text";
    }
    
    public String searchUsersTutor() {
        List<SystemUser> systemUserList = systemUserRemote.getByUsernameByAuthentication(username,Constants.ROLE_TUTOR);
        String html = "";
        html += "<ul>";
        if (!systemUserList.isEmpty()) {
            for (SystemUser su : systemUserList) {
                html += "<li id=\"" + su.getId() + "\">" + su.getUsername() + "</li>";
            }
        }
        html += "</ul>";        
        setInputStream(new ByteArrayInputStream(html.getBytes()));
        return "text";
    }
    
    public String searchUsersStudent() {
        List<SystemUser> systemUserList = systemUserRemote.getByUsernameByAuthentication(username,Constants.ROLE_USER);
        String html = "";
        html += "<ul>";
        if (!systemUserList.isEmpty()) {
            for (SystemUser su : systemUserList) {
                html += "<li id=\"" + su.getId() + "\">" + su.getUsername() + "</li>";
            }
        }
        html += "</ul>";        
        setInputStream(new ByteArrayInputStream(html.getBytes()));
        return "text";
    }
    
    public String searchUsers() {
        List<SystemUser> systemUserList = systemUserRemote.getUsersByUsername(username);
        String html = "";
        html += "<ul>";
        if (!systemUserList.isEmpty()) {
            for (SystemUser su : systemUserList) {
                html += "<li id=\"" + su.getId() + "\">" + su.getUsername() + "</li>";
            }
        }
        html += "</ul>";        
        setInputStream(new ByteArrayInputStream(html.getBytes()));
        return "text";
    }
    
    public List<SystemUser> getListAdmin() {
        return listAdmin;
    }

    public void setListAdmin(List<SystemUser> listAdmin) {
        this.listAdmin = listAdmin;
    }

    public List<SystemUser> getListCoord() {
        return listCoord;
    }

    public void setListCoord(List<SystemUser> listCoord) {
        this.listCoord = listCoord;
    }

    public List<SystemUser> getListProf() {
        return listProf;
    }

    public void setListProf(List<SystemUser> listProf) {
        this.listProf = listProf;
    }

    public List<SystemUser> getListTutor() {
        return listTutor;
    }

    public void setListTutor(List<SystemUser> listTutor) {
        this.listTutor = listTutor;
    }

    public List<SystemUser> getListUser() {
        return listUser;
    }

    public void setListUser(List<SystemUser> listUser) {
        this.listUser = listUser;
    }

    public SystemUserRemote getSystemUserRemote() {
        return systemUserRemote;
    }

    public void setSystemUserRemote(SystemUserRemote systemUserRemote) {
        this.systemUserRemote = systemUserRemote;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public ProfileRemote getProfileRemote() {
        return profileRemote;
    }

    public void setProfileRemote(ProfileRemote profileRemote) {
        this.profileRemote = profileRemote;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    
    public Set<SystemUser> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(Set<SystemUser> allUsers) {
        this.allUsers = allUsers;
    }

    public List<SystemUser> getSystemUsers() {
        return systemUsers;
    }

    public void setSystemUsers(List<SystemUser> systemUsers) {
        this.systemUsers = systemUsers;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSystemUserIds() {
        return systemUserIds;
    }

    public void setSystemUserIds(String systemUserIds) {
        this.systemUserIds = systemUserIds;
    }

    public CourseRemote getCourseRemote() {
        return courseRemote;
    }

    public void setCourseRemote(CourseRemote courseRemote) {
        this.courseRemote = courseRemote;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStIds() {
        return stIds;
    }

    public void setStIds(String stIds) {
        this.stIds = stIds;
    }

    public Long[] getUserIds() {
        return userIds;
    }

    public void setUserIds(Long[] userIds) {
        this.userIds = userIds;
    }
 
    public String getUsernameAdmin() {
        return usernameAdmin;
    }

    public void setUsernameAdmin(String usernameAdmin) {
        this.usernameAdmin = usernameAdmin;
    }

    public String getUsernameCoordinator() {
        return usernameCoordinator;
    }

    public void setUsernameCoordinator(String usernameCoordinator) {
        this.usernameCoordinator = usernameCoordinator;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameTutor() {
        return usernameTutor;
    }

    public void setUsernameTutor(String usernameTutor) {
        this.usernameTutor = usernameTutor;
    }
    
}

class ThreadToResolve extends Thread{

    public Long studentId;
    public Long gradeId;
    public Long courseId; 
    public CourseRemote courseRemote;
    public int res;
    @Override
    public void run() {
     
     res = courseRemote.isFinishedCourse(studentId, courseId, gradeId);   
        
    }
    
    
}