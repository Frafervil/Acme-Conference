<%--
 * edit.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>



	<form:form action="${actionURI}" modelAttribute="author" id="form">

		<form:hidden path="id"/>
	<form:hidden path="version" />
	
	<acme:textbox code="author.name" path="name"/>
	
	<acme:textbox code="author.middleName" path="middleName"/>
	
	<acme:textbox code="author.surname" path="surname"/>
	
	<acme:textbox code="author.email" path="email"/>
	
	<acme:textbox code="author.phone" path="phone"/>
	
	<acme:textbox code="author.address" path="address"/>
	
	<acme:textbox code="author.photo" path="photo"/>
	<br />
	<br />

		<jstl:choose>
			<jstl:when test="${author.id == 0}">

				<form:hidden path="userAccount.authorities[0].authority"
					value="author" />


				<form:label path="userAccount.username">
					<spring:message code="author.username" />:
	</form:label>
				<spring:message code="author.username.placeholder"
					var="usernamePlaceholder" />
				<form:input path="userAccount.username"
					placeholder="${usernamePlaceholder}" size="25" />
				<form:errors cssClass="error" path="userAccount.username" />
				<br />
				<br />



				<form:label path="userAccount.password">
					<spring:message code="author.password" />:
	</form:label>
				<spring:message code="author.password.placeholder"
					var="passwordPlaceholder" />
				<form:password path="userAccount.password"
					placeholder="${passwordPlaceholder}" size="25" />
				<form:errors cssClass="error" path="userAccount.password" />
				<br />
				<br />

				<input type="submit" name="register" id="register"
				value="<spring:message code="author.save" />" >&nbsp; 
		
				<acme:cancel url="welcome/index.do" code="author.cancel"/>
				<br />
				<br />


			</jstl:when>
			<jstl:otherwise>


				<form:hidden path="userAccount" />

				<input type="submit" name="save" id="save"
				value="<spring:message code="author.save" />" >&nbsp; 
		
				<acme:cancel url="welcome/index.do" code="author.cancel"/>
				<br />
				<br />

			</jstl:otherwise>
		</jstl:choose>

	</form:form>