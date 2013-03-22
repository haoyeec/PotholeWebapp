<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="template-top.jsp"></jsp:include>
<table align="center">
<tr><td><span class="subtitle">Profile</span></td></tr>
<tr><td><s:property value="innerPersonBean.userName" /></td></tr>
<tr><td><s:property value="innerPersonBean.zipCode" /></td></tr>
<tr><td><s:property value="innerPersonBean.email" /></td></tr>
<tr><td>
&nbsp;
</td></tr>

<tr><td>
<span class="subtitle">Potholes Hated:</span>
</td></tr>
<s:if test="%{hateBeans.length == 0}">
<tr><td>NONE</td></tr>
</s:if>
<s:else>
<s:iterator status="stat" value="hateBeans">
<tr><td>

<s:url id="url" action="viewPothole">
	<s:param name="potholeId"><s:property value="potholeId"/></s:param>
</s:url>
	


<s:a href="%{url}"><span class="pothole"><s:property value="potholeNames[#stat.index]"/></span></s:a>

</td></tr>

</s:iterator>
</s:else>
</table>

<jsp:include page="template-bottom.jsp"></jsp:include>