<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Bootstrap CSS -->
	<link href="css/bootstrap.min.css" rel = "stylesheet" type = "text/css">
	<style type="text/css">
		body {
			background-color: #DFDBE5;
			background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E%3Cg fill-rule='evenodd'%3E%3Cg fill='%239C92AC' fill-opacity='0.39'%3E%3Cpath d='M0 38.59l2.83-2.83 1.41 1.41L1.41 40H0v-1.41zM0 1.4l2.83 2.83 1.41-1.41L1.41 0H0v1.41zM38.59 40l-2.83-2.83 1.41-1.41L40 38.59V40h-1.41zM40 1.41l-2.83 2.83-1.41-1.41L38.59 0H40v1.41zM20 18.6l2.83-2.83 1.41 1.41L21.41 20l2.83 2.83-1.41 1.41L20 21.41l-2.83 2.83-1.41-1.41L18.59 20l-2.83-2.83 1.41-1.41L20 18.59z'/%3E%3C/g%3E%3C/g%3E%3C/svg%3E");
		}
	</style>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


	<title>CodingQuiz by Gavin</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
		<div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="${pageContext.request.contextPath}/admin">Admin Home <span class="sr-only"></span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/adminResult?sortBy=starttime">User Results</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/adminProfiles">User Profiles</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						For Quizzes
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${pageContext.request.contextPath}/adminCreate">Create Question</a>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/adminUpdate">Update Question</a>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/adminDelete">Delete Question</a>
					</div>
				</li>
			</ul>
		</div>
		<div class="mx-auto order-0">
			<a class="navbar-brand mx-auto" href="#">CodingQuiz</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse w-100 justify-content-end">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/admin">Hi, ${sessionScope.admin}</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
				</li>
			</ul>
		</div>
	</div>
</nav>
	
<div class="container">
	<div class="card border-0 shadow my-5">
	    <div class="card-body p-5">
		  <section>
			<div class="container mt-2">
				<div class="d-flex justify-content-center row">
					<div class="col-md-10 col-lg-10">
						<span class="mx-2">Filter By:  </span>
						<div class="dropdown d-inline-block">
							<button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Category</button>
							<div class="dropdown-menu">
								<a class="dropdown-item <c:if test="${sessionScope.cat eq 'Java'}">active</c:if>" href="${pageContext.request.contextPath}/adminResult?cat=Java" >Java</a>
								<a class="dropdown-item <c:if test="${sessionScope.cat eq 'SQL'}">active</c:if>" href="${pageContext.request.contextPath}/adminResult?cat=SQL" >SQL</a>
								<a class="dropdown-item <c:if test="${sessionScope.cat eq 'Python'}">active</c:if>" href="${pageContext.request.contextPath}/adminResult?cat=Python">Python</a>
							</div>
						</div>
						<div class="dropdown d-inline-block">
							<button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Username</button>
							<div class="dropdown-menu">
								<c:forEach var="user" items="${sessionScope.userList}" varStatus="counter">
									<a class="dropdown-item <c:if test="${sessionScope.user eq user.username}">active</c:if>" href="${pageContext.request.contextPath}/adminResult?user=${user.username}">${user.username}</a>
								</c:forEach>
							</div>
						</div>
						<a class="btn <c:if test="${sessionScope.cat eq 'all' && sessionScope.user eq 'all'}">btn-primary</c:if><c:if test="${sessionScope.cat != 'all' || sessionScope.user != 'all'}">btn-secondary</c:if>" href="${pageContext.request.contextPath}/adminResult?user=all&cat=all" role="button">Show All</a>
						<span class="mx-2">Order By:  </span>
						<a class="btn <c:if test="${sessionScope.sortBy eq 'fullName'}">btn-primary</c:if><c:if test="${sessionScope.sortBy != 'fullName'}">btn-secondary</c:if>" href="${pageContext.request.contextPath}/adminResult?sortBy=fullName" role="button">Full Name</a>
						<a class="btn <c:if test="${sessionScope.sortBy eq 'type'}">btn-primary</c:if><c:if test="${sessionScope.sortBy != 'type'}">btn-secondary</c:if>" href="${pageContext.request.contextPath}/adminResult?sortBy=type" role="button">Category</a>
						<table class="table table-striped">
					  <thead>
						<tr>
						  <th scope="col">Quiz ID</th>
						  <th scope="col">Taken Date</th>
						  <th scope="col">Category</th>
						  <th scope="col">Full Name</th>
						  <th scope="col">No. of Questions</th>
						  <th scope="col">Score</th>
						  <th scope="col"></th>
						</tr>
					  </thead>
					  <tbody>
						<c:forEach var="quiz" items="${sessionScope.qzList}" varStatus="counter">
						<tr>
						  <th scope="row">${quiz.qzid}</th>
						  <td>${quiz.starttime}</td>
						  <td>${quiz.type}</td>
						  <td>${quiz.fullName}</td>
						  <td>10</td>
						  <td>${quiz.result}</td>
						  <td><a href="${pageContext.request.contextPath}/adminQuizResult?qzid=${quiz.qzid}">Details</a></td>
						</tr>
						</c:forEach>
					  </tbody>
					</table>
						<nav>
							<ul class="pagination">
								<c:if test="${requestScope.currentPage != 1}">
									<li class="page-item"><a class="page-link"
															 href="${pageContext.request.contextPath}/adminResult?currentPage=${currentPage-1}">Previous</a>
									</li>
								</c:if>

								<c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
									<c:choose>
										<c:when test="${requestScope.currentPage eq i}">
											<li class="page-item active"><a class="page-link">
													${i} <span class="sr-only"></span></a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link"
																	 href="${pageContext.request.contextPath}/adminResult?currentPage=${i}">${i}</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
									<li class="page-item"><a class="page-link"
															 href="${pageContext.request.contextPath}/adminResult?currentPage=${currentPage+1}">Next</a>
									</li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		  </section>
	    </div>
	</div>
</div>

</body>
</html>