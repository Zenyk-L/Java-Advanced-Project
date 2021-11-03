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

<title><spring:message code="all_requests.title" /></title>

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

			<c:if test="${not empty requests}">
				<c:forEach items="${requests}" var="currentRequest">

					<div class="w3-card-4" style="width: 20%; margin: 2%">
						<form:form  method="POST" action="${contextPath}/asseptRequest"
							modelAttribute="request">
							
							<div style="width: 100%; height: 200px; background-image: url('https://kaverisias.com/wp-content/uploads/2018/01/catalog-default-img.gif'); background-size: 100% 100%">
								<div
									style="width:100%; height:100%; background-image:url('data:image/jpg;base64, ${currentRequest.faculty.encodedImage}'); background-size:100% 100%">
								</div>
							</div>

							<table>
							<tr>
								<td><spring:message code="all_requests.to_faculty" />: </td>
								<td><form:label  path="faculty" style="font-weight: bold">${currentRequest.faculty.name}</form:label></td>
								<td><form:input type= "hidden" path="faculty" value="${currentRequest.faculty.id}" readonly="true" /></td>	
								<td><form:input type= "hidden" path="user" value="${currentRequest.user.id}" readonly="true" /></td>	
								<td><form:input type= "hidden" path="id" value="${currentRequest.id}" readonly="true" /></td>		 						
							</tr>
							<tr >
							<td><spring:message code="all_requests.student_name" />: </td>
								<td><form:label  path="user" >${currentRequest.user.firstName}</form:label></td>
							</tr>
							<tr >
							<td><spring:message code="all_requests.student_lastname" />: </td>
								<td>
								<form:label  path="user" >${currentRequest.user.lastName}</form:label></td>

							</tr>
							</table>
							
							<td><input  class="w3-button w3-block w3-dark-grey" type="submit" value="<spring:message code="all_requests.submit" />Accept request" name="submit" /></td>
							
						</form:form>
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