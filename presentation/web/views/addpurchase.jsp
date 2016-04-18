<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h1>Add new lot</h1>
<c:url var="saveUrl" value="/purchase/addpurchase" />
<form:form modelAttribute="purchaseEntity" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="amount">Name:</form:label></td>
            <td><form:input path="amount"/></td>
            <td><form:errors path="amount" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="paid">Start bid:</form:label></td>
            <td><form:input path="paid"/></td>
            <td><form:errors path="paid" cssClass="error"/></td>
        </tr>
        <tr>
            <fmt:formatDate value="${purchaseEntity.time}" var="timeString" pattern="yyyy-MM-dd'T'HH:mm" />
            <td><form:label path="time">Start time:</form:label></td>
            <td><form:input type="datetime-local"  value="${timeString}" path="time"/></td>
        </tr>
        <tr>
            <td><form:label path="clientEntity.name">Select client:</form:label></td>
            <td><form:select path="clientEntity.name" items="${categories}" /></td>
        </tr>
        <tr>
            <td><form:label path="cashierEntity.name">Select cashier:</form:label></td>
            <td><form:select path="cashierEntity.name" items="${categories}" /></td>
        </tr>
        <tr>
            <td><form:label path="fuelEntity.name">Select fuel:</form:label></td>
            <td><form:select path="fuelEntity.name" items="${categories}" /></td>
        </tr>
    </table>

    <input type="submit" value="Save" />
</form:form>