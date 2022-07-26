<!DOCTYPE html>
<html lang="en">

<head>
    <!-- CSS,bootstrap links only -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="icons/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="icons/boxicons.min.css">
    <link rel="stylesheet" type="text/css" href="icons/remixicon.css">
    <!-- <link rel='stylesheet' type="text/css" href='ttps://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.min.css'> -->
    <!-- <link rel="stylesheet" type="text/css" href="css/sweetalert2.css"> -->
    <link rel="stylesheet" type="text/css" href="css/toastr.min.css">
    <link href="img/company-logo.png" rel="icon">


</head>

<body>

    <!-- ======= Header ======= -->
    <header id="header" class="header fixed-top d-flex align-items-center">

        <div class="d-flex align-items-center justify-content-between">
            <a href="#" class="logo d-flex align-items-center">
                <img src="img/company-name.png" alt="Profile" class="rounded-circle">
            </a> <i class="bi bi-list toggle-sidebar-btn"></i>
        </div>
        <!-- End Logo -->


        <nav class="header-nav ms-auto">
            
            <ul class="d-flex align-items-center">



                <li class="nav-item dropdown pe-5">
                    <a class="nav-link nav-profile d-flex align-items-center pe-3" href="#" data-bs-toggle="dropdown"> <img src="img/users-profile.jpg" alt="Profile" class="rounded-circle">
                        <span class="d-none d-md-block dropdown-toggle ps-2">${sessionScope.employee_name}</span>
                    </a>
                    <!-- End Profile Iamge Icon -->



                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                        <li class="dropdown-header">
                            <span>${sessionScope.designation_name}</span>
                        </li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>

                        <li>
                            <a class="dropdown-item d-flex align-items-center" href="${pageContext.request.contextPath }/Profile"> <i class="bi bi-person"></i> <span>My
									Profile</span>
                            </a>
                        </li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>


                        <li>
                            <hr class="dropdown-divider">
                        </li>

                        <li>
                            <a class="dropdown-item d-flex align-items-center" href="${pageContext.request.contextPath }/login"> <i class="bi bi-box-arrow-right"></i> <span>Log
									Out</span>
                            </a>
                        </li>

                    </ul>
                    <!-- End Profile Dropdown Items -->
                </li>
                <!-- End Profile Nav -->

            </ul>
        </nav>
        <!-- End Icons Navigation -->

    </header>
    <!-- End Header -->

    <!-- ======= Sidebar ======= -->
    <aside id="sidebar" class="sidebar">

        <ul class="sidebar-nav" id="userList">

        </ul>


    </aside>
    <!-- End Sidebar-->
    <!-- <div class="d-flex justify-content-center" id="loading-image">
        <div class="spinner-border" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
      </div> -->

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/tinymce.min.js"></script>
    <!-- <script src="js/sweetalert2.all.min.js"></script> -->
    <script src="js/toastr.min.js"></script>
   
   
    <script>

        // var loader = document.getElementById("loading-image");
        // window.addEventListener ("load", function(){
        // loader.style.display = "none";
        // })

        $(document).ready(function() {

            var roleid = sessionValue("1");
            mappingAjax(roleid);
        });



        function mappingAjax(employeeRoleId) {
//alert(employeeRoleId);
            $.ajax({
                url: httpURL + "mapping/?roleId=" + employeeRoleId,
                type: 'get',
                dataType: 'json',
                async: false,
                success: function(userList) {
                	
                    sidebarList(userList);

                },

                error: function(request, message, error) {
                    //alert(message);
                    handleException(request, message, error);
                }
            });

        }

        function sidebarList(list) {

            var userList = $("#userList");

            $.each(list, function(i, users) {

             
                if ($(location).attr('href').includes(users.url)) {
                    $("<li style='background-color: whitesmoke;border-radius:5px !important; margin:10px !important;'><a class='nav-link' href="+ users.url + "><i class='" + users.icon + "'style='color: black !important; background-color: whitesmoke;font-style: none !important;'></i> <span style='color: black !important; background-color: whitesmoke;'>" + users.menuName + "</span></a></li>").appendTo(userList);
                }
                 else if ($(location).attr('href').includes('/dashboard') && users.url == ('adminDashboard')) {
                    $("<li style='background-color: whitesmoke;border-radius:5px !important; margin:10px !important;'><a class='nav-link' href=" + users.url + "><i class='" + users.icon + "'style='color: black !important; background-color: whitesmoke;font-style: none !important;'></i> <span style='color: black !important; background-color: whitesmoke;'>" + users.menuName + "</span></a></li>").appendTo(userList);
                } 
                else if ($(location).attr('href').includes('/dashboard') && users.url == ('userDashboard')) {
                    $("<li style='background-color: whitesmoke;border-radius:5px !important; margin:10px !important;'><a class='nav-link' href="+ users.url + "><i class='" + users.icon + "'style='color: black !important; background-color: whitesmoke;font-style: none !important;'></i> <span style='color: black !important; background-color: whitesmoke;'>" + users.menuName + "</span></a></li>").appendTo(userList);
                }
                 else {
                    $("<li id='sidebar-list'><a class='nav-link' href="  + users.url + "><i class='" + users.icon + "'></i> <span>" + users.menuName + "</span></a></li>").appendTo(userList);
                }
            });


        }
    </script>

<!-- <div class="overlay"></div> -->

</body>

</html>