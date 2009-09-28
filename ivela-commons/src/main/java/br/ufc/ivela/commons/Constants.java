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
#                                                                                           #
#############################################################################################
# File:     Constants.java                                                                  #
# Document: Constants                                                                       #
# Date        - Author(Company)                    - Issue# - Summary                       #
# XX-XXX-XXXX - Rodrigo                            - XXXXXX - Initial Version               #
# 08-JUN-2009 - Mileine Assato(Instituto Eldorado) - 000007 - SYSTEM_VERSION constant added #
#############################################################################################
*/ 

package br.ufc.ivela.commons;


public class Constants {
    
	//SYSTEM VERSION
	public static final String SYSTEM_VERSION = "V01.02";
	
    //TYPE OF GENDER
    public static final int GENDER_FEMALE = 0;
    public static final int GENDER_MALE = 1;
    
    //TYPE OF QUESTIONS
    public static final int QUESTION_SUBJECTIVE = 1;
    public static final int QUESTION_OBJECTIVE = 2;
    public static final int QUESTION_AUDITIVE = 3;
    public static final int QUESTION_EXTERNAL = 4;
    public static final int QUESTION_READING = 5;
    public static final int QUESTION_COMPLETE = 6;
    public static final int QUESTION_SHUFFLE = 7;
    
    //BIN TYPE
    public static final int JAVA_TYPE = 1;
    public static final int FLASH_TYPE = 2;
    public static final int JAVASCRIPT_TYPE = 3;
    
    
    //STATUS FOR ENROLLMENT
    public static final int ENROLLMENT_PENDING = 0;
    public static final int ENROLLMENT_ACTIVE = 1;
    public static final int ENROLLMENT_SUSPENDED = 2;
    public static final int ENROLLMENT_FINISHED = 3;
    
    //STATUS FOR GRADE
    public static final int GRADE_INACTIVE = 0;
    public static final int GRADE_PERIOD_OF_ENROLLMENT = 1;
    public static final int GRADE_IN_PROGRESS = 3;
    public static final int GRADE_FINISHED = 2;
    
    //Calender
    public static final int MINIMUM_HOUR = 0;
    public static final int MAXIMUM_HOUR = 23;
    
    public static final int MINIMUM_MINUTE = 0;
    public static final int MAXIMUM_MINUTE = 59;
    
    public static final int MINIMUM_SECOND = 0;
    public static final int MAXIMUM_SECOND = 59;
    
    public static final int FIRST_MONTH = 1;
    
    //Method of list note
    public static final int LIST_BY_DAY = 0;
    public static final int LIST_BY_WEEK = 1;
    public static final int LIST_BY_MONTH = 2;
    
  
    public static final String FILE_UPLOAD_PATH = "/uploads/";
    
    public static final String FILE_UPLOAD_PARTNERS = "/opt/ivela/upload/";
    
    public static final String AUDIO_UPLOAD_PATH = "/var/www/public_content/";
    
    public static final String WEB_PATH = "/ivela-web";
    
    public static final String MIME_TYPE_RSS = "application/xml";
    
    public static final String LOCALE_PT = "pt";
    public static final String LOCALE_EN = "en";
    
    //student exercise
     public static final int STUDENT_EXERCISE_FINISHED = 1;
     
     public static final int STUDENT_EXAM_FINISHED = 1;
     
     public static final String DEFAULT_CONTENTPKG_PATH = "/opt/ivela/";
     public static final String RENDER_SERVLET_FILE_PARAM = "file";
     
     public static final Long ROLE_ADMIN = 1L;
     public static final Long ROLE_COORD = 2L;
     public static final Long ROLE_TUTOR = 3L;
     public static final Long ROLE_EAD_USER = 4L;
     public static final Long ROLE_USER = 5L;
     public static final Long ROLE_PROFESSOR = 6L;
     
     
     public static final int   TRANSCRIPT_FAILED =0;
     public static final int   TRANSCRIPT_APPROVED =1;
     public static final int   TRANSCRIPT_SUSPENDED =2;
     public static final int   TRANSCRIPT_ENROLLMENT = 3;
     
     public static final Double  AVERAGE_FOR_APPROVAL = 7.0;
     
     public static final Long MAX_LENGTH_FILE = 3000000L;                      
}