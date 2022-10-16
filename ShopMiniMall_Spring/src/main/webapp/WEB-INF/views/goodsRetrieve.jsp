<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>상품상세 페이지입니다.</h1>
	<jsp:include page="common/top.jsp" flush="true"></jsp:include><br>
	<jsp:include page="common/menu.jsp" flush="true"></jsp:include>
	<hr>
	<jsp:include page="goods/goodsRetrieve.jsp" flush="true"></jsp:include>
</body>
</html>