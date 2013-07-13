package org.ivela.offline.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.ivela.offline.domain.SystemUser;
import org.ivela.offline.domain.SystemUserExample;

public class SystemUserDAOImpl extends DbCommonTasks implements SystemUserDAO {

	@Override
	public int countByExample(SystemUserExample example) throws SQLException {
		return 0;
	}

	@Override
	public int deleteByExample(SystemUserExample example) throws SQLException {
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Long id) throws SQLException {
		return 0;
	}

	@Override
	public void insert(SystemUser record) throws SQLException {

	}

	@Override
	public void insertSelective(SystemUser record) throws SQLException {
	}

	@Override
	public List selectByExample(SystemUserExample example) throws SQLException {
		return null;
	}

	@Override
	public SystemUser selectByPrimaryKey(Long id) throws SQLException {
		return null;
	}

	@Override
	public int updateByExampleSelective(SystemUser record,
			SystemUserExample example) throws SQLException {
		return 0;
	}

	@Override
	public int updateByExample(SystemUser record, SystemUserExample example)
			throws SQLException {
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(SystemUser record)
			throws SQLException {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SystemUser record) throws SQLException {
		return 0;
	}

	@Override
	public String getUserName() throws SQLException {
		String user = "";
		Statement s = null;
		ResultSet rs = null;

		s = getConnection().createStatement();
		rs = s.executeQuery("SELECT * FROM IVELA.\"SYSTEM_USER\"");
		if (rs.next()) {
			user = rs.getString("username");
		}

		return user;
	}

}
