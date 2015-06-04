<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html 
	xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link type="text/css" rel="stylesheet" 
   href="<c:url value="/resources/css/styles.css" /> " />
<script language="javascript" type="text/javascript" 
   src="<c:url value="/resources/js/script.js" /> " >
   </script>
<title>Spring Demo</title>
</head>
<body>
<h1 class="testt" >Admin Index</h1>

<table class="aTable">
<tr>
<th>Id</th>
<th>AccountID</th>
<th>FirstName</th>
<th>LastName</th>
<th>BirthDay</th>
<th>Edit</th>
<th>Delete</th>
</tr>

<!--<c:forEach var="acc" items="${accList}" varStatus="s"></c:forEach>-->
<tr class="aTable${s.index%2}" th:each="acc,rs: ${accList}">
<td th:text="${rs.count+1+(pn-1)*3}"></td>
<td th:text="${acc.id}">${acc.id}</td>
<td th:text="${acc.fname}"></td>
<td th:text="${acc.lname}"></td>
<td th:text="${acc.birthday}"></td>
<td><a href="editAcc?id=${acc.id}">edit</a></td>
<td>
<form method="post" action="delAcc" id="${acc.id}">
	<input type="hidden" name="id" value="${acc.id}" />
	<input type="button" value="del" onclick="ycdialog('${acc.id}');" />
</form>
</td>
</tr>


</table>

<p>Page
<c:forEach var="i" begin="1" end="${p}" >
<a href="index?page=${i}"><c:out value="${i}" /> </a>
</c:forEach>
</p>
<form method="post" id="addacc" >
<input type="button" value="Add Account" onclick="ycdialog('addacc');" />
</form>

</body>
</html>