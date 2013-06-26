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

/**
 * This class will be responsible for the Database Creation
 * 
 * @author pdamico
 * 
 */
public class CheckDB {
	/* the default framework is embedded */
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String protocol = "jdbc:derby:";

	public static void main(String args[]) {
		if (checkDB()){
			System.out.println("BANCO ESTA PRONTO PARA O USO.");
		} else {
			System.out.println("BANCO NAO ESTÁ OPERACIONAL.");
		}

	}
	
	public static boolean checkDB(){
		boolean retorno = false;
		loadDriver();
		Connection conn = null;

		ArrayList statements = new ArrayList(); // list of Statements,
												// PreparedStatements
		Statement s = null;
		ResultSet rs = null;
		try {
			String dbName = "IVELA"; // the name of the database
			conn = DriverManager.getConnection(protocol + dbName + ";create=true");
			System.out.println("Connected to and created database " + dbName);
            
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
				if (((se.getErrorCode() == 50000) && ("XJ015".equals(se
						.getSQLState())))) {
					System.out.println("Derby shut down normally");
				} else {
					System.err.println("Derby did not shut down normally");
					printSQLException(se);
				}
			}

		} catch (SQLException sqle) {
			printSQLException(sqle);
			if (sqle.getMessage().equalsIgnoreCase(
					"Table/View 'IVELA.COURSE' does not exist.")) {
				System.out.println("Ill create the database.");
				if (createDB(conn)){
					System.out.println("Banco Criado");
					System.out.println("Populando Banco");
					if (populateDB(conn)){
						System.out.println("Banco nao pode ser populado");
					} else {
						System.out.println("Banco populado");
						retorno = true;
					}
				} else {
					System.out.println("Banco não pode ser criado");
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
	
	private static boolean createDB(Connection conn){
		FileInputStream fileStream = null;
		try {
			fileStream = new FileInputStream("C:\\Program Files\\English4Smart\\database\\create.sql");
			int result = ij.runScript(conn, fileStream, "UTF-8",System.out, "UTF-8");
			System.out.println("Result code is: " + result);
			if (result == 1){
				return true;
			} else {
				return false;
			}
		} catch (FileNotFoundException e) {
			return false;
		} catch (UnsupportedEncodingException e) {
			return false;
		} finally {
			if (fileStream != null) {
				try {
					fileStream.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	private static boolean populateDB(Connection conn){
		FileInputStream fileStream = null;
		try {
			fileStream = new FileInputStream("C:\\Program Files\\English4Smart\\database\\populate.sql");
			int result = ij.runScript(conn, fileStream, "UTF-8",System.out, "UTF-8");
			System.out.println("Result code is: " + result);
			if (result == 1){
				return true;
			} else {
				return false;
			}
		} catch (FileNotFoundException e) {
			return false;
		} catch (UnsupportedEncodingException e) {
			return false;
		} finally {
			if (fileStream != null) {
				try {
					fileStream.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private static void loadDriver() {
		try {
			Class.forName(driver).newInstance();
			System.out.println("Loaded the appropriate driver");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("\nUnable to load the JDBC driver " + driver);
			System.err.println("Please check your CLASSPATH.");
			cnfe.printStackTrace(System.err);
		} catch (InstantiationException ie) {
			System.err.println("\nUnable to instantiate the JDBC driver "
					+ driver);
			ie.printStackTrace(System.err);
		} catch (IllegalAccessException iae) {
			System.err.println("\nNot allowed to access the JDBC driver "
					+ driver);
			iae.printStackTrace(System.err);
		}
	}

	private static void reportFailure(String message) {
		System.err.println('\t' + message);
	}

	public static void printSQLException(SQLException e) {
		// Unwraps the entire exception chain to unveil the real cause of the
		// Exception.
		while (e != null) {
			System.err.println("\n----- SQLException -----");
			System.err.println("  SQL State:  " + e.getSQLState());
			System.err.println("  Error Code: " + e.getErrorCode());
			System.err.println("  Message:    " + e.getMessage());
			// for stack traces, refer to derby.log or uncomment this:
			// e.printStackTrace(System.err);
			e = e.getNextException();
		}
	}

}
