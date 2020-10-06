<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<jsp:include page="commonHead.jsp"/>
	<link rel="stylesheet" type="text/css" href="css/detail.css">
</head>

<body>
    <div id="container">
        <div class="header fade">
            <header class="header_tit">
				<jsp:include page="header.jsp" />
            </header>
        </div>
        <div class="ct main">
            <div>
                <div class="section_visual">
                    <header>
						<jsp:include page="header.jsp" />
                    </header>
                    <div class="pagination">
                        <div class="bg_pagination"></div>
                        <div class="figure_pagination">
                            <span class="num">1</span>
                            <span class="num off">/ <span>${fn:length(displayInfoResponse.productImages)-1}</span></span>
                        </div>
                    </div>
                    <div class="group_visual">
                        <div>
                            <div class="container_visual">
                                <ul class="visual_img detail_swipe">
                                </ul>
           						<div class="visual_txt">
									<div class="visual_txt_inn">
										<h2 class="visual_txt_tit"> <span>${displayInfoResponse.displayInfo.description}</span> </h2>
										<p class="visual_txt_dsc"></p>
									</div>
								</div>
                            </div>
                            <div class="prev">
                                <div class="prev_inn">
                                    <p class="btn_prev" title="이전">
                                        <!-- [D] 첫 이미지 이면 off 클래스 추가 -->
                                        <i class="spr_book2 ico_arr6_lt"><img src="img/left_arr.png"/></i>
                                    </p>
                                </div>
                            </div>
                            <div class="nxt">
                                <div class="nxt_inn">
                                    <p class="btn_nxt" title="다음">
                                        <i class="spr_book2 ico_arr6_rt"><img src="img/right_arr.png"/></i>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="group_btn_goto"  style="display: none;">
                        <a class="btn_goto_home" title="홈페이지" href="#" target="siteUrl"> <i class="fn fn-home1"></i> </a>
                        <a class="btn_goto_tel" title="전화" href="#"> <i class="fn fn-call1"></i> </a>
						<a class="btn_goto_mail" title="이메일" href="#"> <i class="fn fn-mail1"></i> </a>
                        <a href="#" class="btn_goto_path" title="길찾기"> <i class="fn fn-path-find1"></i> </a>
                        <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
                    </div>
                </div>
                <div class="section_store_details">
                    <!-- [D] 펼쳐보기 클릭 시 store_details에 close3 제거 -->
                    <div class="store_details close3">
                        <p class="dsc">
                        	${displayInfoResponse.displayInfo.content}
                        </p>
                    </div>
                    <!-- [D] 토글 상황에 따라 bk_more에 display:none 추가 -->
                    <button type="button" class="bk_more _open _on">  <span class="bk_more_txt"><img src="img/more_btn.png"/>펼쳐보기</span> <i class="fn fn-down2"></i> </button>
                    <button type="button" class="bk_more _close"> <span class="bk_more_txt"><img src="img/fold_btn.png"/>접기</span> <i class="fn fn-up2"></i></button>
                </div>
                <div class="section_event">
                    <div class="event_info_box">
                        <div class="event_info_tit">
                            <h4 class="in_tit"> <i class="spr_book ico_evt"><img src="img/gift_box.png"/></i> <span>이벤트 정보</span> </h4>
                        </div>
                        <div class="event_info">
                            <div class="in_dsc">[네이버예약 특별할인]<br>R석 50%, S석 60% 할인</div>
                        </div>
                    </div>
                </div>
                <div class="section_btn"> <button type="button" onclick="javascript:goReserve(${displayInfoResponse.displayInfo.id})" class="bk_btn"> <i class="fn fn-nbooking-calender2"><img src="img/calender.png"/></i> <span>예매하기</span> </button> </div>
                <div class="section_review_list">
                    <div class="review_box">
                        <h3 class="title_h3">예매자 한줄평</h3>
                        <div class="short_review_area">
                            <div class="grade_area">
                                <!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
                                <span class="graph_mask"> <em class="graph_value"></em> </span>
                                <strong class="text_value"> <span>${displayInfoResponse.averageScore}</span> <em class="total">/5.0</em> </strong>
                                <span class="join_count"><em class="green">${fn:length(displayInfoResponse.comments)}건</em> 등록</span>
                            </div>
                            <ul class="list_short_review">
                            </ul>
                        </div>
                        <p class="guide"> <i class="spr_book2 ico_bell"><img src="img/bell.png"/></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span> </p>
                    </div>
                    <a class="btn_review_more" href="./review?displayInfoId=${displayInfoResponse.displayInfo.id }"> <span>예매자 한줄평 더보기</span> <i class="fn fn-forward1"></i> </a>
                </div>
                <div class="section_info_tab">
                    <!-- [D] tab 선택 시 anchor에 active 추가 -->
                    <ul class="info_tab_lst">
                        <li class="item _detail">
                            <p class="anchor active"> <span>상세정보</span>
                        </li>
                        <li class="item _path">
                            <p class="anchor"> <span>오시는길</span>
                        </li>
                    </ul>
                    <!-- [D] 상세정보 외 다른 탭 선택 시 detail_area_wrap에 hide 추가 -->
                    <div class="detail_area_wrap">
                        <div class="detail_area">
                        <div class="detail_info">
                                <h3 class="blind">상세정보</h3>
                                <ul class="detail_info_group">
                                    <li class="detail_info_lst">
                                        <strong class="in_tit">[소개]</strong>
                                        <p class="in_dsc">
                                        	${displayInfoResponse.displayInfo.description}
                                        </p>
                                    </li>
                                    <li class="detail_info_lst"> <strong class="in_tit">[공지사항]</strong>
                                        <ul class="in_img_group">
                                            <li class="in_img_lst"> <img alt="" class="img_thumb" src="https://ssl.phinf.net/naverbooking/20170131_238/14858250829398Pnx6_JPEG/%B0%F8%C1%F6%BB%E7%C7%D7.jpg?type=a1000"> </li>
                                        </ul>
                                    </li>
                                    <!-- <li class="detail_info_lst"> <strong class="in_tit">[공연정보]</strong>
                                        <ul class="in_img_group">
                                            <li class="in_img_lst"> <img alt="" class="img_thumb" src="https://ssl.phinf.net/naverbooking/20170131_255/1485825099482NmYMe_JPEG/%B0%F8%BF%AC%C1%A4%BA%B8.jpg?type=a1000"> </li>
                                        </ul>
                                    </li> -->
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- [D] 오시는길 외 다른 탭 선택 시 detail_location에 hide 추가 -->
                    <div class="detail_location hide">
                        <div class="box_store_info no_topline">
                            <a href="#" class="store_location" title="지도웹으로 연결">
                                <img class="store_map img_thumb" alt="map" src="${displayInfoResponse.displayInfoImage.saveFileName}"/>
                                <span class="img_border"></span>
                                <span class="btn_map"><i class="spr_book2 ico_mapview"></i></span>
                            </a>
                            <h3 class="store_name">${displayInfoResponse.displayInfo.description}</h3>
                            <div class="store_info">
                                <div class="store_addr_wrap">
                                    <span class="fn fn-pin2"><img src="img/pin.png"/></span>
                                    <p class="store_addr store_addr_bold">${displayInfoResponse.displayInfo.placeStreet}</p>
                                    <p class="store_addr">
                                        <span class="addr_old">지번</span>
                                        <span class="addr_old_detail">${displayInfoResponse.displayInfo.placeStreet}</span>
                                    </p>
                                    <p class="store_addr addr_detail">${displayInfoResponse.displayInfo.placeName}</p>
                                </div>
                                <div class="lst_store_info_wrap">
                                    <ul class="lst_store_info">
                                        <li class="item"> <span class="item_lt"> <i class="fn fn-call2"><img src="img/call.png"/></i> <span class="sr_only">전화번호</span> </span> <span class="item_rt"> <a href="${displayInfo.tel}" class="store_tel">${displayInfo.tel}</a></span> </li>
                                    </ul>
                                </div>
                            </div>
							<!-- [D] 모바일 브라우저에서 접근 시 column2 추가와 btn_navigation 요소 추가 -->
                            <div class="bottom_common_path column2">
                                <a href="#" class="btn_path"> <i class="fn fn-path-find2"><img src="img/find.png"/></i> <span>길찾기</span> </a>
                                |
								<a href="#" class="btn_navigation before"> <i class="fn fn-navigation2"><img src="img/navigation.png"/></i> <span>내비게이션</span> </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer>
		<jsp:include page="footer.jsp" />
    </footer>
    <div id="photoviwer"></div>
	<jsp:include page="commonScript.jsp"/>
	<script type="text/javascript" src="js/detail.js"></script>
</body>


</html>

















<%-- <html>
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
<button id="reservation_btn" type ="button" onclick="location.href='reservation_view?displayInfoId=${displayInfoResponse.displayInfo.id}'"><img src="img/calendar.png">예약하기</button>
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
<button id="more_comment" type="button"><a href="comments?displayInfoId=${displayInfoResponse.displayInfo.id}">한줄평 더보기&rarr;</a></button>
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
</html> --%>