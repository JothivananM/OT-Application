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
            <!-- <h1>OT Management</h1> -->

        </div>
        <!-- End Page Title -->

        <div class="m-3">

            <div class="modal fade" id="largeModal" tabindex="-1" data-bs-backdrop="static">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="model-name">Create OT</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onClick="cancelReset()"></button>
                        </div>


                        <div class="modal-body">

                            <form class="row g-0 needs-validation" id="form-id" novalidate onsubmit="return false">
                                <input type="hidden" id="id" />
                                <input type="hidden" id="empid" />
                                <div class="row mb-3 ">
                                    <label class="col-sm-3 col-form-label " for="validationCustom04">Project Name <span id="star">*</span></label>

                                    <div class="col-sm-8 ">

                                        <select class="form-select" id="select-project" name="project" aria-label="Default select example " required>
											<option  disabled selected value="0">--- Yours Project ---</option>
											
										</select>
                                        <div class="invalid-feedback">Project Name is mandatory</div>
                                    </div>
                                </div>

                                <div class="row mb-3 ">
                                    <label for="inputText " class="col-sm-3 col-form-label ">On <span id="star">*</span></label>
                                    <div class="col-sm-8 ">
                                        <input type="date" class="form-control" id="date-from" required>
                                        <div class="invalid-feedback">Date is mandatory</div>
                                    </div>
                                </div>


                                <div class="row mb-3 ">
                                    <label class="col-sm-3 col-form-label" for="validationCustom04">Slot <span id="star">*</span></label>
                                    <div class="col-sm-8 ">

                                        <select class="form-select " id="select-unit" name="unit" aria-label="Default select example" required>
											<option selected disabled value="">--- Units ---</option>
											
										</select>
                                        <div class="invalid-feedback">Slot is mandatory</div>
                                    </div>
                                </div>

                                <div class="row mb-3 ">
                                    <label for="inputPassword" class="col-sm-3 col-form-label">Description <span id="star">*</span></label>
                                    <div class="col-sm-8">
                                        <textarea class="form-control" style="height: 100px" maxlength="250" id="txt-description" required></textarea>
                                        <div class="invalid-feedback">Description is mandatory</div>
                                    </div>
                                </div>

                                <div class="modal-footer ">
                                    <button class="btn btn-primary" type="submit" id="btn-save" value="Save" onClick="manipulate()">Save </button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onClick="cancelReset()" id="btn-cancel">Cancel</button>
                                </div>
                                <input type="hidden" id="otcount">
                                <input type="hidden" id="otId">
                            </form>

                        </div>
                        <!-- <div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div> -->
                    </div>
                </div>
            </div>


        </div>




        <div class="row">
            <div class="d-flex justify-content-center">

                <div class="m-2">
                    <label for="inputDate" class="col-sm-0 ">BY PROJECT</label>

                    <select class="form-select " id="project-search" name="project-search" aria-label="Default select example " required>
							<option selected value="0" >All (Project Name)</option>							
						</select>
                </div>

                <div class="dropdown m-2">
                    <label for="form-label">By Status</label>
                    <select class="form-select " id="status-search" name="status-search" aria-label="Default select example " required>
					<option selected value="0" >All (Status)</option>
					
				</select>
                </div>

                <!-- <div class="m-2">
					<label for="inputDate" class="col-sm-0 ">Select Status</label> 

					<select class="form-select " id="project-status" name="project-status"
							aria-label="Default select example " required>
							<option selected value="0" >All (Project Status)</option>
							<option value="1">Active</option>	
							<option value="2">In-Active</option>						
						</select>
				</div> -->

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

        <div class="d-flex justify-content-between">

            <div class="d-flex  bd-highlight">
                <input type="button" value="Submit" class="btn btn-success" id="myBtn" onClick="statussubmit()" />


            </div>

            <div class="d-flex  bd-highlight">
                <button type="button" class="btn btn-success" id="btn-otCreate" id="myBtn" data-bs-toggle="modal" data-bs-target="#largeModal">
						<i class="bi bi-plus-lg p-1"></i>OT 
					</button>
            </div>

        </div>



        </div>

        <!-- pop up to create an Ot management -->

        <!--pop to delete content-->
        <div class="modal fade" id="basicModal" tabindex="-1" data-bs-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Delete Confirmation</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" id="btn-close" aria-label="Close"></button>
                    </div>



                    <div class="modal-body">Are you sure want to Delete?</div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" id="btn-confirmYes" data-bs-dismiss="modal" onClick='deleteOperation()'>Yes</button>
                        <button type="button" class="btn btn-primary" id="btn-confirmNo" data-bs-dismiss="modal">No</button>
                    </div>
                </div>
            </div>
        </div>


        <!--end pop to delete content-->

        <!-- Submit Confirmation -->

        <div class="modal fade" id="submitModal" tabindex="-1" data-bs-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Submit Confirmation</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" id="btn-close" aria-label="Close"></button>
                    </div>



                    <div class="modal-body">Are you sure want to submit?</div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" id="btn-confirmYes" data-bs-dismiss="modal" onClick="finalSubmit()">Yes</button>
                        <button type="button" class="btn btn-primary" id="btn-confirmNo" data-bs-dismiss="modal">No</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Withdraw Confirmation -->

        <div class="modal fade" id="withdrawModal" tabindex="-1" data-bs-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Withdraw Confirmation</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" id="btn-close" aria-label="Close"></button>
                    </div>



                    <div class="modal-body">Are you sure want to Withdraw?</div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" id="btn-confirmYes" data-bs-dismiss="modal" onClick='statusWithdraw()'>Yes</button>
                        <button type="button" class="btn btn-primary" id="btn-confirmNo" data-bs-dismiss="modal">No</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- End Basic Modal-->

        <!--Table Container-->
        <div class="card">
            <div class="card-body">
                <!-- Table with hoverable rows -->
                <table class="table table-hover" id="tbl">
                    <thead>
                        <tr>
                            <th scope="col">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="gridCheck1" onClick="toggle(this)">
                                    <label class="form-check-label" for="gridCheck1">
										</label>
                                </div>
                            </th>
                            <!-- <th scope="col">S.NO</th> -->
                            <th scope="col">Project Name</th>
                            <th scope="col">Date</th>
                            <th scope="col">Description</th>
                            <th scope="col">Unit</th>
                            <th scope="col">Status</th>
                            <th scope="col">Action / Reason</th>
                            <!-- <th scope="col">Action</th> -->
                        </tr>
                    </thead>
                    <tbody id="emp_body">

                    </tbody>


                </table>
                <!-- End Table with hoverable rows -->
            </div>
        </div>



        <div id="pager" style="float: right;">
            <ul id="pagination" class="pagination-md"></ul>
        </div>
        <!-- <div class="overlay"></div> -->
    </main>
    <!-- End #main -->

    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

    <script src="js/jquery.twbsPagination.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/ot-management.js"></script>



</body>

</html>