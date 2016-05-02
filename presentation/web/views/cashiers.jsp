<html>
<head>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <link href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css"
        rel="stylesheet" type="text/css" media="all" />
</head>

  <table>
    <tbody>
      <c:forEach var="i" items="${cashiers}">
        <tr>
          <td><a href="/cashier/cashier?num=${i.id}"> <c:out value="${i}"/></a></td>
          <td><a href="/cashier/deletecashier?num=${i.id}">Delete</a></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

</html>