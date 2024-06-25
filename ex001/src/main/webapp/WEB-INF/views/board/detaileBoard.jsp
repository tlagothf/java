<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.addEventListener('load', ()=>{
		ListBtn.addEventListener('click', ()=>{
			detailBoard.action="/boardList"
			detailBoard.submit();
		});
		
		if (${board.user_id eq id} ) {
			
		
		updateBtn.addEventListener('click', ()=>{
			detailBoard.action="/updateboard"
			detailBoard.submit();
		});
		delBtn.addEventListener('click', ()=>{
			detailBoard.action="/delboard"
			detailBoard.submit();
		});
		}
	});
</script>
</head>
<body>
<%@include file="../common/header.jsp" %>
<h2>게시글 상세페이지</h2>
아이디 : ${id }
로그인아이디 : ${board.user_id }
 <form action="" name="detailBoard">
 	<input type="text" name="pageNo" value="${param.pageNo }">
 	<input type="text" name="board_no" value="${param.board_no}">
 </form>
<table class="table table-striped">
	<tr>
		<th>no</th>
		<th>분류</th>
		<th>제목</th>
		<th>작성자</th>
		<th>내용</th>
		<th>업로드날짜</th>
		<th>조회수</th>
	</tr>

	<tr>
		<td>${board.board_no }</td>
		<td>${board.board_type}</td>
		<td>${board.title}</td>
		<td>${board.contents}</td>
		<td>${board.user_id}</td>
		<td>${board.created_time}</td>
		<td>${board.counts}</td>
	</tr>	
	
	<tr>
		<td colspan="7">
		<button type="button" id="ListBtn">목록</button>
		<!-- 게시글의 작성자만 보여줌 -->
		<c:if test="${board.user_id eq id }">
		<button type="button" id="updateBtn">수정</button>
		<button type="button" id="delBtn">삭제</button>
		</c:if>
		</td>
	</tr>
</table>
<%@include file="../common/footer.jsp" %>
</body>
</html>