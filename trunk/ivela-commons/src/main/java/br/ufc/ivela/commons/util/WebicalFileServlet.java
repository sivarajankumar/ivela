/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marcus
 */
public class WebicalFileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String description = request.getParameter("description");
        String dtStart = request.getParameter("dtStart");
        String dtEnd = request.getParameter("dtEnd");
        String dtNow = request.getParameter("dtNow");
        String where = request.getParameter("where");
        String what = request.getParameter("what");
        String uid = request.getParameter("uid");
        if (user != null && !user.equals("")) {
            try {
                response.setContentType("text/plain");

                String arquivo = "BEGIN:VCALENDAR\n" +
                        "PRODID:-//Webical.org/NONSGML Webical Calendar V1.1//EN\n" +
                        "VERSION:2.0\n" +
                        "END:VCALENDAR";

                String path = request.getRealPath(request.getServletPath());
                //System.out.println("path: " + path);

                path = path.substring(0, path.lastIndexOf("/"));
                //System.out.println("path: " + path);

                java.io.File f = new java.io.File(path + "/calendars/" + user + ".ics");

                if (!f.exists()) {
                    //System.out.println(f.createNewFile());
                    f.createNewFile();
                    PrintWriter pw = new PrintWriter(f);
                    pw.println(arquivo);
                    pw.flush();
                    pw.close();
                }
                
                if (dtStart != null && dtEnd != null && where != null && what != null) {
                    String event = addEvent(description, dtStart, dtEnd, where, what, uid, dtNow);
                    Vector<String> lines = new Vector<String>();
                    BufferedReader br = new BufferedReader(new FileReader(f));
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        if (line != null && line.trim().length() > 0)
                            lines.add(line);
                    }
                    if (lines.size() > 0)
                        lines.insertElementAt(event, lines.size() - 1);
                    PrintWriter pw = new PrintWriter(f);
                    for (int i = 0; lines != null && i < lines.size(); i++) {
                        String s = lines.get(i);
                        pw.println(s);
                    }
                    pw.flush();
                    pw.close();                    
                    
                }

                PrintWriter pw = response.getWriter();
                pw.print("success");
                pw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                PrintWriter pw = response.getWriter();
                pw.print("error");
                pw.close();
            }
        }

    }

    private String addEvent(String description, String dtStart, String dtEnd, String where, String what, String uid, String dtNow) {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String event = "";
        event += "BEGIN:VEVENT" + "\n";
        event += "DTSTAMP:" + dtNow.substring(0, 8) + "T125851Z" + "\n";
        event += "DESCRIPTION:" + description + "\n";
        event += "DTSTART;VALUE=DATE-TIME:" + dtStart.substring(0, 8) + "T" + dtStart.substring(8) + "\n";
        event += "DTEND;VALUE=DATE-TIME:" + dtEnd.substring(0, 8) + "T" + dtEnd.substring(8) + "\n";
        event += "LOCATION:" + where + "\n";
        event += "SUMMARY:" + what + "\n";
        event += "UID:" + uid + "\n";
        event += "END:VEVENT" + "\n";
        event += "BEGIN:VTIMEZONE" + "\n";
        event += "TZID:/webical.org/" + "\n";
        event += "BEGIN:STANDARD" + "\n";
        event += "DTSTART:19691231T210000" + "\n";
        event += "TZOFFSETFROM:-0100" + "\n";
        event += "TZOFFSETTO:-0100" + "\n";
        event += "END:STANDARD" + "\n";
        event += "BEGIN:DAYLIGHT" + "\n";
        event += "DTSTART:19691231T210000" + "\n";
        event += "TZOFFSETTO:-0100" + "\n";
        event += "TZOFFSETFROM:-0100" + "\n";
        event += "END:DAYLIGHT" + "\n";
        event += "END:VTIMEZONE" + "";
        return event;
    }

}
