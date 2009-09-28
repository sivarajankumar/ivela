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
# File: DelayedMailRemote.java                                                              #
# Document: Delayed Mail Remote                                                             # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# 24-SEP-2009 - otofuji (Instituto Eldorado)      - 000016 - Review Mail                    #
#############################################################################################
*/
package br.ufc.ivela.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Remote;

import br.ufc.ivela.commons.model.DelayedMail;

@Remote
public interface DelayedMailRemote {

    public Long add(DelayedMail delayedMail);

    public void add(Collection<DelayedMail> delayedMails);
     
    public boolean update(DelayedMail delayedMail);

    public Collection<DelayedMail> getAll();
    
    public DelayedMail get(Long delayedMailId);

    public boolean remove(Long delayedMailId);

    public boolean remove(Collection<DelayedMail> delayedMails);
}
