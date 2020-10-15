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
<form name="registration" action="/home" method="post" >
    <ul id="registration-list">
        <li><label>Username:</label> <input type='text' name='username' /></li>
        <li><label>Name:</label> <input type='text' name='name' /></li>
        <li><label>LastName:</label> <input type='text' name='lastname' /></li>
        <li><label>Personalid:</label> <input type='number' name='personalid' /></li>
        <li><label>Date:</label> <input type='date' name='date' /></li>
        <li><label>PhoneNumber:</label> <input type='number' name='phonenumber' /></li>
        <li><label>IpAdress:</label> <input type='text' name='ipAdress' value='0.0.0.0' /></li>
        <li><label>Submask:</label> <input type='text' name='subnetmask' value='255.255.255.255' /></li>
        <li><label>Password:</label> <input type='password' name='password' /></li>
        <li><label>repeatpassword</label><input type='password'name='repeatpassword'/></li>
        <li><label>&nbsp;</label> <input type="submit" value="OK" name='save' class="btn"></li>
    </ul>
</form>
</body>
</html>