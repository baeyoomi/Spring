<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<title>페이지2 접근형태 - sessionStorage + get파라미터</title>
</head>
<body>
<input type="button" value="클릭" onclick="abc()">
</body>
<script>
	var today = "";
	var data = "";
	function t(){
		today = Date.now();
		data = sessionStorage.setItem("time",today);
		console.log(today);
	}
	t();
	function abc(){
		//window.open("./index3.jsp?days="+today,"_self","").window.close();
		//top.window.open("./index3.jsp?days="+today,"","");
		//top.window.opener=self;
		//top.self.close();
		window.open("./index3.jsp?days="+today,"","");
		location.href="./login.html";   //창을 안닫고 로그인 창으로 변환시켜버림
	}
</script>
</html>