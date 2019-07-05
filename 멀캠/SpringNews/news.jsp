<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.vo.NewsVO, java.util.ArrayList" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#news {
		margin:0 auto;
		width:90%;
	}
	.inputForm {
		margin:auto;
		width: 30%;
	}
	header { text-align : center; }
	h2 { color : #bc3adc; }
	a { text-decoration : none; }
	table { margin : auto; text-align : center; }
	td { border-bottom : 1px dotted gray; }
	tr:hover { background-color : #fde89d; }
	tr.head { color : #800000; font-weight : bold; background-color : #7bb47b; }
	td:nth-child(2){ width : 400px; }

</style>
</head>
<body>
<%
	ArrayList<NewsVO> al = (ArrayList<NewsVO>) request.getAttribute("list");
	if (al != null) {
%>
	<header id="header">
		<h2>뉴스 게시판</h2>
	</header>
	<section id="news">
		<table>
			<tr class="head">
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
	<%
			for (NewsVO data : al) {
	%>
			<tr>
				<td class='<%= data.getId() %>'><%= data.getId() %></td>
				<td class='<%= data.getId() %>' style="text-align:left">
					<a href="/springnews/news?action=listOne&newsid=<%=data.getId()%>">
					<%= data.getTitle() %></a></td>
				<td class='<%= data.getId() %>'>
					<a href="/springnews/news?action=writer&writer=<%= data.getWriter() %>" >
					<%= data.getWriter() %></a></td>
				<td class='<%= data.getId() %>'><%= data.getWritedate() %></td>
				<td class='<%= data.getId() %>'><%= data.getCnt() %></td>
			</tr>
	<% 
			}
	%>
		</table>
	</section>
<%
	}
%>

<%
	if (request.getAttribute("msg") != null){
%>
	<script>
		alert("${msg}");
	</script>
	
<%
	}
%>
	<br><br>
	<section id="search" style="text-align:center">
		<form method="GET" action="/springnews/news">
			<input type="hidden" name="action" value="search">
			<select name="searchType">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="writer">작성자</option>
			</select>
			<input type="text" name="key">
			<input type="submit" value="뉴스검색">
		</form>
	</section>
	<br><br>
	<div style="text-align:center">
		<button onclick="location.href='/springnews/news'">뉴스홈</button>
		<button onclick="displayForm('1')">뉴스 작성</button>
	</div>
	<br>
	<div id="inputForm" style="display:none; text-align:center">
		<form method="POST" action="/springnews/news">
			<input type="hidden" name="action" value="insert">
			<input id="newsid" type="hidden" name="newsid" >
			<input id="writer" type="text" name="writer" placeholder="작성자명을 입력해주세요" style="width:300px"><br>
			<input id="title" type="text" name="title" placeholder="제목을 입력해주세요" style="width:300px"><br>
			<input id="writedate" type="datetime-local" name="writedate" placeholder="작성일을 입력하세요" style="width:300px"><br>
			<textarea id="content" rows="15" cols="40" name="content" placeholder="내용을 입력해주세요"></textarea>
			<br>
			<input type="submit" value="저장">
			<input type="reset" value="재작성">
			<input type="button" onclick="displayForm('2')" value="취소">
		</form>	
	</div>
<%
	if (request.getAttribute("readVO") != null){	
		NewsVO vo = (NewsVO) request.getAttribute("readVO");
		
%>
	<div id="contentForm" style="display:'block'; text-align:center">
		<form method="POST" action="/springnews/news">
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="newsid" value=<%= vo.getId() %>>
<!-- 			<input id="newsid" type="hidden" name="newsid" > -->
			<input id="writer" type="text" name="writer" style="width:300px" value= <%= vo.getWriter() %>><br>
			<input id="title" type="text" name="title" style="width:300px" value= <%= vo.getTitle() %>><br>
			<input id="writedate" type="datetime-local" name="writedate" style="width:300px" value= <%= vo.getWritedate() %>><br>
			<textarea id="content" rows="15" cols="40" name="content"><%= vo.getContent() %></textarea>
			<br> 
			<input type="button" value="확인" onclick="displayContentForm('2')">
			<input type="submit" value="수정">
			<input type="button" onclick="location.href = '/springnews/news?action=delete&newsid=' + <%= vo.getId() %>" value="삭제">
		</form>	
	</div>
<%
	}
%>

<script>
	function displayForm(num){
		if (num == 1) document.getElementById("inputForm").style.display = 'block';
		else if (num == 2) document.getElementById("inputForm").style.display = 'none';
	}
	function displayContentForm(num){
		if (num == 1) document.getElementById("contentForm").style.display = 'block';
		else if (num == 2) document.getElementById("contentForm").style.display = 'none';
	}
</script>
</body>
</html>