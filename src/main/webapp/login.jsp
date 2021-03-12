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
	        <a class="navbar-brand mx-auto" href="#">CodingQuiz</a>
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
		  <section>
		    <div class="container">
		      <div class="row justify-content-center">
		        <div class="col-12 col-md-8 col-lg-8 col-xl-6">
		          <div class="row">
		            <div class="col text-center">
		              <h1>Login</h1>
		                <c:if test='${not empty requestScope.errorMessage}'>
			              <div class="alert alert-danger" role="alert">
						    <c:out value="${requestScope.errorMessage}"/>
						  </div>
						</c:if> 
		            </div>
		          </div>
		          <form action="checkLogin" method="post">
		          <div class="row align-items-center">
		            <div class="col mt-4">
		              <input name= "username" type="text" class="form-control" placeholder="Username" required>
		            </div>
		          </div>
		          <div class="row align-items-center mt-4">
		            <div class="col">
		              <input name= "password" type="password" class="form-control" placeholder="Password" required>
		            </div>
		          </div>
		          <div class="row justify-content-start mt-4">
		            <div class="col">
		              <input type="submit" name="submit" value="Submit" class="form-control btn btn-primary mt-4">
		            </div>
		          </div>
				</form>
					<div class="sign-up mt-4">
						Don't have an account? <a href="${pageContext.request.contextPath}/register">Create One</a>
				  	</div>
					<div class="sign-up mt-4">
						Admin login <a href="${pageContext.request.contextPath}/adminLogin">here</a>
					</div>
		        </div>
		      </div>
		    </div>
		  </section>
	    </div>
	  </div>
	</div>

</body>
</html>