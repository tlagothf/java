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
		// 페이지처리
		searchForm.action = "/boardList";
		
		// 검색옵션 등록
		let opt = document.createElement('option')
		opt.text='제목';
		opt.value='title'
		searchField.appendChild(opt);
		
		let opt1 = document.createElement('option')
		opt1.text='작성자';
		opt1.value='user_id';
		searchField.appendChild(opt1);
		
		let opt2 = document.createElement('option')
		opt2.text='내용';
		opt2.value='counts';
		searchField.appendChild(opt2);
		
		let trList = document.querySelectorAll("tr")
		// 배열의 각 요소에 이벤트를 적용
		trList.forEach(
			function(item){
			    item.addEventListener('click', function(){
			    let board_no = item.dataset.no;
			    location.href="/detaileboard?board_no="+board_no + "&pageNo=" + ${map.pageDto.pageNo};
		    })
		})
	})
</script>
</head>
<body>
<%@include file="../common/header.jsp" %>
<main>
<h2>자유게시판</h2>
<%@include file="../common/searchForm.jsp" %>
<table class="table table-striped">
	<thead>
	<tr>
		<th>no</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	</thead>
	<c:choose>
		<c:when test="${empty map.list }">
		<tr>
			<td colspan="5">게시판이 비어있습니다.</td>
		</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${map.list }" var="board">
			<!-- 1.태그의 속성에 데이터 값을 보관
				data-* : HTML요소에 추가적인 데이터 속성을 저장하고
						javaScript에서 불러다사용
				 2. 보관된 데이터를 가지고 오는 방법
					요소선택.dataset.변수명 -->
			<tr data-no="${board.board_no }" >
				<td>${board.board_no }</td>
				<td>${board.title }</td>
				<td>${board.user_id }</td>
				<td>${board.created_time }</td>
				<td>${board.counts }</td>
			</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
<%@include file="../common/pageNavi.jsp" %>
</main>

<%@include file="../common/footer.jsp" %>
</body>
</html>