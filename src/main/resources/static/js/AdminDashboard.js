// var tableObject;
var $pagination = $('#pagination'),
            totalRecords = 0,
            records = [],
            displayRecords = [],
            recPerPage = 50,
            page = 1,
            totalPages = 0;

			let sessionRoleId;

$(document).ready(function()
{	
	sessionEmployeeId = sessionValue("3");
	
	var url = httpURL+"project/list";
	
	$.getJSON(url, function(data) {
		$.each(data, function(index, value) {
			
				$('#project-search').append('<option value="' + value.id + '">' + value.projectName + '</option>');
			
		});
	});
	
	var url = httpURL+"user/list";
	
	$.getJSON(url, function(data) {
		$.each(data, function(index, value) {

			if (value.role_id.roleId != 1) {
				
				$('#employee-search').append('<option value="' + value.id + '">' + value.name + '</option>');
			}
		});

	});
	
	var Todate = document.getElementById('toDate');
	var today = new Date();
	Todate.value = today.toISOString().substr(0, 10);

	var d = new Date();
	var currMonth = d.getMonth();
	var currYear = d.getFullYear();
	var startDate = new Date(currYear, currMonth, 2);
	var temp = startDate.toISOString().substr(0, 10);
	$("#fromDate").val(temp);
	
	 otFilter();
});


function test()
{
    otFilter();
}



function otFilter() {

	var projectId = $('select[name="project-search"]').val();
	var employeeId = $('select[name="employee-search"]').val();
	//var status = $('select[name="project-status"]').val();
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();


	$.ajax
		({
			url: httpURL+"dashboard/filters/?project_id=" + projectId + "&employee_table_id=" + employeeId + "&fromDate=" + fromDate + "&toDate=" + toDate,
			type: 'get',
			dataType: 'json',
			success: function(prod) {
				
				//alert(httpURL+"dashboard/filters/?project_id=" + projectId + "&employee_table_id=" + employeeId + "&is_active=" + status + "&fromDate=" + fromDate + "&toDate=" + toDate)
				records = prod;
				totalRecords = records.length;
				totalPages = Math.ceil(totalRecords / recPerPage);

				if (totalRecords == 0) {
					$('#emp_body').html('');
					 tr = $('<tr/>');
					 tr.append("<td colspan='9' class='text-center'><span><img src='img/no-results-me-mode.png'></img></span><div class='m-3'><h5 id='no-head' style='font-family:Segoe UI; font-weight:300;'></h5></div></td>");
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
                            
                            totalPages : totalPages,
                            visiblePages : 5,
                            onPageClick : function(event, page) {                           
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
								tr = $('<tr/>');
								// tr.append("<td>" + (((page - 1) * recPerPage) + i + 1) + "</td>");
								//tr.append("<td>" + displayRecords[i].project_name + "</td>");
								tr.append("<td>" + displayRecords[i].employee_name + "</td>");
								tr.append("<td>" + displayRecords[i].ot_saved_Count + "</td>");
								tr.append("<td>" + displayRecords[i].ot_submitted_Count + "</td>");
								tr.append("<td>" + displayRecords[i].ot_approved_Count + "</td>");
								tr.append("<td>" + displayRecords[i].ot_rejected_Count + "</td>");
								tr.append("<td><a data-bs-toggle='modal' data-bs-target='#ExtralargeModal' style='cursor: pointer;' href='#' data-id=" + displayRecords[i].employee_table_id + " onclick=UserOts("+ displayRecords[i].employee_table_id + ")> " + displayRecords[i].ot_Count + " </a></td>");
								// if (displayRecords[i].project_status) {
								// 	tr.append("<td><span class='badge bg-primary'><i class='bi bi-check-circle me-1'></i> Active</span></td>");
								// } else {
								// 	tr.append("<td><span class='badge bg-danger'><i class='bi bi-x-lg'></i> In-Active</span></td>");
								// }
								count++;
								$('#emp_body').append(tr);
							});
						function newFunction() {
							return "+</td>";
						}
					}


					function UserOts(employeetableid)
					{
						var projectId = $('select[name="project-search"]').val();
						//alert(employeetableid);
						var fromDate = $("#fromDate").val();
						var toDate = $("#toDate").val();
							
						$.ajax
							({
								url: httpURL+"userdashboard/lists/?project_id=" + projectId + "&employee_id="+employeetableid+"&fromDate=" + fromDate + "&toDate=" + toDate,
								type: 'get',
								dataType: 'json',
								async: false,
								success: function(prod)
								{			
									UserOtsListView(prod);			
								},
								error: function(request, message, error) {
									toastr.error("Data Processing Error");
									//alert(message);
									//handleException(request, message, error);
								}
							});
					}
					


function UserOtsListView(products) {
	var count = 1;
	var table = $("#tbl1");
	table.find("tr:not(:first)").remove();

	if($('select[name="project-search"]').val() == 0)
	{
		//document.getElementById(projectName).value="show"
		$('#projectName').show();
	}
	else{
		//document.getElementById(projectName).value="hide"
		$('#projectName').hide();
	}

	$.each(products, function(i, prod) {

		var des="";
		if(prod.ot_description.length>20)
		{
			des=prod.ot_description.substr(0,20)+'...';
		}
		else{
		des=prod.ot_description;
		}

		$("#emp-name").text(prod.employee_name);
        
		if($('select[name="project-search"]').val() == 0)
            {
	             $("#pro-name").text("");
				 $("#prj-name").text("");;
              }	
             else{
				$("#prj-name").text(" for the project ");
	               $("#pro-name").text(prod.project_name);
                }

		var table = $("#tbl1 tbody")
		var row = table[0].insertRow(-1);
		
		$(row).append("<td />");
		$(row).find("td").eq(0).html(prod.ot_date);

		if($('select[name="project-search"]').val() == 0)
		{
			
			$(row).append("<td />");
		$(row).find("td").eq(1).html(prod.project_name);

		$(row).append("<td />");
		$(row).find("td").eq(2).html(prod.ot_action);

		$(row).append("<td />");
		$(row).find("td").eq(3).html("<div class='my-tooltip' style='font-family:Segoe UI;'>"+  des +"<span class='my-tooltiptext' style='text-align:center;'>"+prod.ot_description+"</span></div>");

		$(row).append("<td />");
		$(row).find("td").eq(4).html(prod.ot_count);

		$(row).append("<td />");
		$(row).find("td").eq(5).html(prod.ot_slots);
		}
		else{
			
			$(row).append("<td />");
		$(row).find("td").eq(1).html(prod.ot_action);

		$(row).append("<td />");
		$(row).find("td").eq(2).html("<div class='my-tooltip' style='font-family:Segoe UI;'>"+  des +"<span class='my-tooltiptext' style='text-align:center;'>"+prod.ot_description+"</span></div>");

		$(row).append("<td />");
		$(row).find("td").eq(3).html(prod.ot_count);

		$(row).append("<td />");
		$(row).find("td").eq(4).html(prod.ot_slots);
		}
	});
}

