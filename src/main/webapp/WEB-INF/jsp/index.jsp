<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>

<title>Online storefront</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="OneTech shop project">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="<c:url value="/resources/styles/bootstrap4/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/plugins/fontawesome-free-5.0.1/css/fontawesome-all.css" />" rel="stylesheet">
<link href="<c:url value="/resources/plugins/OwlCarousel2-2.2.1/owl.carousel.css" />" rel="stylesheet">
<link href="<c:url value="/resources/plugins/OwlCarousel2-2.2.1/owl.theme.default.css" />" rel="stylesheet">
<link href="<c:url value="/resources/plugins/OwlCarousel2-2.2.1/animate.css" />" rel="stylesheet">
<link href="<c:url value="/resources/plugins/slick-1.8.0/slick.css" />" rel="stylesheet">
<link href="<c:url value="/resources/styles/main_styles.css" />" rel="stylesheet">
<link href="<c:url value="/resources/styles/responsive.css" />" rel="stylesheet">
<link href="<c:url value="/resources/styles/index.css" />" rel="stylesheet">

<script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
<script src="<c:url value="/resources/styles/bootstrap4/popper.js"/>"></script>
<script src="<c:url value="/resources/styles/bootstrap4/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/plugins/greensock/TweenMax.min.js"/>"></script>
<script src="<c:url value="/resources/plugins/greensock/TimelineMax.min.js"/>"></script>
<script src="<c:url value="/resources/plugins/scrollmagic/ScrollMagic.min.js"/>"></script>
<script src="<c:url value="/resources/plugins/greensock/animation.gsap.min.js"/>"></script>
<script src="<c:url value="/resources/plugins/greensock/ScrollToPlugin.min.js"/>"></script>
<script src="<c:url value="/resources/plugins/OwlCarousel2-2.2.1/owl.carousel.js"/>"></script>
<script src="<c:url value="/resources/plugins/slick-1.8.0/slick.js"/>"></script>
<script src="<c:url value="/resources/plugins/easing/easing.js"/>"></script>
<script src="<c:url value="/resources/js/custom.js"/>"></script>

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
								<div class="Mail">
										<c:if test="${pageContext.request.userPrincipal != null}">
											<div><a class="userPrincipal" href="user_editing" >${pageContext.request.userPrincipal.name}
												</a>
											</div>
										</c:if>
								</div>
								<div class="Register">
									<a href="sign_up">Register</a>
								</div>

								<div class="Login">
									<c:if test="${pageContext.request.userPrincipal == null}">
										<div class="log">
											<a href="login">Sign UP </a>
										</div>
									</c:if>
								</div>

								<div class="User_list">
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<div class="Userlist">
											<c:if test="${pageContext.request.userPrincipal != null}">
												<a href="user_list">User list</a>
											</c:if>
										</div>
									</sec:authorize>
								</div>
								 <div class="Logout">
									 <c:if test="${pageContext.request.userPrincipal != null}">
										 <a href="login?logout" >Logout</a>
									</c:if>
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
											<div class="cart_icon" >
												<img src="resources/images/cart.png" alt="" >
												<div class="cart_count"><span>10</span></div>
											</div>
											<div class="cart_content">
												<div class="cart_text"><a href="cart">Cart</a></div>
												<div class="cart_price">$85</div>
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

	<!-- Banner -->

	<div class="banner">
		<div class="banner_background" style="background-image:url(resources/images/banner_background.jpg)"></div>
		<div class="container fill_height">
			<div class="row fill_height">
				<div class="banner_product_image"><img src="resources/images/banner_product.png" alt=""></div>
				<div class="col-lg-5 offset-lg-4 fill_height">
					<div class="banner_content">
						<h1 class="banner_text">new era of smartphones</h1>
						<div class="banner_price"><span>$530</span>$460</div>
						<div class="banner_product_name">Apple Iphone 6s</div>
						<div class="button banner_button"><a href="#">Shop Now</a></div>
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
