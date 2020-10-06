<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="commonHead.jsp"/>
	<link rel="stylesheet" type="text/css" href="css/detail.css">
</head>
<body>
    <div id="container">
		<!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
		<div class="header fade">
			<header class="header_tit">
				<jsp:include page="header.jsp"/>
			</header>
		</div>
        <div class="ct">
            <div class="wrap_review_list">
                <div class="review_header">
                    <div class="top_title gr">
                    	<button class="btn_back fn fn-backward1" onclick="javascript:goBack()"><img src="img/backward_arr.png"></button>
                        <h2 class="title">${displayInfoResponse.displayInfo.description}</h2>
                    </div>
                </div>
                <div class="section_review_list">
                    <div class="review_box">
                        <h3 class="title_h3">예매자 한줄평</h3>
                        <div class="short_review_area">
                            <div class="grade_area"> <span class="graph_mask"> <em class="graph_value"></em> </span> <strong class="text_value"> <span>${displayInfoResponse.averageScore}</span> <em class="total">5.0</em> </strong> <span class="join_count"><em class="green">${fn:length(displayInfoResponse.comments)}건</em> 등록</span> </div>
                            <ul class="list_short_review">
                            </ul>
                        </div>
                        <p class="guide"> <i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span> </p>
                    </div>
                </div>
            </div>
        </div>
        <hr> </div>
		<footer>
	        <jsp:include page="footer.jsp"/>
	    </footer>
        <div id="photoviwer"></div>
        <jsp:include page="commonScript.jsp"/>
		<script type="text/javascript" src="js/detail.js"></script>
</body>

</html>
