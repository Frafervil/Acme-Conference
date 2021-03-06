<%--
 * viewProfile.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<table class="ui celled table">
	<thead>
		<tr>
			<acme:image src="https://cdn1.iconfinder.com/data/icons/instagram-ui-glyph/48/Sed-09-128.png"/>
		</tr>
	</thead>
	<tbody>
		<tr>
			<acme:displayText code="administrator.name" dataLabel="name" path="${administrator.name}"/>
		</tr>
		<tr>
			<acme:displayText code="administrator.middleName" dataLabel="middleName" path="${administrator.middleName}"/>
		</tr>
		<tr>
			<acme:displayText dataLabel="surname" code="administrator.surname" path="${administrator.surname}"/>
		</tr>
		<tr>
			<acme:displayText dataLabel="phone" code="administrator.phone" path="${administrator.phone}"/>
		</tr>
		<tr>
			<acme:displayText dataLabel="address" code="administrator.address" path="${administrator.address}"/>
		</tr>
		<tr>
			<acme:displayText dataLabel="email" code="administrator.email" path="${administrator.email}"/>
		</tr>
	</tbody>
</table>

<acme:button url="administrator/edit.do" code="administrator.edit"/>

</body>
</html>