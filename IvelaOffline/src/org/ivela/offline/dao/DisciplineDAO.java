package org.ivela.offline.dao;

import java.sql.SQLException;
import java.util.List;

import org.ivela.offline.domain.Discipline;
import org.ivela.offline.domain.DisciplineExample;

public interface DisciplineDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table discipline
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    int countByExample(DisciplineExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table discipline
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    int deleteByExample(DisciplineExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table discipline
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    int deleteByPrimaryKey(Long id) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table discipline
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    void insert(Discipline record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table discipline
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    void insertSelective(Discipline record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table discipline
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    List selectByExample(DisciplineExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table discipline
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    Discipline selectByPrimaryKey(Long id) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table discipline
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    int updateByExampleSelective(Discipline record, DisciplineExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table discipline
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    int updateByExample(Discipline record, DisciplineExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table discipline
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    int updateByPrimaryKeySelective(Discipline record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table discipline
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    int updateByPrimaryKey(Discipline record) throws SQLException;
}