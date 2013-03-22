<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="template-top.jsp"></jsp:include>

<script type="text/javascript">
	var marker= null;

	function createMarker(map,point,markerData) {
	 var marker = new google.maps.Marker({
   	    position: point,
           map: map
       });
           

		
		return marker;
   }
	function initialize(){
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(40.443125669123916, -79.94377591973875);
    var myOptions = {
      zoom: 12,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }

    var longtitude = document.getElementById("upload_longitude");
	longtitude.setAttribute('value', -79.94377591973875);

	var latitude = document.getElementById("upload_latitude");
	latitude.setAttribute('value', 40.443125669123916);
    
    map = new google.maps.Map(document.getElementById("map"), myOptions);

    var markerData = {
            html: "",
     		href: ""
     };
    
    marker = createMarker(map, latlng, markerData);
    
    google.maps.event.addListener(map, 'click', function(event) {
       
    	//call function to create marker
             if (marker) {
                marker.setMap(null);
                marker = null;
             }
         var markerData = {
                html: "",
         		href: ""
         };
         
    	 marker = createMarker(map, event.latLng, markerData);
    	 // create hidden fields
    	 var longtitude = document.getElementById("upload_longitude");
    	 longtitude.setAttribute('value', event.latLng.lng());

    	 var latitude = document.getElementById("upload_latitude");
    	 latitude.setAttribute('value', event.latLng.lat());
    	 
      });
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

<s:if test="%{#session.currUser != null}">
<p align="center"><img src="view/header3.gif" height=20% width=50% onLoad="initialize()"></p>
<div id="map"></div>

<p align="center"><input id="address" type="text" size="30" value="">
<input type="button" class="button" value="Go" onclick="codeAddress()"></p>


<s:form action="upload" method="POST" enctype="multipart/form-data">

<s:hidden  name="longitude"></s:hidden>
<s:hidden name="latitude"></s:hidden>


<table align="center">
<s:file name="upload" label="Upload Image"/>
<s:textfield name="desc" label="Description"/>
<s:textfield name="depth" label="Depth (Inches)"/>
</table>
<table align="center">
<tr><td><input type="submit" class="button" name="uploadButton" value="Upload"/></td></tr>
</table>
</s:form>
</s:if>
<s:else>
</s:else>

<jsp:include page="template-bottom.jsp"></jsp:include>