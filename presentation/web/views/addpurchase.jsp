<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="<c:url value="/resources/js/jquery.autocomplete.js" />"></script>
<script   src="https://code.jquery.com/jquery-1.10.2.js"   integrity="sha256-it5nQKHTz+34HijZJQkpNBIHsjpV8b6QzMJs9tmOBSo="   crossorigin="anonymous"></script>

<script>
    $('#clients_search').autocomplete({
        serviceUrl: '/purchase/clients',
        paramName: "tagName",
        delimiter: ",",
        transformResult: function(response) {

            return {
                //must convert json to javascript object before process
                suggestions: $.map($.parseJSON(response), function(item) {

                    return { value: item.tagName, data: item.id };
                })

            };

        }

    });
</script>

<h1>New purchase</h1>
<c:url var="saveUrl" value="/purchase/addpurchase" />
<form:form modelAttribute="purchaseEntity" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="amount">Amount:</form:label></td>
            <td><form:input path="amount"/></td>
            <td><form:errors path="amount" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="paid">Sum to pay:</form:label></td>
            <td><form:input path="paid"/></td>
            <td><form:errors path="paid" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="client.name">Select client:</form:label></td>
            <td><form:input id="clients_search" path="client.name"/></td>
        </tr>
        <tr>
            <td><form:label path="fuel.name">Select fuel:</form:label></td>
            <td><form:select path="fuel.name" items="${fuels}" /></td>
        </tr>
        <tr>
            <td><form:label path="cashier.name">Cashier:</form:label></td>
            <td><form:input readonly="true" path="cashier.name" /></td>
        </tr>
    </table>

    <input type="submit" value="Save" />
</form:form>