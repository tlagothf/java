<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<!-- 부트스트랩 CSS -->
	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	 
	 <style type="text/css">
	 	main {
	 		margin: 0 auto;
	 		padding: 20px
	 		/*width: 800px;
	 		*/
	 	}
	 </style>
	 <script type="text/javascript">
	 window.addEventListener('load', function(){
		 // 버튼선택
		 let bookRegBtn = document.querySelector('#bookRegBtn');
		 // 버튼 클릭이벤트 부여
		 bookRegBtn.addEventListener('click', function(){
			location.href="/bookReg";
		 });
		 // 페이지 이동 /bookReg
	 });
	 </script>
	 
	 <%@include file="../common/header.jsp" %>
</head>
<body>

<main>

<h2>도서목록</h2>
	<form action="/bookList" name="searchForm" >
		<input type="hidden" name="pageNo" value="${map.pageDto.pageNo }">
	</form>
<!--  
<br>
modelId : ${modelId }
<br>
rttrId : ${rttrId }
<br> 총 건수 : ${map.pageDto.totalCnt }
-->

<!-- 관리자인 경우에만 버튼이 보여질 수 있도록 처리 -->
관리자여부 : ${memberDto.admin_yn eq 'Y' }
<c:if test="${memberDto.admin_yn eq 'Y' }">
<button type="button" id="bookRegBtn">도서등록</button>
</c:if>
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">번호</th>
      <th scope="col">제목</th>
      <th scope="col">작가명</th>
      <th scope="col">대여여부</th>
    </tr>
  </thead>
  <tbody>
	  <c:choose>
		<c:when test="${empty map.list }">
			정보가 없습니다
		</c:when>
		<c:otherwise>
			<c:forEach items="${map.list }" var="book">
    <tr>
      <th scope="row">${book.no }</th>
      <!-- 상세페이지에서 리스트로 돌아올 때 페이지 번호를 유지하기 위해서 pageNo를 넘겨줘야함 
      		내장객체 (map) 에 저장된 값을 출력하는 경우 저장된 이름으로 접근해야함
      		요청으로 전달왼 파라메터의 경우 param.이름으로 접근
      -->
      <td ><a href="/datailBook?no=${book.no }&pageNo=${map.pageDto.pageNo}">${book.title }</a> </td>
      <td>${book.author }</td>
      <td>${book.rent_yn_str }</td>
    </tr>
	</c:forEach>
    </c:otherwise>
	</c:choose>
	
  </tbody>
</table>
</main>
<%@ include file="/WEB-INF/views/common/pageNavi.jsp" %>

<%@include file="../common/footer.jsp" %>

<!-- 부트스트랩 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>


</body>
</html>