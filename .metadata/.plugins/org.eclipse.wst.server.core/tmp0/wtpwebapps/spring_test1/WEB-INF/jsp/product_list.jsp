<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="app" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<link rel="shortcut icon" href="#">
</head>
<body>
<h1>상품 리스트</h1>
<app:set var="ea" value="${fn:length(product)}"></app:set>
<p>상품갯수 : ${ea}</p>
	<table border="1" width="1000">
		<thead>
			<tr>
				<th width="13%">번호</th>
				<th width="20%">상품코드</th>
				<th width="35%">상품명</th>
				<th width="16%">상품가격</th>
				<th width="16%">수정삭제</th>
			</tr>
		</thead>
		<tbody>

				<app:set var="product_ea" value="${fn:length(product)}"></app:set>	
				<app:forEach var="product" items="${product}" varStatus="st">
			<tr align="center">
				<td>${product_ea}</td>
				<td>${product[1]}</td>
				<td>${product[2]}</td>
				<td>${product[3]}</td>
				<td>${update}</td>
			</tr>
				<app:set var="product_ea" value="${product_ea-1}"></app:set>
				</app:forEach>
		</tbody>
	</table>
	
</body>
</html>