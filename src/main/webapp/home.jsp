<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="${pageContext.request.contextPath}">Home <span class="sr-only"></span></a>
				</li>
				<c:if test='${not empty sessionScope.user}'>
					<li class="nav-item">
						<a class="nav-link" href='${pageContext.request.contextPath}/userPanel'>My Results</a>
					</li>
				</c:if>
				<c:if test='${empty sessionScope.user}'>
					<li class="nav-item">
						<a class="nav-link disabled" href='#'>My Results</a>
					</li>
				</c:if>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/feedback">Feedback</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="mailto:yunruixie42@gmail.com">Contact us</a>
				</li>
			</ul>
		</div>
		<div class="mx-auto order-0">
			<a class="navbar-brand mx-auto" href="${pageContext.request.contextPath}">CodingQuiz</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse w-100 justify-content-end">
			<ul class="navbar-nav ml-auto">
				<c:if test='${empty sessionScope.user}'>
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a>
					</li>
				</c:if>
				<c:if test='${not empty sessionScope.user}'>
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/userPanel">Hi, ${sessionScope.user}</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>


<div class="container">
	<div class="card border-0 shadow my-5">
		<div class="card-body p-5">
			<div class="card-group">
				<div class="card">
					<img class="card-img-top" height="400" src="https://www.clipartkey.com/mpngs/m/259-2594332_java-logo.png" alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title">Take a Java Test</h5>
						<p class="card-text">You will answer 10 questions about Java.</p>
						<a href="takeExam?test=Java" class="btn btn-primary">Start</a>
					</div>
				</div>
				<div class="card">
					<img class="card-img-top" height="400" src="https://image.flaticon.com/icons/png/512/29/29165.png" alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title">Take a SQL Test</h5>
						<p class="card-text">You will answer 10 questions about SQL.</p>
						<a href="takeExam?test=SQL" class="btn btn-primary">Start</a>
					</div>
				</div>
				<div class="card">
					<img class="card-img-top" height="400" src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Python-logo-notext.svg/1024px-Python-logo-notext.svg.png">
					<div class="card-body">
						<h5 class="card-title">Take a Python Test</h5>
						<p class="card-text">You will answer 10 questions about Python.</p>
						<a href="takeExam?test=Python" class="btn btn-primary">Start</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>