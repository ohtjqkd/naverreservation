<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="commonHead.jsp"/>
	<link rel="stylesheet" type="text/css" href="css/myreservation.css">
</head>
<body>

<body>
	<div id="container">
		<div class="header">
			<header class="header_tit">
				<jsp:include page = "header.jsp"/>
			</header>
		</div>
		<div class="ct">
			<div class="section_my">
				<!-- 예약 현황 -->
				<div class="my_summary">
					<ul class="summary_board">
						<li class="item">
							<!--[D] 선택 후 .on 추가 link_summary_board -->
							<a href="#" class="link_summary_board on"> <i class="spr_book2 ico_book2"><img src="img/total_reserve.png"/></i> <em class="tit total">전체</em> <div class="figure">0</div> </a>
						</li>
						<li class="item">
							<a href="#" class="link_summary_board"> <i class="spr_book2 ico_book_ss"><img src="img/will_use.png"/></i> <em class="tit confirmed">이용예정</em> <div class="figure">0</div> </a>
						</li>
						<li class="item">
							<a href="#" class="link_summary_board"> <i class="spr_book2 ico_check"><img src="img/used.png"/></i> <em class="tit used">이용완료</em> <div class="figure">0</div> </a>
						</li>
						<li class="item">
							<a href="#" class="link_summary_board"> <i class="spr_book2 ico_back"><img src="img/cancel_refund.png"/></i> <em class="tit canceled">취소·환불</em> <div class="figure">0</div> </a>
						</li>
					</ul>
				</div>
				<!--// 예약 현황 -->

				<!-- 내 예약 리스트 -->
				<div class="wrap_mylist">
					<ul class="list_cards" ng-if="bookedLists.length > 0">
					
					</ul>
					</div>
 					<!--// 내 예약 리스트 -->

					<!-- 예약 리스트 없음 -->
					<div class="err"> <i class="spr_book ico_info_nolist"></i>
						<h1 class="tit">예약 리스트가 없습니다</h1>
					</div>
					<!--// 예약 리스트 없음 -->
				</div>
			</div>
		</div>
		<footer>
			<jsp:include page = "footer.jsp"/>
			
		</footer>

		<!-- 취소 팝업 -->
		<!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
		<div class="popup_booking_wrapper" style="display:none;">
			<div class="dimm_dark" style="display:block"></div>
			<div class="popup_booking refund">
				<h1 class="pop_tit">
					<span>서비스명/상품명</span>
					<small class="sm">2000.0.00.(월)2000.0.00.(일)</small>
				</h1>
				<div class="nomember_alert">
					<p>취소하시겠습니까?</p>
				</div>
				<div class="pop_bottom_btnarea">
					<div class="btn_gray">
						<a href="#" class="btn_bottom"><span>아니오</span></a>
					</div>
					<div class="btn_green">
						<a href="#" class="btn_bottom"><span>예</span></a>
					</div>
				</div>
				<!-- 닫기 -->
				<a href="#" class="popup_btn_close" title="close">
					<i class="spr_book2 ico_cls"></i>
				</a>
				<!--// 닫기 -->
			</div>
		</div>
		<!--// 취소 팝업 -->
		<jsp:include page = "commonScript.jsp"/>
		<script type="text/javascript" src="js/myreservation.js"></script>
	</body>

	</html>
