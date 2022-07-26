<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
<!-- CSS,bootstrap links only -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="icons/bootstrap-icons.css">
<link rel="stylesheet" type="text/css" href="icons/boxicons.min.css">
<link rel="stylesheet" type="text/css" href="icons/remixicon.css">
<link href="img/company-logo.png" rel="icon">


</head>

<body>

	<!-- Calling sidebar to this page -->
	<jsp:include page="Navbar.jsp" />

	<main id="main" class="main">
		<div class="overlay"></div>

		<div class="pagetitle">
			<!-- <h1>Dashboard</h1> -->
			<nav>
				<ol class="breadcrumb"></ol>
			</nav>
		</div>

		<!-- drop down filter starts -->
		<div class="row">
			<div class="d-flex justify-content-center">

				<div class="m-2">
					<label for="inputDate" class="col-sm-0 ">By Project</label> 

					<select class="form-select " id="project-search" name="project-search"
							aria-label="Default select example " required>
							<option selected value="0" >All (Project Name)</option>							
						</select>
				</div>

				

				<div class="m-2">
					<label for="inputDate" class="col-sm-0 ">From</label> 
					<input type="date" class="form-control" name="fromDate" id="fromDate">
				</div>

				<div class="m-2">
					<label for="inputDate" class="col-sm-0">To</label> 
					<input type="date" class="form-control " name="toDate" id="toDate">
				</div>

				<div class="m-4">
					<button type="submit" class="btn btn-primary  m-2" id="btn-search" onClick="otFilter()">Search</button>
				</div>

			</div>

		</div>
		<!-- drop down filter ends -->


		<!--model for particular ot details view for employee or user-->
		<div class="modal fade" id="ExtralargeModal" tabindex="-1" data-bs-backdrop="static">
			<div class="modal-dialog modal-xl">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">OT List for <span id="model-id"></span></h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>

					<div class="modal-body">

						<table class="table table-hover" id="tbl1">
							<thead style="background-color: #e8ecef;">
								<tr>
									<!-- <th scope="col">S.No</th> -->
									<th scope="col">Date</th>
									<th scope="col">Action</th>
									<th scope="col">Unit</th>
									<th scope="col">Slots</th>
								</tr>
							</thead>
							<tbody>

							</tbody>

							<td>data</td>

							<!-- <tfoot>
								<tr>
									<td class="text-center font-weight-bold" ></td>
									<td id="model-approved-td" class="text-center"><span class='badge bg-success'>Total Approved Count : <span id="model-approved-count">0</span></i></span></td>
									tdas
								</tr>
							</tfoot> -->
							
						</table>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success btn-md"
							data-bs-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>


		<div class="row">

			<div class="card">
				<div class="card-body">

					<!-- Table with hoverable rows -->
					<table class="table table-hover" id="tbl">
						<thead class="thead-light">
							<tr>
                                <!-- <th scope="col">S.No</th> -->
                                    <th scope="col">Project Name</th>
                                    <!-- <th scope="col">Project Status</th> -->
                                   	<th scope="col">Saved</th>
                                    <th scope="col">Submitted</th>
                                    <th scope="col">Approved</th>
                                    <th scope="col">Rejected</th>
                                    <th scope="col">OT Count</th>

							</tr>
						</thead>
						<tbody id="emp_body">
						
						</tbody>
						<tfoot>
							<tr>
								<!-- <td class="text-center font-weight-bold" ></td> -->
								<td id="approved-td" class="text-center" colspan="6"><span class='badge bg-success' style="font-size: 14px;">Total Approved Count : <span id="approved-count">0</span></i></span></td>
							</tr>
						</tfoot>
					</table>
					<!-- End Table with hoverable rows -->
				</div>
			</div>
		</div>

		<div id="pager" style="float: right;">
                <ul id="pagination" class="pagination-md"></ul>
            </div>

			<a href="#"
			class="back-to-top d-flex align-items-center justify-content-center"><i
			class="bi bi-arrow-up-short">
		</i></a>

		<!-- <div class="overlay"></div> -->

	</main>
	<!-- End #main -->
<script type="text/javascript" src="js/jquery.twbsPagination.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
		<script type="text/javascript" src="js/UserDashboard.js"></script> 


	<!-- =======body Footer ======= -->
	
</body>

</html>