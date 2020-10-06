<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<div id = "title"><button onclick="window.history.back()">arrow</button>회사이름</div>
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
		<c:forEach items="${displayInfoResponse.comments}" var="comment">
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
<a id = "go_top" href = "#">&uarr;Top</a>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.4/handlebars.min.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/detail.js"></script>
</body>
</html>