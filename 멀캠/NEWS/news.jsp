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
		text-align:center;
		margin:auto;
		width:90%;
	}
	#inputForm {
		margin:0 auto;
		width: 30%;
	}
	h2 {
		color:blue;
	}
	a {
		decoration : none;
	}
	td {
		border-bottom : 1px dotted green;
	}
	tr:hover {
		background-color : gray;
	}
	td:nth-child(2){
		width : 400px;
	}
</style>
</head>
<body>
<%
	ArrayList<NewsVO> al = (ArrayList<NewsVO>) request.getAttribute("list");
	if (al != null) {
%>
	<section id="news">
	<h2>뉴스 게시판</h2>
	<table>
		<tr>
			<td style="color:red">번호</td>
			<td style="color:blue">제목</td>
			<td style="color:pink">작성자</td>
			<td style="color:gray">작성일</td>
			<td style="color:green">조회수</td>
		</tr>
<%
		for (NewsVO data : al) {
%>
		<tr>
			<td class='<%= data.getId() %>'><%= data.getId() %></td>
			<td class='<%= data.getId() %>' style="text-align:left">
				<a href="/mvc/news?action=read&newsid=<%=data.getId()%>">
				<%= data.getTitle() %></a></td>
			<td class='<%= data.getId() %>'><%= data.getWriter() %></td>
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
	if (request.getAttribute("readVO") != null){	
		NewsVO vo = (NewsVO) request.getAttribute("readVO");
%>
	<script>
		window.onload = function() {
			setButton('<%= vo.getId() %>');
			document.getElementById("inputForm").style.display = 'block';
			document.getElementById("title").value = "<%= vo.getTitle() %>";
			document.getElementById("writer").value = "<%= vo.getWriter() %>";
			var str = "<%= vo.getWritedate() %>";
			var ary = str.split(/\D+/g);
			var wdate = ary[0] + "-" + ary[1] + "-" + ary[2] + "T" + ary[3] 
								+ ":" + ary[4];
			document.getElementById("writedate").value = wdate;
			document.getElementById("content").value = "<%= vo.getContent() %>";
		};
	</script>
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
	<br>
	<div style="text-align:center">
		<button onclick="displayForm('1')")>뉴스 작성</button>
	</div>
	<br>
	<div id="inputForm" style="display:none;">
		<form method="POST" action="/mvc/news">
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

<script>
	function setButton(newsid) {
		var dom = document.querySelector('#inputForm [type=submit]'); 
		dom.value="확인";
		dom.addEventListener("click", function(e){
			e.preventDefault();
			document.getElementById("inputForm").style.display = 'none';
		} )
		
		dom = document.querySelector('#inputForm [type=reset]');
		dom.value="수정";
		document.getElementById('newsid').value = newsid;
		document.querySelector('#inputForm [type=hidden]').value = "update";
		dom.type="submit";

		dom = document.querySelector('#inputForm [type=button]');
		dom.value = "삭제";
		dom.addEventListener("click", function(e){
			e.preventDefault();
			location.href = "/mvc/news?action=delete&newsid=" + newsid;
		} )
	} 
	function displayForm(num){
		if (num == 1) document.getElementById("inputForm").style.display = 'block';
		else if (num == 2) document.getElementById("inputForm").style.display = 'none';
	}
</script>
</body>
</html>