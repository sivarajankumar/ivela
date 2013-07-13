package org.ivela.offline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbCommonTasks {
	protected void close(ResultSet rs, PreparedStatement ps, Connection con) throws Exception {
		if(rs!=null) try { rs.close(); } catch (SQLException e) { throw new Exception(e); }
		if(ps!=null) try { ps.close(); } catch (SQLException e) { throw new Exception(e); }
		if(con!=null) try { con.close(); } catch (SQLException e) { throw new Exception(e); }
	}
}
