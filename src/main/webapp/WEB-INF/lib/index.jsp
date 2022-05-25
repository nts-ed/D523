<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String count=(String)request.getAttribute("count");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>クッキーの練習</title>
</head>
<body>
<p>あなたは<%=count %>回目の訪問です。</p>
</body>
</html>