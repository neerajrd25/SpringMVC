<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<c:set var="baseUrl" scope="page" value="http://localhost:8090/neeraj" />

<title>Employee Page</title>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}
</style>
</head>
<body>
	<h1>Add a Employee</h1>

	<c:url var="addAction" value="employee/save"></c:url>

	<form:form action="${baseUrl}/${addAction}"
		modelAttribute="employeeObj">
		<form:hidden path="id" />

		<table>



			<tr>
				<td><form:label path="empName">
						<spring:message text="Name" />
					</form:label></td>
				<td><form:input path="empName" /></td>
			</tr>
			<tr>
				<td><form:label path="salary">
						<spring:message text="Salary" />
					</form:label></td>
				<td><form:input path="salary" type="number" /></td>
			</tr>
			<tr>
				<td><form:label path="country">
						<spring:message text="Country" />
					</form:label></td>
				<td><form:input path="country" /></td>
			</tr>
			<%-- <tr>
				<td><form:label path="country">
						<spring:message text="Country" />
					</form:label></td>
				<td><form:input path="country" /></td>
			</tr> --%>
			<tr>
				<td colspan="2"><c:if test="${!empty employeeObj.empName}">
						<input type="submit" value="<spring:message text="Edit Person"/>" />
					</c:if> <c:if test="${empty employeeObj.empName}">
						<input type="submit" value="<spring:message text="Add Employee"/>" />
					</c:if></td>
			</tr>
		</table>
	</form:form>
	<br>
	<a href="${baseUrl}/employee/caching/?id=${employeeList[0][0]}">Caching Demo</a>
	
	
	<h3>Employee List </h3>
	<c:if test="${not empty employeeList}">
		<table class="tg">
			<tr>
				<th width="120">Sr No</th>
				<th width="120">Person Name</th>
				<th width="120">Salary</th>
				<th width="120">Country</th>
				<th width="120">Edit</th>
				<th width="120">Delete</th>
			</tr>
			<c:forEach items="${employeeList}" var="emp" varStatus="loop">
				<tr>
					<td>${loop.index+1}</td>
					<td>${emp[1]}</td>
					<td>${emp[2]}</td>
					<td>${emp[3]}</td>
					<td><a href="${baseUrl}/employee/edit/?id=${emp[0]}">Edit</a></td>
					<td><a href="delete/${emp[0]}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>