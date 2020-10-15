<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/forms/css/main.css">
</head>
<body>
<form action="/login" method=post>
<div class="login">
	<ul id=login-list>
	<li><label>UserName</label><input type='text' name='username'/>
	<li><label>Password</label><input type="password" name='password'/>
	<li><input type="submit" value='OK'/>
	
	</ul>
	</div>
	
</form>
</body>
</html>