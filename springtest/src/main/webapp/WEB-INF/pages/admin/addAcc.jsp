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
<link type="text/css" rel="stylesheet" 
   href="<c:url value="/resources/css/styles.css" /> " />
<script language="javascript" type="text/javascript" 
   src="<c:url value="/resources/js/script.js" /> " >
   </script>
<title>Spring Demo</title>
</head>
<body>
<h1>Add Account</h1>



<sf:form method="post" modelAttribute="account" 
	onsubmit="return chkforms();"
		enctype="multipart/form-data">
<fieldset>
<table>
<tr>
<th><sf:label path="id">Account Id(*): </sf:label></th>
<td><sf:input id="ipid" path="id" size="15" onblur="valid();"/></td>
<td id="ider"><sf:errors  path="id"/></td>
</tr>
<tr>
<th><sf:label path="fname">First Name(*): </sf:label></th>
<td><sf:input id="ipfname" path="fname" size="15" onblur="validfname();"/></td>
<td id="fnameer"><sf:errors path="fname" /></td>
</tr>
<tr>
<th><sf:label path="lname">Last Name(*): </sf:label></th>
<td><sf:input id="iplname" path="lname" size="15" onblur="validlname();"/></td>
<td id="lnameer"><sf:errors path="lname" /></td>
</tr>
<tr>
<th><sf:label path="birthday">Birthday: </sf:label></th>
<td><sf:input id="ipbirthday" path="birthday" size="15" onblur="validbday();"/></td>
<td id="birthdayer"><sf:errors path="birthday" /></td>
</tr>
</table>
<input name="comit" type="submit" value="Add Account" />
</fieldset>
</sf:form>

</body>
</html>