<!-- %@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%-->

<html lang="en" xmlns:th="http://www.thymeleaf.org">

	<head>
	<title>Confirmation</title>
	 <link th:href="@{/resources/styles/confirm.css}" rel="stylesheet">
	</head>

	<body>
		<div class="header">
			<h2>Confirmation success</h2>
		</div>
		<div class="table">
				<input type="button" value="Login" onClick='location.href="login"'>
				<input type="button" value="Home" onClick='location.href="index"'>
		
		</div>

	</html>
	
