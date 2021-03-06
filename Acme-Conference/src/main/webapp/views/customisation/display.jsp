
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<table class="displayStyle">


<tr>

<th> <spring:message code="customisation.parameters"/>  </th>
<th> <spring:message code="customisation.values"/>  </th>
</tr>

<tr>
<td> <strong> <spring:message code="customisation.systemName" /> : </strong> </td>
<td> <jstl:out value="${customisation.systemName}" /></td>
</tr>

<tr>
<td> <strong> <spring:message code="customisation.welcomeBanner" /> : </strong> </td>
<td> <a target="_blank" href="${customisation.welcomeBanner}"><spring:message code="customisation.welcomeBanner" /></a> </td>
</tr>

<tr>
<td> <strong> <spring:message code="customisation.welcomeMessageEs" /> : </strong> </td>
<td> <jstl:out value="${customisation.welcomeMessageEs}" /> </td>
</tr>

<tr>
<td> <strong> <spring:message code="customisation.welcomeMessageEn" /> : </strong> </td>
<td> <jstl:out value="${customisation.welcomeMessageEn}" /> </td>
</tr>

<tr>
<td> <strong> <spring:message code="customisation.countryCode" /> : </strong> </td>
<td> <jstl:out value="${customisation.countryCode}" /> </td>
</tr>

</table>

<!-- Credit Card Makes -->
<b><spring:message code="customisation.creditCardMakes" /></b>:
<ul>
<jstl:forEach items="${customisation.creditCardMakes}" var="card" >
	<jstl:if test="${card != null}">
       	<li><jstl:out value="${card}"/></li>
       </jstl:if>
</jstl:forEach>
</ul>

<!-- Topics -->
<b><spring:message code="customisation.spanishTopics" /></b>:
<ul>
<jstl:forEach items="${customisation.spanishTopics}" var="estopic" >
	<jstl:if test="${estopic != null}">
       	<li><jstl:out value="${estopic}"/></li>
       </jstl:if>
</jstl:forEach>
</ul>
<b><spring:message code="customisation.englishTopics" /></b>:
<ul>
<jstl:forEach items="${customisation.englishTopics}" var="entopic" >
	<jstl:if test="${entopic != null}">
       	<li><jstl:out value="${entopic}"/></li>
       </jstl:if>
</jstl:forEach>
</ul>
<!-- Void words -->
<b><spring:message code="customisation.voidWords" /></b>:
<ul>
<jstl:forEach items="${customisation.voidWords}" var="voidWord" >
	<jstl:if test="${voidWord != null}">
       	<li><jstl:out value="${voidWord}"/></li>
       </jstl:if>
</jstl:forEach>
</ul>
<div>

<a href="customisation/administrator/edit.do"> <spring:message code="customisation.edit"/> </a>

</div>



