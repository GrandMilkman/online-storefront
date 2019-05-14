<!--%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->

<html lang="en" xmlns="http://www.w3.org/1999/xhtml" 
    xmlns:th="http://www.thymeleaf.org"
    xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">

<head>

<title>Online storefront</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="OneTech shop project">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link th:href="@{/resources/styles/bootstrap4/bootstrap.min.css}" rel="stylesheet">
<link th:href="@{/resources/plugins/fontawesome-free-5.0.1/css/fontawesome-all.css}" rel="stylesheet">
<link th:href="@{/resources/plugins/OwlCarousel2-2.2.1/owl.carousel.css}" rel="stylesheet">
<link th:href="@{/resources/plugins/OwlCarousel2-2.2.1/owl.theme.default.css}" rel="stylesheet">
<link th:href="@{/resources/plugins/OwlCarousel2-2.2.1/animate.css}" rel="stylesheet">
<link th:href="@{/resources/plugins/slick-1.8.0/slick.css}" rel="stylesheet">
<link th:href="@{/resources/styles/main_styles.css}" rel="stylesheet">
<link th:href="@{/resources/styles/responsive.css}" rel="stylesheet">
<link th:href="@{/resources/styles/index.css}" rel="stylesheet">
<link th:href="@{/resources/styles/edit.css}" rel="stylesheet">

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
                                <div class="User_list" sec:authorize="hasRole('ROLE_ADMIN')">
                                    <div class="Userlist">
                                        <a href="user_list">User list</a>
                                    </div>
                                </div>
                                <div class="Home">
                                    <a href="index">Home</a>
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
                                    <div class="logo"><a href="index">Storefront</a></div>
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

                <form id="regForm" th:modelAttribute="userJSP" th:action="@{editUserProcess}"
                    method="post" th:object="${userJSP}">
                    <div class="login-table">
                        <table>
                            <div class="ID">
                                <input name="id" id="id" type="hidden" 
                                    th:value="${userJSP} ? ${userJSP.id}" />
                            </div>
                            <div class="Mail-Validation">
                                <th:errors path="mail" cssClass="error" />
                            </div>
                            <div class="Mail">
                                <label for="mail">Mail</label>
                                <input type="text" name="mail" id="mail" 
                                    th:value="${userJSP} ? ${userJSP.mail}" />
                            </div>

                            <div class="Username-Validation">
                                <errors path="name" cssClass="error" />
                            </div>

                            <div class="Username">
                                <label path="name">Your Name</label>
                                <input type="text" name="name" id="name" 
                                    th:value="${userJSP} ? ${userJSP.name}" />
                            </div>

                            <div class="Password-Validation">
                                <th:errors path="password" cssClass="error" />
                            </div>

                            <div class="Password">
                                <label path="password">Password</label>
                                <input type="password" name="password" id="password" />
                            </div>

                            <div class="Register">
                                <input type="submit" value="Edit">
                            </div>

                            </table>
                        </div>
                    </form>

        </header>


<!-- Copyright -->

    <div class="copyright">
        <div class="container">
            <div class="row">
                <div class="col">

                    <div class="copyright_container d-flex flex-sm-row flex-column align-items-center justify-content-start">
                        <div class="copyright_content"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
</div>
                        <div class="logos ml-sm-auto">
                            <ul class="logos_list">
                                <li><a href="#"><img src="resources/images/logos_1.png" alt=""></a></li>
                                <li><a href="#"><img src="resources/images/logos_2.png" alt=""></a></li>
                                <li><a href="#"><img src="resources/images/logos_3.png" alt=""></a></li>
                                <li><a href="#"><img src="resources/images/logos_4.png" alt=""></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>

</html>
