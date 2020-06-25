<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="#">

<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/detail.css">
</head>
<body>
<div id = "gnb">
	<div class="logo_img">
		<img src="img/reservation_logo.png">
	</div>
	<div id = "login">
		<c:choose>
			<c:when test="${sessionScope.memberEmail!=null}">
				<a href="reservation/${sessionScope.memberEmail}">${sessionScope.memberEmail}</a>
			</c:when>
			<c:otherwise>
				<a href="loginform">예약확인</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<div id = "product_images">
	<div class = "floating_number"><span id="nth">1</span>/<span id="img_cnt">${fn:length(displayInfoResponse.productImages)-1}</span></div>
	<div class = "slide_dummy">
		<ul>
			<li class="product_image">
				<img src ="${displayInfoResponse.productImages[1].saveFileName}">
			</li>
		</ul>
	</div>
	<c:if test="${fn:length(displayInfoResponse.productImages)-1>1}">
		<div class="left"><img src = "img/left_arr.png"></div>
		<div class="right"><img src = "img/right_arr.png"></div>
	</c:if>
</div>
<div id = "title">
	<div class = "description">${displayInfoResponse.displayInfo.description}</div>
	<div class = "content hide">${displayInfoResponse.displayInfo.content}</div>
</div>
<button id = "more_description">펼쳐보기<img src = "img/more_btn.png"></button>
<div id = "event">
	${displayInfoResponse.displayInfo.event}
</div>
<button id="reservation_btn" type ="button"><img src="img/calendar.png">예약하기</button>
<div id = "comment_container">
	<h4>예약자 한줄평</h4>
	<c:forEach var="i" begin="1" end="5">
		<c:if test="${displayInfoResponse.averageScore>i}">
			<img src="img/red_star.png">
		</c:if>
		<c:if test="${displayInfoResponse.averageScore<=i}">
			<img src="img/gray_star.png">
		</c:if>
	</c:forEach>
	${displayInfoResponse.averageScore}/ 5.0 
	${fn:length(displayInfoResponse.comments)}건 등록
	<div id = "comments">
	<ul>
		<c:forEach items="${displayInfoResponse.comments}" var="comment" end="2">
			<li class = "each_comment">
				<h5>${comment.description}</h5>
				<p>${comment.comment}</p>
				<c:if test="${fn:length(comment.commentImages)} > 0">
					<c:forEach items="${comment.commentImages}" var="image">>
						<img src ="${img.fileName}">
					</c:forEach>
				</c:if>			
				${comment.score}
				${comment.reservationEmail}
				${comment.reservationDate}
			</li>
		</c:forEach>
	</ul>
	</div>	
</div>

<button type="button">예매자 한줄평 더보기&rarr;</button>
<div id="information">
	<nav id = "tab_menu">
		<ul>
			<li id="detail" class = "on">상세정보</li>
			<li id="contact">오시는 길</li>
		</ul>
	</nav>
	<section>
		<h4>[소개]</h4>
		${displayInfoResponse.displayInfo.description}
	
		<h4>[공지사항]</h4>
		<img src = "https://ssl.phinf.net/naverbooking/20170131_238/14858250829398Pnx6_JPEG/%B0%F8%C1%F6%BB%E7%C7%D7.jpg?type=a1000">
	</section>
</div>
<a id = "go_top" href = "#">&uarr;Top</a>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.4/handlebars.min.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/detail.js"></script>
</body>
</html>