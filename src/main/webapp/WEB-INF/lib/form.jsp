<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String name=(String)request.getAttribute("name");
String email=(String)request.getAttribute("email");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Insert title here</title>
</head>
<body>
<form action="sample" method="post">
名前:<input type="text" name="name" value="<%=name %>"><br>

e-mail:<input type="email" name="email" value="<%=email %>"><br>

<input type="submit" value="送信">

</form>
</body>
</html>