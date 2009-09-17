<%--
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
# File: list.jsp                                                                            #
# Document: List Discipline                                                                 #
# Date        - Author(Company)                   - Issue# - Summary                        #
# 07-AUG-2008 - Leo Moreira                       - XXXXXX - Initial Version                #
# 23-JUL-2009 - Rafael Lagoa (Instituto Eldorado) - 000012 - Unicode (UTF-8) support        #
############################################################################################# 
--%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<div class="response-upload" id="studentsEnrollmentOk">
    <table>
        <tr class="head">
            <td><s:text name="enrollment.batch.studentName" /></td>
            <td><s:text name="enrollment.batch.status" /></td>
        </tr>
        <s:iterator value="studentsEnrollment" status="stat">
            <s:if test="(#stat.index % 2) == 0">
            <tr>
                <td><s:property /></td>
            </s:if>
            <s:else>
                <s:if test="studentsEnrollment[#stat.index] == 'true'">
                    <td><img src="../images/icon/icon-save.gif" /> <s:text name="enrollment.batch.success" /></td>
                </s:if>
                <s:else>
                    <td><img src="../images/icon/icon-cancel.gif" /> <s:text name="enrollment.batch.failed" /></td>
                </s:else>
            </tr>
            </s:else>
        </s:iterator>
    </table>
</div>
    <%--
    <s:form id="teste">

        
            <input type="hidden" name="<s:property/>" value="<s:property/>">
        </s:iterator> 

    </s:form>
    --%>