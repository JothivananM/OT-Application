<!DOCTYPE html>
<html lang="en">

<head>
    <!-- CSS,bootstrap links only -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="icons/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="css/simple-datatable-styles.css">
    <link rel="stylesheet" type="text/css" href="icons/boxicons.min.css">
    <link rel="stylesheet" type="text/css" href="icons/remixicon.css">
    <link href="img/company-logo.png" rel="icon">
</head>
<!-- <script type="text/javascript" src="ttps://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> -->

<body>



    <!-- Calling sidebar to this page -->
    <jsp:include page="Navbar.jsp" />
    <main id="main" class="main">
        <div class="overlay"></div>
        <div class="pagetitle">
            <!-- <h1>Create Project</h1> -->
            <nav>
                <ol class="breadcrumb">
                </ol>
            </nav>
        </div>
        <div class="d-flex justify-content-end">
            <button type="button" class="btn btn-success mb-3 " data-bs-toggle="modal" data-bs-target="#largeModal" id="btn-addProject" onclick="cancelReset()">
        <i class="bi bi-plus-lg p-1"></i>Project 
       </button>
        </div>
        <div class="modal fade" id="largeModal" tabindex="-1" data-bs-backdrop="static">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="model-name">Create Project</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form class="row g-0 needs-validation" novalidate onsubmit="return false">
                            <div class="row mb-3">
                                <label for="inputText" class="col-sm-3 col-form-label">Project Code <span id="star">*</span></label>
                                <div class="col-sm-8">
                                    <input type="hidden" id="id">
                                    <input type="hidden" id="table-id">
                                    <input type="hidden" id="status">
                                    <input type="text" class="form-control" onblur="checkProjectCode()" id="txt-projectCode" required>
                                    <div class="invalid-feedback">Project Code is mandatory</div>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="text" class="col-sm-3 col-form-label">Project Name <span id="star">*</span></label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" onblur="checkProjectName()" id="txt-projectName" required>
                                    <div class="invalid-feedback">Project Name is mandatory</div>
                                </div>
                            </div>
                            <div class="row mb-3 ">
                                <label for="inputDate" class="col-sm-3 col-form-label">Date <span id="star">*</span></label>
                                <div class="col-sm-8">
                                    <input type="date" class="form-control" id="txt-date" required>
                                    <div class="invalid-feedback">Date is mandatory</div>
                                </div>
                            </div>
                            <div class="row mb-3 ">
                                <label for="inputPassword" class="col-sm-3 col-form-label">Description</label>
                                <div class="col-sm-8">
                                    <textarea class="form-control" id="txt-description" style="height: 100px"></textarea>
                                    <div class="invalid-feedback">Description is mandatory</div>
                                </div>
                            </div>
                            <div class="modal-footer ">
                                <button class="btn btn-primary" type="submit" id="btn-save" value="Save" onClick="manipulate()">Save </button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onClick="cancelReset()" id="btn-cancel">Cancel</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <div class="modal fade" id="basicModal" tabindex="-1" data-bs-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Status Change Confirmation</h5>
                        <button type="button" class="btn-close" onClick='noStatus()' data-bs-dismiss="modal" id="btn-close" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">Are you sure want to Change the Project Status?</div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" id="btn-confirmYes" data-bs-dismiss="modal" onClick='changeProjectStatus()'>Yes</button>
                        <button type="button" class="btn btn-primary" id="btn-confirmNo" onClick='noStatus()' data-bs-dismiss="modal">No</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="card">
                <div class="card-body">
                    <!-- Table with hoverable rows  -->
                    <table id="employee" class="table table-hover" cellspacing="0" width="100%">

                        <thead>
                            <tr>
                                <!-- <th scope="col">S.No</th> -->
                                <th scope="col">Project Code</th>
                                <th scope="col">Project Name</th>
                                <th scope="col">Start Date</th>
                                <th scope="col">Description</th>
                                <th scope="col">Project Status</th>
                                <th scope="col">Edit</th>
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

        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
			class="bi bi-arrow-up-short">
		</i></a>
        <!-- <div class="overlay"></div> -->
    </main>
    <!-- =======body Footer ======= -->

    <script type="text/javascript" src="js/jquery.twbsPagination.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/ProjectList.js"></script>


</body>

</html>