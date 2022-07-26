// import { toastr } from "/js/sweetalert2.js";

var tableObject;
var count;
var $pagination = $('#pagination'),
    totalRecords = 0,
    records = [],
    displayRecords = [],
    recPerPage = 50,
    page = 1,
    totalPages = 0;
    duplicate = false;

let sessionEmployeeId;

$(document).ready(function() {

  
    sessionEmployeeId = sessionValue("3");

    var url = httpURL + "project/list";
    $.getJSON(url, function(data) {
        $.each(data, function(index, value) {
            // APPEND OR INSERT DATA TO SELECT ELEMENT.
            var isAct = value.isActive;
            if (isAct) {
                $('#select-project').append('<option value="' + value.id + '">' + value.projectName + '</option>');
                $('#project-search').append('<option value="' + value.id + '">' + value.projectName + '</option>');
            }


        });

    });
    var url = httpURL + "otStatus/list";
    $.getJSON(url, function(data) {
        $.each(data, function(index, value) {
            // APPEND OR INSERT DATA TO SELECT ELEMENT.
            $('#status-search').append('<option value="' + value.statusId + '">' + value.otAction + '</option>');
        });

    });

    var url = httpURL + "otUnits/list";
    $.getJSON(url, function(data) {
        $.each(data, function(index, value) {
            // APPEND OR INSERT DATA TO SELECT ELEMENT.
            $('#select-unit').append('<option  data-id="' + value.otCount + '" value="' + value.unitId + '" >' + value.otSlots + '</option>');
        });
    });

    //DefaultDate
    var myDate = document.getElementById('toDate');
    var today = new Date();
    myDate.value = today.toISOString().substr(0, 10);
    var d = new Date();
    var currMonth = d.getMonth();
    var currYear = d.getFullYear();
    var startDate = new Date(currYear, currMonth, 2);
    var temp = startDate.toISOString().substr(0, 10);
    $("#fromDate").val(temp);

    otFilter();
});
//--------------------------End of Document Ready------------------------------

function otFilter() {

    var projectId = $('select[name="project-search"]').val();
    var status = $('select[name="status-search"]').val();
    var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    (projectId, status, fromDate, toDate, sessionEmployeeId);

    $.ajax({
        url: httpURL + "ot/filter?pId=" + projectId + "&eId=" + sessionEmployeeId + "&sId=" + status + "&fromDate=" + fromDate + "&toDate=" + toDate,
        type: 'get',
        dataType: 'json',
        async: 'false',
        success: function(data) {

            //alert(httpURL+"ot/filter?pId="+projectId+"&eId="+sessionEmployeeId+"&sId="+status+"&fromDate="+fromDate+"&toDate="+toDate,)
            records = data;
            totalRecords = records.length;
            totalPages = Math.ceil(totalRecords / recPerPage);

            if (totalRecords == 0) {
                $('#emp_body').html('');
                tr = $('<tr/>');
                tr.append("<td colspan='7' class='text-center'><span><img src='img/no-results-me-mode.png'></img></span><div class='m-3'><h5 id='no-head' style='font-family:Segoe UI; font-weight:300;'></h5></div></td>");
                $('#emp_body').append(tr);
                $pagination.twbsPagination('destroy');
            } else {
                apply_pagination();
            }
        },
        error: function(request, message, error) {
            //toastr.error("Data Processing Error");
            // alert(message);
            //handleException(request, message, error);
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

            var des = "";
            if (displayRecords[i].ot_description.length > 20) {
                des = displayRecords[i].ot_description.substr(0, 20) + '...';
            } else {
                des = displayRecords[i].ot_description;
            }

            tr = $('<tr/>');

            if (displayRecords[i].ot_status_id == 1) {
                tr.append("<td><div class='form-check'>" +
                    "<input class='form-check-input' data-id=" + displayRecords[i].ot_detail_id + " type='checkbox' id='gridCheck1'" +
                    "name='check'> <label class='form-check-label' for='gridCheck1'/>" +
                    "</div></td>");
            } else {
                tr.append("<td><div class='form-check'>" +
                    "<input class='form-check-input' data-id=" + displayRecords[i].ot_detail_id + " type='checkbox' id='gridCheck1'" +
                    "name='check' disabled> <label class='form-check-label' for='gridCheck1'/>" +
                    "</div></td>");
            }

            // tr.append("<td>"+ (((page-1)*recPerPage)+i+1)+"</td>");
            tr.append("<td>" + displayRecords[i].project_name + "</td>");
            tr.append("<td>" + displayRecords[i].ot_date + "</td>");

            tr.append("<td> <div class='my-tooltip'>" + des + "<span class='my-tooltiptext'>" + displayRecords[i].ot_description + "</span></div> </td>");

            tr.append("<td>" + displayRecords[i].ot_unit_id + "</td>");

            if (displayRecords[i].ot_status_id == 1) {
                tr.append("<td><span class='badge bg-primary'><i class='bi bi-check-circle me-1'></i>" + displayRecords[i].ot_action + "</span></td>");
            } else if (displayRecords[i].ot_status_id == 2) {
                tr.append("<td><span class='badge bg-secondary'><i class='bi bi-collection me-1'></i>" + displayRecords[i].ot_action + "</span></td>");
            } else if (displayRecords[i].ot_status_id == 3) {
                tr.append("<td><span class='badge bg-success'><i class='bi bi-hand-thumbs-up me-1'></i>" + displayRecords[i].ot_action + "</span></td>");
            } else {
                tr.append("<td><span class='badge bg-danger'><i class='bi bi-exclamation-octagon me-1'></i>" + displayRecords[i].ot_action + "</span></td>");
            }

            //  data-toggle='tooltip' data-placement='right' title='"+displayRecords[i].reject_reason+"'


            if (displayRecords[i].ot_status_id == 1) {
                tr.append("<td><a data-bs-toggle='modal' data-bs-target='#largeModal' style='cursor: pointer;' href='#' onclick=selectOt(" + displayRecords[i].ot_detail_id + ")> Edit </a> /" +
                    "<a data-bs-toggle='modal' data-bs-target='#basicModal' style='cursor: pointer;' href='#' onclick='deleteOt(" + displayRecords[i].ot_detail_id + ")'> Delete </a></td> ");
            } else if (displayRecords[i].ot_status_id == 2) {
                tr.append("<td><a data-bs-toggle='modal' data-bs-target='#withdrawModal' style='cursor: pointer;' href='#' onclick=withdrawOt(" + displayRecords[i].ot_detail_id + ")> Withdraw </a></td> ");
            } else if (displayRecords[i].ot_status_id == 4) {
                //<td class="text-center" colspan="6"><span class='badge bg-success'><i class='bi bi-hand-thumbs-up me-1'>Total Approved Count : <span id="rejected-count"></span></i></span></td>
                tr.append("<td> <div class='my-tooltip'><span class='badge bg-info'>Reason</i></span><span class='my-tooltiptext'>" + displayRecords[i].reject_reason + "</span></div> </td>")
            } else {
                tr.append("<td><span class='text-center'>-</span></td> ");
            }
            count++;
            $('#emp_body').append(tr);
        });

    function newFunction() {
        return "+</td>";
    }
}

function statussubmit() {

    var count = 0;
    $("#tbl tr").each(function() {
        var ctrl = $(this).find("td:eq(0) input[type='checkbox']");
        if (ctrl.prop("checked")) {
            count = 1;
            //submitStatus(ctrl.attr("data-id"));
            //alert(ctrl.prop("checked"));
        }
    });

    if (count == 1) {
        $("#submitModal").modal("show");
    } else {
        toastr.warning('Please select atleast 1 record', "Warning!");
    }

}

function finalSubmit() {

    $("#tbl tr").each(function() {

        var ctrl = $(this).find("td:eq(0) input[type='checkbox']");

        if (ctrl.prop("checked")) {
            submitStatus(ctrl.attr("data-id"));
            //alert(ctrl.prop("checked"));
        }

    });
    $("#gridCheck1").prop("checked", false);
    toastr.success('Successfully Submitted', 'Done!', { timeOut: 4000 });
    otFilter();
}


function submitStatus(employeeid) {
    $.ajax({
        url: httpURL + "otDetails/updateStatus?sId=2&dId=" + employeeid,
        type: 'put',
        //dataType: 'json'
        async: false,
        contentType: "application/json;charset=utf-8",

        success: function() {

            // otFilter();
            //location.reload();


        },
        error: function(request, message, error) {
           // toastr.error("Data Processing Error");
            //alert(message);
            //                   handleException(request, message, error);
        }
    });
}


function manipulate() {

    // if(saveCount>0){
    //     cancelReset();
    // }
    //alert($("#select-project").val());

    if ($("#select-project").val() != null && $("#date-from").val() != "" 
        && $("#txt-description").val() != "" &&    $("#select-unit").val() != null) {
        $('#form-id').removeClass('was-validated');


        if ($("#id").val() == "") {
            if (!duplicate) {
                checkOtCount();
                var counts = parseInt($("#otcount").val()) + parseInt($("#select-unit").val());


                if (counts < 4) {
                    addOt();
                    $('#form-id').removeClass('was-validated');
                    toastr.success('OT Successfully Created.', 'Done!', { timeOut: 5000 });
                } else {
                    toastr.error('Already ' + $("#otcount").val() + ' OTs booked in this date. Only 3-OTs applicable per Day.', 'ERROR!', { timeOut: 5000 });
                    if (parseInt($("#otcount").val()) == 2) {
                        toastr.info('You can only select 0-2hours slot!', { timeout: 5000 });
                    } else if (parseInt($("#otcount").val()) == 1) {
                        toastr.info('You can select either 0-2hours or 2-4hours slot!', { timeout: 5000 });
                    }

                }

            } else {
                toastr.error('You have crossed the OT limit for  ' + $("#date-from").val(), 'ERROR!', { timeOut: 5000 });
                toastr.info('Try some other Date!', { timeout: 5000 });
            }

        } else {
            //$('#form-id').removeClass('was-validated');
            if (!duplicate) {

                var counts = parseInt($("#otcount").val()) + parseInt($("#select-unit").val());


                if (counts < 4) {

                    updateOt();
                    //$('#form-id').removeClass('was-validated');
                    toastr.success('OT Successfully Updated.', 'Done!', { timeOut: 5000 });
                } else {
                    //toastr.error('Already ' + $("#otcount").val() + ' OTs Booked. Only 3-OTs applicable per Day', 'ERROR!', { timeOut: 5000 });
                    toastr.error( `You've already crossed OT Book limit for this Date. Only 3-OTs applicable per Day.`, 'ERROR!', { timeOut: 6000 });
                    
                    if (parseInt($("#otcount").val()) == 2) {
                        toastr.info('You can only select 0-2hours slot!', { timeout: 5000 });
                    } else if (parseInt($("#otcount").val()) == 1) {
                        toastr.info('You can select either 0-2 hours or 2-4 hours slot!', { timeout: 5000 });
                    }

                }


            } else {
                // toastr.warning('OT Date cannot be changed!',{timeout : 4000});
                toastr.error('OT record for the selected Date is already exists.', 'ERROR!', { timeOut: 5000 });

            }

        }
        otFilter();
    }
}

function deleteOt(id) {
    (id);
    $("#otId").val(id);
    ($("#otId").val());
}

function deleteOperation() {
    $.ajax({
        url: httpURL + "otDetails/delete/" + $("#otId").val(),
        type: 'put',
        async: false,
        success: function() {

            toastr.success('Successfully Deleted', 'Done!', { timeOut: 4000 });
            $('#successDelete').alert('show')
            otFilter();

            $("#otId").val("");
        },
        error: function(request, message, error) {
            //toastr.error("Data Processing Error");
            // alert(message);
            //handleException(request, message, error);
        }
    });

}

function withdrawOt(id) {
    (id);
    $("#otId").val(id);
    ($("#otId").val());
}

function statusWithdraw() {
    ("Inside function")
    $.ajax({
        url: httpURL + "ot/withdraw?sId=1&dId=" + $("#otId").val(),
        type: 'put',
        success: function() {
            toastr.success('Successfully Withdrawn', 'Done!', { timeOut: 4000 });

            $('#successDelete').alert('show')
            otFilter();

            $("#otId").val("");
        },
        error: function(request, message, error) {
            //toastr.error("Data Processing Error");
            //alert(message);
            //handleException(request, message, error);
        }
    });

}


function selectOt(id) {

    $('#select-project').prop('disabled', 'disabled');
    $("#date-from").prop('disabled', 'disabled');
    $.ajax({
        url: httpURL + "otDetails/" + id,
        type: 'get',
        dataType: 'json',
        async: false,
        success: function(prod) {

            $("#id").val(prod.detailId);
            $("#empid").val(prod.employeeId.id);
            $('select[name="project"]').val(prod.projectId.id);
            $("#date-from").val(prod.otDate.substring(0, 10));
            $('select[name="unit"]').val(prod.unitId.unitId);
            $("#txt-description").val(prod.otDescription);
            $("#btn-save").text("Update");
            $("#model-name").text("Update OT");

            checkOtCount();
            var updatecount = $("#otcount").val() - $('select[name="unit"]').val();
            $("#otcount").val(updatecount);

        },
        error: function(request, message, error) {
            //toastr.error("Data Processing Error");
            //alert(message);
            //handleException(request, message, error);
        }
    });
}

function updateOt() {

    //$('#form-id').removeClass('was-validated');

    $(".was-validated").removeClass("was-validated");

    const project = {
        detailId: $("#id").val(),

        otDate: $("#date-from").val(),
        unitId: $("#select-unit").val(),
        otDescription: $("#txt-description").val(),
        statusId: 1
    }
    //alert($("#select-unit").val());
    $.ajax({
        url: httpURL + "otDetails/update",
        type: 'put',
        dataType: 'json',
        async: false,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(project),
        success: function() {
            //toastr.success('OT Successfully Updated', 'Done!', { timeOut: 4000 });
            $(this).find('form').trigger('reset');
            $('#largeModal').modal('toggle');
        },
        error: function(request, message, error) {
            //toastr.error("Data Processing Error");
            //alert(message);
            //              handleException(request, message, error);
        }
    });
}

function addOt() {

    // //cancelReset();
    // $('#form-id').removeClass('was-validated');


    const projectadd = {
        detailId: '',
        employeeId: sessionEmployeeId,
        projectId: $("#select-project").val(),
        otDate: $("#date-from").val(),
        unitId: $("#select-unit").val(),
        otDescription: $("#txt-description").val(),
        statusId: 1
    }
    
    $.ajax({
        url: httpURL + "otDetails/save",
        type: 'post',
        dataType: 'json',
        async: false,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(projectadd),
        success: function() {

            $('#largeModal').modal('toggle');
            $("#btn-save").text("Save");
            $("#model-name").text("Create OT");
            //$(".was-validated").removeClass("was-validated");
        },

        error: function(request, message, error) {
            //toastr.error("Data Processing Error");
            //alert(message);
            //handleException(request, message, error);
        }
    });
}

function checkOtCount() {
    //alert($("#date-from").val());
    $.ajax({
        url: httpURL + "otDetail/checkcount?ot_date=" + $("#date-from").val() + "&employee_id=" + sessionEmployeeId,
        //http: //localhost:8080/otDetail/checkcount?ot_date=2022-05-03&employee_id=154
        type: 'get',
        async: false,
        success: function(count) {
            $("#otcount").val(count);

            // if (count < 3) {
            //     duplicate = false;
            //     toastr.error("count less than 3", "ERROR!", { timeOut: 4000 });
            //     //checkCountOts(count);
            // } else {
            //     duplicate = true;
            //     toastr.error("count more than 3", "ERROR!", { timeOut: 4000 });
            // }
        },
        error: function(request, message, error) {
            //toastr.error("Data Processing Error");
            //toastr.error("");
            //handleException(request, message, error);
        }
    });
}

// function checkCountOts() {
//     alert(count);
//     alert($('#select-unit').find(':selected').data('id'));
// }



$('#largeModal').on('hidden.bs.modal', function() {

    $(this).find('form').trigger('reset');
    $(".was-validated").removeClass("was-validated");
    $("#btn-save").text("Save");
    $("#model-name").text("Create OT");
    $('#select-project').prop('disabled', false);
    $("#date-from").prop('disabled', false);
    $("#select-project").prop('selectedIndex', 0);
    $("#select-unit").prop('selectedIndex', 0);

    //$("#txt-description").val("");
})

function cancelReset() {
    $(".was-validated").removeClass("was-validated");
    classClear();
}

function classClear() {
    $(".was-validated").removeClass("was-validated");
    $("#id").val("");
    $("#empid").val("");
    $('select[name="project"]').val("");
    $("#date-from").val("");
    $('select[name="unit"]').val("");
    $("#txt-description").val("");
    //$("#btn-save").text("Update");
    //$("#model-name").text("Update OT");
}

//error message throw function in that list
function handleException(request, message, error) {
    var msg = "";
    msg += "Code: " + request.status + "\n";
    msg += "Text: " + request.statusText + "\n";
    if (request.responseJSON != null) {
        msg += "Message" + request.responseJSON.Message + "\n";
    }
    alert(msg);
}