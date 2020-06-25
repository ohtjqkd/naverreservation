<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id = "gnb">
		<img src="../img/favicon.ico">
		<div id = "login">
		<c:choose>
			<c:when test="${sessionScope.memberEmail!=null}">
				<a href="reservation/${sessionScope.memberEmail}">${sessionScope.memberEmail}</a>
			</c:when>	
			<c:otherwise>
				<a href='loginform'>예약확인</a>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
	<div class = "detail_image">
		<img src = "../${mainImg}">
	</div>
	<div class = "product_desc">
		${displayInfo.productId}
		${displayInfo.placeLot}
		${cnt}
	</div>
	<div class = "evt">
		${productInfo.event}
	</div>
	<div class = "do_reservation">
		<button type = "button">예메하기</button>
	</div>
	<div class = "section_comments">
		예약 한줄평
		${comments[0].score}, ${cnt}건 등록
		<ul class = "comments">
		
		</ul>
	</div>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.4/handlebars.min.js"></script>	
<script src="../js/mainajax.js" type="text/javascript"></script>
</body>
</html>