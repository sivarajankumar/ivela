/**
 * @(#)ConnectionUtil.java
 *
 * Eldorado Confidential and Proprietary Information
 * Copyright (C)2010 Eldorado, All Rights Reserved
 *
 * Hist�rico de Modifica��es
 *
 * Data           Quem              Descri��o
 * ------------------------------------------------------------------------------------------------
 * 10 Fev 2010    Rafael Lagoa      (1984)Classe utilit�ria de inicializa��o e uso do iBatis.  
 */
package org.ivela.offline.commons;

import java.io.Reader;
import java.sql.SQLException;
/*
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.sqlmap.client.SqlMapSession;
*/
public class ConnectionUtil {
///*
//    private static LogWrapper logger = new LogWrapper(ConnectionUtil.class);
//    public static ConnectionUtil instance;
//
//    private static final ThreadLocal<SqlMapSession> threadLocal = new ThreadLocal<SqlMapSession>();
//    private static final String resource = "br/org/eldorado/offline/dao/ibatis/sqlmap/SqlMapConfig.xml";
//    private static SqlMapClient sqlMap;
//
//    static {
//        try {
//			Reader reader = Resources.getResourceAsReader(resource);
//			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
//			reader.close();
//        } catch (Exception e) {
//        	logger.error(e, "Error reading file SqlMapConfig.xml");
//        }
//    }
//
//    /**
//     * Singleton
//     */
//    private ConnectionUtil() {
//    }
//
//    public synchronized static ConnectionUtil getInstance() {
//        if (ConnectionUtil.instance == null) {
//        	ConnectionUtil.instance = new ConnectionUtil();
//        }
//        return ConnectionUtil.instance;
//    }
//
//    /**
//     * Returns the ThreadLocal SqlMapClient instance. Lazy initialize the
//     * <code>SqlMapSession</code> if needed.
//     */
//    public SqlMapSession getSession() {
//    	SqlMapSession session = (SqlMapSession) threadLocal.get();
//
//        if (session == null) {
//            if (sqlMap == null) {
//                rebuildSessionFactory();
//            }
//            session = (sqlMap != null) ? sqlMap.openSession() : null;
//            threadLocal.set(session);
//        }
//        return session;
//    }
//
//    /**
//     * Rebuild SqlMapClient sqlMapClient
//     */
//    public static void rebuildSessionFactory() {
//        try {
//			Reader reader = Resources.getResourceAsReader(resource);
//			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
//			reader.close();
//        } catch (Exception e) {
//            logger.error(e, "Error creating Session Factory");
//        }
//    }
//
//    /**
//     * Close the single ibatis session instance.
//     */
//    public static void closeSession() {
////    	SqlMapClient session = (SqlMapClient) threadLocal.get();
//        threadLocal.set(null);
//
////        if (session != null) {
////            session.close();
////        }
//    }
//
//    public static SqlMapClient getSqlMapClient() {
//        return sqlMap;
//    }
//
//    public void beginTransaction() throws SQLException {
//        getSession().startTransaction();
//    }
//
//    public void commit() throws SQLException {
//        getSession().commitTransaction();
//        closeSession();
//    }
//
//    public void roolback() throws SQLException {
//        getSession().endTransaction();
//        closeSession();
//    }
//    */
}