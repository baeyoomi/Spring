<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Spring 기초1</title>
</head>
<body>

<header>
<%@ include file="./top.jsp"%>
</header>

<!--  
$ { } : 표현식 코드 문법 (JSTL)
< % = % > : JSP 전용 코드 (주석은 <%-- --%>)
-->    

<%=request.getAttribute("name") %>
<br>
고객명 : ${name}

</body>
</html>