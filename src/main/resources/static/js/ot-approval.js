var tableObject;
var $pagination = $('#pagination'),
	totalRecords = 0,
	records = [],
	displayRecords = [],
	recPerPage = 50,
	page = 1,
	totalPages = 0;

$(document).ready(function() {

	var url = httpURL+"project/list";
	$.getJSON(url, function(data) {
		$.each(data, function(index, value) {
			var isAct = value.isActive;
			if (isAct) {
				$('#project-search').append('<option value="' + value.id + '">' + value.projectName + '</option>');
				}
		});

	});


	var url = httpURL+"user/list";
	$.getJSON(url, function(data) {
		$.each(data, function(index, value) {
			// APPEND OR INSERT DATA TO SELECT ELEMENT.
			//$('#select-project').append('<option value="' + value.id + '">' + value.projectName + '</option>');

			if (value.role_id.roleId != 1) {
				
				$('#employee-search').append('<option value="' + value.id + '">' + value.name + '</option>');
			}

		});

	});


	var url = httpURL+"otStatus/list";
	$.getJSON(url, function(data) {
		$.each(data, function(index, value) {
			// APPEND OR INSERT DATA TO SELECT ELEMENT.
			//$('#select-project').append('<option value="' + value.id + '">' + value.projectName + '</option>');
			if (value.statusId != 1 && value.statusId != 5) {
				$('#status-search').append('<option value="' + value.statusId + '">' + value.otAction + '</option>');
			}

		});

	});

	var url = httpURL+"otUnits/list";
	$.getJSON(url, function(data) {
		$.each(data, function(index, value) {
			// APPEND OR INSERT DATA TO SELECT ELEMENT.
			$('#select-unit').append('<option value="' + value.unitId + '">' + value.otSlots + '</option>');
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
	var empId = $('select[name="employee-search"]').val();
	var status = $('select[name="status-search"]').val();
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();

	$.ajax
		({
			url: httpURL+"ot/approval?pId=" + projectId+ "&eId="+empId+"&sId=" + status + "&fromDate=" + fromDate + "&toDate=" + toDate,
			type: 'get',
			dataType: 'json',
			async: false,
			success: function(data) {
				records = data;
				totalRecords = records.length;
				totalPages = Math.ceil(totalRecords / recPerPage);

				if (totalRecords == 0) {
					$('#emp_body').html('');
					 tr = $('<tr/>');
				 tr.append("<td colspan='7' class='text-center'><span><img src='img/no-results-me-mode.png'></img></span><div class='m-3'><h5 id='no-head'style='font-family:Segoe UI; font-weight:300;'></h5></div></td>");
				$('#emp_body').append(tr);
					$pagination.twbsPagination('destroy');
				}
				else{
					apply_pagination();
				}           
  
			},
			error: function(request, message, error) {
toastr.error("Data Processing Error");
				//alert(message);
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
			if(displayRecords[i].ot_description.length>20)
			{
				des=displayRecords[i].ot_description.substr(0,20)+'...';
			}
			else{
			des= displayRecords[i].ot_description;
			}
			
			tr = $('<tr/>');
			if (displayRecords[i].ot_status_id == 2) {
				tr.append("<td><div class='form-check'>"
					+ "<input class='form-check-input' data-id=" + displayRecords[i].ot_detail_id + " type='checkbox' id='gridCheck1'"
					+ "name='check'> <label class='form-check-label' for='gridCheck1'/>"
					+ "</div></td>");
			}

			else {
				tr.append("<td><div class='form-check'>"
					+ "<input class='form-check-input' data-id=" + displayRecords[i].ot_detail_id + " type='checkbox' id='gridCheck1'"
					+ "name='check' disabled> <label class='form-check-label' for='gridCheck1'/>"
					+ "</div></td>");
			}
			// tr.append("<td>" + (((page - 1) * recPerPage) + i + 1) + "</td>");
			tr.append("<td>" + displayRecords[i].project_name + "</td>");
			// tr.append("<td> <span data-toggle='tooltip' data-placement='top' title='"+displayRecords[i].ot_description+"'>"+  des +"</span></td>");
			tr.append("<td> <div class='my-tooltip'>"+  des +"<span class='my-tooltiptext'>"+displayRecords[i].ot_description+"</span></div> </td>")

			tr.append("<td>" + displayRecords[i].employee_name + "</td>");
			tr.append("<td>" + displayRecords[i].ot_date + "</td>");
			tr.append("<td>" + displayRecords[i].ot_count+ "</td>");

			if (displayRecords[i].ot_status_id == 2) {
				tr.append("<td><span class='badge bg-secondary'><i class='bi bi-collection me-1'></i>" + displayRecords[i].ot_action + "</span></td>");
			} else if (displayRecords[i].ot_status_id == 3) {
				tr.append("<td><span class='badge bg-success'><i class='bi bi-hand-thumbs-up me-1'></i>" + displayRecords[i].ot_action + "</span></td>");
			} else if (displayRecords[i].ot_status_id == 4) {
				tr.append("<td><span class='badge bg-danger'><i class='bi bi-exclamation-octagon me-1'></i>" + displayRecords[i].ot_action + "</span></td>");
			}


			count++;
			$('#emp_body').append(tr);
		});

	function newFunction() {
		return "+</td>";
	}
}


function submit() {
	
	var count=0;
	$("#tbl tr").each(function() {
		var ctrl = $(this).find("td:eq(0) input[type='checkbox']");
		if (ctrl.prop("checked")) {

			count = 1;
			ctrl.prop("checked", false);
			submitStatus(ctrl.attr("data-id"));
			//alert(ctrl.prop("checked"));
		}
	});
	if(count==0)
	{
		toastr.error("Please select atleast one record","ERROR!");
     }
    else{
		toastr.success("Successfully Approved","Done!", {timeOut: 4000});
	}
	$("#chk-tick").prop("checked", false);
}




function rejectCheck() {
	
	var count =0;
	
	$("#tbl tr").each(function() {
		var ctrl = $(this).find("td:eq(0) input[type='checkbox']");
		if (ctrl.prop("checked")) {	
			count=1;		
		}
		});
		if(count==1){
			$("#basicModal").modal("show");
		}
		else{
			toastr.error("Please select atleast one record","ERROR!");
		}
		
}


function reject() {


	$("#tbl tr").each(function() {

		var ctrl = $(this).find("td:eq(0) input[type='checkbox']");

		if (ctrl.prop("checked")) {

			ctrl.prop("checked", false);
			rejectStatus(ctrl.attr("data-id"));
			//alert(ctrl.prop("checked"));
			
		}

	});
	

	$("#basicModal").modal("hide");

	$("#chk-tick").prop("checked", false);

	toastr.success("Rejected Successfully","Done!", {timeOut: 4000});	

}


function submitStatus(employeeid) {



	$.ajax
		({
			url: httpURL+"otDetails/updateStatus?sId=3&dId=" + employeeid,
			type: 'put',
			//dataType: 'json'
			async: false,
			contentType: "application/json;charset=utf-8",

			success: function() {

				otFilter();
				//toastr.success("Approved Successfully","Done!",{timeOut: 4000});

			},
			error: function(request, message, error) {
				toastr.error("Data Processing Error");
				//alert(message);
				//handleException(request, message, error);
			}
		});
}


function rejectStatus(employeeid) {
//alert(employeeid);
	$.ajax
		({
			url: httpURL+"ot/reject?reason=" + $("#txt-description").val() + "&sId=4&id=" + employeeid,
			type: 'put',
			//dataType: 'json'
			async: false,
			contentType: "application/json;charset=utf-8",

			success: function() {


				otFilter();

			},
			error: function(request, message, error) {
				toastr.error("Data Processing Error");
				//alert(message);
				//handleException(request, message, error);
			}
		});
}



function manipulate() {

	if ($("#txt-description").val() != "") {
		("True")
		reject();
	}
	else {
		toastr.error("Reason is mandatory","Error!",{timeOut: 4000})
		//otFilter();
	}

}



$('#basicModal').on('hidden.bs.modal', function() {
	$(this).find('form').trigger('reset');
	$("#btn-save").text("Save");
	$("#model-name").text("New-User");
	$('#select-project').prop('disabled', false);
	$("#select-project").prop('selectedIndex', 0);
	$("#select-unit").prop('selectedIndex', 0);

	//$("#txt-description").val("");
})

function cancelReset() {
	$(".was-validated").removeClass("was-validated");


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
