<!--%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%-->

<html lang="en" xmlns="http://www.w3.org/1999/xhtml"
    xmlns:th="http://www.thymeleaf.org"
    xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">

<head>

<title>Login</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="icon" type="image/png"
    th:href="@{resources/images/icons/favicon.ico}" />
<link th:href="@{/resources/styles/login.css}" rel="stylesheet">
</head>
<body>
    <div class="header">
        <h2>Welcome</h2>
    </div>
    <!--div class="Er">
        <c:if test="${param.error ne null}">
            <div style="color: red">Invalid credentials.</div>
        </c:if>
    </div-->
    <form action="/login" method="post" th:object="${userJSP}">
        <div class="Er"th:if="${param.error}">
            <div style="color: red">You are invalid</div>
        </div>
        <div class="login-table">
            <div class="Mail" data-validate="Enter mail">
                <label for="mail">Your email</label>
                <input type="text" id="mail" name="mail"
                    th:value="${userJSP} ? ${userJSP.mail}"></input>
            </div>

            <div class="Password" data-validate="Enter password">
                <label for="password">Password</label> 
                <input type="password" id="password" 
                    name="password"></input>
            </div>

            <div class="Submit">
                <button id="submit" name="submit">Submit</button>
                <input type="hidden" th:name="${_csrf.parameterName}"
                    th:value="${_csrf.token}" />
            </div>
            
            <div class="regText">
                    <a>Don`t have an account?</a>
            </div>
            <div class="Register">
                <input type="button" value="Register" onclick="window.location.href='sign_up'" />
            </div>
        </div>
    </form>
</body>
</html>
