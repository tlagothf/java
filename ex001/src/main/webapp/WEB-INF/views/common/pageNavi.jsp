<%@page import="java.util.function.Function"%>
<%@page import="com.spring.ex001.dto.PageDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function go(pageNo) {
		// searchForm
		searchForm.pageNo.value = pageNo;
		// searchForm 을 submit
		searchForm.submit();
		//alert(pageNo)
		//document.location='/memberList?pageNo=' + pageNo;
		//location.href='bookList?pageNo='+pageNo
		
	}
</script>
</head>
<body>
<c:set var="pageDto" value="${map.pageDto }"></c:set>
<!--  
${map.pageDto } <br>
startNo : ${pageDto.startNo }
/ endNo : ${pageDto.endNo }
/ prev : ${pageDto.prev }
/ next : ${pageDto.next }
-->
<nav aria-label="...">
  <ul class="pagination justify-content-center">
  <!-- 이전 블럭 -->
    <c:choose>
  	<c:when test="${pageDto.prev }">
    <li class="page-item ">
      <a class="page-link" href="javascript:go(1)"><<</a>
    </li> 	
  	</c:when>
  	<c:otherwise>
  <!-- disabled : 비활성화 - 화면에 보여주는데 누르지 못하게 -->
  	    <li class="page-item disabled">
      <a class="page-link"><<</a>
  	</c:otherwise>
  </c:choose>
  
  <c:choose>
  	<c:when test="${pageDto.prev }">
    <li class="page-item ">
      <a class="page-link" href="javascript:go(${pageDto.startNo - 1 })">Previous</a>
    </li> 	
  	</c:when>
  	<c:otherwise>
  <!-- disabled : 비활성화 - 화면에 보여주는데 누르지 못하게 -->
  	    <li class="page-item disabled">
      <a class="page-link">Previous</a>
  	</c:otherwise>
  </c:choose>

    <!-- 페이지번호 -->
    <c:forEach begin="${pageDto.startNo }" end="${pageDto.endNo }" var="pageNo">
   	<c:choose>
   		<c:when test="${pageNo eq pageDto.pageNo}">
    <li class="page-item active" aria-current="page">
      <a class="page-link" href="">${pageNo }</a>
    </li>
   		</c:when>
   		<c:otherwise>
   		<!-- 일반 -->
   			    <li class="page-item"><a class="page-link" href="javascript:go(${pageNo })">${pageNo }</a></li>
   		</c:otherwise>
   	</c:choose>
    
    </c:forEach>
    <!-- 일반 -->


    <!-- 다음 블럭 -->
    
    <c:choose>
  	<c:when test="${pageDto.next }">
    <li class="page-item ">
      <a class="page-link" href="javascript:go(${pageDto.endNo + 1 })">Next</a>
    </li> 	
  	</c:when>
  	<c:otherwise>
  <!-- disabled : 비활성화 - 화면에 보여주는데 누르지 못하게 -->
  	    <li class="page-item disabled">
      <a class="page-link">Next</a>
  	</c:otherwise>
  </c:choose>
    <c:choose>
  	<c:when test="${pageDto.next }">
    <li class="page-item ">
      <a class="page-link" href="javascript:go(${pageDto.realEndNo })">>></a>
    </li> 	
  	</c:when>
  	<c:otherwise>
  <!-- disabled : 비활성화 - 화면에 보여주는데 누르지 못하게 -->
  	    <li class="page-item disabled">
      <a class="page-link">>></a>
  	</c:otherwise>
  </c:choose>
  </ul>
</nav>
</body>
</html>