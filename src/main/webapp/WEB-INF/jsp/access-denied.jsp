<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
<!-- CSS,bootstrap links only -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="icons/bootstrap-icons.css">
<link rel="stylesheet" type="text/css" href="icons/boxicons.min.css">
<link rel="stylesheet" type="text/css" href="icons/remixicon.css">
<link href="img/company-logo.png" rel="icon">
 
</head>
<!-- <script
	src="ttps://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> -->

           
<body>
	
	<main>
		<div class="container">

			<section
				class="section error-404 min-vh-100 d-flex flex-column align-items-center justify-content-center">
				<h1>403</h1>
				<h2>Access Denied</h2>

				
				<a class="btn" href="${pageContext.request.contextPath }/logout">Back to home</a> 
				
				<img src="img/not-found.png" class="img-fluid py-5" alt="Page Not Found">
				<div class="credits">
					<a href="https://nipurnait.com//">&copy; 2022 Nipurna IT. All rights reserved.</a>
				</div>
			</section>

		</div>

		<div class="overlay"></div>
	</main>
	<!-- End #main -->

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

		
<!-- =======body Footer ======= -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="js/tinymce.min.js"></script>
	<script src="js/errorpage.js"></script>
	<script src="js/jquery.twbsPagination.min.js"></script>
	<script src="js/main.js"></script>

</body>
</html>