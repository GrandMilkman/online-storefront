<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>

<title>Login</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="icon" type="image/png"
    href="resources/images/icons/favicon.ico" />
<link href="<c:url value="/resources/styles/login.css" />" rel="stylesheet">

</head>
<body>

                <div class="header">
                    <h2>Welcome</h2>
                </div>
                <div class="Er">
                    <c:if test="${param.error ne null}">
                        <div style="color: red">Invalid credentials.</div>
                    </c:if>
                </div>
                <form:form id="regForm" modelAttribute="userJSP" action="/storefront/login" method="post">
                    <div class="login-table">
                    <div class="Mail" data-validate="Enter mail">
                        <form:label path="mail">Your email</form:label>
                        <form:input path="mail" name="mail" id="mail" />
                    </div>

                    <div class="Password" data-validate="Enter password">
                        <form:label path="password">Password</form:label>
                        <form:password path="password" name="password"
                            id="password" />
                    </div>

                    <div class="Submit">
                        <form:button id="submit" name="submit">Submit</form:button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </div>

                </div>
                </form:form>
                <div class="regText">
                    <a>Don`t have an account?</a>
                </div>
                <div class="Register">
                    <input type="button" value="Register" onclick="window.location.href='sign_up'" />
                </div>


</body>
</html>
