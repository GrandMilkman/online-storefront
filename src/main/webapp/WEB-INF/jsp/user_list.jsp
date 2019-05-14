<!--%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%-->

<html lang="en" xmlns="http://www.w3.org/1999/xhtml" 
    xmlns:th="http://www.thymeleaf.org"
    xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">

<head>

<title>Users</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="OneTech shop project">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link th:href="@{/resources/styles/bootstrap4/bootstrap.min.css}" rel="stylesheet">
<link th:href="@{/resources/plugins/fontawesome-free-5.0.1/css/fontawesome-all.cs}" rel="stylesheet">
<link th:href="@{/resources/plugins/OwlCarousel2-2.2.1/owl.carousel.css}" rel="stylesheet">
<link th:href="@{/resources/plugins/OwlCarousel2-2.2.1/owl.theme.default.css}" rel="stylesheet">
<link th:href="@{/resources/plugins/OwlCarousel2-2.2.1/animate.css}" rel="stylesheet">
<link th:href="@{/resources/styles/product_styles.css}" rel="stylesheet">
<link th:href="@{/resources/styles/product_responsive.css}" rel="stylesheet">
<link th:href="@{/resources/styles/user_list.css}" rel="stylesheet">

<script src="/resources/js/jquery-3.3.1.min.js"></script>
<script src="/resources/styles/bootstrap4/popper.js"></script>
<script src="/resources/styles/bootstrap4/bootstrap.min.js"></script>
<script src="/resources/plugins/greensock/TweenMax.min.js"></script>
<script src="/resources/plugins/greensock/TimelineMax.min.js"></script>
<script src="/resources/plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="/resources/plugins/greensock/animation.gsap.min.js"></script>
<script src="/resources/plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="/resources/plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="/resources/plugins/slick-1.8.0/slick.js"></script>
<script src="/resources/plugins/easing/easing.js"></script>
<script src="/resources/js/custom.js"></script>
</head>

<body>
    <div class="all">
    <div class="super_container">

        <!-- Header -->

        <header class="header">

            <!-- Top Bar -->

            <div class="top_bar">
                <div class="container">
                    <div class="row">
                        <div class="col d-flex flex-row">
                            <div class="top_bar_content ml-auto">
                                <div class="top_bar_user">
                                    <div class="user_icon">
                                        <img src="resources/images/user.svg" alt="">
                                    </div>
                                    <div class="Mail" sec:authorize="isAuthenticated()">
                                        <div>
                                            <a class="userPrincipal" href="user_editing" sec:authentication="name"></a>
                                        </div>
                                    </div>
                                    <div>
                                        <a href="index">  Home  </a>
                                    </div>
                                    <div class="Logout" sec:authorize="isAuthenticated()">
                                        <form th:action="@{/logout}" method="POST">
                                            <input type="submit" value="Logout">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Header Main -->

            <div class="header_main">
                <div class="container">
                    <div class="row">

                        <!-- Logo -->
                        <div class="col-lg-2 col-sm-3 col-3 order-1">
                            <div class="logo_container">
                                <div class="logo">
                                    <a href="index">Storefront</a>
                                </div>
                            </div>
                        </div>

                        <!-- Search -->
                        <div
                            class="col-lg-6 col-12 order-lg-2 order-3 text-lg-left text-right">
                            <div class="header_search">
                                <div class="header_search_content">
                                    <div class="header_search_form_container">
                                        <form action="#" class="header_search_form clearfix">
                                            <input type="search" required="required"
                                                class="header_search_input" placeholder="Search users..">
                                            <div class="custom_dropdown">
                                                <div class="custom_dropdown_list">
                                                    <span class="custom_dropdown_placeholder clc">All
                                                        Categories</span> <i class="fas fa-chevron-down"></i>
                                                    <ul class="custom_list clc">
                                                        <li><a class="clc" href="#">Name</a></li>
                                                        <li><a class="clc" href="#">ID</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <button type="submit" class="header_search_button trans_300"
                                                value="Submit">
                                                <img src="resources/images/search.png" alt="">
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- user table  -->
            <div class="user_table">
            <table >
                <tr>
                    <th>User id</th>
                    <th>User name</th>
                    <th>Mail</th>
                </tr>
                    <tr id="123" th:each="user : ${users}">
                        <td th:text="${user.id}"></td>
                        <td th:text = "${user.name}"></td>
                        <td th:text="${user.mail}"></td>
                        <td> 
                            <form th:action="@{delete{id}(id=${user.id})}" th:object="${user}" method="post">
                                <input type="submit" th:field="${user}" value="delete"/>
                            </form>
                        </td>
                        <td>
                            <form th:action="@{edit{id}(id=${user.id})}" th:object="${user}" method="get">
                                <input type="submit" th:field="${user}" value="edit"/>
                            </form>
                        </td>
                    </tr>
            </table>
            </div>
            <!-- user table end -->
                          
             <div class="free-space"></div>
            
               <!-- Copyright -->

            <div class="copyright">
                <div class="container">
                    <div class="row">
                        <div class="col">

                            <div
                                class="copyright_container d-flex flex-sm-row flex-column align-items-center justify-content-start">
                                <div class="copyright_content">
                                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                    Copyright &copy;
                                    <script>
                                        document
                                                .write(new Date().getFullYear());
                                    </script>
                                    All rights reserved | This template is made with <i
                                        class="fa fa-heart" aria-hidden="true"></i> by <a
                                        href="https://colorlib.com" target="_blank">Colorlib</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </div>
</div>
</body>
</html>
