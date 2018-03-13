<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
<title>PhotoFlow</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript">
	addEventListener("load", function () {
		setTimeout(hideURLbar, 0);
	}, false);

	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>
<!-- //custom-theme -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="css/slider.css" type="text/css" media="screen" property="" />
<link href="css/style7.css" rel="stylesheet" type="text/css" media="all" />
<!-- Owl-carousel-CSS -->
<link href="css/owl.carousel.css" rel="stylesheet">

<link rel="stylesheet" href="css/team.css" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="css/sweet-alert.css" rel="stylesheet" type="text/css" media="all" />
<!-- font-awesome-icons -->
<link href="css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome-icons -->
<link href="http://fonts.googleapis.com/css?family=Montserrat:100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800"
	rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800" rel="stylesheet">
</head>

<body>
<!-- banner -->
<div class="banner_top" id="home">
	<div class="wrapper">
		<div class="agile_banner_text_info">
			<h1><img src="images/haha.png"><span>NanJing university Software Institu</span><a>PhotoFlow</a></h1>
			<a class="btn btn-group-sm btn-lg scroll" data-toggle="modal" data-target="#login" href="#" role="button">Login »</a>
			<a class="btn btn-group-sm btn-lg scroll" data-toggle="modal" data-target="#join" href="#" role="button">Join »</a>
		</div>
		<!-- login -->
		<div class="modal fade" id="login" tabindex="-1" role="dialog">
			<div class="modal-dialog" style="width: 350px">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<div class="modal-info">
							<h5>Welcome</h5>
							<div style="padding-top: 20px; height:  280px">
								<form method='POST' action='/Main'>
									<img src="images/user.png"> :
									<input type="text" id="userName" name="userName" class="input-lg" align="center" placeholder="用户名">
									<hr width="10px">
									<img src="images/lock.png" style="height: 18.99px; width: 15.99px"> :
									<input type="password" id="passWord" name="passWord" class="input-lg" placeholder="密码">
									<hr width="10px">
									<input type="submit" class="btn btn-8 btn-8b" id="loginBtn"></input>
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- register -->
		<div class="modal fade" id="join" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<div class="modal-info">
							<h5>Welcome</h5>
							<img src="images/banner77.jpeg"  style="height: 350px; padding-left: 10px">
							<div style="padding-top: 20px; height: 350px">
								<img src="images/user.png"> :
								<input type="text" id="register_username" class="input-lg" align="center" placeholder="用户名">
								<hr width="10px">
								<img src="images/lock.png" style="height: 18.99px; width: 15.99px"> :
								<input type="password" id="register_passwd" class="input-lg" placeholder="密码">
								<hr width="10px">
								<img src="images/lock.png" style="height: 18.99px; width: 15.99px"> :
								<input type="password" id="register_passwd2" class="input-lg" placeholder="再次输入密码">
								<hr width="10px">

								<button class="btn btn-8 btn-8b" id="registerBtn">Join</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="gallery">
			<div class="gallery__img-block">
				<span class="">
				 PHOTO FLOW
			</span>
				<img src="images/banner1.jpg" thumb-url="images/banner11.jpg" alt="" />
			</div>
			<div class="gallery__img-block  current">
				<span class="">
				PHOTO FLOW
			</span>
				<img src="images/banner2.jpg" thumb-url="images/banner22.jpg" alt="" />
			</div>
			<div class="gallery__img-block  ">
				<span class="">
			   PHOTO FLOW
			</span>
				<img src="images/banner3.jpg" thumb-url="images/banner33.jpg" alt="" />
			</div>
			<div class="gallery__img-block  ">
				<span class="">
				PHOTO FLOW
			</span>
				<img src="images/banner4.jpg" thumb-url="images/banner44.jpg" alt="" />
			</div>
			<div class="gallery__img-block  ">
				<span class="">
			   PHOTO FLOW
			</span>
				<img src="images/banner5.jpg" thumb-url="images/banner55.jpg" alt="">
			</div>
			<div class="gallery__img-block  ">
				<span class="">
			  PHOTO FLOW
			</span>
				<img src="images/banner6.jpg" thumb-url="images/banner66.jpg" alt="" />
			</div>
			<div class="gallery__img-block  ">
				<span class="">
				 PHOTO FLOW
			</span>
				<img src="images/banner1.jpg" thumb-url="images/banner11.jpg" alt="" />
			</div>
			<div class="gallery__img-block  current">
				<span class="">
				PHOTO FLOW
			</span>
				<img src="images/banner2.jpg" thumb-url="images/banner22.jpg" alt="" />
			</div>
			<div class="gallery__img-block  ">
				<span class="">
			   PHOTO FLOW
			</span>
				<img src="images/banner3.jpg" thumb-url="images/banner33.jpg" alt="" />
			</div>
			<div class="gallery__img-block  ">
				<span class="">
				PHOTO FLOW
			</span>
				<img src="images/banner4.jpg" thumb-url="images/banner44.jpg" alt="" />
			</div>
			<div class="gallery__img-block  ">
				<span class="">
			   PHOTO FLOW
			</span>
				<img src="images/banner5.jpg" thumb-url="images/banner55.jpg" alt="" />
			</div>
			<div class="gallery__img-block  ">
				<span class="">
			  PHOTO FLOW
			</span>
				<img src="images/banner6.jpg" thumb-url="images/banner66.jpg" alt="" />
			</div>
			<div class="gallery__controls">

			</div>
		</div>
	</div>

</div>
<!-- //banner -->
<!-- /about -->

<!--// contact -->
<div class="w3l_footer">
	<div class="container">
		<div class="col-md-4">
			<h2><a href="index.jsp">PhotoHub</a></h2>
		</div>

		<p class="agileits_w3ls_copyright">Copyright &copy; 2017.NJU All rights reserved.</p>

	</div>
</div>
<!--//footer -->



<!-- js -->
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<!-- //js -->
<!-- Slider script -->
<script src="js/slider.js"></script>

<!-- Kickoff the slider -->
<script>
	$(document).ready(function () {
		var $gallery = $('.gallery');

		$gallery.vitGallery({
			autoplay: true
		})
	})
</script>
<!-- /nav -->
<script src="js/modernizr-2.6.2.min.js"></script>
<script src="js/classie.js"></script>
<script src="js/demo1.js"></script>
<!-- //nav -->
<!-- js for portfolio lightbox -->
<script src="js/jquery.chocolat.js "></script>
<link rel="stylesheet " href="css/chocolat.css " type="text/css" media="all" />
<!--light-box-files -->
<script type="text/javascript ">
	$(function () {
		$('.portfolio-grids a').Chocolat();
	});
</script>
<!-- /js for portfolio lightbox -->
<!-- stats -->
<script src="js/jquery.waypoints.min.js"></script>
<script src="js/jquery.countup.js"></script>
<script>
	$('.counter').countUp();
</script>
<!-- //stats -->
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function ($) {
		$(".scroll").click(function (event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop: $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<!-- start-smoth-scrolling -->
<!-- requried-jsfiles-for owl -->
<script src="js/owl.carousel.js"></script>
<script>
	$(document).ready(function () {
		$("#owl-demo2").owlCarousel({
			items: 1,
			lazyLoad: false,
			autoPlay: true,
			navigation: false,
			navigationText: false,
			pagination: true
		});
	});
</script>
<!-- //requried-jsfiles-for owl -->

<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
<script type="text/javascript" src="js/sweet-alert.js"></script>
<script type="text/javascript" src="feature_js/login.js"></script>
<script type="text/javascript" src="feature_js/register.js"></script>

</body>
</html>