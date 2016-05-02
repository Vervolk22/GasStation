<%--
  Created by IntelliJ IDEA.
  User: AndreyZholudev
  Date: 3/10/2016
  Time: 9:14 AM
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Site.css"/>
    <meta charset="utf-8"/>
    <title><tiles:getAsString name="title"/></title>
</head>
<body>
<tiles:insertDefinition name="header"/>
<aside>
    <tiles:insertAttribute name="navigation"/>
    <tiles:insertAttribute name="categories"/>
</aside>
<div id="maincontent">
    <tiles:insertAttribute name="body"/>
</div>
<tiles:insertAttribute name="footer"/>
</body>
</html>