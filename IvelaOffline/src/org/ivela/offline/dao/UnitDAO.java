package org.ivela.offline.dao;
import java.sql.SQLException;
import java.util.List;

import org.ivela.offline.domain.Unit;
import org.ivela.offline.domain.UnitExample;

public interface UnitDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table unit
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    int countByExample(UnitExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table unit
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    int deleteByExample(UnitExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table unit
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    int deleteByPrimaryKey(Long id) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table unit
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    void insert(Unit record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table unit
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    void insertSelective(Unit record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table unit
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    List selectByExample(UnitExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table unit
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    Unit selectByPrimaryKey(Long id) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table unit
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    int updateByExampleSelective(Unit record, UnitExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table unit
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    int updateByExample(Unit record, UnitExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table unit
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    int updateByPrimaryKeySelective(Unit record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table unit
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    int updateByPrimaryKey(Unit record) throws SQLException;
}