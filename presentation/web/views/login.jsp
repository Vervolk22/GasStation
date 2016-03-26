<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if (!Boolean.parseBoolean(request.getParameter("normallogin"))) { %>
<h4 class="redinfo">You are not allowed to see that page. Please, log in.</h4><p/>
<% } %>
<%
    if (Boolean.parseBoolean(request.getParameter("error"))) { %>
<h4 class="redinfo">Bad credentials. Please, try again.</h4><p/>
<% } %>
<form action="j_spring_security_check" method="post">

    <div class="inputdiv">
        <span>Username</span><input type="text" name="username" placeholder="Enter your username"/>
    </div>
    <div class="inputdiv">
        <span>Password</span><input type="password" name="password" placeholder="Enter your password"/>
    </div>
    <div class="inputdiv">
        <span>Remember me?</span><input type="checkbox" name="_spring_security_remember_me"/>
    </div>
    <div class="inputdiv">
        <span><input type="submit" value="Log in"/></span>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
