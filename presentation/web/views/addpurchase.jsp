<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="<c:url value="/resources/js/jquery-1.12.3.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.autocomplete.min.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>

<h1>New purchase</h1>
<c:url var="saveUrl" value="/purchase/addpurchase" />
<c:set var="temp" value=""/>
<form:form modelAttribute="purchaseEntity" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="fuel.name">Select fuel:</form:label></td>
            <td><form:select id="fuelInput" path="fuel.name" items="${fuels}" /></td>
        </tr>
        <tr>
            <td><form:label path="${temp}">Fuel price:</form:label></td>
            <td><form:select id="priceSelect" path="${temp}" items="${fuelPrices.values()}" disabled="true"/></td>
        </tr>
        <tr>
            <td><form:label path="amount">Enter amount:</form:label></td>
            <td><form:input path="amount"/></td>
            <td><form:errors path="amount" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="paid">Sum to pay:</form:label></td>
            <td><form:input path="paid" readonly="true"/></td>
            <td><form:errors path="paid" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="client.name">Select client:</form:label></td>
            <td><form:input id="clients_search" path="client.name"/></td>
        </tr>
        <tr>
            <td><form:label path="cashier.name">Cashier:</form:label></td>
            <td><form:input readonly="true" path="cashier.name" /></td>
        </tr>
    </table>

    <input type="submit" value="Save" />
</form:form>