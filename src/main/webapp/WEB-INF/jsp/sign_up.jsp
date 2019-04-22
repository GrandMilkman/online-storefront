<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en" xmlns:th="http://www.thymeleaf.org">

	<head>

		<title>SignUp</title>

		<link href="<c:url value="/resources/styles/sign_up.css" />" rel="stylesheet">

	</head>

	<body>
		<div class="header">
			<h2>Sign up</h2>
		</div>
		<form:form id="regForm" modelAttribute="userJSP" action="registerProcess"
			method="post">
			<div class="login-table">

				<table>
					<div class="Mail-Validation">
						<form:errors path="mail" cssClass="error" />
					</div>
					<div class="Mail">
						<form:label path="mail">Mail</form:label>
						<form:input  path="mail" name="mail"
							id="mail" />
					</div>

					<div class="Username-Validation">
						<form:errors path="name" cssClass="error" />
					</div>

					<div class="Username">
						<form:label path="name">Your Name</form:label>
						<form:input path="name" name="name" id="name" />
					</div>


					<div class="Password-Validation">
					<form:errors path="password" cssClass="error" />
					</div>

					<div class="Password">

						<form:label path="password">Password</form:label>
						<form:password path="password" name="password"
							id="password" />
					</div>

					<div class="Register">
						<form:button id="register" name="register">Register</form:button>
					</div>

					</table>
				</div>
			</form:form>
		</body>

	</html>
