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
<!-- <script
	type="text/javascript" src="ttps://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> -->

<body>
    <!-- Calling sidebar to this page -->
    <jsp:include page="Navbar.jsp" />
    <main id="main" class="main">
        <div class="overlay"></div>
        <div class="pagetitle">
            <!-- <h1>User Creation</h1> -->
        </div>
        <!-- End Page Title -->
        <!-- pop up to create a user start -->

        <div class="d-flex justify-content-end">
            <div class="dropdown m-4">
                <button type="button" class="btn btn-success" data-bs-toggle="modal" id="btn-addUser" onclick="cancelReset()" data-bs-target="#largeModal">
					<i class="bi bi-plus-lg p-1"></i>User</i>
				</button>
                <div class="modal fade" id="largeModal" tabindex="-1" data-bs-backdrop="static">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="model-name">Create User</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="cancelReset()"></button>
                            </div>
                            <div class="modal-body">
                                <form class="row g-0 needs-validation" novalidate onsubmit="return false">

                                    <input type="hidden" id="id">
                                    <input type="hidden" id="table-id">
                                    <input type="hidden" id="status">

                                    <div class="row mb-3 ">
                                        <label for="inputText" class="col-sm-3 col-form-label ">Employee
											Code <span id="star">*</span></label>
                                        <div class="col-sm-8 ">
                                            <input type="hidden" id="id">
                                            <input type="text" onblur="employeeCodeCheck()" class="form-control " id="txt-employeeCode" required>
                                            <div class="invalid-feedback">Employee code is mandatory</div>
                                        </div>
                                    </div>
                                    <div class="row mb-3 ">
                                        <label for="inputText " class="col-sm-3 col-form-label">Name
											<span id="star">*</span></label>
                                        <div class="col-sm-8 ">
                                            <input type="text" class="form-control " id="txt-name" name="fname" required>
                                            <div class="invalid-feedback">Employee Name is mandatory</div>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="text" class="col-sm-3 col-form-label">Personal
											Email-id <span id="star">*</span></label>
                                        <div class="col-sm-8">
                                            <input type="email" class="form-control" id="txt-personalEmail" required>
                                            <div class="invalid-feedback">Employee Personal Email-id is mandatory</div>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="text" class="col-sm-3 col-form-label">Company
											Email-id <span id="star">*</span></label>
                                        <div class="col-sm-8">
                                            <input type="email" class="form-control" onblur="checkMailId()" id="txt-companyEmail" required>
                                            <div class="invalid-feedback">Employee Company Email-id is mandatory</div>
                                        </div>
                                    </div>
                                    <div class="row mb-3 ">
                                        <label for="inputText " class="col-sm-3 col-form-label ">Mobile-No
											<span id="star">*</span></label>
                                        <div class="col-sm-8 ">
                                            <div class="input-group mb-3">
                                                <span class="input-group-text" id="basic-addon1">+91</span>
                                                <input type="text" class="form-control" id="txt-mobileNo" onblur="checkmobileno()" minlength="10" maxlength="10" required>
                                                <div class="invalid-feedback">10 Digits of Mobile-No is mandatory</div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row mb-3 ">
                                        <label class="col-sm-3 col-form-label " for="validationCustom04">Role <span id="star">*</span></label>
                                        <div class="col-sm-8 ">
                                            <select class="form-select " id="slec-Role" name="role" aria-label="Default select example" required>
												<option value="" selected>-- Select --</option>
											</select>
                                            <div class="invalid-feedback">Role is mandatory</div>
                                        </div>
                                    </div>

                                    <div class="row mb-3 ">
                                        <label class="col-sm-3 col-form-label " for="validationCustom04">Designation <span id="star">*</span></label>
                                        <div class="col-sm-8 ">
                                            <select class="form-select " id="slec-designation" name="designation" aria-label="Default select example" required>
												<option value="" id="opt-slec-designation">--
													Select --</option>
											</select>
                                            <div class="invalid-feedback">Designation is mandatory</div>
                                        </div>
                                    </div>

                                    <div class="row mb-3" id="password-div">
                                        <label for="inputText" class="col-sm-3 col-form-label">New Password <span id="star">*</span></label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control " id="txt-newPassword" name="fname" required>

                                        </div>
                                        <div class="col-sm-2" style="margin-left: 3%;">
                                            <button class="btn btn-success" type="button" id="btn-change" onClick="changePassword()">Reset</button>
                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <button class="btn btn-primary" type="submit" id="btn-save" onClick="manipulate()">Save</button>

                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="cancelReset()" id="btn-cancel">Cancel</button>

                                        <button class="btn btn-success" type="button" id="btn-change-password">Reset Password</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Large Modal-->
            </div>
        </div>
        <!-- pop up to create a user end -->
        <!--Table Container Start-->
        <div class="card">
            <div class="card-body">
                <!-- Table with hoverable rows -->
                <table class="table table-hover" id="tbl">

                    <thead>
                        <tr>
                            <!-- <th scope="col">S.No</th> -->
                            <th scope="col">Employee Name</th>
                            <th scope="col">Employee Code</th>
                            <th scope="col">Mobile No</th>
                            <th scope="col">Designation</th>
                            <th scope="col">Role</th>
                            <th scope="col">Action</th>
                            <th scope="col">Status</th>
                        </tr>
                    </thead>

                    <tbody id="emp_body">

                    </tbody>

                </table>

            </div>
        </div>

        <div class="modal fade" id="basicModal" tabindex="-1" data-bs-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Status Change Confirmation</h5>
                        <button type="button" class="btn-close" onClick='noStatus()' data-bs-dismiss="modal" id="btn-close" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">Are you sure want to Change the Status?</div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" id="btn-confirmYes" data-bs-dismiss="modal" onClick='changeEmployeeStatus()'>Yes</button>
                        <button type="button" class="btn btn-primary" id="btn-confirmNo" onClick='noStatus()' data-bs-dismiss="modal">No</button>
                    </div>
                </div>
            </div>
        </div>
        <!--Table Container End-->
        <div id="pager" style="float: right;">
            <ul id="pagination" class="pagination-md"></ul>
        </div>

        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
			class="bi bi-arrow-up-short">
		</i></a>
        <!-- <div class="overlay"></div> -->
    </main>

    <script type="text/javascript" src="js/jquery.twbsPagination.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/EmployeeList.js"></script>

</body>

</html>