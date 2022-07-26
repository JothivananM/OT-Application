
<head>
	<!-- CSS,bootstrap links only -->
	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="icons/bootstrap-icons.css">
	<link rel="stylesheet" type="text/css" href="icons/boxicons.min.css">
	<link rel="stylesheet" type="text/css" href="icons/remixicon.css">
	<link href="img/company-logo.png" rel="icon">
	</head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	
			   
	<body>
	
		<main>
			<div class="overlay"></div>
			<div class="container">
	
				<section
					class="section error-404 min-vh-100 d-flex flex-column align-items-center justify-content-center">
					<h1>${status}</h1>
					<h2></h2>
					<a class="btn" href="${pageContext.request.contextPath }/login"><i class="bi bi-arrow-bar-left"> Back to home</i></a> <img	src="img/not-found.png" class="img-fluid py-5"
						alt="Page Not Found">
					<div class="credits">
						<!-- All the links in the footer should remain intact. -->
						<!-- You can delete the links only if you purchased the pro version. -->
						<!-- Licensing information: https://bootstrapmade.com/license/ -->
						<!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
						<a href="https://nipurnait.com//">&copy; 2022 Nipurna IT. All rights reserved.</a>
					</div>
				</section>
	
			</div>
			<div class="overlay"></div>
		</main>
		<!-- End #main -->
	
		<a href="#"
			class="back-to-top d-flex align-items-center justify-content-center"><i	class="bi bi-arrow-up-short"></i></a>
	
		<!-- =======body Footer ======= -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.bundle.min.js"></script>
		<script src="js/tinymce.min.js"></script
		<script src="js/toastr.min.js"></script>
		<script src="js/jquery.twbsPagination.min.js"></script>
		<script src="js/main.js"></script>	

		
		
		
		<script>

		var a=${status};
		if(a==404)
			{
			$("h2").html("Not Found!. The requested resource was not found.");
			}
		else if(a==400)
			{
			$("h2").html("Bad Request!. The request was invalid.");
			}
		else if(a==500)
			{
			$("h2").html("Internal Server Error!.The request was not completed due to an internal error on the server side.");
			}
		else{
			$("h2").html("Not Allowed!");
		}
		
		
		
		</script>
	
	</body>
	</html>