package org.ivela.offline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.infoserver.jotpcenter.util.exceptions.JOtpCenterException;

public class DbCommonTasks {
	protected void close(ResultSet rs, PreparedStatement ps, Connection con) throws JOtpCenterException {
		if(rs!=null) try { rs.close(); } catch (SQLException e) { throw new JOtpCenterException(e); }
		if(ps!=null) try { ps.close(); } catch (SQLException e) { throw new JOtpCenterException(e); }
		if(con!=null) try { con.close(); } catch (SQLException e) { throw new JOtpCenterException(e); }
	}
}
