<!--%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%-->

<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
    xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">

<head>

<title>Shop</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="OneTech shop project">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link th:href="@{/resources/styles/bootstrap4/bootstrap.min.css}" rel="stylesheet">
<link th:href="@{/resources/plugins/fontawesome-free-5.0.1/css/fontawesome-all.css}" rel="stylesheet">
<link th:href="@{/resources/plugins/OwlCarousel2-2.2.1/owl.carousel.css}" rel="stylesheet">
<link th:href="@{/resources/plugins/OwlCarousel2-2.2.1/owl.theme.default.css}" rel="stylesheet">
<link th:href="@{/resources/plugins/OwlCarousel2-2.2.1/animate.css}" rel="stylesheet">
<link th:href="@{/resources/plugins/jquery-ui-1.12.1.custom/jquery-ui.css}" rel="stylesheet">
<link th:href="@{/resources/styles/shop_styles.css}" rel="stylesheet">
<link th:href="@{/resources/styles/shop.css}" rel="stylesheet">
<link th:href="@{/resources/styles/shop_responsive.css}" rel="stylesheet">

<script src="/resources/js/jquery-3.3.1.min.js"></script>
<script src="/resources/styles/bootstrap4/popper.js"></script>
<script src="/resources/styles/bootstrap4/bootstrap.min.js"></script>
<script src="/resources/plugins/greensock/TweenMax.min.js"></script>
<script src="/resources/plugins/greensock/TimelineMax.min.js"></script>
<script src="/resources/plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="/resources/plugins/greensock/animation.gsap.min.js"></script>
<script src="/resources/plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="/resources/plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="/resources/plugins/easing/easing.js"></script>
<script src="/resources/plugins/Isotope/isotope.pkgd.min.js"></script>
<script src="/resources/plugins/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script src="/resources/plugins/parallax-js-master/parallax.min.js"></script>
<script src="/resources/js/shop_custom.js"></script>

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
                                <div class="user_icon"><img src="resources/images/user.svg" alt=""></div>
                                <div class="Mail" sec:authorize="isAuthenticated()">
                                    <div>
                                        <a class="userPrincipal" href="user_editing" sec:authentication="name"></a>
                                    </div>
                                </div>
                                <div class="Register" sec:authorize="!isAuthenticated()">
                                    <a href="sign_up">  Sign Up  </a>
                                </div>
                                <div class="Login" sec:authorize="!isAuthenticated()">
                                    <div class="log">
                                        <a href="login">  Log In  </a>
                                    </div>
                                </div>
                                <div class="User_list" sec:authorize="hasRole('ROLE_ADMIN')">
                                    <div class="Userlist">
                                        <a href="user_list">  User list  </a>
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
                            <div class="logo"><a href="index">Storefront</a></div>
                        </div>
                    </div>

                    <!-- Search -->
                    <div class="col-lg-6 col-12 order-lg-2 order-3 text-lg-left text-right">
                        <div class="header_search">
                            <div class="header_search_content">
                                <div class="header_search_form_container">
                                    <form action="#" class="header_search_form clearfix">
                                        <input type="search" required="required" class="header_search_input" placeholder="Search for products...">
                                        <div class="custom_dropdown">
                                            <div class="custom_dropdown_list">
                                                <span class="custom_dropdown_placeholder clc">All Categories</span>
                                                <i class="fas fa-chevron-down"></i>
                                                <ul class="custom_list clc">
                                                    <li><a class="clc" href="#">All Categories</a></li>
                                                    <li><a class="clc" href="#">Computers</a></li>
                                                    <li><a class="clc" href="#">Laptops</a></li>
                                                    <li><a class="clc" href="#">Cameras</a></li>
                                                    <li><a class="clc" href="#">Hardware</a></li>
                                                    <li><a class="clc" href="#">Smartphones</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <button type="submit" class="header_search_button trans_300" value="Submit"><img src="resources/images/search.png" alt=""></button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Wishlist -->
                    <div class="col-lg-4 col-9 order-lg-3 order-2 text-lg-left text-right">
                        <div class="wishlist_cart d-flex flex-row align-items-center justify-content-end">

                            <!-- Cart -->
                            <div class="cart">
                                <div class="cart_container d-flex flex-row align-items-center justify-content-end">
                                    <div class="cart_icon">
                                        <img src="resources/images/cart.png" alt="">
                                    </div>
                                    <div class="cart_content">
                                        <div class="cart_text"><a href="/cart">Cart</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Main Navigation -->

        <nav class="main_nav">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="main_nav_content d-flex flex-row">

                            <!-- Categories Menu -->

                            <div class="cat_menu_container">
                                <div class="cat_menu_title d-flex flex-row align-items-center justify-content-start">
                                    <div class="cat_burger"><span></span><span></span><span></span></div>
                                    <div class="cat_menu_text">categories</div>
                                </div>

                                <ul class="cat_menu">
                                    <li><a href="#">Computers & Laptops <i class="fas fa-chevron-right ml-auto"></i></a></li>
                                    <li><a href="#">Cameras & Photos<i class="fas fa-chevron-right"></i></a></li>
                                    <li class="hassubs">
                                        <a href="#">Hardware<i class="fas fa-chevron-right"></i></a>
                                        <ul>
                                            <li class="hassubs">
                                                <a href="#">Menu Item<i class="fas fa-chevron-right"></i></a>
                                                <ul>
                                                    <li><a href="#">Menu Item<i class="fas fa-chevron-right"></i></a></li>
                                                    <li><a href="#">Menu Item<i class="fas fa-chevron-right"></i></a></li>
                                                    <li><a href="#">Menu Item<i class="fas fa-chevron-right"></i></a></li>
                                                    <li><a href="#">Menu Item<i class="fas fa-chevron-right"></i></a></li>
                                                </ul>
                                            </li>
                                            <li><a href="#">Menu Item<i class="fas fa-chevron-right"></i></a></li>
                                            <li><a href="#">Menu Item<i class="fas fa-chevron-right"></i></a></li>
                                            <li><a href="#">Menu Item<i class="fas fa-chevron-right"></i></a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#">Smartphones & Tablets<i class="fas fa-chevron-right"></i></a></li>
                                    <li><a href="#">TV & Audio<i class="fas fa-chevron-right"></i></a></li>
                                    <li><a href="#">Gadgets<i class="fas fa-chevron-right"></i></a></li>
                                    <li><a href="#">Car Electronics<i class="fas fa-chevron-right"></i></a></li>
                                    <li><a href="#">Video Games & Consoles<i class="fas fa-chevron-right"></i></a></li>
                                    <li><a href="#">Accessories<i class="fas fa-chevron-right"></i></a></li>
                                </ul>
                            </div>

                            <!-- Main Nav Menu -->

                            <div class="main_nav_menu ml-auto">
                                <ul class="standard_dropdown main_nav_dropdown">
                                    <li><a href="index">Home<i class="fas fa-chevron-down"></i></a></li>
                                    <li><a href="shop">Catalog<i class="fas fa-chevron-down"></i></a></li>
                                    <li class="hassubs">
                                        <a href="#">Super Deals<i class="fas fa-chevron-down"></i></a>
                                        <ul>
                                            <li>
                                                <a href="#">Menu Item<i class="fas fa-chevron-down"></i></a>
                                                <ul>
                                                    <li><a href="#">Menu Item<i class="fas fa-chevron-down"></i></a></li>
                                                    <li><a href="#">Menu Item<i class="fas fa-chevron-down"></i></a></li>
                                                    <li><a href="#">Menu Item<i class="fas fa-chevron-down"></i></a></li>
                                                </ul>
                                            </li>
                                            <li><a href="#">Menu Item<i class="fas fa-chevron-down"></i></a></li>
                                            <li><a href="#">Menu Item<i class="fas fa-chevron-down"></i></a></li>
                                            <li><a href="#">Menu Item<i class="fas fa-chevron-down"></i></a></li>
                                        </ul>
                                    </li>
                                    <li class="hassubs">
                                        <a href="#">Featured Brands<i class="fas fa-chevron-down"></i></a>
                                        <ul>
                                            <li>
                                                <a href="#">Menu Item<i class="fas fa-chevron-down"></i></a>
                                                <ul>
                                                    <li><a href="#">Menu Item<i class="fas fa-chevron-down"></i></a></li>
                                                    <li><a href="#">Menu Item<i class="fas fa-chevron-down"></i></a></li>
                                                    <li><a href="#">Menu Item<i class="fas fa-chevron-down"></i></a></li>
                                                </ul>
                                            </li>
                                            <li><a href="#">Menu Item<i class="fas fa-chevron-down"></i></a></li>
                                            <li><a href="#">Menu Item<i class="fas fa-chevron-down"></i></a></li>
                                            <li><a href="#">Menu Item<i class="fas fa-chevron-down"></i></a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>

                            <!-- Menu Trigger -->

                            <div class="menu_trigger_container ml-auto">
                                <div class="menu_trigger d-flex flex-row align-items-center justify-content-end">
                                    <div class="menu_burger">
                                        <div class="menu_trigger_text">menu</div>
                                        <div class="cat_burger menu_burger_inner"><span></span><span></span><span></span></div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </nav>
        <!-- Menu -->

        <div class="page_menu">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="page_menu_content">
                            <div class="page_menu_search">
                                <form action="#">
                                    <input type="search" required="required" class="page_menu_search_input" placeholder="Search for products...">
                                </form>
                            </div>
                            <ul class="page_menu_nav">
                                <li class="page_menu_item">
                                    <a href="index">Home<i class="fa fa-angle-down"></i></a>
                                </li>
                                <li class="page_menu_item">
                                    <a href="shop">Catalog<i class="fa fa-angle-down"></i></a>
                                </li>
                                <li class="page_menu_item has-children">
                                    <a href="#">Super Deals<i class="fa fa-angle-down"></i></a>
                                    <ul class="page_menu_selection">
                                        <li><a href="#">Super Deals<i class="fa fa-angle-down"></i></a></li>
                                        <li class="page_menu_item has-children">
                                            <a href="#">Menu Item<i class="fa fa-angle-down"></i></a>
                                            <ul class="page_menu_selection">
                                                <li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
                                                <li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
                                                <li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
                                                <li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
                                            </ul>
                                        </li>
                                        <li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
                                        <li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
                                        <li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
                                    </ul>
                                </li>
                                <li class="page_menu_item has-children">
                                    <a href="#">Featured Brands<i class="fa fa-angle-down"></i></a>
                                    <ul class="page_menu_selection">
                                        <li><a href="#">Featured Brands<i class="fa fa-angle-down"></i></a></li>
                                        <li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
                                        <li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
                                        <li><a href="#">Menu Item<i class="fa fa-angle-down"></i></a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </header>
    <!-- Home -->

    <div class="home">
        <div class="home_background parallax-window" data-parallax="scroll" data-image-src="resources/images/shop_background.jpg"></div>
        <div class="home_overlay"></div>
        <div class="home_content d-flex flex-column align-items-center justify-content-center">
            <h2 class="home_title">Smartphones & Tablets</h2>
        </div>
    </div>

    <!-- Shop -->

    <div class="shop">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">

                    <!-- Shop Sidebar -->
                    <div class="shop_sidebar">
                        <div class="sidebar_section">
                            <div class="sidebar_title">Categories</div>
                            <ul class="sidebar_categories">
                                <li><a href="#">Computers & Laptops</a></li>
                                <li><a href="#">Cameras & Photos</a></li>
                                <li><a href="#">Hardware</a></li>
                                <li><a href="#">Smartphones & Tablets</a></li>
                                <li><a href="#">TV & Audio</a></li>
                                <li><a href="#">Gadgets</a></li>
                                <li><a href="#">Car Electronics</a></li>
                                <li><a href="#">Video Games & Consoles</a></li>
                                <li><a href="#">Accessories</a></li>
                            </ul>
                        </div>
                        <div class="sidebar_section filter_by_section">
                            <div class="sidebar_title">Filter By</div>
                            <div class="sidebar_subtitle">Price</div>
                            <div class="filter_price">
                                <div id="slider-range" class="slider_range"></div>
                                <p>Range: </p>
                                <p><input type="text" id="amount" class="amount" readonly style="border:0; font-weight:bold;"></p>
                            </div>
                        </div>
                        <div class="sidebar_section">
                            <div class="sidebar_subtitle color_subtitle">Color</div>
                            <ul class="colors_list">
                                <li class="color"><a href="#" style="background: #b19c83;"></a></li>
                                <li class="color"><a href="#" style="background: #000000;"></a></li>
                                <li class="color"><a href="#" style="background: #999999;"></a></li>
                                <li class="color"><a href="#" style="background: #0e8ce4;"></a></li>
                                <li class="color"><a href="#" style="background: #df3b3b;"></a></li>
                                <li class="color"><a href="#" style="background: #ffffff; border: solid 1px #e1e1e1;"></a></li>
                            </ul>
                        </div>
                        <div class="sidebar_section">
                            <div class="sidebar_subtitle brands_subtitle">Brands</div>
                            <ul class="brands_list">
                                <li class="brand"><a href="#">Apple</a></li>
                                <li class="brand"><a href="#">Beoplay</a></li>
                                <li class="brand"><a href="#">Google</a></li>
                                <li class="brand"><a href="#">Meizu</a></li>
                                <li class="brand"><a href="#">OnePlus</a></li>
                                <li class="brand"><a href="#">Samsung</a></li>
                                <li class="brand"><a href="#">Sony</a></li>
                                <li class="brand"><a href="#">Xiaomi</a></li>
                            </ul>
                        </div>
                    </div>

                </div>

                <div class="col-lg-9">
                    <!-- Shop Content -->

                    <div class="shop_content">
                        <div class="shop_bar clearfix">
                            <div class="shop_product_count"><span>186</span> products found</div>
                            <div class="shop_sorting">
                                <span>Sort by:</span>
                                <ul>
                                    <li>
                                        <span class="sorting_text">highest rated<i class="fas fa-chevron-down"></span></i>
                                        <ul>
                                            <li class="shop_sorting_button" data-isotope-option='{ "sortBy": "original-order" }'>highest rated</li>
                                            <li class="shop_sorting_button" data-isotope-option='{ "sortBy": "name" }'>name</li>
                                            <li class="shop_sorting_button"data-isotope-option='{ "sortBy": "price" }'>price</li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="product_grid">
                            <div class="product_grid_border"></div>

                            <!-- ware table-->
                            <div class="ware_table">
                                <table >
                                    <tr>
                                        <th>id</th>
                                        <th>name</th>
                                        <th>price</th>
                                    </tr>
                                        <tr id="123" th:each="ware : ${wares}">
                                            <td th:text="${ware.id}" />
                                            <td th:text="${ware.name}" />
                                            <td th:text="${ware.price}" />
                                            <td><a th:href="@{addToCart{wareId}(wareId=${ware.id})}"> Add To Cart</a></td>
                                        </tr>
                                </table>
                            </div>
                            <!-- ware table end-->

                        <div>
                        <!-- Shop Page Navigation -->

                        <div class="shop_page_nav d-flex flex-row">
                            <div class="page_prev d-flex flex-column align-items-center justify-content-center"><i class="fas fa-chevron-left"></i></div>
                            <ul class="page_nav d-flex flex-row">
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">...</a></li>
                                <li><a href="#">21</a></li>
                            </ul>
                            <div class="page_next d-flex flex-column align-items-center justify-content-center"><i class="fas fa-chevron-right"></i></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

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
