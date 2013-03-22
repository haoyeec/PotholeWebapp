<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="template-top.jsp"></jsp:include>
<table align="center">
	
	<s:iterator status="stat" var="pothole">
	<tr><td><s:property value="#stat.count"/>&nbsp;&nbsp;&nbsp;&nbsp;</td><td>
	
	<s:url id="url" action="viewPothole">
		<s:param name="potholeId"><s:property value="potholeId"/></s:param>
	</s:url>
	
	<s:a href="%{url}">
	
	<span class="pothole">
	<s:property value="description"/>
	</span>
	
	</s:a>
	
	</td></tr>
	<tr><td></td><td>hates: <s:property value="hateCount"/> </td></tr>
	<tr><td></td><td>&nbsp;</td></tr>
	</s:iterator>

	
</table>

<jsp:include page="template-bottom.jsp"></jsp:include>