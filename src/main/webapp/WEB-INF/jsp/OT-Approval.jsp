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
<!-- <script type="text/javascript" src="ttps://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> -->
<body>
    <!-- Calling sidebar to this page -->
    <jsp:include page="Navbar.jsp" />
    <main id="main" class="main">
        <div class="overlay"></div>
        <div class="pagetitle">
            <!-- <h1>OT Approval</h1> -->

           
        </div>
        <div class="row">
            <div class="d-flex justify-content-center">
                <div class="m-2">
                    <label for="inputDate" class="col-sm-0 ">By Project</label> 
                    <select class="form-select " id="project-search" name="project-search"
                            aria-label="Default select example " required>
                            <option selected value="0" >All (Projects)</option>                           
                        </select>
                </div>
                <div class="m-2">
                    <label for="inputDate" class="col-sm-0 ">By Employee</label> 
                    <select class="form-select " id="employee-search" name="employee-search"
                            aria-label="Default select example" required>
                            <option selected value="0" >All (Employees)</option>                          
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
        <div class="d-flex justify-content-start">
            <input type="button" value="Approve" class="btn btn-success m-2" id="btn-approve" onClick="submit()"/>
            <input type="button" value="Reject" class="btn btn-danger m-2" id="btn-reject" onClick="rejectCheck()"/>
        </div>

        <div class="modal fade" id="basicModal" tabindex="-1"
            data-bs-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Reason</span></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" id="btn-close"
                            aria-label="Close"></button>
                    </div>
                     <form class="row g-0 needs-validation" novalidate onsubmit="return false">
                        <div class="modal-body">
                            <div class="row mb-3">
                                <label for="inputPassword" class="col-sm-3 col-form-label">Reason<span id="star">*</label>
                                <div class="col-sm-8">
                                    <textarea class="form-control" style="height: 100px" id="txt-description" maxlength="250" required></textarea>
                                    <div class="invalid-feedback">Enter Reason</div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer ">
                            <button class="btn btn-primary " type="button" id="btn-save"  onClick="manipulate()">Save </button>
                            <button type="reset" class="btn btn-secondary" data-bs-dismiss="modal" onClick="cancelReset()">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="card">
                <div class="card-body">
                    <!-- Table with hoverable rows -->
                    <table class="table table-hover"  id="tbl">
                        <thead>
                            <tr>                              
                                <th scope="col"><input class="form-check-input"
                                    type="checkbox" id="chk-tick" onClick="toggle(this)"></th>
                                <!-- <th scope="col">S.No</th> -->
                                <th scope="col">Project Name</th>
                                <th scope="col">Description</th>
                                <th scope="col">Employee Name</th>
                                <th scope="col">OT Date</th>
                                <th scope="col">Unit</th>
                                <th scope="col"><span>
                                    <select class="form-select " id="status-search" name="status-search" onClick="otFilter()"
                                    aria-label="Default select example " required>
                                    <option selected value="0" >All (Status)</option>
                                    </select>
                                </span></th>
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
        <!-- <div class="overlay"></div> -->
    </main>
    <!-- End #main -->
    
 
<script type="text/javascript" src="js/jquery.twbsPagination.min.js"></script> 
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/ot-approval.js"></script>
    
    <!-- =======body Footer ======= -->
  
</body>
</html>