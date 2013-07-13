package org.ivela.offline.functions;

import java.sql.SQLException;
import java.util.List;

import org.ivela.offline.commons.FunctionUtil;
import org.ivela.offline.dao.DisciplineDAO;
import org.ivela.offline.dao.DisciplineDAOImpl;
import org.ivela.offline.domain.Discipline;
import org.ivela.offline.domain.DisciplineExample;
import org.ivela.offline.utils.LoggerManager;

public class LabelDependantDisciplineStatus {
	
	private static DisciplineDAO disciplineDao = new DisciplineDAOImpl();
	
	public Object function (Object[] arguments) {
        DisciplineExample disciplineExample = new DisciplineExample();
        disciplineExample.createCriteria().andTagEqualTo(arguments[1].toString());
        List<Discipline> disciplines = null;
        String disciplineStatus = "";
        try {
            disciplines = disciplineDao.selectByExample(disciplineExample);
            int dependantStatus = FunctionUtil.isDisciplineFinished(arguments[0].toString());
	        String onClickFunc = "./"+disciplines.get(0).getId()+"/table_contents.html";
	        if (0 == dependantStatus) {
	            int status = FunctionUtil.isDisciplineFinished(arguments[1].toString());
	            if (0 == status) {
	                disciplineStatus = "<a href='"+onClickFunc+"'><img src='./images/modulo_selo_concluido.png'></a>";
	            } else if (-1 == status){
	                disciplineStatus = "<a href='"+onClickFunc+"'><img src='./images/modulo_selo_acesse.png'></a>";
	            } else {
	                disciplineStatus = "<a href='"+onClickFunc+"'><img src='./images/modulo_selo_continuar.png'></a>";
	            }
	        } else {
	            disciplineStatus = "<img src='./images/modulo_selo_bloqueado.png'>";
	        }
        } catch (SQLException e) {
        	LoggerManager.getInstance().logAtExceptionTime("LabelDependantDisciplineStatus.class", e.getMessage());
        }
        return disciplineStatus;
    }

}
