package org.ivela.offline.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import org.ivela.offline.commons.Constants;

public class LoggerManager {
	private static final Logger logger = Logger.getLogger("LoggerManager");

	private static LoggerManager INSTANCE;
	public static LoggerManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LoggerManager();
		}
		return INSTANCE;
	}
	private LoggerManager(){}

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


		String fileName = Constants.LOG_NAME;

		fileName = Constants.USER_DATA_FOLDER + fileName;

		

		String stime = Utils.getInstance().getCurrentDateTimeFormated("yyyyMMMdd_HH:mm:ss");
		if(logLevel){
			formatedLog = stime+Constants.SEVERE_LOGLEVEL+formatedLog+"\n";
		}else{
			formatedLog = stime+Constants.NORMAL_LOGLEVEL+formatedLog+"\n";
		}

		int limit = Constants.FIXED_LOGLIMIT;



		File listenerLog = null;
		FileWriter fw = null;
		BufferedWriter bwr = null;
		try{

			listenerLog = new File(fileName);
			if(!listenerLog.exists()){
				listenerLog.createNewFile();
			} else if(listenerLog.length() > limit){
				/* 
				 * check if file is too big
				 */
				 listenerLog.delete();
				listenerLog.createNewFile();

			}

			fw = new FileWriter(fileName, true);
			bwr = new BufferedWriter(fw);
			bwr.write(formatedLog);
			bwr.close();
			fw.close();
		}catch(IOException ioe){
			System.err.println("write log error");
		}finally{
			try {
				if(bwr!=null) bwr.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				if(fw!=null) fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			listenerLog = null;
			fw = null;
			bwr = null;
		}




	}


}
