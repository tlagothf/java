<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="" name="searchForm" >
		<input type="hidden" name="pageNo" value="${map.pageDto.pageNo }">
		<br>
		<div class="input-group">
			  <select class="form-select" id="searchField" aria-label="Example select with button addon" name="searchField">			  
			  </select>
			  <input type="text" class="form-control" name="searchWord" value="${param.searchWord }">
		  <button class="btn btn-outline-secondary" type="submit">검색</button>
		</div>
	</form>
</body>
</html>