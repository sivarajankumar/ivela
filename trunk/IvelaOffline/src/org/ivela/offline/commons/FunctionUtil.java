package org.ivela.offline.commons;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ivela.offline.dao.DisciplineDAO;
import org.ivela.offline.dao.DisciplineDAOImpl;
import org.ivela.offline.dao.FinishedUnitContentDAO;
import org.ivela.offline.dao.FinishedUnitContentDAOImpl;
import org.ivela.offline.dao.SystemUserDAO;
import org.ivela.offline.dao.SystemUserDAOImpl;
import org.ivela.offline.dao.UnitContentDAO;
import org.ivela.offline.dao.UnitContentDAOImpl;
import org.ivela.offline.dao.UnitDAO;
import org.ivela.offline.dao.UnitDAOImpl;
import org.ivela.offline.domain.Discipline;
import org.ivela.offline.domain.DisciplineExample;
import org.ivela.offline.domain.FinishedUnitContent;
import org.ivela.offline.domain.FinishedUnitContentExample;
import org.ivela.offline.domain.SystemUser;
import org.ivela.offline.domain.SystemUserExample;
import org.ivela.offline.domain.Unit;
import org.ivela.offline.domain.UnitContent;
import org.ivela.offline.domain.UnitContentExample;
import org.ivela.offline.domain.UnitExample;

public class FunctionUtil {

	private static DisciplineDAO disciplineDao = new DisciplineDAOImpl();
    private static UnitDAO unitDao = new UnitDAOImpl();
    private static UnitContentDAO unitContentDao = new UnitContentDAOImpl();
    private static FinishedUnitContentDAO finishedUnitContentDao = new FinishedUnitContentDAOImpl();
    private static SystemUserDAO systemUserDao = new SystemUserDAOImpl();

	public static Long[] getLocation(String currentLocation) {
        Long[] location = new Long[4];

        currentLocation = "/" + currentLocation.replace("%20", " ").split(Constants.INSTALL_PATH)[1];
        location[Constants.ID_COURSE] = new Long(currentLocation.substring(1, currentLocation.indexOf("/", 1)));
        try {
            currentLocation = currentLocation.substring(currentLocation.indexOf("/"+location[Constants.ID_COURSE])+("/"+location[Constants.ID_COURSE]).length());
            location[Constants.ID_DISCIPLINE] = new Long(currentLocation.substring(1, currentLocation.indexOf("/", 1)));
            currentLocation = currentLocation.substring(currentLocation.indexOf("/"+location[Constants.ID_DISCIPLINE])+("/"+location[Constants.ID_DISCIPLINE]).length());
            location[Constants.ID_UNIT] = new Long(currentLocation.substring(1, currentLocation.indexOf("/", 1)));
            currentLocation = currentLocation.substring(currentLocation.indexOf("/"+location[Constants.ID_UNIT])+("/"+location[Constants.ID_UNIT]).length());
            location[Constants.ID_LESSON] = new Long(currentLocation.substring(1, currentLocation.indexOf("/", 1)));
        } catch(Exception e) {
            // do nothing
        }

        return location;
    }

    public static int isDisciplineFinished(String disciplineTag) throws SQLException {
        int result = 0;
        DisciplineExample disciplineExample = new DisciplineExample();
        disciplineExample.createCriteria().andTagEqualTo(disciplineTag);
        List<Discipline> discIds = disciplineDao.selectByExample(disciplineExample);
        if (discIds.isEmpty()) {
            return -1;
        }

        UnitExample unitExample = new UnitExample();
        unitExample.createCriteria().andDisciplineEqualTo(discIds.get(0).getId());
        List<Unit> unitIds = unitDao.selectByExample(unitExample);
        for(Unit unit: unitIds){
            result += isUnitFinished(unit.getId());               
        }
        if (result < 0) {
            result = -result;
            result = result == unitIds.size()? -1 : result;
        }

        return result;
    }

    public static int isUnitFinished(Long unitId) throws SQLException {
        int result = 0;

        UnitContentExample unitContentExample = new UnitContentExample();
        unitContentExample.createCriteria().andUnitEqualTo(unitId);
        List<UnitContent> unitContents = unitContentDao.selectByExample(unitContentExample);
        List<FinishedUnitContent> finishedUnitContents = getByCourseAndSystemUser();               

        outerLoop:
        for(UnitContent unitContent: unitContents) {
            for (FinishedUnitContent finishedUnit: finishedUnitContents) {
                if (finishedUnit.getUnitContent().equals(unitContent.getId())) {
                    continue outerLoop;
                }
            }
            result += 1;
        }

        result = result == unitContents.size()? -1 : result;
        return result;
    }

    public static List<FinishedUnitContent> getByCourseAndSystemUser() throws SQLException {
        FinishedUnitContentExample finishedUnitContentExample = new FinishedUnitContentExample();
        List<FinishedUnitContent> finishedUnitContents = finishedUnitContentDao.selectByExample(finishedUnitContentExample);
        if (finishedUnitContents == null)
            finishedUnitContents = new ArrayList<FinishedUnitContent>();
        return finishedUnitContents;
    }

    public static SystemUser getSystemUser() throws SQLException {
        SystemUserExample systemUserExample = new SystemUserExample();
        List<SystemUser> systemUsers = null;
        systemUsers = systemUserDao.selectByExample(systemUserExample);
        return systemUsers.get(0);
    }

    public static void updateSystemUser(String email, String username, String syncCode) throws SQLException {
        SystemUserExample systemUserExample = new SystemUserExample();
        List<SystemUser> systemUsers = null;
        systemUsers = systemUserDao.selectByExample(systemUserExample);
        SystemUser systemUser = systemUsers.get(0);
        systemUser.setEnabled(true);
        systemUser.setEmail(email);
        systemUser.setUsername(username);
        // ATTENTION: Using social number to save sync code
        systemUser.setSocialNumber(syncCode);
        systemUserDao.updateByPrimaryKey(systemUser);
    }

    public static void updateSystemUser(Long authentication) throws SQLException {
        SystemUserExample systemUserExample = new SystemUserExample();
        List<SystemUser> systemUsers = null;
        systemUsers = systemUserDao.selectByExample(systemUserExample);
        SystemUser systemUser = systemUsers.get(0);
        systemUser.setAuthentication(authentication);
        systemUserDao.updateByPrimaryKey(systemUser);
    }

    public static boolean isSystemUserEnabled() throws SQLException {
        SystemUserExample systemUserExample = new SystemUserExample();
        List<SystemUser> systemUsers = null;
        systemUsers = systemUserDao.selectByExample(systemUserExample);
        return systemUsers.get(0).getEnabled();
    }
}