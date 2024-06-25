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
<!-- 공통스크립트 -->
<script type="text/javascript" src="/resources/JS/common.js"></script>

<style type="text/css">
.form-control-dark {
  border-color: var(--bs-gray);
}
.form-control-dark:focus {
  border-color: #fff;
  box-shadow: 0 0 0 .25rem rgba(255, 255, 255, .25);
}

.text-small {
  font-size: 85%;
}

.dropdown-toggle:not(:focus) {
  outline: 0;
}
main{
	width: 700px;
	margin: 0 auto;
}

</style>

<script type="text/javascript">
// window.onload=function(){}을 이용해서 이벤트를 추가할 경우, 한개의 이벤트만 적용이 가능
// addEventListener를 이용할 경우, 여러개의 이벤트를 적용 할 수 있다
	window.addEventListener('load',function(){
		let logoutBtn = document.querySelector("#logoutBtn");
		let loginBtn = document.querySelector("#loginBtn");
		let signupBtn = document.querySelector("#signupBtn");
		// 로그인상태면 로그아웃 버튼에 이벤트를 추가
		if(logoutBtn != null){
			// 로그아웃 버튼이 클릭되면 실행 함수를 추가
			logoutBtn.addEventListener('click', function(){
				// 로그아웃 처리 후 로그인 페이지로 이동
				location.href = "/logout";
			});	
		}
		
		// 로그아웃 상태면 로그인 버튼에 이벤트를 추가
		if(logoutBtn == null){
			loginBtn.addEventListener('click', function(){
				// 로그인 페이지로 이동
				location.href = "/login/login";
			});	
		
			signupBtn.addEventListener('click', function(){
				// 회원가입 페이지로 이동
				location.href = "/signup";
			});	
		}

	});
</script>
</head>
<body>



  <div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
      <div class="col-md-3 mb-2 mb-md-0">
        <a href="/" class="d-inline-flex link-body-emphasis text-decoration-none">
          <svg class="bi" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
        </a>
      </div>

      <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><a href="#" class="nav-link px-2 link-secondary">Home</a></li>
        <li><a href="/bookList" class="nav-link px-2">도서목록</a></li>
        <li><a href="/memberList" class="nav-link px-2">회원목록</a></li>
        <li><a href="/boardList" class="nav-link px-2">게시판</a></li>
        <li><a href="#" class="nav-link px-2">FAQs</a></li>
        <li><a href="#" class="nav-link px-2">About</a></li>
      </ul>

      <div class="col-md-3 text-end">
      <c:choose>
      	<c:when test="${empty sessionScope.id }">
	        <button type="button" id="loginBtn" class="btn btn-outline-primary me-2">Login</button>
	        <button type="button" id="signupBtn" class="btn btn-primary">Sign-up</button>      	
      	</c:when>
      	<c:otherwise>
      	 	<button type="button" id="logoutBtn" class="btn btn-outline-primary me-2">Logout</button>
      	</c:otherwise>
      </c:choose>
      </div>
    </header>
  </div>
</body>
</html>