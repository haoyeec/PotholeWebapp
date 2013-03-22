<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="template-top.jsp" />



<s:form action="login">
	<table align="center">
	<tr><td colspan="2"><s:textfield name="userName"  label="User Name" required="true"/></td></tr>
	<tr><td colspan="2"><s:password name="password" label="Password" required="true"/></td></tr>
	<tr>
		<td><input type="submit" class="button" name="loginButton" value="Login"/></td>
		<td><input type="submit" class="button" name="registerButton" value="Register"/></td>
	</tr>
	</table>
</s:form>

<jsp:include page="template-bottom.jsp" />