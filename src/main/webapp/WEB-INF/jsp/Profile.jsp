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
    <!-- <script type="text/javascript" src="ttps://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> -->

    <body>
        <jsp:include page="Navbar.jsp" />
        <main id="main" class="main">
            <div class="overlay"></div>
            <!-- End Page Title -->
            <div class="col-xl-9" style="margin-left:100px">
                <div class="card">
                    <div class="card-body pt-3">
                        <!-- Bordered Tabs -->
                        <ul class="nav  nav-tabs-bordered" style="padding:10px">
                            <h4 style="font-family: Segoe UI;">My Profile</h4>
                        </ul>
                        <div class="tab-content pt-2" style="padding-left:30px">
                            <!-- Profile Edit Form -->
                            <form>
                                <div class="row mb-4">
                                    <label for="Name" class="col-md-4 col-lg-3 col-form-label">Name</label>
                                    <div class="col-md-8 col-lg-6">
                                        <input name="fullName" type="text" class="form-control" id="txt-Name" disabled>
                                    </div>
                                </div>
                                <div class="row mb-4">
                                    <label for="employeecode" class="col-md-4 col-lg-3 col-form-label">Employee Code</label>
                                    <div class="col-md-8 col-lg-6">
                                        <input name="fullName" type="text" class="form-control" id="txt-code" disabled>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <label for="companyEmail" class="col-md-4 col-lg-3 col-form-label">Company Mail-Id</label>
                                    <div class="col-md-8 col-lg-6">
                                        <input name="email" type="email" class="form-control" id="txt-companyEmail" disabled>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <label for="personalEmail" class="col-md-4 col-lg-3 col-form-label">Personal Mail-Id</label>
                                    <div class="col-md-8 col-lg-6">
                                        <input name="email" type="email" class="form-control" id="txt-personalEmail" disabled>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <label for="mobilenumber" class="col-md-4 col-lg-3 col-form-label">Mobile Number </label>
                                    <div class="col-md-8 col-lg-6">
                                        <input name="job" type="text" class="form-control" id="txt-mobileno" disabled>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <label for="role" class="col-md-4 col-lg-3 col-form-label">Role</label>
                                    <div class="col-md-8 col-lg-6">
                                        <input name="country" type="text" class="form-control" id="txt-role" disabled>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <label for="designation" class="col-md-4 col-lg-3 col-form-label">Designation</label>
                                    <div class="col-md-8 col-lg-6">
                                        <input name="address" type="text" class="form-control" id="txt-designation" disabled>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <!-- <label for="Password" class="col-md-4 col-lg-3 col-form-label">Log-in Password</label>
                                    <div class="col-sm-6 col-sm-4 d-flex justify-content-between">
                                        <div class="col-sm-7">
                                            <input name="text" type="password" class="form-control " id="txt-password" disabled>
                                        </div>
                                        <div>
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#basicModal">Change password</button>
                                        </div>
                                    </div> -->
                                    <label for="Password" class="col-md-4 col-lg-3 col-form-label">Log-in Password</label>
                                    <div class="input-group" style="width: 50%;">
                                        <!-- <input type="password" class="form-control" aria-describedby="basic-addon2" id="txt-password" disabled>-->
                                        <div class="input-group-append">
                                          <button class="btn btn-outline-primary" type="button" data-bs-toggle="modal" data-bs-target="#basicModal">Change Password</button>
                                        </div>
                                      </div>
                                </div>
                            </form>
                            <!-- End Profile Edit Form -->
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="basicModal" tabindex="-1" data-bs-backdrop="static">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Change Password</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="cancelReset()"></button>
                        </div>
                        <form class="row g-0 needs-validation" novalidate onsubmit="return false">
                            <div class="modal-body">
                                <div class="row mb-3">
                                    <label for="newPassword" class="col-md-5 col-lg-5 col-form-label">New Password<span id="star">*</span></label>
                                    <div class="col-md-6 col-lg-7">
                                        <input name="newpassword" type="password" class="form-control" id="newPassword" minlength="8" maxlength="15" required>
                                    </div>
                                    <div class="invalid-feedback">Enter New Password</div>
                                </div>
                                <div class="row mb-3">
                                    <label for="renewPassword" class="col-md-5 col-lg-5 col-form-label">Confirm Password<span id="star">*</span></label>
                                    <div class="col-md-6 col-lg-7">
                                        <input name="renewpassword" type="password" class="form-control" id="renewPassword" minlength="8" maxlength="15" required>
                                    </div>
                                    <div class="invalid-feedback">Re-enter New Password</div>
                                </div>
                            </div>
                            <div class="modal-footer ">
                                <button class="btn btn-primary" type="submit" id="btn-save" value="Save" onClick="manipulate()">Save</button>
                                <button type="button" id="cancelbutton" class="btn btn-secondary" data-bs-dismiss="modal" onclick="cancelReset()">Cancel</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- <div class="overlay"></div> -->
        </main>
        <!-- =======body Footer ======= -->
        <script type="text/javascript" src="js/jquery.twbsPagination.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        <!--<script type="text/javascript" src="js/profile.js"></script>-->
        <script>
            let sessionEmployeeId;
            ('Profile')
            $(document).ready(function() {
                sessionEmployeeId = sessionValue("3");
                EmployeeDetails();
            });

            function EmployeeDetails() {
                $.ajax({
                    url: httpURL + "user/" + sessionEmployeeId,
                    type: 'get',
                    dataType: 'json',
                    success: function(prod) {
                        $("#txt-Name").val(prod.name);
                        $("#txt-code").val(prod.employeeCode);
                        $("#txt-personalEmail").val(prod.personalEmailId);
                        $("#txt-companyEmail").val(prod.companyEmailId);
                        $("#txt-mobileno").val(prod.mobileNo);
                        $('#txt-role').val(prod.role_id.role);
                        $('#txt-designation').val(prod.designation_id.designationName);
                        $('#txt-password').val(prod.userPassword);
                        $('#currentPassword').val(prod.userPassword);
                    },
                    error: function(request, message, error) {
                        alert(message);
                        handleException(request, message, error);
                    }
                });
            }

            function manipulate() {

                if ($("#newPassword").val() != "" && $("#renewPassword").val() != "") {

                    if ($('#newPassword').val() != $('#renewPassword').val()) {
                        //Swal.fire('Passwords not matches!');
                        toastr.warning("Password not matches!", "ERROR!");
                        //return false;
                    }

                    else if($('#newPassword').val().length < 8) {

                        ($('#newPassword').val());
                        ($('#newPassword').val().length);

                        toastr.warning("password should atleast have 8 characters");
                    }

                    else if($('#newPassword').val().length > 15) {

                        ($('#newPassword').val());
                        ($('#newPassword').val().length);
                        
                        toastr.warning("Password should not contain more than 15 characters");
                    }
                     else {
                        updateEmployee();
                        //$("#cancelbutton").trigger('click');
                        EmployeeDetails();
                        //return true;
                    }
                } 
                else {
                    //Swal.fire("Please enter all the fields!");
                    toastr.error("Please enter all the fields!", "ERROR!");
                    //return true;
                }
            }

            function updateEmployee() {
                $.ajax({
                    url: httpURL + "user/changepassword?employee_id=" + sessionEmployeeId + "&newpassword=" + $('#renewPassword').val(),
                    type: 'put',
                    async: false,
                    contentType: "application/json;charset=utf-8",
                    success: function() {

                        toastr.success("Password successfully changed", "Done!", {timeOut: 5000});

                        location.href = "${pageContext.request.contextPath }/login";
                        
                        $(this).find('form').trigger('reset');
                        $('#basicModal').modal('toggle');
                        
                    },
                    error: function(request, message, error) {
                        handleException(request, message, error);
                    }
                });
            }

            function cancelReset() {
                $(".was-validated").removeClass("was-validated");
                clear();
            }

            function clear() {
                $('#newPassword').val("");
                $("#renewPassword").val("");
            }

            $('#basicModal').on('hidden.bs.modal', function() {

                $(this).find('form').trigger('reset');
                $(".was-validated").removeClass("was-validated");
            })



            function handleException(request, message, error) {
                var msg = "";
                msg += "Code: " + request.status + "\n";
                msg += "Text: " + request.statusText + "\n";
                if (request.responseJSON != null) {
                    msg += "Message" + request.responseJSON.Message + "\n";
                }
                alert(msg);
            }
        </script>
    </body>

    </html>