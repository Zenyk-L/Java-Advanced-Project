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

<title>Create faculty</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>
<body>
	<div class="container">

		<!-- Sidebar -->
		<jsp:include page="sideBar.jsp"></jsp:include>

		<!-- Page Content -->
		<div style="margin-left: 10%">

			<form:form method="POST" action="${contextPath}/addFaculty"
				modelAttribute="faculty" >
				<table>
					<tr>
						<td><form:label path="name">Name</form:label></td>
						<td><form:select path="name">
						<%-- <form:option value="NONE"> --SELECT--</form:option> --%>
    					<form:options items="${namesList}"></form:options>
						</form:select> </td>
					</tr>
					<tr>
						<td><form:label path="studentQuantity">Student Quantity</form:label></td>
						<td><form:input path="studentQuantity" required="required"/></td>
					</tr>
					<c:forEach items="${subjectsList}" var="currentSubject">
					<tr>
						<td><form:checkbox path="subjects" value="${currentSubject}" label="  ${currentSubject} " /></td>
						<%-- <td><c:out value="${currentSubject}" /></td> --%>
					</tr>
					</c:forEach>
					<tr>
						<td><input type="submit" value="Submit" /></td>
					</tr>
				</table>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form:form>

		</div>


	</div>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>