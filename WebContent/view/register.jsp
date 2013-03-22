<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="template-top.jsp" />

<s:form action="register">
	<table align="center">
	<tr><td><s:textfield name="userName" label="User Name" /></td></tr>
	<tr><td><s:textfield name="zipCode" label="Zip Code" /></td></tr>
	<tr><td><s:textfield name="email" label="Email" /></td></tr>
	<tr><td><s:password name="password" label="Password" /></td></tr>
	<tr>
		<td align="center"><input type="submit" class="button" value="Register"/></td>
	</tr>
	</table>
</s:form>

<jsp:include page="template-bottom.jsp" />