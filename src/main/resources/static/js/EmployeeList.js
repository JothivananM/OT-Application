var $pagination = $('#pagination'),
    totalRecords = 0,
    records = [],
    displayRecords = [],
    recPerPage = 50,
    page = 1,
    totalPages = 0;
duplicate = false;



$(document).ready(function() {
   
    EmployeeList();
    var url = httpURL + "designation/list";
    $.getJSON(url, function(data) {
        $.each(data, function(index, value) {
            // APPEND OR INSERT DATA TO SELECT ELEMENT.
            $('#slec-designation').append('<option value="' + value.designationId + '">' + value.designationName + '</option>');
        });
    });
    var url = httpURL + "role/list";
    $.getJSON(url, function(data) {
        $.each(data, function(index, value) {
            // APPEND OR INSERT DATA TO SELECT ELEMENT.
            $('#slec-Role').append('<option value="' + value.roleId + '">' + value.role + '</option>');
        });
    });
    //$('select[name="role"]').val(1);
    //alert($('select[name="role"]').val());

    $("#password-div").hide();
    $("#btn-change-password").hide();

    $("#btn-addUser").click(function() {
        $("#btn-change-password").hide();
    });    
    
    $("#btn-change-password").click(function() {
        //$(".was-validated").removeClass("was-validated");
        $("#password-div").toggle();
    });

});

function manipulate() {

    if ($("#txt-name").val() != "" && $("#txt-employeeCode").val() != "" &&
        $("#txt-personalEmail").val() != "" && $("#txt-companyEmail").val() != "" &&
        $("#txt-mobileNo").val() != "" && $("#slec-designation").val() != "" &&
        $("#slec-role").val() != "") {
        if ($("#id").val() == "") {

            if (!duplicate) {
                addEmployee();
                EmployeeList();
            } else {
                toastr.error("Employee Code or Company Email-id or Mobile-no already exists", "ERROR!");
            }
        } else {

            if (!duplicate) {

                updateEmployee();
                EmployeeList();
            } else {
                // toastr.error("Employee Code or Company Email-id or Mobile-no already exists", "ERROR!");
            }

        }
    }
}
// Call Web API to get a list of Product
function EmployeeList() {
    $.ajax({
        url: httpURL + "user/list",
        type: 'get',
        dataType: 'json',
        success: function(data) {
            records = data;
            totalRecords = records.length;
            totalPages = Math.ceil(totalRecords / recPerPage);
            if (totalRecords == 0) {
                $('#emp_body').html('');
                tr = $('<tr/>');
                tr.append("<td colspan='7' class='text-center' ><span><img src='img/no-results-me-mode.png'></img></span><div class='m-3'><h5 id='no-head'style='font-family:Segoe UI; font-weight:300;'></h5></div></td>");
                $('#emp_body').append(tr);
                $pagination.twbsPagination('destroy');
            } else {
                apply_pagination();
            }
        },
        error: function(request, message, error) {
            toastr.error("Data Processing Error");
            //alert(message);
            //          handleException(request, message, error);
        }
    });
}

function apply_pagination() {
    $pagination.twbsPagination('destroy');
    $pagination.twbsPagination({
        totalPages: totalPages,
        visiblePages: 5,
        onPageClick: function(event, page) {
            displayRecordsIndex = Math.max(page - 1, 0) *
                recPerPage;
            endRec = (displayRecordsIndex) + recPerPage;
            displayRecords = records.slice(
                displayRecordsIndex, endRec);
            generate_table(page);
        }
    })
}

function generate_table(page) {
    var count = 1;
    var tr;
    $('#emp_body').html('');
    $.each(
        displayRecords,
        function(i, l) {

            tr = $('<tr/>');
            // tr.append("<td>" + (((page - 1) * recPerPage) + i + 1) + "</td>");

            tr.append("<td>" + displayRecords[i].name + "</td>");
            tr.append("<td>" + displayRecords[i].employeeCode + "</td>");
            tr.append("<td>" + displayRecords[i].mobileNo + "</td>");
            tr.append("<td>" + displayRecords[i].designation_id.designationName + "</td>");
            tr.append("<td>" + displayRecords[i].role_id.role + "</td>");
            tr.append("<td id='edit-link'><a data-bs-toggle='modal' data-bs-target='#largeModal' style='cursor: pointer;' href='#' onclick=selectEmployee(" + displayRecords[i].id + ")> Edit </a> " +
                "</td>");

            if (displayRecords[i].isActive) {
                //alert(displayRecords[i].isActive);
                tr
                    .append("<td><div class='form-check form-switch'>" +
                        "<input class='form-check-input' type='checkbox' checked id='flexSwitchCheckDefault'  data-bs-toggle='modal' data-bs-target='#basicModal' style='cursor: pointer;' onclick=getConformation(this," +
                        displayRecords[i].isActive +
                        "," +
                        displayRecords[i].id +
                        ")>" +
                        "<span class='badge bg-primary'><i class='bi bi-check-circle me-1'></i>Active</span>" +
                        "</div>" +
                        "</td>");
            } else {
                tr
                    .append("<td><div class='form-check form-switch'>" +
                        "<input class='form-check-input' type='checkbox'  id='flexSwitchCheckDefault' data-bs-toggle='modal'  data-bs-target='#basicModal' style='cursor: pointer;' onclick=getConformation(this," +
                        displayRecords[i].isActive +
                        "," +
                        displayRecords[i].id +
                        ")>" +
                        "<span class='badge bg-danger'><i class='bi bi-x-lg'></i>In-Active</span>" +
                        "</div>" +
                        "</td>");
            }

            count++;
            $('#emp_body').append(tr);
        });

    function newFunction() {
        return "+</td>";
    }
}

function selectEmployee(id) {

    $("#edit-link").click(function() {
        $("#btn-change-password").show();
    });

    $(".was-validated").removeClass("was-validated");
    $.ajax({
        url: httpURL + "user/" + id,
        type: 'get',
        dataType: 'json',
        success: function(prod) {
            $("#id").val(prod.id);
            $("#txt-name").val(prod.name);
            $("#txt-employeeCode").val(prod.employeeCode);
            $("#txt-personalEmail").val(prod.personalEmailId);
            $("#txt-companyEmail").val(prod.companyEmailId);
            $("#txt-mobileNo").val(prod.mobileNo);
            $('select[name="role"]').val(prod.role_id.roleId);
            $('select[name="designation"]').val(prod.designation_id.designationId);
            $("#btn-save").text("Update");
            $("#model-name").text("Update User");
        },
        error: function(request, message, error) {
            toastr.error("Data Processing Error");
            // alert(message);
            //          handleException(request, message, error);
        }
    });
}

function updateEmployee() {
    //$("#txt-employeeCode").attr("disabled", "disabled");
    const emp = {
        id: $("#id").val(),
        name: $("#txt-name").val(),
        employeeCode: $("#txt-employeeCode").val(),
        mobileNo: $("#txt-mobileNo").val(),
        personalEmailId: $("#txt-personalEmail").val(),
        companyEmailId: $("#txt-companyEmail").val(),
        designation_id: $('select[name="designation"]').val(),
        role_id: $('select[name="role"]').val(),
    }
    $.ajax({
        url: httpURL + "user/update",
        type: 'put',
        dataType: 'json',
        async: false,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(emp),
        success: function() {

            toastr.success("Employee Details Updated!", "Done!", { timeOut: 4000 });
            $('#largeModal').modal('toggle');
            // $("#btn-save").text("Save");
            // $("#model-name").text("Create User");

        },
        error: function(request, message, error) {
            toastr.error("Data Processing Error");
            // handleException(request, message, error);
        }
    });
}

function addEmployee() {
    const empl = {
        id: $("#id").val(),
        name: $("#txt-name").val(),
        employeeCode: $("#txt-employeeCode").val(),
        mobileNo: $("#txt-mobileNo").val(),
        personalEmailId: $("#txt-personalEmail").val(),
        companyEmailId: $("#txt-companyEmail").val(),
        designation_id: $('select[name="designation"]').val(),
        role_id: $('select[name="role"]').val(),
        password: $("#txt-mobileNo").val(),
    }
    
    $.ajax({
        url: httpURL + "user/save",
        type: 'post',
        dataType: 'json',
        async: false,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(empl),
        success: function() {
            toastr.success("User Added Succesfully", "Done!", { timeOut: 4000 });
            $('#largeModal').modal('toggle');
            // $("#btn-save").text("Update");
            // $("#model-name").text("Update User");
        },
        error: function(request, message, error) {
            toastr.error("Data Processing Error");
            //handleException(request, message, error);
        }
    });
}

function checkmobileno() {

    if ($("#id").val() == "") {
        isMobileExists();
    } else {
        Updatecheckmobileno();
    }
}

function checkMailId() {
    //alert('Check mail id');
    if ($("#id").val() == "") {
        checkEmail();
    } else {
        UpdatecheckEmail();
    }
}

function employeeCodeCheck() {
    //alert('Check employee code');
    if ($("#id").val() == "") {
        checkEmployeeCode();
    } else {
        UpdatecheckEmployeeCode();
    }
}





function checkEmail() {
    $.ajax({
        url: httpURL + "employeeList/emailcheck?company_email=" + $("#txt-companyEmail").val(),
        type: 'get',
        async: false,
        success: function(count) {
            if (count > 0) {
                duplicate = true;
                toastr.error("This email already exists", "ERROR!");
            } else {
                duplicate = false;
            }
        },
        error: function(request, message, error) {
            toastr.error("Data Processing Error");
            //handleException(request, message, error);
        }
    });
}


function isMobileExists() {
    $.ajax({
        url: httpURL + "employeeList/mobilenocheck?mobile_no=" + $("#txt-mobileNo").val(),
        type: 'get',
        async: false,
        success: function(count) {
            //alert(httpURL+"employeeList/emailcheck?company_email="+$("#txt-companyEmail").val(),);
            if (count > 0) {
                duplicate = true;
                toastr.error("This Mobile No already exists!", "ERROR");
            } else {
                duplicate = false;
            }
        },
        error: function(request, message, error) {
            toastr.error("Data Processing Error");
            //handleException(request, message, error);
        }
    });
}


function checkEmployeeCode() {
    $.ajax({
        url: httpURL + "employeeList/employeeCodecheck?employee_code=" + $("#txt-employeeCode").val(),
        type: 'get',
        async: false,
        success: function(count) {
            if (count > 0) {
                duplicate = true;
                toastr.error("This Employee Code already exists!", "ERROR!");
            } else {
                duplicate = false;
            }
        },
        error: function(request, message, error) {
            toastr.error("Data Processing Error");
            // handleException(request, message, error);
        }
    });
}


function UpdatecheckEmail() {
    $.ajax({
        url: httpURL + "updateEmployeeList/emailcheck?company_email=" + $("#txt-companyEmail").val() + "&employee_table_id=" + $("#id").val(),
        type: 'get',
        async: false,
        success: function(count) {
            //alert(httpURL+"updateEmployeeList/emailcheck?company_email="+$("#txt-companyEmail").val()+"&employee_table_id=" + $("#id").val());
            if (count > 0) {
                duplicate = true;
                toastr.error(" MailId Already Exists", "ERROR");
            } else {
                duplicate = false;
            }
        },
        error: function(request, message, error) {
            toastr.error("Data Processing Error");
            //handleException(request, message, error);
        }
    });
}



function Updatecheckmobileno() {
    $.ajax({
        url: httpURL + "updateEmployeeList/mobilenocheck?mobile_no=" + $("#txt-mobileNo").val() + "&employee_table_id=" + $("#id").val(),
        type: 'get',
        async: false,
        success: function(count) {
            //alert(httpURL+"updateEmployeeList/mobilenocheck?mobile_no="+$("#txt-mobileNo").val()+"&employee_table_id=" + $("#id").val());
            if (count > 0) {
                duplicate = true;
                toastr.error("This Mobile-no already exists!", "ERROR");
            } else {
                duplicate = false;
            }
        },
        error: function(request, message, error) {
            toastr.error("Data Processing Error");
            // handleException(request, message, error);
        }
    });
}


function UpdatecheckEmployeeCode() {
    $.ajax({
        url: httpURL + "updateEmployeeList/employeeCodecheck?employee_code=" + $("#txt-employeeCode").val() + "&employee_table_id=" + $("#id").val(),
        type: 'get',
        async: false,
        success: function(count) {
            //alert(httpURL+"updateEmployeeList/employeeCodecheck?employee_code="+$("#txt-employeeCode").val()+"&employee_table_id=" + $("#id").val());
            if (count > 0) {
                duplicate = true;
                toastr.error("This Employee Code already exists!", "ERROR");
            } else {
                duplicate = false;
                //alert();
            }
        },
        error: function(request, message, error) {
            toastr.error("Data Processing Error");
            //handleException(request, message, error);
        }
    });
}


function getConformation(row, status, id) {
    $("#table-id").val(id);
    $("#status").val(status);
    tableObject = row;
}


function changeEmployeeStatus() {
    var stat = $("#status").val();

    if (stat == 'true') {
        stat = 'false';
    } else {
        stat = 'true';
    }
    //alert(stat);
    $.ajax({
        url: httpURL + "user/delete/"+ stat + "/" + $("#table-id").val(),
        type: 'put',
        dataType: 'json',
        async: false,
        success: function() {
            EmployeeList();
            toastr.success("Status changed", "Done!", { timeOut: 4000 });
        },
        error: function(request, message, error) {
            toastr.error("Data Processing Error");
            //handleException(request, message, error);
        }
    });
}

function noStatus() {

    if ($(tableObject).prop("checked")) {
        $(tableObject).prop("checked", false);
    } else {
        $(tableObject).prop("checked", true);
    }
}



function changePassword() {
    //$("#password-div").hide();
    var id = $("#id").val();
    var newPassword = $("#txt-newPassword").val();


    if (newPassword != "") {
        $("#password-div").hide();

        $.ajax({
            url: httpURL + "user/update-password/?newPassword=" + $("#txt-newPassword").val() + "&employeeId=" + $("#id").val(),
            type: 'put',
            async: false,
            contentType: "application/json;charset=utf-8",
            success: function() {
                EmployeeList();
                toastr.success("Password Changed Successfully", "Done!", { timeOut: 4000 });
            },
            error: function(request, message, error) {
                toastr.error("Data Processing Error");
                //handleException(request, message, error);
            }
        });
    } else {
        toastr.error("Password field is mandatory", "ERROR!", { timeOut: 4000 })
    }

}


$('#largeModal').on('hidden.bs.modal', function() {
    $(this).find('form').trigger('reset');
    $("#btn-save").text("Save");
    $("#model-name").text("Add User");
})

function cancelReset() {
    $(".was-validated").removeClass("was-validated");
    clear();
    //return true;
}

function clear() {
    $(".was-validated").removeClass("was-validated");
    $("#id").val("");
    $("#txt-name").val("");
    $("#txt-employeeCode").val("");
    $("#txt-personalEmail").val("");
    $("#txt-companyEmail").val("");
    $("#txt-newPassword").val("");
    $("#txt-mobileNo").val("");
    $('select[name="designation"]').val("");
    $('select[name="role"]').val("");
}
//error message throw function in that list
function handleException(request, message, error) {
    var msg = "";
    msg += "Code: " + request.status + "\n";
    msg += "Text: " + request.statusText + "\n";
    if (request.responseJSON != null) {
        msg += "Message" + request.responseJSON.Message + "\n";
    }
    //alert(msg);
}