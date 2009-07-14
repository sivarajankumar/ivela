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
# File: ChallengeGetScore.jsp                                                                            #
# Document: Getting Score from exercises                                                                     #
# Date        - Author(Company)                   - Issue# - Summary                        #
# XXXXXXXXXXX - Unknown                           - XXXXXX - Initial Version                #
# 01-JUL-2009 - Fabio Fantato(Instituto Eldorado) - 000010 - Score page does not working    #
#############################################################################################
*/
package br.ufc.ivela.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.context.SecurityContextHolder;

import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.ChallengeRemote;
import br.ufc.ivela.servlets.challenge.Challenge;
import br.ufc.ivela.servlets.challenge.Constants;
import br.ufc.ivela.servlets.challenge.Converter2ObjectFactory;
import br.ufc.ivela.servlets.challenge.IvelaObj;
import br.ufc.ivela.servlets.challenge.XMLObject;

/**
 *
 * @author jdamico
 */
public class ChallengeGetScore extends HttpServlet {

    /**
     * ivela-web/ChallengeGetScore?scoreType=reading&scoreXMLUrl=/1/1/1/84/scores/unit1.xml
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long uid = 0L;
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj != null && obj instanceof SystemUser) {
            uid = ((SystemUser) obj).getId();
        }
        ChallengeRemote challRemote = this.getChallengeRemote();


        String[] scoreType = {"reading", "writing", "listening", "speaking"};
        String scoreXMLUrl = request.getParameter("scoreXMLUrl");
        
        double[] result = new double[4];
        int[] scoreListTotal = new int[4];
        double[] studentPointsTotal = new double[4];
        String[] pointColor = new String[4];

        DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.0" );
        DecimalFormat df1 = new DecimalFormat( "#,###,###,##0" );
        
        for (int j = 0; j < scoreType.length; j++) {
            int studentTotal = 0;
            double studentPoints = .0;
            ArrayList<String> scoreList = getScore(scoreType[j], scoreXMLUrl);
            int scoreTotal = scoreList.size();
            for (int i = 0; i < scoreTotal; i++) {
                br.ufc.ivela.commons.model.Challenge chalFromModel = challRemote.get(scoreList.get(i), uid);
                if (chalFromModel != null) {

                    studentPoints+= chalFromModel.getChallvalue();
                    
                    studentTotal++;


                }
            }
            result[j] = (studentTotal * 100) / scoreTotal;
            scoreListTotal[j] = scoreTotal;
            studentPointsTotal[j] = (studentPoints * 100) / (scoreListTotal[j]*100) ;
            studentPointsTotal[j] = new Double(df2.format(studentPointsTotal[j])).doubleValue();
            pointColor[j] = (studentPointsTotal[j] > 70) ? "green" : "red";
        }


        

        

        String ret = "<div class=\"content-lesson\">" +
                "<h2>Percentage of done exercises</h2>" +
                "<table width=\"569\" border=\"0\" align=\"center\">" +
                "<tr><td width=\"40\" valign=\"middle\"><div align=\"center\"><img src=\"RenderServlet?file=1/1/1/84/images/read_ico.gif\"><br><center><font size='1'>*</font></center></div></td><td width=\"271\" valign=\"middle\">" +
                "  <div align=\"left\">" +
                "    <applet code=\"org.jdamico.gaugeapplet.core.DrwGauge\" archive=\"RenderServlet?file=/globals/applets/jdamicoGauge.jar\" width=\"200\" height=\"200\">" +
                "      <param name=\"percentValue\" value=\""+result[0]+"\">" +
                "      <param name=\"pointerColor\" value=\"E05A59\" >" +
                "    </applet>" +
                "  </div></td>" +
                "  <td width=\"40\" valign=\"middle\"><img src=\"RenderServlet?file=1/1/1/84/images/write_ico.gif\" alt=\"\" />" +
                "<br><center><font size='1'>*</font></center></td>" +
                "  <td width=\"200\" valign=\"middle\"><applet code=\"org.jdamico.gaugeapplet.core.DrwGauge\" archive=\"RenderServlet?file=/globals/applets/jdamicoGauge.jar\" width=\"200\" height=\"200\">" +
                "    <param name=\"percentValue\" value=\""+result[1]+"\" />" +
                "    <param name=\"pointerColor\" value=\"62A165\" />" +
                "  </applet></td>" +
                "</tr>" +
                "<tr>" +
                "  <td valign=\"middle\"><img src=\"RenderServlet?file=1/1/1/84/images/listen_ico.gif\" alt=\"\" />" +
                "<br><center><font size='1'>*</font></center></td></td>" +
                "  <td valign=\"middle\"><applet code=\"org.jdamico.gaugeapplet.core.DrwGauge\" archive=\"RenderServlet?file=/globals/applets/jdamicoGauge.jar\" width=\"200\" height=\"200\">" +
                "    <param name=\"percentValue\" value=\""+result[2]+"\" />" +
                "    <param name=\"pointerColor\" value=\"61799F\" />" +
                "  </applet></td>" +
                "  <td valign=\"middle\"><img src=\"RenderServlet?file=1/1/1/84/images/speak_ico.gif\" alt=\"\" />" +
                "<br><center><font size='1'>*</font></center></td></td>" +
                "  <td valign=\"middle\"><applet code=\"org.jdamico.gaugeapplet.core.DrwGauge\" archive=\"RenderServlet?file=/globals/applets/jdamicoGauge.jar\" width=\"200\" height=\"200\">" +
                "    <param name=\"percentValue\" value=\""+result[3]+"\" />" +
                "    <param name=\"pointerColor\" value=\"9D609F\" />" +
                "  </applet></td>" +
                "</tr>" +
                "</table><br><br>" +
                "<center>" +
                "* This percentage represents the quantity of exercises<br> done in this unit, per skills." +
                "</center>";
                

       ret = ret + "<br><br>" +
                    "<h2>Score</h2>" +
                    "<table width='400' align='center' bgcolor='#CCCCCC'>" +
                    "<tr valign = 'middle' bgcolor='#CCCCCC'><td width='80'><b><center>Skills</center></b></td><td width='160'><b><center>Score</center></b></td><td width='160'><b><center>Total number of exercises<br> of this unit</center></b></td></tr>" +
                    "<tr valign = 'middle' bgcolor='#EFEFEF'><td width='80'><center><img src=\"RenderServlet?file=1/1/1/84/images/read_ico.gif\"></center></td><td width='160'><center>  <h1><font color = \""+pointColor[0]+"\">"+studentPointsTotal[0]+"%</font></h1></center></td><td width='160'><center><h1>"+scoreListTotal[0]+"</h1></center></td></tr>" +
                    "<tr valign = 'middle' bgcolor='#FFFFFF'><td width='80'><center><img src=\"RenderServlet?file=1/1/1/84/images/write_ico.gif\"></center></td><td width='160'><center> <h1><font color = \""+pointColor[1]+"\">"+studentPointsTotal[1]+"%</font></h1></center></td><td width='160'><center><h1>"+scoreListTotal[1]+"</h1></center></td></tr>" +
                    "<tr valign = 'middle' bgcolor='#EFEFEF'><td width='80'><center><img src=\"RenderServlet?file=1/1/1/84/images/listen_ico.gif\"></center></td><td width='160'><center><h1><font color = \""+pointColor[2]+"\">"+studentPointsTotal[2]+"%</font></h1></center></td><td width='160'><center><h1>"+scoreListTotal[2]+"</h1></center></td></tr>" +
                    "</table><br><br>" +
                    "<center>" +
                    "Dear student, if you score is showing any percentage below 70%,<br>" +
                    "we strongly recommend that you try to do the respective exercises again. " +
                    "</center>" +
                    "</div>";
           response.getWriter().println(ret);

    }

    private ChallengeRemote getChallengeRemote() {
        ChallengeRemote challengeRemote = null;

        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("ChallengeBean#br.ufc.ivela.ejb.interfaces.ChallengeRemote");
            challengeRemote = (ChallengeRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, ChallengeRemote.class);
        } catch (NamingException e) {
            e.printStackTrace();
            challengeRemote = null;
        }

        return challengeRemote;
    }

    private ArrayList<String> getScore(String scoreType, String scoreXMLUrl) {
        StringBuffer sb = null;
    	Boolean err = true;
    	String errMsg = "Incorrect file path or incorrect file name. ("+br.ufc.ivela.commons.Constants.DEFAULT_CONTENTPKG_PATH+scoreXMLUrl+") ";
    	String filename = br.ufc.ivela.commons.Constants.DEFAULT_CONTENTPKG_PATH+scoreXMLUrl;        	
    	// Create a URL for the desired page
    	File file = null;

    	try {
              
              if(scoreXMLUrl!=null){
                      file = new File(filename);
                      if(file.exists()){
                    	  System.err.println(filename);                    	  
                          // Read all the text returned by the server
                          BufferedReader in = new BufferedReader(new FileReader(filename));
                          sb = new StringBuffer();
                          String str;
                          while ((str = in.readLine()) != null) {
                              sb.append(str);
                          }
                          in.close();
                      }
              } else {
                  err = true;
              }
          
        } catch(NullPointerException npe){
            err = true;
            errMsg = errMsg + npe.getMessage();
        }catch (MalformedURLException e) {
        	err = true;
            e.printStackTrace();
        } catch (IOException e) {
        	err = true;
            e.printStackTrace();
        }
        
        if (err) System.err.println(errMsg);

        XMLObject xo = new XMLObject();
        xo.setIntType(Constants.SCORE_TYPE);
        xo.setEntireXml(sb.toString());
        IvelaObj ivelaObj = Converter2ObjectFactory.getConverter(xo).getConverterType();


        List<Challenge> challList = ivelaObj.getIvelaPayload().getListChallenge();
        ArrayList<String> scoreList = new ArrayList<String>();
        for (int i = 0; i < challList.size(); i++) {
            Challenge challElement = challList.get(i);
            if (challElement.getScore().equals(scoreType)) {
                scoreList.add(challElement.getName());
            }
        }

        System.err.println("challList: " + challList.size());
        System.err.println("scoreList: " + scoreList.size());

        return scoreList;
    }
}
