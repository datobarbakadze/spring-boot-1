<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="${request.contextPath}/forms/css/main.css">
<script src="${request.contextPath}/forms/js/jquery.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script src="${request.contextPath}/forms/js/main.js"></script>

</head>
<body>
<div class="container">
	<div class="rows">
	
		<form class="searchform"  action="/search" method="POST">
			<div class="col col-sm-3 nopadding">
				<label for="username">Username: </label>
				<input id="username" class="text-fields" type="text" name='username'>
			</div>
			
			<div class="col col-sm-3">
				<label for="firstanem">First Name:</label>
				<input id="firstanem" class="text-fields" type="text" name='name'>
			</div>
			
			
			<div class="col col-sm-3 nopadding">
				<label for="lastname">Last name:</label>
				<input id="lastname" class="text-fields" type="text" name='lastname'>
			</div>
			
			
			<div class="col col-sm-3 nopadding ">
				<label for="person_id">Person id:</label>
				<input id="person_id" class="text-fields" type="text" name='personalid'>
			</div>
			<br><br>
			<div class="col col-12 nopadding btncont">
				<a href="/search" class="btn btn-info pull-right bootbtnstyle">
					<span class="glyphicon glyphicon-plus"></span>
					გასუფთავება
				</a>
				<button type="submit"  class="btn btn-info pull-right bootbtnstyle"><span class="glyphicon glyphicon-search"></span>&nbsp; ძებნა</button>
				
				<a href="/home" class="btn btn-info bootbtnstyle">
				<span class="glyphicon glyphicon-plus"></span>
				ახალი მომხმარებელი</a>
			</div>
		</form>
	</div>
	<br>
	<div class="rows">
			<table class="table table-striped pull-left" style="width:55%">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Username</th>
						<th scope="col">First name</th>
						<th scope="col">Last name</th>
						<th scope="col">Phone number</th>
						<th scope="col">Status</th>
					<tr>
				</thead>
				<tbody>
					<!-- for loop   -->
					<c:forEach items="${persons}" var="person" varStatus="loop">
					<tr data-user-id="${person.id}" id="user-row-${person.id}" class="user-row">
						<th>${loop.index+1}</th>
						<td class="user-row-username">${person.username }</td>
						<td class="user-row-name">${person.name }</td>
						<td class="user-row-lastname">${person.lastname }</td>
						<td class="user-row-phonenumber">${person.phonenumber}</td>
						<td class="user-row-status"><span style="background-color:${person.status==1 ? "#0b2e13" : "#491217"}">${person.status==1 ? "აქტიური" : "ბლოკირებული"}</span></td>
					<tr>
					</c:forEach>
					<!-- end of for loop  -->
				</tbody>
			</table>
		<div style="width:43%" class="pull-right right-bar-container" >
			<div class="btn-container">
				<button  class="btn btn-info bootbtnstyle update-btn"  ><span class="glyphicon glyphicon-edit"></span>&nbsp; რედაქტირება</button>
				<button  class="btn btn-info bootbtnstyle block-btn pull-right" ><span class='glyphicon glyphicon-minus-sign'></span>&nbsp;ბლოკირება</button>
				<button  class="btn btn-info bootbtnstyle change-pass-btn" ><span class='glyphicon glyphicon-flash'></span>&nbsp;პაროლის შეცვლა</button>
			</div>
			<table class="table" id="user-detail-table">
				<tr>
					<td>მომხ სახელი:</td>
					<td id="username-val">Jhon Doe</td>
				</tr>
				<tr>
					<td>სახელი:</td>
					<td id="firstname-val">Jhon</td>
				</tr>
				<tr>
					<td>გვარი:</td>
					<td id="lastname-val">Doe</td>
				</tr>
				<tr>
					<td>პირადი N:</td>
					<td id="personalid-val">54545455054</td>
				</tr>
				<tr>
					<td>დაბ თარიღი:</td>
					<td id="birthdate-val">28-19-2019</td>
				</tr>
				<tr>
					<td>ტელეფონი</td>
					<td id="phone-val">598979100</td>
				</tr>
				<tr>
					<td>IP შუალედი</td>
					<td id="ip-val">192,168,1,1</td>
				</tr>
				<tr>
					<td>სტატუსი</td>
					<td id="status"><span>აქტიური</span></td>
				</tr>
			</table>
			
			<table class="table table-dark" id="user-update-table">
				<tr>
					<td>მომხ სახელი:</td>
					<td ><input type="text" class="update-fields" name="username-update"></td>
				</tr>
				<tr>
					<td>სახელი:</td>
					<td ><input type="text" class="update-fields" name="firstname-update"></td>
				</tr>
				<tr>
					<td>გვარი:</td>
					<td><input type="text" class="update-fields" name="lastname-update"></td>
				</tr>
				<tr>
					<td>პირადი N:</td>
					<td><input type="text" class="update-fields" name="personalid-update"></td>
				</tr>
				<tr>
					<td>დაბ თარიღი:</td>
					<td ><input type="date" class="update-fields" name="birthdate-update"></td>
				</tr>
				<tr>
					<td>ტელეფონი</td>
					<td ><input type="text" class="update-fields" name="phone-update"></td>
				</tr>
				<tr>
					<td>IP შუალედი</td>
					<td id="ip-val"><input type="text" class="update-fields" name="ip-update"> - <input type="text" class="update-fields" name="subnetmask-update"></td>
				</tr>
				<input type="hidden" name="id-update">
				<!-- <tr>
				<td><button  class="btn btn-info bootbtnstyle pull-left" id="update-action-btn" >რედაქტირება</button></td>
				</tr> -->
			</table>
			<table class="table table-dark" id="user-password-table">
				<tr>
					<td>ახალი პაროლი:</td>
					<td ><input type="password"  name="password-input"></td>
				</tr>
				<tr>
				<td><button  class="btn btn-info bootbtnstyle pull-left" id="pass-update-btn" >პაროლის განახლება</button></td>
				</tr> 
			</table>
			
		</div>
	</div>
</div>
</body>
</html>