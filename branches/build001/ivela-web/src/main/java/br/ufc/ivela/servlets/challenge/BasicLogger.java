/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.servlets.challenge;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jdamico
 */
public class BasicLogger{
    private boolean verbose;
	private Date date;
	private Format dateFormatter;
	private String logPath;
	private String className;

	public BasicLogger(boolean verbose, String className){
		this.className = className;
		setVerbose(verbose);		
		date = new Date();
		dateFormatter = new SimpleDateFormat("ddMMMyyyy");
	    logPath = "dCSCLii"+dateFormatter.format(date)+".log";
	}
	
	public void setOutput(String logline){
		date = new Date();
		dateFormatter = new SimpleDateFormat("ddMMMyyyy - HH:mm:ss ");
		logline = dateFormatter.format(date)+"["+className+"] "+logline;
		if(isVerbose()){ 
			System.out.println(logline);
		}else{
			
			 try {
			        BufferedWriter out = new BufferedWriter(new FileWriter(logPath, true));        
			        out.write(logline);
			        out.close();
			    } catch (IOException e) {
			    	System.err.println(e);
			    }
			
		}
		
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
	
}
