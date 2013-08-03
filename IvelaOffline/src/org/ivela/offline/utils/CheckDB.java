package org.ivela.offline.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.derby.tools.ij;
import org.ivela.offline.commons.Constants;

/**
 * This class will be responsible for the Database Creation
 * 
 * @author julianom
 * @author damico
 * 
 */
public class CheckDB {
	/* the default framework is embedded */
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String protocol = "jdbc:derby:";
	
	public static boolean checkDB(){
		boolean retorno = false;
		loadDriver();
		Connection conn = null;

		ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements,
												// PreparedStatements
		Statement s = null;
		ResultSet rs = null;
		try {
			String dbName = Constants.USER_DATA_FOLDER+"ivela"; // the name of the database
			conn = DriverManager.getConnection(protocol + dbName + ";create=true");
			
			LoggerManager.getInstance().logAtDebugTime("CheckDB.class", "Connected to and created database " + dbName);
            
			s = conn.createStatement();
			rs = s.executeQuery("SELECT * FROM ivela.course");
			if (!rs.next()) {
				reportFailure("No rows in ResultSet");
			} else {
				reportFailure("Have data Can continue with my work");
				retorno = true;
			}

			try {
				DriverManager.getConnection("jdbc:derby:;shutdown=true");
			} catch (SQLException se) {
				if (((se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState())))) {
					LoggerManager.getInstance().logAtDebugTime("CheckDB.class", "Derby shut down normally");
				} else {
					LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", "Derby did not shut down normally");
					printSQLException(se);
				}
			}

		} catch (SQLException sqle) {
			printSQLException(sqle);
			if (sqle.getMessage().equalsIgnoreCase("Table/View 'IVELA.COURSE' does not exist.") || sqle.getErrorCode() == 30000) {
				LoggerManager.getInstance().logAtDebugTime("CheckDB.class","Ill create the database.");
				if (createStructure(conn)){
					LoggerManager.getInstance().logAtDebugTime("CheckDB.class","Estrutura de Banco Criada");
					LoggerManager.getInstance().logAtDebugTime("CheckDB.class","Populando Banco");
					if (populateDB(conn)){
						LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", "Banco nao pode ser populado");
					} else {
						LoggerManager.getInstance().logAtDebugTime("CheckDB.class", "Banco populado");
						retorno = true;
					}
				} else {
					LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", "Estrutura de Banco não pode ser criada.");
					
				}

			}
		} finally {
			// release all open resources to avoid unnecessary memory usage

			// ResultSet
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);

			}

			// Statements and PreparedStatements
			int i = 0;
			while (!statements.isEmpty()) {
				// PreparedStatement extend Statement
				Statement st = (Statement) statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			// Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
		return retorno;
	}
	
	private static boolean createStructure(Connection conn){
		FileInputStream fileStream = null;
		try {
			// TODO  No instalador tem que criar a pasta scripts e colocar as sql
			fileStream = new FileInputStream(Constants.USER_DATA_FOLDER+"scripts/create.sql");
			int result = ij.runScript(conn, fileStream, "UTF-8",System.out, "UTF-8");
			LoggerManager.getInstance().logAtDebugTime("CheckDB.class", "createStructure() Result code is: " + result);
			if (result == 0){
				return true;
			} else {
				return false;
			}
		} catch (FileNotFoundException e) {
			LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", e.getMessage());
			return false;
		} catch (UnsupportedEncodingException e) {
			LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", e.getMessage());
			return false;
		} finally {
			if (fileStream != null) {
				try {
					fileStream.close();
				} catch (IOException e) {
					LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", e.getMessage());
				}
			}
		}
	}
	
	private static boolean populateDB(Connection conn){
		FileInputStream fileStream = null;
		try {
			fileStream = new FileInputStream(Constants.USER_DATA_FOLDER+"scripts/populate.sql");
			int result = ij.runScript(conn, fileStream, "UTF-8",System.out, "UTF-8");
			LoggerManager.getInstance().logAtDebugTime("CheckDB.class", "Result code is: " + result);
			if (result == 1){
				return true;
			} else {
				return false;
			}
		} catch (FileNotFoundException e) {
			LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", e.getMessage());
			return false;
		} catch (UnsupportedEncodingException e) {
			LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", e.getMessage());
			return false;
		} finally {
			if (fileStream != null) {
				try {
					fileStream.close();
				} catch (IOException e) {
					LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", e.getMessage());
				}
			}
		}
	}

	private static void loadDriver() {
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

	private static void reportFailure(String message) {
		LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", '\t' + message);
	}

	public static void printSQLException(SQLException e) {
		// Unwraps the entire exception chain to unveil the real cause of the
		// Exception.
		while (e != null) {
			LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", "  SQL State:  " + e.getSQLState());
			LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", "  Error Code: " + e.getErrorCode());
			LoggerManager.getInstance().logAtExceptionTime("CheckDB.class", "  Message:    " + e.getMessage());
			// for stack traces, refer to derby.log or uncomment this:
			// e.printStackTrace(System.err);
			e = e.getNextException();
		}
	}

}
