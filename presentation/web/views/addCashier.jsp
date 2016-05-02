<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="<c:url value="/resources/js/jquery-1.12.3.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.autocomplete.min.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>

<h1>New cashier</h1>
<c:url var="saveUrl" value="/cashier/addcashier" />
<c:set var="temp" value=""/>
<form:form modelAttribute="cashierEntity" acceptCharset="utf-16" method="POST" action="${saveUrl}">
  <table>
    <tr>
      <td><form:label path="branch.id">Select branch:</form:label></td>
      <td><form:select path="branch.id" items="${branches}" /></td>
    </tr>
    <tr>
      <td><form:label path="name">Enter name:</form:label></td>
      <td><form:input path="name"/></td>
    </tr>
    <tr>
      <td><form:label path="${password}">Enter password:</form:label></td>
      <td><form:input path="${password}"/></td>
    </tr>
  </table>

  <input type="submit" value="Save" />
</form:form>
