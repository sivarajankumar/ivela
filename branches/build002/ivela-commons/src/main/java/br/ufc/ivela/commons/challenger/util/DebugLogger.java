package br.ufc.ivela.commons.challenger.util;

import br.ufc.ivela.commons.challenger.config.Constants;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/** 
 * Used to implement log at debug time or at exception time. Based upon java.util.logging
 * @author Jose Damico (jdamico@br.ibm.com)
 * 2008 Sep 07 
 */

public class DebugLogger {
	
	private static final Logger logger = Logger.getLogger("DebugLogger");
	
	private static DebugLogger INSTANCE = new DebugLogger();
	public static DebugLogger getInstance() {
		return INSTANCE;
	}
	
	public void logAtDebugTime(String className, String logLine){
		log(className, logLine, false);
	}
	
	public void logAtExceptionTime(String className, String logLine) {
		log(className, logLine, true);
	}
	
	/** 
	 * Check if log direction is SystemOut or a separate file
	 * Check if a log file exist
	 * If there is no file create, on the contrary, use the file
	 * @throws IOException 
	 *
	 */
	private void log(String className, String logLine, boolean logLevel) {
		String formatedLog = " ("+className+") "+logLine;
		if(Constants.IS_SEPARATE_LOG){
			Date date = new Date();
			Format formatter = new SimpleDateFormat("yyyyMMMdd_HH:mm:ss");
			String stime = formatter.format(date);
			if(logLevel){
				formatedLog = stime+Constants.SEVERE_LOGLEVEL+formatedLog+"\n";
			}else{
				formatedLog = stime+Constants.NORMAL_LOGLEVEL+formatedLog+"\n";
			}
			try{
				File listenerLog = new File(Constants.SEPARATE_LOG_FILE);
				if(!listenerLog.exists()){
					listenerLog.createNewFile();
				}
				
				FileWriter fw = new FileWriter(Constants.SEPARATE_LOG_FILE, true);
				BufferedWriter bwr = new BufferedWriter(fw);
				
				bwr.write(formatedLog);
				bwr.close();
			}catch(IOException ioe){
				logger.log(Level.SEVERE, ioe.getMessage());
			}
			
			
		}else{
			if(logLevel){
				logger.log(Level.SEVERE, formatedLog);
			}else{
				logger.info(formatedLog);
			}
		}
	}
	
}

