/*    
#############################################################################################
# Copyright(c) 2009 by IBM Brasil Ltda and others                                           #
# This file is part of ivela project, an open-source                                        #
# Program URL   : http://code.google.com/p/ivela/                                           #  
#                                                                                           #
# This program is free software; you can redistribute it and/or modify it under the terms   #
# of the GNU General Public License as published by the Free Software Foundation; either    #
# version 3 of the License, or (at your option) any later version.                          #
#                                                                                           #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. #
# See the GNU General Public License for more details.                                      #  
#                                                                                           #
#############################################################################################
# File: TopicDao.java                                                                       #
# Document: Topic Dao Interface                                                             # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 10-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Initial Creation               #
#############################################################################################
*/
package br.ufc.ivela.commons.dao.interfaces;

import java.util.List;

import br.ufc.ivela.commons.dao.GenericDao;
import br.ufc.ivela.commons.model.Enrollment;
import br.ufc.ivela.commons.model.Topic;

public interface TopicDao<T> extends GenericDao<T> {
    public Topic getLastTopicByForum(Long forumId);
    public List<Topic> getRecentTopics(int count, List<Enrollment> gradeIds);
    public String getTopicListCountQuery(Long systemUser, boolean isAdministrator, boolean isPublic, Long forum, String title);
    public String getTopicListQuery(Long systemUser, boolean isAdministrator, boolean isPublic, Long forum, String title);
    public List<Topic> getTopicList(Long systemUser, boolean isAdministrator, boolean isPublic, Long forum, String title);
}
