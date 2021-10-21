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
						<form:form  method="POST" action="${contextPath}/asseptRequest"
							modelAttribute="request">
							
							<div style="width: 100%; height: 200px; background-image: url('https://kaverisias.com/wp-content/uploads/2018/01/catalog-default-img.gif'); background-size: 100% 100%">
								<div
									style="width:100%; height:100%; background-image:url('data:image/jpg;base64, ${currentRequest.faculty.encodedImage}'); background-size:100% 100%">
								</div>
							</div>

							<table>
							<tr>
								<td>To faculty: </td>
								<td><form:label  path="faculty" style="font-weight: bold">${currentRequest.faculty.name}</form:label></td>
								<td><form:input type= "hidden" path="faculty" value="${currentRequest.faculty.id}" readonly="true" /></td>	
								<td><form:input type= "hidden" path="user" value="${currentRequest.user.id}" readonly="true" /></td>	
								<td><form:input type= "hidden" path="id" value="${currentRequest.id}" readonly="true" /></td>		 						
							</tr>
							<tr >
							<td>Student name: </td>
								<td><form:label  path="user" >${currentRequest.user.firstName}</form:label></td>
							</tr>
							<tr >
							<td>Student last name: </td>
								<td>
								<form:label  path="user" >${currentRequest.user.lastName}</form:label></td>

							</tr>
							</table>
							
							<td><input  class="w3-button w3-block w3-dark-grey" type="submit" value="Accept request" name="submit" /></td>  
							
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