package org.ivela.offline.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ivela.offline.commons.Constants;
import org.ivela.offline.utils.LoggerManager;


public class DbCommonTasks {
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String protocol = "jdbc:derby:";

	/**
	 * Conecta-se no banco
	 * @return objeto conexao
	 */
	protected Connection getConnection(){
		Connection conn = null;
		try {
			loadDriver();
			String dbName = Constants.USER_DATA_FOLDER+"IVELA_DB"; // the name of the database
			conn = DriverManager.getConnection(protocol + dbName + ";create=true");
		} catch (SQLException e) {
			LoggerManager.getInstance().logAtDebugTime("DbCommonTasks.class", "Problem when connecting: " + e.getMessage());
		}
		return conn;
	}
	
	protected static void loadDriver() {
		try {
			Class.forName(driver).newInstance();
			LoggerManager.getInstance().logAtDebugTime("CheckDB.class", "Loaded the appropriate driver");
		} catch (ClassNotFoundException cnfe) {
			LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", "\nUnable to load the JDBC driver " + driver);
			LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", "Please check your CLASSPATH.");
			cnfe.printStackTrace(System.err);
		} catch (InstantiationException ie) {
			LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", "\nUnable to instantiate the JDBC driver "
					+ driver);
			ie.printStackTrace(System.err);
		} catch (IllegalAccessException iae) {
			LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", "\nNot allowed to access the JDBC driver "
					+ driver);
			iae.printStackTrace(System.err);
		}
	}

	
}
