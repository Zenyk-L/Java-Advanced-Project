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

			<form:form method="POST" action="${contextPath}/addMarks"
				modelAttribute="user1">

				<table>
					<tr>
						<h4>Fill your marks</h4>

					</tr>
					<%-- <c:if test="${not empty user.markMap}"> --%>

					<c:forEach var="entry" items="${user1.markMap}">
						<tr>

							<td><form:label path="markMap">"${entry.key}"
									<%-- <c:out value="${entry.key}" /> --%>
								</form:label></td>
							<td><form:input type="number" min="0" max="12"
									path="markMap" value="${entry.value}" required="required" /></td>

						</tr>
					</c:forEach>
					<%-- </c:if> --%>

					<tr>
						<td><input type="submit" value="Submit" name="submit" /></td>
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