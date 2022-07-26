<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<head>

<!-- CSS,bootstrap links only -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="icons/bootstrap-icons.css">
<link rel="stylesheet" type="text/css" href="icons/boxicons.min.css">
<link rel="stylesheet" type="text/css" href="icons/remixicon.css">
<link rel="stylesheet" type="text/css" href="css/toastr.min.css">

<link href="img/company-logo.png" rel="icon">

    <script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="js/tinymce.min.js"></script>
	<script src="js/toastr.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>

   
</head>

<style>
	/* #error{
		font-size: large;
		color: red;

	} */
</style>

<body>
	
	<main>
		<div class="overlay"></div>
		<div class="container">
			<section	class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

							<!-- End Logo -->

							<div class="card p-3">

								<div class="card-body" id="card-body">

									<!-- header and img div-->
									<div class="p-4">
										<a href="#"	class="logo d-flex align-items-center"> 
											<img type="text/javascript" src="img/company-name.png" alt="" style="margin-left: 23%;" width="50%" >
										</a><hr>
										<h5 class="card-title text-center pb-0 fs-4">Login to your Account</h5>
									</div>

									<!-- log-in form starts-->
									<span id="error">${error}</span>

									<form class="row g-3 needs-validation" novalidate method="post"  name="form" 
																action="${pageContext.request.contextPath }/dashboard">

										<!-- username tag -->
										<div class="col-12 p-2">

											<label for="yourUsername" class="form-label" style="margin-left: 0% !important;">Username <span id="star">*</span></label>

											<div class="input-group has-validation">
												<input type="email" name="username" class="form-control"
													id="txt-Username" placeholder="abc@gmail.com" required>

												<span class="input-group-text" id="inputGroupPrepend">
													<i class="ri-account-pin-circle-fill" id="icons"></i>
												</span>
												<div class="invalid-feedback">Please enter valid Company Mail-Id</div>

											</div>
										</div>

										<!-- password tag -->
										<div class="col-12 p-2">

											<label for="yourPassword" class="form-label">Password <span id="star">*</span></label>

											<div class="input-group has-validation">

												<input type="password"name="password" class="form-control"
													id="txt-yourPassword" minlength="8" maxlength="15"
													placeholder="Enter your Password" required>

													 <span	class="input-group-text" id="inputGroupPrepend"> <i class="ri-key-fill" id="icons"></i></span>

												<div class="invalid-feedback">Please enter your	Password</div>
											</div>
										</div>

										
										<div class="col-12">
										
											<input type="submit" value="Login" class="btn btn-primary" id="login-btn">
										</div>

									</form>
									<!-- log-in form ends-->

								</div>
							</div>

							<div class="credits">
								<!--<a href="#">&copy; 2022 Nipurna IT. All
									rights reserved.</a>  -->
									<a href="#">&copy;<span id="copyright"> <script>$('#copyright').text(new Date().getFullYear());</script> </span> Nipurna IT. All
									rights reserved.</a>
							</div>

						</div>
					</div>
				</div>

			</section>

		</div>
		<!-- <div class="overlay"></div> -->
	</main>
	<!-- End #main -->

	<!-- =======body Footer ======= -->

	<script>

		$( document ).ready(function() {

			let a = $("#error").text();

			(a)
			
			if(a !=""){
				toastr.error('Invalid credentials','Error!');
			}
		});

	</script>
	
</body>

</html>