/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.servlets;

import br.ufc.ivela.commons.model.ChallengeItems;
import br.ufc.ivela.commons.model.SystemUser;
import br.ufc.ivela.ejb.interfaces.ChallengeItemsRemote;
import br.ufc.ivela.ejb.interfaces.ChallengeRemote;
import br.ufc.ivela.servlets.challenge.XMLObject;
import br.ufc.ivela.servlets.challenge.Constants;
import br.ufc.ivela.servlets.challenge.Converter2ObjectFactory;
import br.ufc.ivela.servlets.challenge.Field;
import br.ufc.ivela.servlets.challenge.IvelaObj;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.context.SecurityContextHolder;

/**
 *
 * @author jdamico
 * use this url to test: http://localhost:17026/ivela-web/RenderServlet?file=%2Fimg%2Fjose.gif
 */
public class ChallengeSolver extends HttpServlet {

    private List<Field> getRightAnswers(String challid) {

        ChallengeItemsRemote challItemsRemote = this.getChallengeItemsRemote();
        ChallengeItems challItems = challItemsRemote.get(challid);
        XMLObject xo = new XMLObject();
        xo.setIntType(Constants.CHALLENGE_TYPE);
        xo.setEntireXml(challItems.getXml());
        IvelaObj ivelaObj = Converter2ObjectFactory.getConverter(xo).getConverterType();
        return ivelaObj.getIvelaPayload().getChallenge().getField();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double result = 0.0;
        int points = 0;
        String debug = "clear";
        String reqParam = "clear";
        String dbAnswer = "clear";
        String status = "err";
        response.setContentType("application/json");
        ChallengeRemote challRemote = this.getChallengeRemote();
        String challid = request.getParameter("challid");

        Long uid = 0L;
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj != null && obj instanceof SystemUser) {
            uid = ((SystemUser) obj).getId();
        }
        debug = debug + "(callid =>"+challid+") (uid => "+uid+") ";
        try {
            if (!challid.equals("audio")) {

                List<Field> answers = getRightAnswers(challid);
                String option = challid;
                for (int i = 0; i < answers.size(); i++) {
                    option = challid + answers.get(i).getName();
                    reqParam = request.getParameter(option);
                    //System.err.println(option);
                    reqParam = reqParam.toLowerCase();
                    dbAnswer = answers.get(i).getValue().toLowerCase();
                    debug = debug + "(option => "+option+") ";
                    debug = debug + "(dbAnswer => "+dbAnswer+") ";
                    debug = debug + "(reqParam => "+reqParam+") ";

                    if (dbAnswer.contains(reqParam) && !reqParam.equals("")) {

                        points++;
                    }

                }
                result = (points * 100) / answers.size();
                status = "err";
                if (result >= 70) {
                    status = "ok";
                }
                challRemote.add(challid, uid, result);
                response.getWriter().println("{\"list\":{\"results\":[{\"ret\":\"" + result + "\"}]},\"name\":\"" + challid + "\", \"status\": \"" + status + "\", \"debug\": \"" + debug + "\"}");

            } else if (challid.equals("audio")) {
                challRemote.add(request.getParameter("value"), uid, 100);
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, request.getParameter("value").trim());

            }
        } catch (NullPointerException npe) {
            challRemote.add(challid, uid, result);
            response.getWriter().println("{\"list\":{\"results\":[{\"ret\":\"" + result + "\"}]},\"name\":\"" + challid + "\", \"status\": \"" + status + "\", \"debug\": \"" + debug + "\"}");
            npe.printStackTrace();
        } catch (Exception e) {
            challRemote.add(challid, uid, result);
            response.getWriter().println("{\"list\":{\"results\":[{\"ret\":\"" + result + "\"}]},\"name\":\"" + challid + "\", \"status\": \"" + status + "\", \"debug\": \"" + debug + "\"}");
            e.printStackTrace();

        }
    }

    private String stripPontuation(String source) {
        source = source.replaceAll("!", "");
        source = source.replaceAll("\\?", "");
        source = source.replaceAll(":", "");
        source = source.replaceAll(".", "");
        source = source.replaceAll(",", "");
        source = source.replaceAll("  ", " ");
        return source;
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

    private ChallengeItemsRemote getChallengeItemsRemote() {
        ChallengeItemsRemote challengeItemsRemote = null;

        try {
            InitialContext initialContext = new InitialContext();
            java.lang.Object ejbRemoteRef = initialContext.lookup("ChallengeItemsBean#br.ufc.ivela.ejb.interfaces.ChallengeItemsRemote");
            challengeItemsRemote = (ChallengeItemsRemote) javax.rmi.PortableRemoteObject.narrow(ejbRemoteRef, ChallengeItemsRemote.class);
        } catch (NamingException e) {
            e.printStackTrace();
            challengeItemsRemote = null;
        }

        return challengeItemsRemote;
    }
}
