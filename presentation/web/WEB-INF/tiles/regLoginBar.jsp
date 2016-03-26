<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: AndreyZholudev
  Date: 3/10/2016
  Time: 9:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="reglogin">
    <sec:authorize var="loggedIn" access="isAuthenticated()" />
    <c:choose>
        <c:when test="${loggedIn}">
            <form action="/j_spring_security_logout" method="post">
                <sec:authentication var="user" property="principal" />
                Hello, <c:out value="${user.username}"/>!
                <input type="submit" id="linkbutton" value="Logout"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </c:when>
        <c:otherwise>
            <a href="/user/login?normallogin=true">Login</a>
        </c:otherwise>
    </c:choose>
</div>
