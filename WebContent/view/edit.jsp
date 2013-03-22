<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="template-top.jsp"></jsp:include>
<table align="center">
<tr><td><span class="subtitle"><br/>Profile<br/></span></td></tr>
<tr><td><s:property value="#session.currUser.userName"/></td></tr>
<tr><td><s:property value="#session.currUser.zipCode"/></td></tr>
<tr><td><s:property value="#session.currUser.email"/></td></tr>
<tr><td>
<s:form action="edit">
	<table align="center">
	<tr><td><s:password name="oldPassword" label="New Password" /></td></tr>
	<tr><td><s:password name="newPassword" label="Confirm Password" /></td></tr>
	<tr>
		<td align="center"><input type="submit" class="button" value="Change Password" name="passwordButton"/></td>
	</tr>
	</table>
</s:form>
</td></tr>
</table>
<jsp:include page="template-bottom.jsp"></jsp:include>