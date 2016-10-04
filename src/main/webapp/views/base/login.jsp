<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style=" height: 90%;">
    <div id="templatemo_main">
    <div style="font-size: 16px; text-align: center; width: 300px; height: 126px; background-color: #999999; color: white; float: inherit;position: absolute;left:36%; margin: 22px; border: 3px double black; border-radius: 0px;">
        <form:form action="/loginprocessing" method="post">
            <p><input name="username" type="text" placeholder="Login"></p>
            <p><input name="password" type="password" placeholder="Password"></p>
            <p><input type="submit" value="Sign in"></p>
        </form:form>
    </div>
</div>
</div>