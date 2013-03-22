
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="template-top.jsp" />
<script type="text/javascript">
	function initialize(){
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(40.443125669123916, -79.94377591973875);
    var myOptions = {
      zoom: 12,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById("map"), myOptions);

    var marker = new google.maps.Marker({
   	    position: new google.maps.LatLng (40.443125669123916, -79.94377591973875),
           map: map
       });
	}



  function codeAddress() {
    var address = document.getElementById("address").value;
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        map.setCenter(results[0].geometry.location);
        
      } else {
        alert("No such place");
      }
    });
  }

</script>
<p align="center"><img src="view/header2.gif" height=20% width=50% onLoad="initialize()"></p>
<div id="map"></div>

<p align="center"><input id="address" type="text" size="30" value="">
<input type="button" class="button" value="Search" onclick="codeAddress()"></p>

<jsp:include page="template-bottom.jsp" />