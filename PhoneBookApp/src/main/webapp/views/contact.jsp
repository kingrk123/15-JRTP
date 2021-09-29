<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PhoneApp</title>
</head>
<body>

	<h3>Save Contact Here</h3>
	
	<p><font color='green'>${succMsg}</font></p>
	<p><font color='green'>${errMsg }</font></p>
	<form:form action="saveContact?contactId=${contact.contactId }" modelAttribute="contact" method="post">
		<table>
			<tr>
				<td>Contact Name</td>
				<td><form:input path="contactName" /></td>
				<td style="color: red"><form:errors path="contactName" /> </td>
			</tr>

			<tr>
				<td>Contact Email</td>
				<td><form:input path="contactEmail" /></td>
				<td style="color: red"><form:errors path="contactEmail" /> </td>
			</tr>

			<tr>
				<td>Contact Number</td>
				<td><form:input path="contactNumber" /></td>
				<td style="color: red"><form:errors path="contactNumber" /> </td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="save" /></td>
			</tr>

		</table>
	<a href="viewContacts">View All List</a>

	</form:form>
</body>
</html>