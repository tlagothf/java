<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var checkBox;
	function changeAdminYN(checkBox) {
		window.checkBox=checkBox;
		console.log(checkBox);
		// 태그에 저장된 데이터 속성을 읽어옵니다
		console.log(checkBox.dataset.mamberid); // 사용자ID
		console.log(checkBox.dataset.adminyn);	// 관리자 여부
		
		let changeAdminYN = checkBox.dataset.adminyn == 'Y' ? 'N': 'Y';
		
		// 서버에 전달 할 데이터를 자바스크립트 오브젝트 형태로 생성
		let data = {id : checkBox.dataset.mamberid
					, admin_yn : changeAdminYN};
		
		console.log('data', data);
		// 서버에 POST 방식으로 요청
		// POST 방식으로 요청 시 url 의 다음 인자로 옵션을 지정
		fetch_post('/changeAdminYN', data, fetch_res);
		}
			/*
			method : 'post'
			, headers: {
			"Content-Type": "application/json",
			 // 'Content-Type': 'application/x-www-form-urlencoded',
			}
			// 오브젝트를 json 형식의 문자열로 변경
			, body : JSON.stringify(data)
		})
		// 받아온 데이터를 json형식으로 바꾸어 가져오는 코드
		.then(res=>res.json())
		.then(data=>{
			console.log('data', data);
			// 업데이트 실패
			if (data.res == 0) {
				checkBox.checked = checkBox.dataset.adminyn == 'Y'? true:false;
			}else{
				checkBox.dataset.adminyn = changeAdminYN;
			}
		})
		.catch(error=>{
			console.log('error', error)
		});
		*/
		// 관리자로 변경 또는 일반 사용자로 변경
		// update 문장, id, admon_yn
		// fatch 를 이용해서 post방식으로 ㅂ녀경
		
		// if(true)){
		// admin_yn 값에 따라 체크박스 상태를 변경	
		// } else{
		//	admin_yn 값의 반대로 체크박스 상태를 변경 
		//}
	
	
	//fatch_post('/changeAdminYN', data, 함수명)
	// callback : 바로 실행되지 않고 특정 지점에 실행 할 수 있도록 함수의 이름만 전달
		function fetch_res(data) {
		console.log('data', data);
		// 업데이트 실패
		if (data.res == 0) {
			checkBox.checked = checkBox.dataset.adminyn == 'Y'? true:false;
		}else{
			checkBox.dataset.adminyn = changeAdminYN;
		}
	}

</script>
<!-- 부트스트랩 CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<%@include file="../common/header.jsp" %>
<c:set var="list" value="${map.list }"></c:set>
<main>
	<h2>회원목록</h2>
	${param.searchField }
<!-- <%@include file="../common/searchForm.jsp" %> -->
	<form action="/memberList" name="searchForm" >
		<input type="hidden" name="pageNo" value="${map.pageDto.pageNo }">
		<br>
		<div class="input-group">
			  <select class="form-select" id="searchField" aria-label="Example select with button addon" name="searchField">			  
			    <option value="id" ${param.searchField eq "id" ? "selected" : "" }>아이디</option>
			    <option value="name" ${param.searchField eq "name" ? "selected" : "" }>이름</option>
			    <option value="all">${param.searchField eq "all" ? "selected" : "" }아이디&이름</option>
			  </select>
			  <input type="text" class="form-control" name="searchWord" value="${param.searchWord }">
		  <button class="btn btn-outline-secondary" type="submit">검색</button>
		</div>
	</form>
	<!--  
		<br>searchField - 아이디, 이름, searchWord
		1. 서버에 값을 전달
		2. 서버에서 값을 받아서확인
		3. 쿼리 수정
		4. 쿼리 테스트
	-->	
      <div class="table-responsive small">
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">아이디</th>
              <th scope="col">이름</th>
              <th scope="col">가입일</th>
              <th scope="col">관리자 여부</th>
            </tr>
          </thead>
          <tbody>
                <c:choose>
      	<c:when test="${empty map.list }">
      		<tr> <td colspan="5">회원 정보를 조회 할 수 없습니다</td> </tr>
      	</c:when>
      	<c:otherwise>
          <c:forEach items="${list }" var="member">
	            <tr>
	              <td>${member.rn }</td>
	              <td>${member.id }</td>
	              <td>${member.name }</td>
	              <td>${member.reg_date }</td>	
	              <td>
	              <div class="form-check form-switch">
						<input class="form-check-input" type="checkbox" role="switch"
						data-mamberId = "${member.id }"
						data-adminYN = "${member.admin_yn }" 
						onclick="changeAdminYN(this)"
						 id="adminActionBtn" ${member.admin_yn eq 'Y' ?'checked' : ''}>
				  </div> 
	              </td>
	            </tr>
           </c:forEach>
      	</c:otherwise>
      </c:choose>
          </tbody>
        </table>
      </div>
      
      1. jstl
      2. 반복문 리스트

      3. 페이지 블럭 출력
      
      <%@include file="../common/pageNavi.jsp" %>
      <!-- 
	${map }
	<br>${map.list}
	<br>${map.pageDto }
 -->


</main>
<%@include file="../common/footer.jsp" %>
</body>
</html>