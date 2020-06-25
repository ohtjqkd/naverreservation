<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
<div id = "gnb">
	<img src="img/favicon.ico">
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
<div id = "promotion">
		<ul>
		</ul>
</div>
<nav id = "tab_menu">
	<ul>
		<li class = "on">전체</li>
		<li value = "1">전시</li>
		<li value = "2">콘서트</li>
		<li value = "3">클래식</li>
		<li value = "4">연극</li>
	</ul>
</nav>
<div class="cnt_row">바로 예매 가능한 행사가 <span id="cnt">${productResponse.totalCount}개</span> 있습니다.</div>
<div id = "product_container">
	<section id = "left_container">
		<ul>
			<c:forEach items="${productResponse.items}" var="p" begin="0" step="2">
				<li class="main_content">
					<a href = "detail?displayInfoId=${p.displayInfoId}"> 
						<div class = "main_img"> 
							<img src = "${p.productImageUrl}"> 
						</div> 
						<div class = "description"> 
							<h4>${p.productDescription}</h4> 
						</div> 
						<div class = "content">  
							<h5>${p.productContent}</h5> 
						</div> 
					</a>
				</li>
			</c:forEach>
		</ul>
	</section>
	<section id = "right_container">
		<ul>
			<c:forEach items="${productResponse.items}" var="p" begin="1" step="2">
				<li class="main_content">
					<a href = "detail?displayInfoId=${p.displayInfoId}"> 
						<div class = "main_img"> 
							<img src = "${p.productImageUrl}"> 
						</div> 
						<div class = "description"> 
							<h4>${p.productDescription}</h4> 
						</div>
						<div class = "content">  
							<h5>${p.productContent}</h5> 
						</div> 
					</a>
				</li>
			</c:forEach>		
		</ul>
	</section>
</div>
<div id="more_btn">
	<button type = "button">더보기</button>
</div>
<a id = "go_top" href = "#">&uarr;Top</a>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.4/handlebars.min.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
</body>
</html>
