<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!-- 로그인 처리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("form").submit(function () {
			var userid = $("#userid").val();
			var passwd = $("#passwd").val();
				if(userid.length ==0){
					alert("아이디를 입력하세요");
					$("#userid").focus();
					event.preventDefault();
				} else if (passwd.length ==0){
					alert("비밀번호를 입력하세요");
					$("#passwd").focus();
					event.preventDefault();
				}
		});
	});
</script>

<!-- 메세지 처리 -->
<c:if test="${! empty mesg }">
	<script>alert("${mesg}");</script>
	<!-- <script type="text/javascript">
		alert('mesg');
	</script> -->
</c:if>

<form action="login" method="post">
	아이디:<input type="text" name="userid"><br>
	비밀번호:<input type="text" name="passwd"><br>
	<input type="submit" value="로그인">
	<input type="reset" value="취소">
</form>