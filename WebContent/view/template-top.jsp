<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Holes!</title>
<link rel="stylesheet" type="text/css" href="view/main.css"/>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"> </script>
<script type="text/javascript">
	
    function clearSubmenu(){
    	var toRemove;
		while(toRemove = document.getElementById('submenu')){
			toRemove.parentNode.removeChild(toRemove);
		}

		var addTo = document.getElementById('about-menu');
		var subtable = document.createElement('table');
		subtable.setAttribute('id', 'submenu');
		var tr = document.createElement('tr');
		tr.setAttribute('width', '100%');
		
		var td = document.createElement('td');
		td.setAttribute('align', 'center');
		td.setAttribute('width', '100%');

		var text = document.createTextNode('\u00A0');
		
		td.appendChild(text);

		tr.appendChild(td);
	
		subtable.appendChild(tr);
		addTo.appendChild(subtable);
    }

    function mouseOver(i){
		switch(i){
		case 1:
			mouseOverHome();
			break;
		case 2:
			clearSubmenu();
			break;
		case 3:
			mouseOverPothole();
			break;
		case 4:
		case 5:
		default:
			clearSubmenu();
		}			

    }
	function mouseOverHome(){
		var addTo = document.getElementById('home-menu');
		clearSubmenu();
		var subtable = document.createElement('table');
		subtable.setAttribute('id', 'submenu');
		
		var tr = document.createElement('tr');
		tr.setAttribute('width', '100%');
		
		<s:if test="%{#session.currUser != null}">
		// add map
		var td = document.createElement('td');
		td.setAttribute('align', 'center');
		td.setAttribute('width', '33%');
		var link = document.createElement('a');
		link.setAttribute('href', 'index.action');
		link.setAttribute('onMouseOut', 'mouseOver(5)');
		var text = document.createTextNode("Map");
		var span = document.createElement('span');
		span.setAttribute('class', 'submenu-item');
		span.appendChild(text);
		link.appendChild(span);

		td.appendChild(link);

		tr.appendChild(td);

		// add users
		var td = document.createElement('td');
		td.setAttribute('align', 'center');
		td.setAttribute('width', '50%');
		var link = document.createElement('a');
		link.setAttribute('href', 'list_users.action');
		link.setAttribute('onMouseOut', 'mouseOver(5)');
		var text = document.createTextNode("Users");
		var span = document.createElement('span');
		span.setAttribute('class', 'submenu-item');
		span.appendChild(text);
		link.appendChild(span);

		td.appendChild(link);

		tr.appendChild(td);

		// add edit
		var td = document.createElement('td');
		td.setAttribute('align', 'center');
		td.setAttribute('width', '50%');
		var link = document.createElement('a');
		link.setAttribute('href', 'edit.action');
		link.setAttribute('onMouseOut', 'mouseOver(5)');
		var text = document.createTextNode("Profile");
		var span = document.createElement('span');
		span.setAttribute('class', 'submenu-item');
		span.appendChild(text);
		link.appendChild(span);
		
		td.appendChild(link);

		tr.appendChild(td);
		</s:if>
		<s:else>
		// add map
		var td = document.createElement('td');
		td.setAttribute('align', 'center');
		td.setAttribute('width', '50%');
		
		var link = document.createElement('a');
		link.setAttribute('href', 'index.action');
		link.setAttribute('onMouseOut', 'mouseOver(5)');
		var text = document.createTextNode("Map");
		var span = document.createElement('span');
		span.setAttribute('class', 'submenu-item');
		span.appendChild(text);
		link.appendChild(span);

		td.appendChild(link);

		tr.appendChild(td);
		// add users
		var td = document.createElement('td');
		td.setAttribute('align', 'center');
		td.setAttribute('width', '50%');
		var link = document.createElement('a');
		link.setAttribute('href', 'list_users.action');
		link.setAttribute('onMouseOut', 'mouseOver(5)');
		var text = document.createTextNode("Users");
		var span = document.createElement('span');
		span.setAttribute('class', 'submenu-item');
		span.appendChild(text);
		link.appendChild(span);

		td.appendChild(link);

		tr.appendChild(td);
		</s:else>
		subtable.appendChild(tr);
		addTo.appendChild(subtable);
			
	}
	function mouseOverPothole(){
		var addTo = document.getElementById('pothole-menu');
		var toRemove;
		if(toRemove = document.getElementById('submenu')){
			toRemove.parentNode.removeChild(toRemove);
		}
		var subtable = document.createElement('table');
		subtable.setAttribute('id', 'submenu');
		var tr = document.createElement('tr');
		tr.setAttribute('width', '100%');

		<s:if test="%{#session.currUser != null}">
		// add upload
		var td = document.createElement('td');
		td.setAttribute('align', 'center');
		td.setAttribute('width', '50%');

		var link = document.createElement('a');
		link.setAttribute('href', 'upload.action');
		link.setAttribute('onMouseOut', 'mouseOver(5)');
		var text = document.createTextNode("Upload");
		var span = document.createElement('span');
		span.setAttribute('class', 'submenu-item');
		span.appendChild(text);
		link.appendChild(span);
		
		td.appendChild(link);

		tr.appendChild(td);

		// add list_potholes
		var td = document.createElement('td');
		td.setAttribute('align', 'center');
		td.setAttribute('width', '50%');
		var link = document.createElement('a');
		link.setAttribute('href', 'list_potholes.action');
		link.setAttribute('onMouseOut', 'mouseOver(5)');

		var text = document.createTextNode("List");

		var span = document.createElement('span');
		span.setAttribute('class', 'submenu-item');
		span.appendChild(text);
		link.appendChild(span);
		
		td.appendChild(link);
		</s:if>
		<s:else>
		// add list_potholes
		var td = document.createElement('td');
		td.setAttribute('align', 'center');
		td.setAttribute('width', '50%');
		var link = document.createElement('a');
		link.setAttribute('href', 'list_potholes.action');
		link.setAttribute('onMouseOut', 'mouseOver(5)');

		var text = document.createTextNode("List");

		var span = document.createElement('span');
		span.setAttribute('class', 'submenu-item');
		span.appendChild(text);
		link.appendChild(span);
		
		td.appendChild(link);
		</s:else>
		tr.appendChild(td);
	
		subtable.appendChild(tr);
		addTo.appendChild(subtable);
			
	}

</script>
</head>
<body>

<table width=50% height=50% align="center">

	<tr>
		
		<td colspan="4" align="right"> 
		<s:if test="%{#session['currUser'] == null}">
			<a href="login.action"><span class="menu-item">&nbsp;</span></a>
		</s:if>
		<s:else>
		<span class="menu-item">welcome ${currUser.userName}</span>
		</s:else>
		</td>
	</tr>

	<tr>
		<td id="top" colspan="4" align="center"> <h1 >Holes!</h1></td>
	</tr>

	<tr>
		<td width=25% align="center"><a href="javascript:void(0)" onMouseOver="mouseOver(1)"><span class="menu-item">Home</span></a></td>
		<td width=25% align="center"><a href="javascript:void(0)" onMouseOver="mouseOver(3)"><span class="menu-item">Potholes</span></a></td>
		<td width=25% align="center"><a href="about.action" onMouseOver="mouseOver(5)"><span class="menu-item">About</span></a></td>
		<td width=25% align="center">
		<s:if test="%{#session['currUser'] == null}">
			<a href="login.action" onMouseOver="mouseOver(2)"><span class="menu-item">Login</span></a>
		</s:if>
		<s:else>
			<a href="logout.action" onMouseOver="mouseOver(2)"><span class="menu-item">Logout</span></a>
		</s:else>
		
		
		</td>
	</tr>
	
	<tr>
		<td id="home-menu" width=20% align="center"></td>
		<td id="pothole-menu" width=20% align="center"><table id="submenu"><tr><td>&nbsp</td></tr></table></td>
		<td id="about-menu" width=20% align="center"></td>
		<td id="login-menu" width=20% align="center"></td>
	</tr>
	
	<tr>
		<td colspan="4">
		<s:if test="hasActionErrors()">
			<div class="error">
				<s:actionerror />
			</div>
		</s:if>
	<!-- CONTENT -->