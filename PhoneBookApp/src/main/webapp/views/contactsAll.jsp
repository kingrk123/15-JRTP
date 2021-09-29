<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Contacts</title>
</head>
<body>
	<h1>All</h1>
	<a href="show">+Add Contacts</a>
	
	<table border="1">
		<thead>
			<tr>
				<th>S.No</th>
				<th>Name</th>
				<th>Email</th>
				<th>Phone</th>
				<th>Operations</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${lst}" var="item" varStatus="count">
		<tr>
			<td> ${count.index+1}  </td>
			<td> ${item.contactName}</td>
			<td> ${item.contactEmail}</td>
			<td> ${item.contactNumber}</td>
			<td> 
				<a href="editcontact?cid=${item.contactId }">Edit</a> &nbsp | &nbsp 
				<a href="deleteContact?cid=${item.contactId }">Delete</a>
			</td>
		</tr>   
</c:forEach>
</tbody>
	</table>
	<c:if test="${currPno>1}">
		<a href="viewContacts?pno=${currPno-1}">Previous</a>
	</c:if>
	<c:forEach begin="1" end ="${tp}" var="pageNo"> 
		
			<c:choose>
				<c:when test="${currPno==pageNo}">
					${pageNo}
				</c:when>
			<c:otherwise>
				<a href="viewContacts?pno=${pageNo}">${pageNo}</a>
			</c:otherwise>
			</c:choose>
		
	</c:forEach>
	<c:if test="${currPno < tp}">
		<a href="viewContacts?pno=${currPno+1}">Next</a>
	</c:if>
</body>
</html>