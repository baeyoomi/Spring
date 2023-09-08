<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int total = (int)request.getAttribute("total");  //전체가입자 수

	List<ArrayList<String>> member_data = (List<ArrayList<String>>)request.getAttribute("member_data");
	int many = member_data.size();

	String part = (String)request.getAttribute("part");
	String sel = "";
	//null 값을 조건에서 1순위로 체크 후 그리고 해당 데이터를 어떻게 처리할지 코드를 작성
	if(part != null && part.equals("tel") ){
		sel = "selected";
	}
%>  
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>고객 리스트 페이지</title>
<link rel="shortcut icon" href="#">
</head>
<body>

<p>회원가입 고객 리스트 가입자수 : <%=total%>명</p>
	<table border="1" width="1000">
		<thead>
			<tr>
				<th width="5%">번호</th>
				<th width="20%">아이디</th>
				<th width="30%">이메일</th>
				<th width="20%">연락처</th>
				<th width="5%">나이</th>
				<th width="20%">가입일자</th>
			</tr>
		</thead>
		<tbody>
		<!-- database에 내용이 출력 파트 -->
		
		<!-- 배열 값 중 첫번째 아이디값을 확인하여 값이 없을 경우에 대한 조건 -->
		<% if(member_data.get(0).get(1) == "") { %>
		<tr align="center">
		<td colspan="6">검색한 내용을 찾을 수 없습니다.</td>
		</tr>
		<% 
		} else {   //아이디 값이 null이 아닐 경우
			int w = 0;
			do{
		%>
		<!-- 아름답게 질문을....... -->
			<tr align="center">
				<td><%=total-- %></td>
				<td><%=member_data.get(w).get(1) %></td>
				<td><%=member_data.get(w).get(2) %></td>
				<td><%=member_data.get(w).get(3) %></td>
				<td><%=member_data.get(w).get(4) %></td>
				<td><%=member_data.get(w).get(5).substring(0,10) %></td>
			</tr>
		<% 
			w++;
			}while(w < many);
		}
		%>			
		</tbody>
	</table>


	<br><br>
	<form id="f" method="get" action="./spring6ok.do" onsubmit="return idsearch()">
	검색 : 
	<!-- 검색시 분류 설정을 하여 검색 되도록 함 -->
	<select name="part">
		<option value="id" selected>아이디</option>
		<option value="tel" <%=sel %> >연락처</option>
	</select>
	<!-- 끝 -->
	<input type="text" name="search">	
	<input type="submit" value="검색">
	<input type="button" value="전체목록" onclick="alldata()">
	</form>
</body>
<script>
//var word = "장바구니의 정보를 출력하는 페이지 바구니";
//var word2 = word.trim(); //앞,뒤에 공백만 삭제
//var word2 = word.replace(" ","");  //문장 전체중 첫번째 나오는 해당 단어만 변경
//var word2 = word.replaceAll("바구니","aaa"); //문장 전체의 해당 단어를 변경 (나중에 욕설같은거 막을때 쓸 수 있음)
//console.log(word2);

	//검색한 단어를 입력값에 유지시키는 스크립코드
	var wd = decodeURI(window.location.search);
	//console.log(wd);  // ?part=id&search=hanpak33
	if(wd != ""){
		var sh = wd.split("&search=");
		f.search.value = sh[1];		
	}
	else{
		f.search.value = "";
	}
	
	function alldata(){
		location.href="./spring6ok.do"
	}

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