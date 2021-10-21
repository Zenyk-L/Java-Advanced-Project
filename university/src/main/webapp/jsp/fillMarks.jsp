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

<title>Fill marks</title>

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

			<form:form id="f" method="POST" action="${contextPath}/addMarks"
				modelAttribute="container">

				<table>
					<tr>
						<h4>Fill your marks</h4>
						

					</tr>

					<c:forEach var="entry" items="${container.marks}"
						varStatus="tagStatus">
						<tr>
							<td><form:label  path="marks[${tagStatus.index}].subject" >"${entry.subject}"</form:label></td>
							<td><form:input type= "hidden" path="marks[${tagStatus.index}].id" value="${entry.id}" readonly="true" /></td>
							<td><form:input type= "hidden" path="marks[${tagStatus.index}].subject" value="${entry.subject}" readonly="true" /></td>
							<td><form:input type="number" min="0" max="12" path="marks[${tagStatus.index}].grade" value="${entry.grade}" /></td>
							
						</tr>
					</c:forEach>

					<tr>
						<td><input type="submit" value="Submit" name="submit" onclick="myFunction()" /></td> 
						<!-- <td><button type="submit" value="Submit" name="submit" onclick="myFunction()" >Submit</button></td> -->
					</tr>
				</table>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form:form>

		</div>

	</div>
	
	<script>
	var form = document.getElementById('f');

	function myFunction() {
	  if (form.checkValidity()) {
	    alert("Marks added Succesful!");
	  }
	}
	</script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>