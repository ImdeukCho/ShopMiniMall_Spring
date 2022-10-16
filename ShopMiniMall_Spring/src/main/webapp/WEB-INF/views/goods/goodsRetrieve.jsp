<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	
	// 카트에 상품 저장
	$("#cart").click(function() {
		$("form").attr("action","loginCheck/cartAdd");
	});	// end cart 
	
	// 주문수량 up, down 버튼
	$("#up").click(function() {
		var result = $("#gAmount").val();
		result = parseInt(result)+1;
		$("#gAmount").val(result);
	}); // end up
	$("#down").click(function() {
		var result = $("#gAmount").val();
		if(parseInt(result)!=1){
			result = parseInt(result)-1;
		}
		$("#gAmount").val(result);
	}); // end down
	
// 장바구니 이벤트 유효성검사 후 GoodsCartServlet 으로 데이터 전달
	$("#cart").click(function() {
		
		if($("#gSize").val()=="사이즈선택"){
			alert("사이즈를 선택하세요");
			event.preventDefault();
		}
		if($("#gColor").val()=="색상선택"){
			alert("색상을 선택하세요");
			event.preventDefault();
		}
		if($("#gAmount").val()=="0"){
			alert("수량을 선택하세요");
			event.preventDefault();
		}
	});	// end cart
	
}); // end ready
</script>

	<c:if test="${!empty mesg}">
		<script>alert("${mesg}상품을 장바구니에 담았습니다.");</script>	
	</c:if>
	<% if(session.getAttribute("mesg") != null){
	session.removeAttribute("mesg");} %>
	
<FORM name="goodRetrieveForm" method="GET" action="#"><!--action을 막음 , 위에서 js코드로 이동하게 함 -->
	    <input type="hidden" name="gImage" value="${goodsRetrieve.gImage }"> <!-- hidden data -->
	    <input type="hidden" name="gCode" value="${goodsRetrieve.gCode }">
	    <input	type="hidden" name="gName" value="${goodsRetrieve.gName }"> 
	    <input	type="hidden" name="gPrice" value="${goodsRetrieve.gPrice }">

	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30">
		</tr>
		<tr>
			<td>
				<table align="center" width="710" cellspacing="0" cellpadding="0"
					border="0" style='margin-left: 30px'>
					<tr>
						<td class="td_default"><font size="5"><b>- 상품 정보 -</b></font>
							&nbsp;</td>
					</tr>
					<tr>
						<td height="5"></td>
					</tr>
					<tr>
						<td height="1" colspan="8" bgcolor="CECECE"></td>
					</tr>
					<tr>
						<td height="10"></td>
					</tr>

					<tr>
						<td rowspan="7"><img src="images/items/${goodsRetrieve.gImage }.gif"
							border="0" align="center" width="300" /></td>
						<td class="td_title">제품코드</td>
						<td class="td_default" colspan="2" style='padding-left: 30px'>
							${goodsRetrieve.gCode }
						</td>
					</tr>
					<tr>
						<td class="td_title">상품명</td>
						<td class="td_default" colspan="2" style='padding-left: 30px'>
							${goodsRetrieve.gName }
						</td>
					</tr>
					<tr>
						<td class="td_title">가격</td>							
						<td class="td_red" colspan="2" style='padding-left: 30px'>
							${goodsRetrieve.gPrice }
						</td>
					</tr>
					<tr>
						<td class="td_title">배송비</td>
						<td colspan="2"><font color="#2e56a9" size="2"
							style='padding-left: 30px'><b> 무료배송</b> </font> <font size="2">(도서
								산간지역 별도 배송비 추가)</font></td>
					</tr>
					<tr>
						<td class="td_title" rowspan="2">상품옵션</td>
						<td colspan="2" style='padding-left: 30px'><select
							class="select_change" size="1" name="gSize" id="gSize">
								<option selected value="사이즈선택">사이즈선택</option>
								<option value="L">L</option>
								<option value="M">M</option>
								<option value="S">S</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" style='padding-left: 30px'><select
							class="select_change" size="1" name="gColor"
							id="gColor">
								<option selected value="색상선택">색상선택</option>
								<option value="navy">navy</option>
								<option value="black">black</option>
								<option value="ivory">ivory</option>
								<option value="white">white</option>
								<option value="gray">gray</option>
						</select></td>
					</tr>

					<tr>
						<td class="td_title">주문수량</td>
						<td style="padding-left: 30px"><input type="text"
							name="gAmount" value="1" id="gAmount"
							style="text-align: right; height: 18px"> <img
							src="images/up.PNG" id="up"> <img src="images/down.png"
							id="down"></td>
					</tr>
				</table>

			</td>
		</tr>
	</table>

	<br> <button >구매</button>
	&nbsp;&nbsp;
	<button id="cart">장바구니</button>
</FORM>
