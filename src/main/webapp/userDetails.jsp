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

    <title>CodingQuiz by Gavin</title>
  </head>
  <body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="container">
	    <div>
	        <a class="navbar-brand" href="${pageContext.request.contextPath}">Student Records</a>
	        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
	            <span class="navbar-toggler-icon"></span>
	        </button>
	    </div>

	  	<form class="form-inline" action="search" method="post">
		  <div class="row">
		    <div class="col">
		      <input class="form-control mr-sm-2" type="search" placeholder="Search" name="Search"> 
		      	<script>
					$("#Search").autocomplete("getdata.jsp");
				</script>
		    </div>
		    <div class="col">
		      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		    </div>
		  </div>
		</form>
	  </div>
	</nav>

<div class="container mt-5">
    <div class="d-flex justify-content-center row">
        <div class="col-md-10 col-lg-10">
		<ul class="list-group">
		  <li class="list-group-item">Student's id is: ${requestScope.student.sid}</li>
		  <li class="list-group-item">Name: ${requestScope.student.firstName} ${sessionScope.student.lastName}</li>
		  <li class="list-group-item">Phone: ${requestScope.phone}</li>
		  <li class="list-group-item">Email: ${requestScope.email}</li>
		  <li class="list-group-item">Date of Birth: ${requestScope.dob}</li>
		</ul>
	 </div>
   </div>
 </div>
</body>
</html>