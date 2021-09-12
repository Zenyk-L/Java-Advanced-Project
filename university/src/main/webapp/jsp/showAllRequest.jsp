<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<title>All requests</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>
<body>
	<div class="container">

		<!-- Sidebar -->
		<jsp:include page="sideBar.jsp"></jsp:include>

		<!-- Page Content -->
		<div style="margin-left: 10%; display: flex; flex-wrap: wrap">

			<c:if test="${not empty requests}">
				<c:forEach items="${requests}" var="currentRequest">

					<div class="w3-card-4" style="width: 20%; margin: 2%">
						<img
							src="https://kaverisias.com/wp-content/uploads/2018/01/catalog-default-img.gif"
							alt="Faculty" style="width: 100%">
						<div class="w3-container w3-center">
							<h3>To faculty: ${currentRequest.faculty.name}</h3>
							<p>Student name: ${currentRequest.user.firstName}</p>
							<p>Student last name: ${currentRequest.user.lastName}</p>
						</div>
						<button class="w3-button w3-block w3-dark-grey">Accept request</button>
							<%-- <a class="w3-button w3-block w3-dark-grey"
                               href="${contextPath}/addRequestToFaculty?facultyId=${currentFaculty.id}&email=${pageContext.request.userPrincipal.name}">
                                Register</a> --%>
					</div>


				</c:forEach>
			</c:if>


		</div>


	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>