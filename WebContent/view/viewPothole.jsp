<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="template-top.jsp"></jsp:include>

<script type="text/javascript">
function createMarker(map,point) {
	 var marker = new google.maps.Marker({
  	    position: point,
        map: map
      });
     
		
	return marker;
  }
function initialize(lat, lng){
	// Display the map, with some controls and set the initial location 
   var myLatlng = new google.maps.LatLng (lat, lng);
   var myOptions = {
   	zoom : 17,
       center: myLatlng,
       mapTypeId: google.maps.MapTypeId.ROADMAP
   };

  	var map = new google.maps.Map(document.getElementById("map"), myOptions);

  
  createMarker(map,myLatlng);
}
</script>

<s:if test="hasActionErrors()">
</s:if>
<s:else>
<s:form action="viewPothole">
<table align="center" width=50% height=50%>

<tr><td colspan="2" align="center"><span class="subtitle"><s:property value="pothole.description" /></span> </td></tr>
<tr>
	<td>
	
	<img src="<s:property value="pothole.imagePath" />" height=200px width=200px onLoad="initialize(<s:property value="pothole.latitude" />, <s:property value="pothole.longitude" />)">
	
	</td>
	<td >
		<div id="map" style="width: 200px; height: 200px;"></div>
	</td>
</tr>

<tr>
	
	<td align="center" colspan="2">
	
	<s:hidden name="potholeId" value="%{pothole.potholeId}"></s:hidden>
	<s:if test="%{#session.currUser == null}">
	</s:if>
	<s:else>
	<input type="submit" class="button" value="Hate it!" name="hateButton"></input>
	</s:else>
	
	</td>
</tr>

</table>

<table align="center" width=50%>
<tr><td colspan="2"><span class="subtitle"><br/>Vital Stats<br/></span></td></tr>
<tr><td colspan="2">hates: <s:property value="pothole.hateCount" /></td></tr>
<tr><td colspan="2">depth: <s:property value="pothole.depth" /> inches</td></tr>
</table>



<table align="center" width=50%>
<tr><td colspan="2"><span class="subtitle"><br/>Pothole Angst<br/></span></td></tr>
<s:if test="%{comments.length == 0}">
<tr><td width=25%></td><td>NONE</td></tr>
</s:if>
<s:else>


<s:iterator status="stat" value="comments">

<tr><td width=25%>
<s:url id="url" action="viewUser">
	<s:param name="userId"><s:property value="userId"/></s:param>
</s:url>
	


<s:a href="%{url}"><span class="pothole"><s:property value="userNames[#stat.index]" /> </span></s:a></td>
<td><s:property value="comment" /></td></tr>
<tr><td colspan="2">&nbsp;</td></tr>

</s:iterator>
</s:else>
</table>

<table align="center" width=50%>
<s:if test="%{#session.currUser != null}">
<tr><td colspan="2"><span class="subtitle">What's your experience? (255 chars)</span></td></tr>
<tr><td colspan="2" align="center"><s:textarea name="review" rows="6" cols="48" />
</td></tr>
<tr><td colspan="2" align="center"><input type="submit" class="button" value="Share" name="shareButton"></input></td></tr>
</s:if>
<s:else>
</s:else>
</table>
</s:form>
</s:else>
<jsp:include page="template-bottom.jsp"></jsp:include>