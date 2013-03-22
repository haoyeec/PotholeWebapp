
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="template-top.jsp" />
<script type="text/javascript">

function initialize(start){
	if(start){
	var myLatlng = new google.maps.LatLng (40.443125669123916, -79.94377591973875);
	var myOptions = {
		zoom : 14,
    	center: myLatlng,
    	mapTypeId: google.maps.MapTypeId.ROADMAP
	};

	map = new google.maps.Map(document.getElementById("map"), myOptions);
	
	<s:iterator>
	initMarker(<s:property value="latitude" />, <s:property value="longitude" />, "<s:property value="potholeId" />", "<s:property value="description" />", "<s:property value="hateCount" />", "<s:property value="depth"/>", "<s:property value="imagePath" />");
	</s:iterator>
	}
	else{
		codeAddress();
	}
}

function createMarker(lat, lng ,markerData) {
	 var point = new google.maps.LatLng (lat, lng);
	 var marker = new google.maps.Marker({
   	    position: point,
           map: map
       });
           
       google.maps.event.addListener(marker, 'mouseover', function(){
       	var infowindow = new google.maps.InfoWindow(
           {
           		content: markerData.html,
           		maxWidth: 10,
               	position: marker.getPosition()
           }
           );
               
           marker.infowindow = infowindow
           infowindow.open(map);
                        
      	});

      google.maps.event.addListener(marker, 'mouseout', function(){
           marker.infowindow.close();             
       });

       google.maps.event.addListener(marker, 'click', function(){
           document.location.href=markerData.href;   
       });
		
	return marker;
}



function initMarker(lat, lng, id, description, hateCount, depth, imagePath){
	var html = '<span class="infoWindow">' + description + '<br/>' + 
				'<div style="font-size: 15px"> hates: ' + hateCount + '<br/>' + 
				'depth: ' + depth+ ' inches </div></span>' + '<img src="' +  imagePath +'" height=100px width=100px/>';
	var href = 'viewPothole.action?potholeId=' + id;

	var markerData = {
			html: html,
			href: href
	};

	createMarker(lat, lng, markerData);
}

function codeAddress() {
	var geocoder = new google.maps.Geocoder();
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


<p align="center"><img src="view/header1.gif" height=20% width=50% onLoad="initialize(true)"></p>
<div id="map" ></div>

<p align="center"><input id="address" type="text" size="30" value="">
<input type="button" class="button" value="Locate" onclick="initialize(false)"></p>

<jsp:include page="template-bottom.jsp" />
