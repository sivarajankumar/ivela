package org.ivela.offline.dao;

import java.sql.SQLException;
import java.util.List;

import org.ivela.offline.domain.SystemUser;
import org.ivela.offline.domain.SystemUserExample;

public class SystemUserDAOImpl extends DbCommonTasks implements SystemUserDAO {

	@Override
	public int countByExample(SystemUserExample example) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByExample(SystemUserExample example) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insert(SystemUser record) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertSelective(SystemUser record) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List selectByExample(SystemUserExample example) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemUser selectByPrimaryKey(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByExampleSelective(SystemUser record,
			SystemUserExample example) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByExample(SystemUser record, SystemUserExample example)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(SystemUser record)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SystemUser record) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
