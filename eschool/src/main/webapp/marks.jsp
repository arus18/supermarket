<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="eschool.marks.service.Service"%>
<%@ page import="eschool.marks.model.Marks"%>
<%
HttpSession sess = request.getSession(false);
if (sess.getAttribute("name") == null) {
	response.sendRedirect("login.jsp");
}
%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<!-- Head -->

<head>
    <title>eSchool | Dashboard Template</title>

    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="keywords" content="Bootstrap Theme, Freebies, Dashboard, MIT license">
    <meta name="description" content="Stream - Dashboard UI Kit">
    <meta name="author" content="htmlstream.com">

    <!-- Favicon -->
    <link rel="shortcut icon" href="favicon.png" type="image/x-icon">
    <!-- Web Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">
    <!-- Components Vendor Styles -->
    <link rel="stylesheet" href="assets/vendor/font-awesome/css/all.min.css">
    <link rel="stylesheet" href="assets/vendor/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.css">
    <link rel="stylesheet" href="assets/css/nice-select.css">
    <link rel="stylesheet" href="assets/css/jquery-ui.min.css">
    <!-- Theme Styles -->
    <link rel="stylesheet" href="assets/css/theme.css">
    <link rel="stylesheet" href="assets/css/style.css">

    <!-- Custom Charts -->
    <style>
        .js-doughnut-chart {
            width: 70px !important;
            height: 70px !important;
        }
    </style>
</head>
<!-- End Head -->

<body>
    <!-- Header (Topbar) -->
    <!--Modal-->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLongTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Update
						details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form method="POST" action="UpdateMarks">
					<div class="modal-body">
						<div class="container">
							<div class="row">
								<div class="col-sm">
									<div class="form-group">
										<label for="formGroupExampleInput">Marks</label> <input
											type="number" class="form-control" id="formGroupExampleInput"
											placeholder="Name" name="marks" required>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="hidden" value="" name="studentID">
						<input type="hidden" value="" name="class">
						<input type="hidden" value="" name="section">
						<input type="hidden" value="" name="subject">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save
							changes</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!--Modal-->
    <header class="u-header">
        <div class="u-header-left">
            <a class="u-header-logo" href="index.jsp">
                <img class="u-logo-desktop" src="assets/img/logo.png" width="160" alt="Stream Dashboard">
                <img class="img-fluid u-logo-mobile" src="assets/img/logo-mobile.png" width="50" alt="Stream Dashboard">
            </a>
        </div>

        <div class="u-header-middle">
            <a class="js-sidebar-invoker u-sidebar-invoker text-danger" href="#!" data-is-close-all-except-this="true" data-target="#sidebar">
                <i class="fa fa-bars u-sidebar-invoker__icon--open"></i>
                <i class="fa fa-times u-sidebar-invoker__icon--close"></i>
            </a>


        </div>


    </header>
    <!-- End Header (Topbar) -->

    <main class="u-main" role="main">
        <!-- Sidebar -->
        <aside id="sidebar" class="u-sidebar">
            <div class="u-sidebar-inner bg-gradient bdrs-30">
                <header class="u-sidebar-header">
                    <a class="u-sidebar-logo" href="index.jsp">
                        <img class="img-fluid" src="assets/img/logo.png" width="124" alt="Stream Dashboard">
                    </a>
                </header>

                <nav class="u-sidebar-nav">
                    <ul class="u-sidebar-nav-menu u-sidebar-nav-menu--top-level">
                      <!-- Dashboard -->
                      <li class="u-sidebar-nav-menu__item">
                          <a class="u-sidebar-nav-menu__link active" href="index.jsp">
                              <i class="fas fa-tachometer-alt u-sidebar-nav-menu__item-icon"></i>
                              <span class="u-sidebar-nav-menu__item-title">Dashboard</span>
                          </a>
                      </li>
                      <!-- End Dashboard -->

                      <!-- Attendances -->
                      <li class="u-sidebar-nav-menu__item">
                          <a class="u-sidebar-nav-menu__link" href="#!" data-target="#attendances">
                              <i class="fas fa-user-check u-sidebar-nav-menu__item-icon"></i>
                              <span class="u-sidebar-nav-menu__item-title">Attendances</span>
                              <i class="fa fa-angle-right u-sidebar-nav-menu__item-arrow"></i>
                              <span class="u-sidebar-nav-menu__indicator"></span>
                          </a>

                          <ul id="attendances" class="u-sidebar-nav-menu u-sidebar-nav-menu--second-level" style="display: none;">

                              <li class="u-sidebar-nav-menu__item">
                                  <a class="u-sidebar-nav-menu__link" href="attendances-list.jsp">
                                      <span class="u-sidebar-nav-menu__item-title">Attendances List</span>
                                  </a>
                              </li>

                              <li class="u-sidebar-nav-menu__item">
                                  <a class="u-sidebar-nav-menu__link" href="attendances-report.jsp">
                                      <span class="u-sidebar-nav-menu__item-title">Attendances Report</span>
                                  </a>
                              </li>
                          </ul>
                      </li>
                      <!-- End Attendances -->

                      <!-- Routines -->
                      <li class="u-sidebar-nav-menu__item">
                          <a class="u-sidebar-nav-menu__link" href="#!" data-target="#subMenu1">
                              <i class="fas fa-stopwatch u-sidebar-nav-menu__item-icon"></i>
                              <span class="u-sidebar-nav-menu__item-title">Teachers</span>
                              <i class="fa fa-angle-right u-sidebar-nav-menu__item-arrow"></i>
                              <span class="u-sidebar-nav-menu__indicator"></span>
                          </a>

                          <ul id="subMenu1" class="u-sidebar-nav-menu u-sidebar-nav-menu--second-level" style="display: none;">
                              <li class="u-sidebar-nav-menu__item">
                                  <a class="u-sidebar-nav-menu__link" href="teachers-all.jsp">
                                      <span class="u-sidebar-nav-menu__item-title">Teachers-list</span>
                                  </a>
                              </li>
                              <li class="u-sidebar-nav-menu__item">
                                  <a class="u-sidebar-nav-menu__link" href="teacher-add.jsp">
                                      <span class="u-sidebar-nav-menu__item-title">Add teacher</span>
                                  </a>
                              </li>
                          </ul>
                      </li>
                      <!-- End Routines -->

                      <!-- Online-Exam -->
                      <li class="u-sidebar-nav-menu__item">
                          <a class="u-sidebar-nav-menu__link" href="#!" data-target="#online-exam">
                              <i class="fas fa-diagnoses u-sidebar-nav-menu__item-icon"></i>
                              <span class="u-sidebar-nav-menu__item-title">Online Exam</span>
                              <i class="fa fa-angle-right u-sidebar-nav-menu__item-arrow"></i>
                              <span class="u-sidebar-nav-menu__indicator"></span>
                          </a>

                          <ul id="online-exam" class="u-sidebar-nav-menu u-sidebar-nav-menu--second-level" style="display: none;">

                              <li class="u-sidebar-nav-menu__item">
                                  <a class="u-sidebar-nav-menu__link" href="online-exams.jsp">
                                      <span class="u-sidebar-nav-menu__item-title">Online Exams</span>
                                  </a>
                              </li>
                              <li class="u-sidebar-nav-menu__item">
                                  <a class="u-sidebar-nav-menu__link" href="online-exam-add.jsp">
                                      <span class="u-sidebar-nav-menu__item-title">Add Online Exam</span>
                                  </a>
                              </li>
                          </ul>
                      </li>
                      <!-- End Online-Exams -->

                      <!-- Marks -->
                      <li class="u-sidebar-nav-menu__item">
                          <a class="u-sidebar-nav-menu__link" href="#!" data-target="#marks">
                              <i class="far fa-clipboard u-sidebar-nav-menu__item-icon"></i>
                              <span class="u-sidebar-nav-menu__item-title">Marks</span>
                              <i class="fa fa-angle-right u-sidebar-nav-menu__item-arrow"></i>
                              <span class="u-sidebar-nav-menu__indicator"></span>
                          </a>

                          <ul id="marks" class="u-sidebar-nav-menu u-sidebar-nav-menu--second-level" style="display: none;">
                              <li class="u-sidebar-nav-menu__item">
                                  <a class="u-sidebar-nav-menu__link" href="marks.jsp">
                                      <span class="u-sidebar-nav-menu__item-title">Marks</span>
                                  </a>
                              </li>
                              <li class="u-sidebar-nav-menu__item">
                                  <a class="u-sidebar-nav-menu__link" href="marks-add.jsp">
                                      <span class="u-sidebar-nav-menu__item-title">Add Marks</span>
                                  </a>
                              </li>
                          </ul>
                      </li>
                      <!-- End Marks -->

                      <!-- Students -->
                      <li class="u-sidebar-nav-menu__item">
                          <a class="u-sidebar-nav-menu__link" href="#!" data-target="#students">
                              <i class="fas fa-user-graduate u-sidebar-nav-menu__item-icon"></i>
                              <span class="u-sidebar-nav-menu__item-title">Students</span>
                              <i class="fa fa-angle-right u-sidebar-nav-menu__item-arrow"></i>
                              <span class="u-sidebar-nav-menu__indicator"></span>
                          </a>
                          <ul id="students" class="u-sidebar-nav-menu u-sidebar-nav-menu--second-level" style="display: none;">
                              <li class="u-sidebar-nav-menu__item">
                                  <a class="u-sidebar-nav-menu__link" href="students-all.jsp">
                                      <span class="u-sidebar-nav-menu__item-title">All Student</span>
                                  </a>
                              </li>
                              <li class="u-sidebar-nav-menu__item">
                                  <a class="u-sidebar-nav-menu__link" href="students-add.jsp">
                                      <span class="u-sidebar-nav-menu__item-title">Add Student</span>
                                  </a>
                              </li>
                          </ul>
                      </li>
                      <!-- End Students -->
                    </ul>
                </nav>
            </div>
        </aside>
        <!-- End Sidebar -->

        <div class="u-content">
            <div class="u-body">

                <!-- breadcumb-area -->
                <section class="breadcumb-area card bg-gradient mb-5">
                    <div class="bread-cumb-content card-body d-flex align-items-center">
                        <div class="breadcumb-heading">
                            <h2 class="text-white">All Students Marks</h2>
                        </div>
                        <div class="breadcumb-image ml-auto">
                            <img src="assets/img/breadcumb-marks.png" alt="">
                        </div>
                    </div>
                </section>
                <!-- End breadcumb-area -->




                <section class="es-form-area">
                    <div class="card">
                        <header class="card-header bg-gradient border-0 pt-5 pb-5 d-flex align-items-center">
                            <a href="marks-add.jsp" class="btn btn-sm btn-pill btn-outline-light ml-auto">+ Add New</a>
                        </header>
                        <div class="card-body">
                            <form action="SubjectMarksReport" class="es-form" method="POST">
                                <div class="row align-items-center">
                                    <div class="col">
                                        <label for="class">Class</label>
                                        <select id="class" class="" name="class" required>
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <label for="section">Section</label>
                                        <select id="section" class="" name="section" required>
                                            <option>A</option>
                                            <option>B</option>
                                            <option>C</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <label for="subject">Subject</label>
                                        <select id="subject" class="" name="subject" required>
                                            <option>Accounting</option>
                                            <option>Another option</option>
                                            <option>Potato</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <button type="submit" class="es-form-btn btn btn-block bg-gradient text-white">Report</button>
                                    </div>
                                </div>
                            </form>
                            <form action="SearchMarksByClassSectionSubject" class="es-form" method="POST">
                                <div class="row align-items-center">
                                    <div class="col">
                                        <label for="class">Class</label>
                                        <select id="class" class="" name="class" required>
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <label for="section">Section</label>
                                        <select id="section" class="" name="section" required>
                                            <option>A</option>
                                            <option>B</option>
                                            <option>C</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <label for="subject">Subject</label>
                                        <select id="subject" class="" name="subject" required>
                                            <option>Accounting</option>
                                            <option>Another option</option>
                                            <option>Potato</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <button type="submit" class="es-form-btn btn btn-block bg-gradient text-white">Search</button>
                                    </div>
                                </div>
                            </form>

                            <div class="attendances-list-wrap mt-5">
                                <div class="show-option d-flex align-items-center mb-4">
                                    <div class="show-list-count">
                                        <p>show
                                        <select id="section" class="">
                                            <option data-display="10">10</option>
                                            <option value="1">20</option>
                                            <option value="2">30</option>
                                        </select>
                                        items</p>
                                    </div>
                                    <div class="search-student ml-auto">
                                        <form action="SearchMarksByStudentName" class="search-student-form" method="POST">
                                            <input type="text" placeholder="Student Name" name="search">
                                            <button type="submit" class="btn btn-lg btn-pill bg-gradient text-white">Search</button>
                                        </form>
                                    </div>
                                </div>

                                <div class="table-responsive">
                                    <table class="table mb-0">
                                        <thead class="bg-gradient">
                                            <tr>
                                                <th scope="col" class="text-white">#</th>
                                                <th scope="col" class="text-white">Student</th>
                                                <th scope="col" class="text-white text-center">Class</th>
                                                <th scope="col" class="text-white text-center">Section</th>
                                                <th scope="col" class="text-white text-center">Subject</th>
                                                <th scope="col" class="text-white text-center">Marks</th>
                                                <th scope="col" colspan="2" class="text-white text-center">Options</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                        <%
											if (request.getAttribute("searchMarksByClassSectionSubject") != null) {
												ArrayList<Marks> result = (ArrayList<Marks>) request.getAttribute("searchMarksByClassSectionSubject");
												int count = 1;
												for (Marks m : result) {
											%>
											<tr>
												<th scope="row"><%=count%></th>
												<td><%=m.getName()%></td>
												<td><%=m.getYear()%></td>
												<td><%=m.getSection()%></td>
												<td><%=m.getSubject()%></td>
												<td><%=m.getMarks()%></td>
												<td><form method="POST" action="DeleteMarks">
														<button type="submit" class="btn btn-primary">Delete</button>
														<input type="hidden" value=<%=m.getStudentID()%>
															name="studentID">
															<input type="hidden" value=<%=m.getYear()%>
															name="class">
															<input type="hidden" value=<%=m.getSection()%>
															name="section">
															<input type="hidden" value=<%=m.getSubject()%>
															name="subject">
													</form></td>
												<td><a href="#exampleModalCenter" data-toggle="modal"
													data-target="#exampleModalCenter"
													data-student-id=<%=m.getStudentID()%>
													data-student-subject=<%=m.getSubject()%>
													data-student-class=<%=m.getYear()%>
													data-student-section=<%=m.getSection()%> class="text-dark">Edit</a></td>
											</tr>
											<%
											count++;
											}
											} else if (request.getAttribute("searchMarksByStudentName") != null) {
												ArrayList<Marks> result = (ArrayList<Marks>) request.getAttribute("searchMarksByStudentName");
												int count = 1;
												for (Marks m : result) {
											%>
											<tr>
												<th scope="row">count</th>
												<td><%=m.getName()%></td>
												<td><%=m.getYear()%></td>
												<td><%=m.getSection()%></td>
												<td><%=m.getSubject()%></td>
												<td><%=m.getMarks()%></td>
												<td><form method="POST" action="DeleteMarks">
														<button type="submit" class="btn btn-primary">Delete</button>
														<input type="hidden" value=<%=m.getStudentID()%>
															name="studentID">
															<input type="hidden" value=<%=m.getYear()%>
															name="class">
															<input type="hidden" value=<%=m.getSection()%>
															name="section">
															<input type="hidden" value=<%=m.getSubject()%>
															name="subject">
													</form></td>
												<td><a href="#exampleModalCenter" data-toggle="modal"
													data-target="#exampleModalCenter"
													data-student-id=<%=m.getStudentID()%>
													data-student-subject=<%=m.getSubject()%>
													data-student-class=<%=m.getYear()%>
													data-student-class=<%=m.getMarks()%>
													data-student-section=<%=m.getSection()%> class="text-dark">Edit</a></td>
											</tr>
											<%
											count++;
											}
											} else {
												ArrayList<Marks> result = Service.getMarks();
												int count = 1;
												for (Marks m : result) {
											%>
											<tr>
												<th scope="row">count</th>
												<td><%=m.getName()%></td>
												<td><%=m.getYear()%></td>
												<td><%=m.getSection()%></td>
												<td><%=m.getSubject()%></td>
												<td><%=m.getMarks()%></td>
												<td><form method="POST" action="DeleteMarks">
														<button type="submit" class="btn btn-primary">Delete</button>
														<input type="hidden" value=<%=m.getStudentID()%>
															name="studentID">
															<input type="hidden" value=<%=m.getYear()%>
															name="class">
															<input type="hidden" value=<%=m.getSection()%>
															name="section">
															<input type="hidden" value=<%=m.getSubject()%>
															name="subject">
													</form></td>
												<td><a href="#exampleModalCenter" data-toggle="modal"
													data-target="#exampleModalCenter"
													data-student-id=<%=m.getStudentID()%>
													data-student-subject=<%=m.getSubject()%>
													data-student-class=<%=m.getYear()%>
													data-student-section=<%=m.getSection()%> class="text-dark">Edit</a></td>
											</tr>
											<%
											count++;
											}
											}
											%>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <div class="row">
                    <div class="col-md-12 text-center">
                        <nav aria-label="Bootstrap Pagination" class="mt-5 text-center d-inline-block">
                            <ul class="pagination mb-0">
                                <li class="page-item">
                                    <a class="btn btn-outline-danger prev" href="#"><span class="ml-1 d-none d-xl-inline-block">Previous</span></a>
                                </li>
                                <li class="page-item">
                                    <a class="btn btn-danger bg-gradient text-white ml-4 mr-4" href="#">1</a>
                                </li>
                                <li class="page-item">
                                    <a class="btn btn-outline-danger next" href="#"><span class="mr-1 d-none d-xl-inline-block">Next</span></a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>



            </div>
        </div>
    </main>

    <!-- Global Vendor -->
    <script src="assets/vendor/jquery/dist/jquery.min.js"></script>
    <script src="assets/vendor/jquery-migrate/jquery-migrate.min.js"></script>
    <script src="assets/vendor/popper.js/dist/umd/popper.min.js"></script>
    <script src="assets/vendor/bootstrap/bootstrap.min.js"></script>

    <!-- Plugins -->
    <script src="assets/vendor/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="assets/vendor/chart.js/dist/Chart.min.js"></script>
    <script src="assets/js/jquery.nice-select.min.js"></script>
    <script src="assets/js/jquery-ui.min.js"></script>

    <!-- Initialization  -->
    <script src="assets/js/sidebar-nav.js"></script>
    <script src="assets/js/main.js"></script>
    <script src="assets/js/dashboard-page-scripts.js"></script>
    <!--<script src="assets/js/scripts.js"></script>-->
    	<script type="text/javascript">
		$('#exampleModalCenter')
				.on(
						'show.bs.modal',
						function(e) {

							//get data-id attribute of the clicked element
							var studID = $(e.relatedTarget).data('student-id');
							var year = $(e.relatedTarget).data('student-class');
							var section = $(e.relatedTarget).data(
									'student-section');

							var subject = $(e.relatedTarget).data(
							'student-subject');
							var marks = $(e.relatedTarget).data(
							'student-marks');
							//populate the textbox
							$(e.currentTarget)
									.find('input[name="studentID"]').val(
											studID);
							$(e.currentTarget)
									.find('input[name="class"]').val(
											year);
							$(e.currentTarget).find('input[name="section"]').val(
									section);
							$(e.currentTarget).find('input[name="subject"]').val(
									subject);
							$(e.currentTarget).find('input[name="marks"]')
									.val(marks);
						});
	</script>
</body>
</html>
