<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"  uri="http://www.springframework.org/security/tags"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Side Bar</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>
<body>
	<div class="container">

		<!-- Sidebar -->
		<div class="w3-sidebar w3-light-grey w3-bar-block" style="width: 10%">
			<h3 class="w3-bar-item">Menu</h3>
			<a href="/home" class="w3-bar-item w3-button">Home</a>
			<security:authorize access="hasRole('ROLE_USER')">
			<a href="/addMarks" class="w3-bar-item w3-button">Fill marks</a>
			</security:authorize>
			<security:authorize access="hasRole('ROLE_ADMIN')">
			 <a href="/createFaculty" class="w3-bar-item w3-button">Create faculty</a>
			</security:authorize>
			<security:authorize access="hasRole('ROLE_ADMIN')">
			<a href="/showAllRequest" class="w3-bar-item w3-button">Show all requests</a>
			</security:authorize>
			<security:authorize access="hasRole('ROLE_ADMIN')">
			<a href="/showAllFacultys" class="w3-bar-item w3-button">Show university</a>
			</security:authorize>

		</div>
		
			<div style="margin-left: 10%; hight: 50px">

			<div class="w3-container w3-teal">					

			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<form id="logoutForm" method="POST" action="${contextPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>

				<h2>
					University        Welcome ${pageContext.request.userPrincipal.name} | <a
						onclick="document.forms['logoutForm'].submit()">Logout</a>
				</h2>

			</c:if>
			</div>
			</div>


	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>