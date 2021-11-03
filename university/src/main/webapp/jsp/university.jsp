<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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

<title><spring:message code="university.university" /></title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<script
			src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
			src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			var selItem = localStorage.getItem("locales");
			$('#locales').val(selItem ? selItem : 'en');
			$('#locales').change(function(){
				var selectedOption = $('#locales').val();
				if(selectedOption){
					window.location.replace('?lang=' + selectedOption);
					localStorage.setItem("locales", selectedOption);
				}
			});

		});
	</script>

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
							<th><spring:message code="university.id" /></th>
							<th><spring:message code="university.faculty_name" /></th>
							<th><spring:message code="university.quantity" /></th>
							<th><spring:message code="university.level" /></th>
							<th><spring:message code="university.student_present" /></th>
							<th style="width: 20%"><spring:message code="university.image" /></th>
							<th><spring:message code="university.name" /></th>
							<th><spring:message code="university.lastname" /></th>
							<th><spring:message code="university.action" /></th>
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
										<th style="width: 20%"><img
											src="data:image/jpg;base64, ${currenfaculty.encodedImage}"
											alt="File not found" style="width: 10%"></th>


										<th>${currentUser.firstName}</th>
										<th>${currentUser.lastName}</th>
										<th><a
											href="deleteUser?userId=${currentUser.id}&facultyId=${currenfaculty.id}"><spring:message code="university.delete" /></a></th>
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
										<th style="width: 20%"><img
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