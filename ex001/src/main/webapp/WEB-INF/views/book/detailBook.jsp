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
	 		width: 800px;
	 	}
	 </style>
	 
<script type="text/javascript">
	window.addEventListener('load', ()=>{
		ListBtn.addEventListener('click', ()=>{
			// 리스트로 이동시 페이지 번호를 전달
			// 페이지 번호를 전달 하지 않으면 무조건 1페이지로(cri 의 기본값)
			// 사용자사 보고 있던 페이지를 그대로 보여주기 우해 pageNo 파라메터가 필요
			
			// 링크를 이용해서 화면을 이동
			// location.href = "/bookList?pageNo=${param.pageNo}";
			
			// form을 이용해서 화면을 이동
			detailForm.action = '/bookList';
			detailForm.submit();
		});
		delBtn.addEventListener('click', ()=>{
			detailForm.action = '/delBook';
			// Form 전송
			detailForm.submit();
			
		});
		editBtn.addEventListener('cilck', ()=>{
			detailForm.action = '/updateBook';
			detailForm.submit();
		})
	});
</script>
	 
<%@include file="../common/header.jsp" %>
</head>
<body>

<main>

<h2>도서 상세페이지</h2>
<form action="" method="get" name="detailForm">
	<input type="text" name="pageNo" value="${param.pageNo }">
	<input type="text" name="no" value="${param.no }">
</form>
<table class="table table-striped">
<thead>
<tr>
	<th scope="col">번호</th>
	<th scope="col">제목</th>
	<th scope="col">작가</th>
	<th scope="col">출판사</th>
	<th scope="col">가격</th>
	<th scope="col">등록일</th>
	<th scope="col">대여여부</th>
	<th scope="col">첨부파일</th>
</tr>
</thead>
<tbody>
	<tr> 
		<th scope="row">${map.book.no }</th>
		<td>${map.book.title }</td>
		<td>${map.book.author }</td>
		<td>${map.book.publisher }</td>
		<td>${map.book.price }</td>
		<td>${map.book.pub_day }</td>
		<td>${map.book.rent_yn_str }</td>
		<td>${map.fileList}</td>
		<c:choose>
			<c:when test="${empty map.fileList }">
				첨부파일이 없습니다
			</c:when>
			<c:otherwise>
				<c:forEach items="${map.fileList }" var="file">
					<a href = "/download?oname=${file.ofilename }&sname=${file.sfilename }">
					${file.ofilename } </a> <br>
					
				</c:forEach>
					<!-- 업로드된 파일을 화면에 이미지로 출력 하는 방법
							파일명의 확장자가 이미지인 경우, 이미지로 보여줄 수 있도록 처리 -->
					<img src="/download?oname=test.jpg&sname=test20240610_15235132.jpg">
					<hr>
					<!-- 서버에서 서비스 하는 경로 /webapp/resources 폴더에 파일이 있는 경우 -->
					<img src="/resources/img/test20240610_15235132.jpg">
			</c:otherwise>
		</c:choose>
	</tr>
	
	<tr>
	<!--  버튼을 클릭하면 이벤트 실행 - 목록으로 이동
			 -->
		<td colspan="4">
			<button type="button" id="ListBtn" class="btn btn-secondary">목록</button>
			<c:if test="${memberDto.admin_yn eq 'Y'}">
			<button type="button" id="editBtn" class="btn btn-success">수정</button>	
			<button type="button" id="delBtn" class="btn btn-danger">삭제</button>	
			</c:if>
		</td>
	</tr>
</tbody>
</table>
<br>


</main>

<%@include file="../common/footer.jsp" %>
</body>
</html>