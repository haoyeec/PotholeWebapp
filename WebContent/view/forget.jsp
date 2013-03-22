
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="template-top.jsp" />

<s:form action="forget">
<s:textfield name="email" />
<s:submit name="emailButton" label="Send Password" class="button" />
</s:form>

<jsp:include page="template-bottom.jsp" />