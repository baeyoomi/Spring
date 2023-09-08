<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//배열값이 null일 경우 jsp에서 해당 HTML코드를 비활성화 시키는 방법
	//out.print(request.getAttribute("member_data"));
	String aa = null;
	try{
		List<ArrayList<String>> member_data = (List<ArrayList<String>>)request.getAttribute("member_data");
		int many = member_data.size();
	}catch(Exception e){
		out.print("오류발생");
		aa = (String)request.getAttribute("member_data");
		if(aa == null){
			out.print(aa);
		}
	}

%>  
<%if(aa != null){ %>  
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>고객 리스트 페이지</title>
</head>
<body>
	<form id="f" method="get" action="./spring6ok.do" onsubmit="return idsearch()">
	검색 : <input type="text" name="search">	<input type="submit" value="검색">
	</form>
</body>
<%} %>
<script>
//var word = "장바구니의 정보를 출력하는 페이지 바구니";
//var word2 = word.trim(); //앞,뒤에 공백만 삭제
//var word2 = word.replace(" ","");  //문장 전체중 첫번째 나오는 해당 단어만 변경
//var word2 = word.replaceAll("바구니","aaa"); //문장 전체의 해당 단어를 변경 (나중에 욕설같은거 막을때 쓸 수 있음)
//console.log(word2);

	function idsearch(){
		//입력에 따른 공백을 제거 후 조건문으로 재확인
		f.search.value = f.search.value.replaceAll(" ","");
		if(f.search.value==""){
			alert("검색할 단어를 입력하세요!!");
			return false;
		}
		else{
			return;
		}
	}
</script>
</html>