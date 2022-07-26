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
<!-- <script type="text/css" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> -->
<body>

    <!-- Calling sidebar to this page -->
    <jsp:include page="Navbar.jsp"/>
    
        <main id="main" class="main">
			<div class="overlay"></div>
             <div class="row">
                <div class="row">
                   
                   <!-- <div class="pagetitle" style="text-align: center;">
                        <h1>Welcome Admin </h1>
                    </div> -->
             
             <div class="pagetitle">
					<!-- <h1>Dashboard</h1> -->
					<nav>
						<ol class="breadcrumb"></ol>
					</nav>
				</div>
                </div>
            </div>

		

		<!-- drop down filter starts -->
		<div class="row">
			<div class="d-flex justify-content-center">

				 <div class="m-2">
					<label for="inputDate" class="col-sm-0 ">By Project</label> <select
						class="form-select " id="project-search" name="project-search"
						aria-label="Default select example " required>
						<option selected value="0">All (Project's Name)</option>
					</select>
				</div> 
				
				<div class="m-2">
					<label for="inputDate" class="col-sm-0 ">By Employee</label>
					 <select
						class="form-select " id="employee-search" name="employee-search"
						aria-label="Default select example " required>
						<option selected value="0" >All (Employee's Name)</option>
					</select>
				</div>

				

				<!-- <div class="m-2">
					<label for="inputDate" class="col-sm-0 ">Select Status</label> <select
						class="form-select " id="project-status" name="project-status"
						aria-label="Default select example " required>
						<option selected value="0">All (Project Status)</option>
						<option value="1">Active</option>
						<option value="2">In-Active</option>
					</select>
				</div> -->

				<div class="m-2">
					<label for="inputDate" class="col-sm-0 ">From</label> <input
						type="date" class="form-control" name="fromDate" id="fromDate">
				</div>

				<div class="m-2">
					<label for="inputDate" class="col-sm-0">To</label> <input
						type="date" class="form-control " name="toDate" id="toDate">
				</div>

				<div class="m-4">
					<button type="submit" class="btn btn-primary  m-2" id="btn-search"
						onClick="otFilter()">Search</button>
				</div>
			</div>

		</div>
		<!-- drop down filter ends -->


         <!--model for particular ot details view for employee or user-->
		<div class="modal fade" id="ExtralargeModal" tabindex="-1" data-bs-backdrop="static">
			<div class="modal-dialog modal-xl">
				<div class="modal-content">
					<div class="modal-header">
						<h6 class="modal-title">OT List of <span style="font-weight: bold;" id="emp-name"></span><span id="prj-name"></span><span style="font-weight: bold;" id="pro-name"></span></h6>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>

					<div class="modal-body">

						<table class="table table-hover" id="tbl1">
							<thead>
								<tr>
									<!-- <th scope="col">S.No</th> -->
									<th scope="col">OT-Date</th>
									<th scope="col" id="projectName" >Project</th>
									<th scope="col">Action</th>
									<th scope="col">Description</th>
									<th scope="col">Count</th>
									<th scope="col">Slot</th>
								</tr>
							</thead>
							<tbody >
							</tbody>
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
						<thead>
							<tr>
                                <!-- <th scope="col">S.No</th> -->
                                    
                                    <th scope="col">Employee Name</th>
                                    <th scope="col">Saved</th>
                                    <th scope="col">Submitted</th>
                                    <th scope="col">Approved</th>
                                    <th scope="col">Rejected</th>
                                    <th scope="col">OT Count</th>
                                    <!-- <th scope="col">Project Status</th> -->
							</tr>
						</thead>
						<tbody id="emp_body">
						
						</tbody>
						
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
		
		

	</main>
	<!-- End #main 
     <script src="js/AdminDashboard.js"></script>
	<script  src="js/jquery.twbsPagination.min.js"></script>
	<script src="js/main.js"></script>-->
	<!-- =======body Footer ======= -->
	
	 <script src="js/jquery.twbsPagination.min.js"></script>
    <script src="js/main.js"></script>
     <script src="js/AdminDashboard.js"></script>
	

</body>
</html>