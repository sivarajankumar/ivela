package org.ivela.offline.utils;


public class BrowserStart implements Runnable {

	public void run() {

		try {
			String[] cmd = new String[] { Utils.getInstance().getRealExecPath("/Internet Explorer/iexplore.exe") , "http://localhost:8080/content/1" };
			Utils.getInstance().execute(cmd);
		} catch (Exception e) {
			LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), "run() "+e.getMessage());
		}

	}

}
