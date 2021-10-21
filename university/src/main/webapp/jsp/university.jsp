<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

<title>Welcome</title>

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

			<c:if test="${not empty faculty}">
				<%-- <c:forEach items="${faculty}" var="currentFaculty"> --%>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>StudentQuantity</th>
							<th>RequiredLevel</th>
							<th>StudentPresent</th>
							<th>Image</th>
							<th>Name</th>
							<th>Lastname</th>
							<th>Action</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${faculty}" var="currenfaculty">
							<c:if test="${not empty currenfaculty.users}">

								<c:forEach items="${currenfaculty.users}" var="currentUser">
									<tr>
										<td>${currenfaculty.id}</td>
										<th>${currenfaculty.name}</th>
										<th>${currenfaculty.studentQuantity}</th>
										<th>${currenfaculty.requiredLevel}</th>
										<th>${currenfaculty.count}</th>
										<th><img
											src="data:image/jpg;base64, ${currenfaculty.encodedImage}"
											alt="File not found" style="width: 10%"></th>


										<th>${currentUser.firstName}</th>
										<th>${currentUser.lastName}</th>
										<th><a
											href="deleteUser?userId=${currentUser.id}&facultyId=${currenfaculty.id}">delete</a></th>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty currenfaculty.users}">

								
									<tr>
										<td>${currenfaculty.id}</td>
										<th>${currenfaculty.name}</th>
										<th>${currenfaculty.studentQuantity}</th>
										<th>${currenfaculty.requiredLevel}</th>
										<th>${currenfaculty.count}</th>
										<th><img
											src="data:image/jpg;base64, ${currenfaculty.encodedImage}"
											alt="File not found" style="width: 10%"></th>					
									</tr>
								
							</c:if>
							
						</c:forEach>

					</tbody>

				</table>


				<%-- 	</c:forEach> --%>
			</c:if>


		</div>


	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>