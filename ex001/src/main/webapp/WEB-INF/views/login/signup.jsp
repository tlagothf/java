<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#msgBox{
	color:red;
}
main{
	width: 200;
	height: 200;
}
#id{
	display: inline-block;
}
</style>
</head>
<script type="text/javascript" src="/resources/JS/signup.js"></script>
<%@include file="../common/header.jsp" %>
<%@include file="../common/modal.jsp" %>

<body>

<main>
	<h2>회원가입</h2>
	<!--  Form 에 정의된 요소를 서버에 전달 하기 위해서
		수집하려는 Dto 의 필드에 맞춰서 name 속성을 지정 -->
	<form name="signupForm" action="/signupAction">
	
	<div class="row md-3">
	  <div class="row md-3">
	  중복검사성공:1<input type="text" id="checkIdTxt" value="">
	  <div id="msgBox"></div>
	   아이디 : <input type="text" class="form-control"  id="id" name="id">
	  <button type="button" class="btn btn-primary" id="checkIdBtn">아이디중복체크</button>
	  </div>

	</div>
	  <div class="row md-3">
	   비밀번호 : <input type="text" class="form-control"  id="pwTxt" name="pw">
	   <br>비밀번호확인 : <input type="text" id="pwCheckTxt" value="">
	  </div>
	   <button type="button" class="btn btn-primary" id="signupActionBtn">회원가입</button>

</form>
</main>
<%@include file="../common/footer.jsp" %>
</body>
</html>