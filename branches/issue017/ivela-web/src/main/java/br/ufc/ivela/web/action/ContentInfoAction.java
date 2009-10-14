/*
#############################################################################################
# Copyright(c) 2009 by IBM Brasil Ltda and others                                           #
# This file is part of ivela project, an open-source                                        #
# Program URL   : http://code.google.com/p/ivela/                                           #
#                                                                                           #
# This program is free software; you can redistribute it and/or modify it under the terms   #
# of the GNU General Public License as published by the Free Software Foundation; either    #
# version 3 of the License, or (at your option) any later version.                          #
#                                                                                           #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. #
# See the GNU General Public License for more details.                                      #
#############################################################################################
# File: ContentInfoAction.java                                                              #
# Document: Action for Course Content                                                       #
# Date        - Author(Company)                   - Issue# - Summary                        #
# 09-OCT-2009 - Rafael Lagoa (Instituto Eldorado) - 000017 - Initial version                #
#############################################################################################
*/
package br.ufc.ivela.web.action;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.struts2.ServletActionContext;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.model.Discipline;
import br.ufc.ivela.commons.model.FinishedUnitContent;
import br.ufc.ivela.commons.model.Profile;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.commons.model.Transcript;
import br.ufc.ivela.commons.model.Unit;
import br.ufc.ivela.commons.model.UnitContent;
import br.ufc.ivela.ejb.interfaces.CourseRemote;
import br.ufc.ivela.ejb.interfaces.DisciplineRemote;
import br.ufc.ivela.ejb.interfaces.FinishedUnitContentRemote;
import br.ufc.ivela.ejb.interfaces.GradeUnitContentRemote;
import br.ufc.ivela.ejb.interfaces.HistoryRemote;
import br.ufc.ivela.ejb.interfaces.ProfileRemote;
import br.ufc.ivela.ejb.interfaces.SystemUserRemote;
import br.ufc.ivela.ejb.interfaces.UnitContentRemote;
import br.ufc.ivela.ejb.interfaces.UnitRemote;
import br.ufc.ivela.util.PropertiesUtil;
import br.ufc.ivela.util.PropertiesUtil.IVELA_PROPERTIES;

public class ContentInfoAction extends CourseAwareAction {

    private static final String DEFAULT_RENDERER = "RenderServlet";
    
    public static Cache cache;
    
    static {
        cache = cacheManager.getCache("contentInfoCache");        
    } 
    
    private InputStream inputStream;   
    
    private DisciplineRemote disciplineRemote;
    private UnitRemote unitRemote;
    private UnitContentRemote unitContentRemote;
    private GradeUnitContentRemote gradeUnitContentRemote;
    private FinishedUnitContentRemote finishedUnitContentRemote;
    private SystemUserRemote systemUserRemote;
    private ProfileRemote profileRemote;    

    private Discipline discipline;
    private Unit unit;
    private UnitContent unitContent;    
    private String goToPage;
    private String goToIndex;
    private String disciplineTag;
    private String unitTag;
    private String pageHtml;

    private String path;
    private String replacePath;
    private String scoreType;
    
    public String getSystemUser() {
        Profile profile = profileRemote.getBySystemUserId(getAuthenticatedUser().getId());
        String name = profile.getFirstName()+" "+profile.getLastName();
        if (name == null || "".equals(name.trim())) {
            name = getAuthenticatedUser().getUsername();
        }
        setInputStream(new ByteArrayInputStream(name.getBytes()));
        return "text";
    }

    public String getProgress() {
        Integer progress = courseRemote.getProgress(getAuthenticatedUser().getId(), course.getId());
        if (!new Integer(0).equals(progress)) {
            progress = progress/10;
        }
        setInputStream(new ByteArrayInputStream(progress.toString().getBytes()));
        return "text";
    }

    private String getFilenameByDisciplineTag(String disciplineTag) {
    	discipline = disciplineRemote.getByCourseAndTag(course.getId(), disciplineTag);
    	replacePath = ""+ course.getId() + File.separator + discipline.getId();
    	
    	return discipline.getId() + "/" + goToPage;
    }

    private String getFilenameByUnitTag(String unitTag) {
    	Long disc = discipline.getId();
    	unitContent = unitContentRemote.getByDisciplineAndTag(disc,unitTag);
    	replacePath = ""+ course.getId() + File.separator + discipline.getId() + File.separator + unitContent.getUnitId() + File.separator + unitContent.getId();
    	return unitContent.getUnitId() + "/" + unitContent.getId() + "/" + goToPage;
    }

    public String getTimeLeft() {
        String timeLeft = courseRemote.getTimeLeft(getAuthenticatedUser().getId(), course.getId());
        setInputStream(new ByteArrayInputStream(timeLeft.toString().getBytes()));
        return "text";
    }

    public String getTutorsEmail() {
        grade = gradeRemote.get(grade.getId());
        Set<SystemUser> tutors = grade.getTutors();         
        StringBuilder builder = new StringBuilder();
        builder.append("mailto:");
        for (SystemUser tutor: tutors) {
            builder.append(tutor.getEmail());
            builder.append(',');            
        }
        
        // No Tutors? Try Professors
        if (builder.length() == 7) {         
            Set<SystemUser> professors = grade.getProfessors();
            for (SystemUser professor: professors) {
                builder.append(professor.getEmail());
                builder.append(',');            
            }            
        }
        
        // No Professors too?!? Coordinator!
        if (builder.length() == 7) {            
            SystemUser coord = systemUserRemote.get(grade.getCoordinatorId());
            builder.append(coord.getEmail());
            builder.append(',');
        }
        
        if (builder.length() > 7) {
            builder.deleteCharAt(builder.length() - 1);
            builder.append("?subject=[iVela] ");
            builder.append(getText("grade.contact.questions"));
            builder.append(' ');
            builder.append(grade.getName());            
            if (unitContent != null && unitContent.getId() != null) {
                builder.append(' ');
                builder.append('-');
                builder.append(' ');
                unitContent = unitContentRemote.get(unitContent.getId());
                builder.append(unitContent.getTitle());                
            }            
        }
        
        setInputStream(new ByteArrayInputStream(builder.toString().getBytes()));
        return "text";
    }
    
    public String getScore() {
        SystemUser user = getAuthenticatedUser();
        List<Transcript> transcripts = historyRemote.getTranscriptsByStudentByGrade(user.getId(), grade.getId());
        
        if (transcripts.size() < 0) return "";
        
        Transcript transcript = transcripts.get(0);
        StringBuilder builder = new StringBuilder();
        int total = (int) transcript.getChallengesTotal().doubleValue();                
        builder.append(total);
        if (scoreType.equals("current")) {
            builder.append(' ');
            int currentP = transcript.getChallengesWeight() * Transcript.DEFAULT_GRADE;
            builder.append(getText("history.of"));
            builder.append(' ');
            builder.append(currentP);
        } else if (scoreType.equals("total")) {
            builder.append(' ');
            course = courseRemote.get(course.getId());
            int totalP = course.getChallengeWeight() * Transcript.DEFAULT_GRADE;
            builder.append(getText("history.of"));
            builder.append(' ');
            builder.append(totalP);
        }
        builder.append(' ');
        builder.append(getText("history.points"));
        
        setInputStream(new ByteArrayInputStream(builder.toString().getBytes()));
        
        return "text";
    }
    
    public String showContentCustom() {        
    	String filename = Constants.DEFAULT_CONTENTPKG_PATH + "/" + goToPage;
    	SystemUser user = systemUserRemote.get(getAuthenticatedUser().getId());        
    	if (goToIndex!=null) {
    		replacePath = ""+course.getId();
    		filename = Constants.DEFAULT_CONTENTPKG_PATH + "/" + course.getId() + "/" + goToPage;        	
    	} else if (disciplineTag!=null) {                	    		
        	filename = Constants.DEFAULT_CONTENTPKG_PATH + "/" + course.getId() + "/" + getFilenameByDisciplineTag(disciplineTag);
        } else if (unitTag!=null) {
        	filename = Constants.DEFAULT_CONTENTPKG_PATH + "/" + course.getId() + "/"+discipline.getId()+"/" + getFilenameByUnitTag(unitTag);        		
       	} else {
       		replacePath = course.getId() + File.separator + discipline.getId() + File.separator + unit.getId() + File.separator + unitContent.getId();
       		filename = Constants.DEFAULT_CONTENTPKG_PATH + "/" + course.getId() + "/" + discipline.getId() + "/" + unit.getId() + "/" + unitContent.getId() + "/" + goToPage;
		  user.setLastUnitContentId(unitContent.getId());
       	} 
    	path = DEFAULT_RENDERER + "?file=" + replacePath + File.separator;
        systemUserRemote.update(user);
        setPageHtml(loadContentFile(filename));
        return "show";
    }
    
    
    public String showContent() {        
        
    	String filename = Constants.DEFAULT_CONTENTPKG_PATH + "/" + course.getId() + "/" + discipline.getId() + "/" + unit.getId() + "/" + unitContent.getId() + "/" + goToPage;
    	replacePath = course.getId() + File.separator + discipline.getId() + File.separator + unit.getId() + File.separator + unitContent.getId();  		
    	path = DEFAULT_RENDERER + "?file=" + replacePath + File.separator;
    	
        setPageHtml(loadContentFile(filename));

        SystemUser user = systemUserRemote.get(getAuthenticatedUser().getId());
        user.setLastUnitContentId(unitContent.getId());
        systemUserRemote.update(user);

        return "show";
    }

    public String isUnlocked() {
        boolean isUnlocked = gradeUnitContentRemote.isUnlocked(grade.getId(), unitContent.getId());
        setInputStream(new ByteArrayInputStream(new Boolean(isUnlocked).toString().getBytes()));
        return "text";
    }

    public String isCompleted() {
        setInputStream(new ByteArrayInputStream("false".getBytes()));
        List<FinishedUnitContent> finishedUnitContentlist = finishedUnitContentRemote.getByUnitContentAndSystemUser(unitContent.getId(), getAuthenticatedUser().getId());
        if (!finishedUnitContentlist.isEmpty()) {
            setInputStream(new ByteArrayInputStream("true".getBytes()));
        }
        return "text";
    }

    public String finishLesson() {
        List<FinishedUnitContent> finishedUnitContentlist = finishedUnitContentRemote.getByUnitContentAndSystemUser(unitContent.getId(), getAuthenticatedUser().getId());
        if (finishedUnitContentlist.size() == 0) {
            FinishedUnitContent finishedUnitContent = new FinishedUnitContent();
            finishedUnitContent.setSystemUser(getAuthenticatedUser().getId());
            finishedUnitContent.setCourse(course.getId());
            finishedUnitContent.setUnitContent(unitContent.getId());
            finishedUnitContentRemote.add(finishedUnitContent);
        }

        boolean sendMail = gradeUnitContentRemote.sendMail(grade.getId(), unitContent.getId());
        if (sendMail) {
            HttpServletRequest request = ServletActionContext.getRequest();
            String url = "http://" + request.getServerName() + ":"
                    + request.getServerPort() + PropertiesUtil.getPropertiesUtil().getProperty(IVELA_PROPERTIES.WEB_PATH);
            if (!url.endsWith("/")) {
                url += "/";
            }
            unitContent = unitContentRemote.get(unitContent.getId());
            Map params = new HashMap();
            params.put("unitContentTitle", unitContent.getTitle());
            params.put("url", url);
            mailer.send(new SystemUser[] { getAuthenticatedUser() }, null, "lesson.finished", "lesson.finished.velocity", new Map[]{params}, true);
        }
        return "text";
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setGradeUnitContentRemote(GradeUnitContentRemote gradeUnitContentRemote) {
        this.gradeUnitContentRemote = gradeUnitContentRemote;
    }

    public void setFinishedUnitContentRemote(FinishedUnitContentRemote finishedUnitContentRemote) {
        this.finishedUnitContentRemote = finishedUnitContentRemote;
    }

    public void setDisciplineRemote(DisciplineRemote disciplineRemote) {
        this.disciplineRemote = disciplineRemote;
    }

    public void setUnitRemote(UnitRemote unitRemote) {
        this.unitRemote = unitRemote;
    }

    public void setUnitContentRemote(UnitContentRemote unitContentRemote) {
        this.unitContentRemote = unitContentRemote;
    }

    public void setSystemUserRemote(SystemUserRemote systemUserRemote) {
        this.systemUserRemote = systemUserRemote;
    }

    public void setProfileRemote(ProfileRemote profileRemote) {
        this.profileRemote = profileRemote;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Discipline getDiscipline() {
        return discipline;
    }
    
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }
    
    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }
    
    public UnitContent getUnitContent() {
        return unitContent;
    }
    
    public void setGoToPage(String goToPage) {
        this.goToPage = goToPage;
    }

    public void setGoToIndex(String goToIndex) {
        this.goToIndex = goToIndex;
    }
    
    public void setDisciplineTag(String disciplineTag) {
        this.disciplineTag = disciplineTag;
    }

    public void setUnitTag(String unitTag) {
        this.unitTag = unitTag;
    }

    public String getPageHtml() {
        return pageHtml;
    }

    public void setPageHtml(String pageHtml) {
        this.pageHtml = pageHtml;
    }    
    
    /**
     * @param scoreType the scoreType to set
     */
    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    /**
     * @return the scoreType
     */
    public String getScoreType() {
        if (scoreType == null) return "";
        return scoreType;
    }
    
    /**
     * Load a Content File
     * 
     * @param filename The File name 
     * 
     * @return a String representation of the file contents. 
     */
    private String loadContentFile(String filename)  {
        String content = "";
        
        try {
            File file = new File(filename);
            
            // It tries to load from the cache first.
            // Caches are kept by the complete filename and date of the file
            // Old files are not removed until the cache is full.
            String key = filename + file.lastModified();
            Element cacheElement = cache.get(key);            
            if (cacheElement != null) {
                log.debug("retrieved "+ filename + " from cache ");                
                content = (String) cacheElement.getValue();
            } else {
                content = parseContentFile(file);                
                cache.put(new Element(key, content));
            }
        }  catch (Exception e) {
            log.error("Error Loading Course Content:" + filename, e);
        }
        
        return content;
    }
    
    /**
     * Parses the Content of a File (Replacing all the dynamic contents)
     * 
     * @param file The File to be parsed
     * 
     * @return a String with the content properlu parsed.
     */
    private String parseContentFile(File file) {                    
        BufferedReader in = null;
        StringBuilder html = new StringBuilder();
        try {   
            
            //TODO Force the User to specify the enconding?
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));
            String str;
            while ((str = in.readLine()) != null) {
                html.append(str);
            }
        } catch (IOException ioe) {
            log.error("Error Reading the File Content:" + file.getName(), ioe);
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                log.debug("Error Closing file:" + file.getName(), e);
            }
        }             
       
        //path = DEFAULT_RENDERER + "?file=" + course.getId() + File.separator + discipline.getId()
        //+ File.separator + unit.getId() + File.separator + unitContent.getId() + '/';
        
        // Parsing Contents
        parseContentFilePath(html);
        parseContentFileCSS(html);
        parseContentFileImg(html);
        parseContentFileInput(html);
        parseContentFileJS(html);        
        
        return html.toString();        
    }

    private void parseContentFilePath(StringBuilder builder) {
        String css = "@path";
        int position = -1;
        int tempPosition = 0;
        while ((position = builder.indexOf(css, tempPosition)) > -1) {
            builder.replace(position, position + css.length(), replacePath);
            tempPosition = position + 1;
        }
    }
    
    private void parseContentFileCSS(StringBuilder builder) {
        String css = "@import";        
        
        int position = -1;
        int tempPosition = 0;
        while ((position = builder.indexOf(css, tempPosition)) > -1) {            
            int start = builder.indexOf("'", position + 1);
            int start_2 = builder.indexOf("\"", position + 1);
            start = start < 0? builder.length() - 1: start;
            start_2 = start_2 < 0? builder.length() - 1 : start_2;
            start = start > start_2? start_2 : start;
            builder.insert(start + 1,  path);
            tempPosition = start;
        }
    }        
        
    private void parseContentFileJS(StringBuilder builder) {
        String input = "<script";
        String src = "src=";
        String type = "type";
        String end = ">";
        
        int position = -1;
        int tempPosition = 0;
        while ((position = builder.indexOf(input, tempPosition)) > -1) {
            int typePos = builder.indexOf(type, position + 1);
            int endPos = builder.indexOf(end, position + 1);
            typePos = typePos < 0? builder.length() : typePos;            
            
            if (typePos > endPos) {
                tempPosition = position + 1;
                continue;
            }
            
            String typeOfInput = extractValue(builder, typePos, endPos, "\"");
            typeOfInput = typeOfInput.equals("")? extractValue(builder, typePos, endPos, "'") : typeOfInput;
            
            if (typeOfInput.equalsIgnoreCase("text/javascript")) {
                int srcPos = builder.indexOf(src, position + 1);
                builder.insert(srcPos + src.length() + 1,  path);                   
            }            
            
            tempPosition = position + 1;
        }
    }    

    private void parseContentFileImg(StringBuilder builder) {
        String img = "<img";
        String src = "src=";        
        
        int position = -1;
        int tempPosition = 0;
        while ((position = builder.indexOf(img, tempPosition)) > -1) {
            int srcPos = builder.indexOf(src, position + 1);
            builder.insert(srcPos + src.length() + 1,  path);
            tempPosition = position + 1;
        }
    }
    
    private void parseContentFileInput(StringBuilder builder) {
        String input = "<input";
        String src = "src=";
        String type = "type";
        String end = ">";
        
        int position = -1;
        int tempPosition = 0;
        while ((position = builder.indexOf(input, tempPosition)) > -1) {
            int typePos = builder.indexOf(type, position + 1);
            int endPos = builder.indexOf(end, position + 1);
            typePos = typePos < 0? builder.length() : typePos;            
            
            if (typePos > endPos) {
                tempPosition = position + 1;
                continue;
            }
            
            String typeOfInput = extractValue(builder, typePos, endPos, "\"");
            typeOfInput = typeOfInput.equals("")? extractValue(builder, typePos, endPos, "'") : typeOfInput;
            
            if (typeOfInput.equalsIgnoreCase("image")) {
                int srcPos = builder.indexOf(src, position + 1);
                builder.insert(srcPos + src.length() + 1,  path);                   
            }            
            
            tempPosition = position + 1;
        }
    }
    
    private String extractValue(StringBuilder builder, int initPost, int endPos, String separator) {
        String value = "";
        int start = builder.indexOf(separator, initPost);
        int end = builder.indexOf(separator, start + 1);
        
        if (end < endPos) {
            value = builder.substring(start + 1, end); 
        }
        
        return value;
    }

}