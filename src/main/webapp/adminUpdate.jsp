<%--
  Created by IntelliJ IDEA.
  User: yunruixie
  Date: 3/5/21
  Time: 10:24 PM
  To change this template use File | Settings | File Templates.
--%>
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
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-12 col-md-8 col-lg-8 col-xl-6">
                            <c:if test='${not empty requestScope.m}'>
                                <div class="alert alert-success" role="alert">
                                    <c:out value="${requestScope.m}"/>
                                </div>
                            </c:if>
                            <c:if test='${not empty requestScope.error}'>
                                <div class="alert alert-danger" role="alert">
                                    <c:out value="${requestScope.error}"/>
                                </div>
                            </c:if>
                            <div class="row">
                                <div class="col text-center">
                                    <h1>Update Question</h1>
                                </div>
                            </div>
                            <form action="adminCRUD?method=update" method="post">
                                <div class="row align-items-center mt-4">
                                    <div class="col">
                                        <input name= "qid" type="text" class="form-control" placeholder="Question ID" required>
                                    </div>
                                </div>
                                <div class="row align-items-center mt-4">
                                    <select name="type" class="form-control" required>
                                        <option disabled selected>Question type</option>
                                        <option>Java</option>
                                        <option>SQL</option>
                                        <option>Python</option>
                                    </select>
                                </div>
                                <div class="row align-items-center mt-4">
                                    <div class="col">
                                        <input name= "desc" type="text" class="form-control" placeholder="Question Description" required>
                                    </div>
                                </div>
                                <div class="row align-items-center mt-4">
                                    <div class="col">
                                        <input name= "a" type="text" class="form-control" placeholder="Option A" required>
                                    </div>
                                </div>
                                <div class="row align-items-center mt-4">
                                    <div class="col">
                                        <input name= "b" type="text" class="form-control" placeholder="Option B" required>
                                    </div>
                                </div>
                                <div class="row align-items-center mt-4">
                                    <div class="col">
                                        <input name= "c" type="text" class="form-control" placeholder="Option C" required>
                                    </div>
                                </div>
                                <div class="row align-items-center mt-4">
                                    <div class="col">
                                        <input name= "d" type="text" class="form-control" placeholder="Option D" required>
                                    </div>
                                </div>
                                <div class="row align-items-center mt-4">
                                    <div class="col">
                                        <input name= "answerindex" type="number" class="form-control" placeholder="Answer Index" required>
                                    </div>
                                </div>
                                <div class="row justify-content-start mt-4">
                                    <div class="col">
                                        <input type="submit" name="submit" value="Submit" class="form-control btn btn-primary mt-4">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>

</body>
</html>
