var tableObject;
var $pagination = $('#pagination'),

	totalRecords = 0,
	records = [],
	displayRecords = [],
	recPerPage = 50,
	page = 1,
	totalPages = 0;
	duplicate=false;

$(document).ready(function() {
	
	ProjectList();
});

function manipulate() {

	if ($("#txt-projectCode").val() != "" && $("#txt-projectName").val() != "" &&
		$("#txt-date").val() != "") {
		if ($("#id").val() == "") {
			if (!duplicate) {
				addProject();
				ProjectList();
			}
			else {
				toastr.error("Project Code or Project Name already exists", "ERROR!");
			}
		}
		else {
			if (!duplicate) {
				updateProject();
				ProjectList();
			}
			else {
				toastr.error("Project Code or Project Name already exists", "ERROR!");
			}
		}
	}
}

function ProjectList() {

	$.ajax
		({
			url: httpURL+"project/list",
			type: 'get',
			async: false,
			dataType: 'json',
			success: function(data) {
				records = data;
				totalRecords = records.length;
				totalPages = Math.ceil(totalRecords / recPerPage);

				if (totalRecords == 0) {
					$('#emp_body').html('');
					 tr = $('<tr/>');
				 tr.append("<td colspan='7' class='text-center'><span><img src='img/no-results-me-mode.png'></img></span><div class='m-3'><h5 style='font-family:Segoe UI; font-weight:300;'></h5></div></td>");
				$('#emp_body').append(tr);
					$pagination.twbsPagination('destroy');
				}
				else{
					apply_pagination();
				}

			}
		});
}

function apply_pagination() {
	 $pagination.twbsPagination('destroy');
	$pagination.twbsPagination({
		totalPages: totalPages,
		visiblePages: 5,
		onPageClick: function(event, page) {

			displayRecordsIndex = Math.max(page - 1, 0)
				* recPerPage;
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

			var des="";
			if(displayRecords[i].description.length>20)
			{
				des=displayRecords[i].description.substr(0,20)+'...';
			}
			else{
			des= displayRecords[i].description;
			}

			tr = $('<tr/>');
			//tr.append("<td>" + (((page - 1) * recPerPage) + i + 1) + "</td>");
			tr.append("<td>" + displayRecords[i].projectCode + "</td>");
			tr.append("<td>" + displayRecords[i].projectName + "</td>");
			tr.append("<td>" + displayRecords[i].startDate + "</td>");
			tr.append("<td> <div class='my-tooltip'>"+  des +"<span class='my-tooltiptext'>"+displayRecords[i].description+"</span></div> </td>")
			if (displayRecords[i].isActive) {
				//alert(displayRecords[i].isActive);
				tr
					.append("<td><div class='form-check form-switch'>"
						+ "<input class='form-check-input' type='checkbox' checked id='flexSwitchCheckDefault'  data-bs-toggle='modal' data-bs-target='#basicModal' style='cursor: pointer;' onclick=getConformation(this,"
						+ displayRecords[i].isActive
						+ ","
						+ displayRecords[i].id
						+ ")>"
						+ "<span class='badge bg-primary'><i class='bi bi-check-circle me-1'></i> Active</span>"
						+ "</div>"
						+ "</td>");
			} else {
				tr
					.append("<td><div class='form-check form-switch'>"
						+ "<input class='form-check-input' type='checkbox'  id='flexSwitchCheckDefault' data-bs-toggle='modal'  data-bs-target='#basicModal' style='cursor: pointer;' onclick=getConformation(this,"
						+ displayRecords[i].isActive
						+ ","
						+ displayRecords[i].id
						+ ")>"
						+ "<span class='badge bg-danger'><i class='bi bi-x-lg'></i> In-Active</span>"
						+ "</div>"
						+ "</td>");
			}
			if (displayRecords[i].isActive) {
				tr.append("<td><a data-bs-toggle='modal' data-bs-target='#largeModal' style='cursor: pointer;' href='#' onclick=selectProject("
					+ displayRecords[i].id
					+ ")> Edit </a> "
					+ "</td>");
			} else {
				tr.append("<td><span>-</span></td>");
			}
			count++;
			$('#emp_body').append(tr);
		});

	function newFunction() {
		return "+</td>";
	}
}

function selectProject(id) {

	$.ajax
		({
			url: httpURL+"project/" + id,
			type: 'get',
			dataType: 'json',

			success: function(prod) {

				$("#id").val(prod.id);
				$("#txt-projectCode").val(prod.projectCode);
				$("#txt-projectName").val(prod.projectName);
				$("#txt-date").val(prod.startDate);
				$("#txt-description").val(prod.description);
				$("#btn-save").text("Update");
				$("#model-name").text("Update Project");
			},
			error: function(request, message, error) {
toastr.error("Data Processing Error");
				//alert(message);
				//handleException(request, message, error);
			}
		});
}



function updateProject() {
	
	const project = {
		id: $("#id").val(),
		projectCode: $("#txt-projectCode").val(),
		projectName: $("#txt-projectName").val(),
		startDate: $("#txt-date").val(),
		description: $("#txt-description").val(),
	}

	$.ajax
		({
			url: httpURL+"project/update",
			type: 'put',
			dataType: 'json',
			async: false,
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify(project),
			success: function() {

				toastr.success('Project Updated Sucessfully','Done!',{timeOut: 2000});

				$('#largeModal').modal('toggle');
				//$("#btn-save").text("Update");
				//$("#model-name").text("Update-Project");
			},
			error: function(request, message, error) {
				toastr.error("Data Processing Error");
				//alert(message);
				//handleException(request, message, error);
			}
		});
}

function addProject() {

	const projectadd = {
		id: $("#id").val(),
		projectCode: $("#txt-projectCode").val(),
		projectName: $("#txt-projectName").val(),
		startDate: $("#txt-date").val(),
		description: $("#txt-description").val(),
	}

	$.ajax
		({
			url: httpURL+"project/save",
			type: 'post',
			dataType: 'json',
			async: false,
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify(projectadd),
			success: function() {

				toastr.success('Project Added Successfully','Done!',{timeOut: 3000});
				$('#largeModal').modal('toggle');
				//$("#btn-save").text("Save");
				//$("#model-name").text("Create-Project");
			},
			error: function(request, message, error) {
				toastr.error("Data Processing Error");
				//alert(message);
				//handleException(request, message, error);
			}
		});
}

function getConformation(row, status, id) {
	$("#table-id").val(id);
	$("#status").val(status);
	tableObject = row;
}

function changeProjectStatus() {
	var stat = $("#status").val();
	if (stat == 'true') {
		stat = 'false';
	}
	else {
		stat = 'true';
	}
	$.ajax
		({
			url: httpURL+"project/delete/" + stat + "/" + $("#table-id").val(),
			type: 'put',
			dataType: 'json',
			async: false,
			success: function() {
				ProjectList();
				toastr.success("Project status changed","Done!",{timeOut: 2000});
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
	}
	else {
		$(tableObject).prop("checked", true);
	}
}


function checkProjectName()
{
	if($("#id").val() == "")
	{
		isprojectnameExists();
	}
	else
	{
		Updatecheckprojectname();
	}
}

function checkProjectCode()
{
	if($("#id").val() == "")
	{
		isprojectcodeExists();
	}
	else{
		Updatecheckprojectcode();
	}
}


function isprojectnameExists()
{
$.ajax
	({
		url: httpURL+"projectList/namecheck?project_name="+$("#txt-projectName").val(),
		type: 'get',
		async: false,
		success: function(count) {
			if (count > 0) {
				duplicate=true;
				toastr.error("This project name is already exists","ERROR!");
			}
			else {
				duplicate=false;
			}
		},
		error: function(request, message, error) {
			toastr.error("Data Processing Error");
			//handleException(request, message, error);
		}
	});
}


function isprojectcodeExists()
{
$.ajax
	({
		url: httpURL+"projectList/idcheck?project_id="+$("#txt-projectCode").val(),
		type: 'get',
		async: false,
		success: function(count) {
			//alert(httpURL+"employeeList/emailcheck?company_email="+$("#txt-companyEmail").val(),);
			if (count > 0) {
				duplicate=true;
				toastr.error("This Project Code is already exists!","ERROR");
			}
			else {
				duplicate=false;
			}
		},
		error: function(request, message, error) {
			toastr.error("Data Processing Error");
			//handleException(request, message, error);
		}
	});
}


function Updatecheckprojectname()
{
$.ajax
	({
		url: httpURL+"updateProjectList/namecheck?project_name="+$("#txt-projectName").val()+"&id=" + $("#id").val(),
		type: 'get',
		async: false,
		success: function(count) {
			//alert(httpURL+"updateEmployeeList/emailcheck?company_email="+$("#txt-companyEmail").val()+"&employee_table_id=" + $("#id").val());
		     if (count > 0) {
				duplicate=true;
				toastr.error("This Project Name is already exists another project used!","ERROR");
			}
			else {
				duplicate=false;
			}
		},
		error: function(request, message, error) {
			toastr.error("Data Processing Error");
			//handleException(request, message, error);
		}
	});
}



function Updatecheckprojectcode()
{
$.ajax
	({
		url: httpURL+"updateProjectList/idcheck?project_id="+$("#txt-projectCode").val()+"&id=" + $("#id").val(),
		type: 'get',
		async: false,
		success: function(count) {
			//alert(httpURL+"updateEmployeeList/mobilenocheck?mobile_no="+$("#txt-mobileNo").val()+"&employee_table_id=" + $("#id").val());
			if (count > 0) {
				duplicate=true;
				toastr.error("This Project code is already exists another project used!","ERROR");
			}
			else {
				duplicate=false;
			}
		},
		error: function(request, message, error) {
			toastr.error("Data Processing Error");
			//handleException(request, message, error);
		}
	});
}


$('#largeModal').on('hidden.bs.modal', function() {
	$(this).find('form').trigger('reset');
	$("#btn-save").text("Save");
	$("#model-name").text("Create Project");
})

function cancelReset() {
	$(".was-validated").removeClass("was-validated");
	 clear()
}


function clear() {
	$(".was-validated").removeClass("was-validated");
	$("#id").val("");
	$("#txt-projectCode").val("");
	$("#txt-projectName").val("");
	$("#txt-date").val("");
	$("#txt-description").val("");
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





