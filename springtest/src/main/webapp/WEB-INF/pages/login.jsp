<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>

</head>
<body>
<h3>Login Page</h3>

                    <form id="loginForm" class="loginbox" name="loginForm" action="j_spring_security_check" method="post">
                     
                       <table> 
                            
                        <tr>
                       
                            <th>id</th>
                             
                           <td> <input type="text"
                                 id="username"  name="username" />
                        </td>
                       <tr>
                            <th>Password</th>
                             
                            <td><input type="password"
                                id="password" name="password" />
                        </td>
                        <tr>
                            <c:if test="${param.error != null}">
                                <td colspan=2>
                                    Tên đăng nhập hoặc mật khẩu không chính xác
                                    
                                </td>
                            
                               
                            </c:if>
                            <c:if test="${param.loggedout != null }" >
                            <td colspan=2>
                                    Đã đăng xuất
                                </td>
                            </c:if>
                            </tr>
  </table>

                            <button>Đăng nhập</button>
                           
                            </form>

</body>
</html>