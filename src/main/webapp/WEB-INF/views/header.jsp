<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1 class="logo">
    <a href="./" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo"><img src="img/naver_white_log.png"></span> </a>
    <a href="./myreservation.html" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
</h1>
<c:choose>
	<c:when test="${sessionScope.memberEmail!=null}">
		<span>
			<a href="myreservation?reservationEmail=${sessionScope.memberEmail}" class="btn_my"><span class="viewReservation" title="나의 예약">${sessionScope.memberEmail}</span></a>
			<a href="logout" class="btn_my"><span id="logout" title="로그아웃">로그아웃</span></a>
		</span>
	</c:when>
	<c:otherwise>
	   	<a href="bookinglogin" class="btn_my"> <span class="viewReservation" title="예약확인">예약확인</span> </a>
	</c:otherwise>
</c:choose>
