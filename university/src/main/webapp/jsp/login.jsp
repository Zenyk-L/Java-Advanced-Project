<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link type="text/css" href="login.css" rel="stylesheet">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script
            src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script
            src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

    <script type="text/javascript">

        $(document).ready(function() {
            var selItem = localStorage.getItem("locales");

            var paramsFromURL = new window.URLSearchParams(window.location.search);

            if(paramsFromURL.get('lang') == null){
                localStorage.setItem("locales", selItem);
                    window.location.replace('?lang=' +selItem);
                }

            $('#locales').val(selItem);

            $('#locales').change(function(){
                var selectedOption = $('#locales').val();
                console.log("selectedOption "+selectedOption);
                if(selectedOption){
                    localStorage.setItem("locales", selectedOption);
                    window.location.replace('?lang=' + selectedOption);
                }
            });
        });
    </script>

</head>
<body class="w3-teal">
<div class="wrapper fadeInDown ">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class="fadeIn first">
            <img src="https://previews.123rf.com/images/captainvector/captainvector1703/captainvector170309945/74377645-university-logo-element.jpg" id="icon" alt="Login icon"/>
        </div>
        <form method="POST" action="${contextPath}/login" class="form-signin">
            <h2 class="form-heading"><spring:message code="login.title" /></h2>

            <div class="form-group ${error != null ? 'has-error' : ''}">
                <span>${message}</span>
                <input name="email" type="text" class="fadeIn second" placeholder="<spring:message code="login.email" />" value="user@gmail.com"
                       autofocus/>
                <input name="password" type="password" class="fadeIn third" placeholder="<spring:message code="login.password" />" value="user"/>
                <span>${error}</span>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <input class="fadeIn fourth w3-teal" type="submit" value="<spring:message code="login.logIn" />"/>
            </div>
        </form>

        <div>
            <fieldset>
                <label style= "color: black"> <spring:message code="login.choose_language" /> </label>
                <select
                        id="locales">
                    <option value="en" ><spring:message code="login.english"/></option>
                    <option value="ua"><spring:message code="login.ukrainian" /></option>
                </select>

            </fieldset>
        </div>


        <div id="formFooter" class="w3-teal">
            <a class="underlineHover"  href="${contextPath}/registration" ><spring:message code="login.create_account" /></a>
        </div>

    </div>

</div>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>