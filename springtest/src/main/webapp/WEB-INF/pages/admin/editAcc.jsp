<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Demo</title>
<script language="javascript" type="text/javascript" 
   src="<c:url value="/resources/js/script.js" /> " >
   </script>
</head>
<body>
<h1>Add Account</h1>



<sf:form method="post" modelAttribute="account"
	onsubmit="return chkeform();"
		enctype="multipart/form-data">
<fieldset>
<table>
<tr>
<th>Account Id:</th>
<td><sf:label path="id">${acc.id}</sf:label></td>
<td><sf:errors path="id" /></td>
</tr>
<tr>
<th>First Name:</th>
<td><sf:input id="ipfname" path="fname" value="${acc.fname}" size="15" onblur="validfname();"/></td>
<td id="fnameer"><sf:errors path="fname" /></td>
</tr>
<tr>
<th>Last Name:</th>
<td><sf:input id="iplname" path="lname" value="${acc.lname}" size="15" onblur="validlname();"/></td>
<td id="lnameer"><sf:errors path="lname" /></td>
</tr>
<tr>
<th>Birthday:</th>
<td><sf:input id="ipbirthday" path="birthday" value="${acc.birthday}" size="15" onblur="validbday();"/></td>
<td id="birthdayer"><sf:errors path="birthday" /></td>
</tr>
</table>
<input name="comit" type="submit" value="Save Account" />
</fieldset>
</sf:form>

</body>
</html>