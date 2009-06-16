<%-- 
    Document   : input Grade
    Created on : Aug 7, 2008, 9:14:56 AM
    Author     : leoomoreira
--%>

<%@ taglib prefix="s" uri="/struts-tags" %>

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