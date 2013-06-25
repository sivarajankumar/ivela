/**
 * @(#)CustomMessageFormatter.java
 *
 * Eldorado Confidential and Proprietary Information
 * Copyright (C)2010 Eldorado, All Rights Reserved
 *
 * Hist�rico de Modifica��es
 *
 * Data           Quem              Descri��o
 * ------------------------------------------------------------------------------------------------
 * 10 May 2010    Rafael Lagoa      (4115)Formatador de mesagem de log.  
 */
package org.ivela.offline.commons;

import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomMessageFormatter extends Formatter {

	private MessageFormat messageFormat = new MessageFormat("{0}\t{1}\t{2,date,dd:MM:yy:h:mm:ss.SSS}:\t\t{3}\n");

	@Override
	public String format(LogRecord record) {
		Object[] arguments = new Object[4];
		arguments[0] = record.getLevel();
		arguments[1] = Thread.currentThread().getName();
		arguments[2] = new Date(record.getMillis());
		arguments[3] = record.getMessage();
		return messageFormat.format(arguments);
	}

	public CustomMessageFormatter(MessageFormat mf) {
		super();
		messageFormat = mf;
	}
}