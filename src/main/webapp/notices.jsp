<%@page import="com.Notice"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Notice</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/notice.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Notice Management</h1>
				<form id="formNotice" name="formNotice" method="post" action="notices.jsp">
					admin Name: 
					<input id="adminName" name="adminName" type="text"
						class="form-control form-control-sm"> <br> 
					admin ID:
					<input id="adminID" name="adminID" type="text"
						class="form-control form-control-sm"> <br> 
					admin Header: 
					<input id="adminHeader" name="adminHeader" type="text"
						class="form-control form-control-sm"> <br> 
					admin Content: 
					<input id="adminContent" name="adminContent" type="text"
						class="form-control form-control-sm"> <br> 
					<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidNoticeIDSave" name="hidNoticeIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divNoticeGrid">
					<%
					Notice noticeObj = new Notice();
					out.print(noticeObj.readNotices());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>