<!--%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%-->

<html lang="en" xmlns="http://www.w3.org/1999/xhtml" 
    xmlns:th="http://www.thymeleaf.org"
    xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">

    <head>

        <title>SignUp</title>

        <link th:href="@{/resources/styles/sign_up.css}" rel="stylesheet">

    </head>

    <body>
        <div class="header">
            <h2>Sign up</h2>
        </div>
        <form id="regForm" th:modelAttribute="userJSP" th:action="@{registerProcess}"
            method="post" th:object="${userJSP}">
            <div class="login-table">
                <table>
                    <div class="Mail-Validation">
                        <th:errors path="mail" cssClass="error" />
                    </div>
                    <div class="Mail">
                        <label for="mail">Mail</label>
                        <input type="text" name="mail" id="mail" 
                            th:value="${userJSP} ? ${userJSP.mail}"/>
                    </div>

                    <div class="Username-Validation">
                        <th:errors path="name" cssClass="error" />
                    </div>

                    <div class="Username">
                        <label for="name">Your Name</label>
                        <input type="text" name="name" id="name" 
                            th:value="${userJSP} ? ${userJSP.name}"/>
                    </div>

                    <div class="Password-Validation">
                        <th:errors path="password" cssClass="error" />
                    </div>

                    <div class="Password">
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password"/>
                    </div>

                    <div class="Register">
                        <button id="register" name="register">Register</button>
                    </div>
                    
                    </table>
                </div>
            </form>
        </body>

    </html>
