	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="commonHead.jsp"/>
	<link rel="stylesheet" type="text/css" href="css/reviewWrite.css">
</head>
<body>
	<div id="container">
		<div class="header fade">
			<header class="header_tit">
				<jsp:include page="header.jsp"/>
			</header>
		</div>
		<div class="ct">
			<div class="ct_wrap">
				<div class="top_title review_header">
                   	<button class="btn_back fn fn-backward1" onclick="javascript:goBack()"></button>
                    <h2 class="title">${response.productDescription}</h2>
				</div>
				<div class="middle-wrap">
					<form id = "reviewForm" enctype="multipart/form-data">
						<!-- 리뷰 별점 -->
						<div class="write_act">
							<p class="title_star">별점과 이용경험을 남겨주세요.</p>
							<div class="review_rating rating_point">
								<div class="rating">
									<!-- [D] 해당 별점이 선택될 때 그 점수 이하의 input엘리먼트에 checked 클래스 추가 -->
									<input type="checkbox" name="rating2" value="1" id="star1" class="rating_rdo" title="1점">
									<label for="star1" class="span"></label>
									<input type="checkbox" name="rating3" value="2" id="star2" class="rating_rdo" title="2점">
									<label for="star2" class="span"></label>
									<input type="checkbox" name="rating4" value="3" id="star3" class="rating_rdo" title="3점" >
									<label for="star3" class="span"></label>
									<input type="checkbox" name="rating5" value="4" id="star4" class="rating_rdo" title="4점">
									<label for="star4" class="span"></label>
									<input type="checkbox" name="rating6" value="5" id="star5" class="rating_rdo" title="5점">
									<label for="star5" class="span"></label>
									<!-- [D] 0점일 때 gray_star 추기 -->
									<span class="star_rank gray_star">0</span>
								</div>
							</div>
						</div>
						<!-- //리뷰 별점 -->
		
						<!-- 리뷰 입력 -->
						<div class="review_contents write border_light_gray">
						<!-- [D] review_write_info 클릭 시 자신을 숨기고 review_textarea 에 focus를 보낸다. -->
		 					<div class="review_write_info gray">
								<span class="middot">
									실 사용자의 리뷰는 상품명의 더 나은 서비스 제공과 다른 사용자들의 선택에 큰 도움이 됩니다.
								</span><br>
								<span class="middot">
									소중한 리뷰에 대한 감사로 네이버페이 포인트 500원을 적립해드립니다.
								</span>
								<span class="left_space">(단, 리뷰 포인트는 ID 당 1일 최대 5건까지 지급됩니다.)</span>
							</div>
							<textarea maxlength="400" cols="30" rows="10" class="review_textarea border_light_gray" name="reviewText" ></textarea>
						</div>
							<!-- //리뷰 입력 -->
		
						<!-- 리뷰 작성 푸터 -->
						<div class="review_write_footer_wrap">
							<div class="review_write_footer border_light_gray">
								<label class="btn_upload" for="reviewImageFileOpenInput">
									<i class="fn fn-image1" aria-hidden="true"></i>
									<span class="text_add_photo">사진 추가</span>
								</label>
								<div class="guide_review">
									<span>0</span>/400
									<span>(최소5자이상)</span>
								</div>
							</div>
		
							<!-- 리뷰 포토 -->
							<div class="review_photos review_photos_write">
								<div class="item_preview_thumbs">
									<ul class="lst_thumb">
									</ul>
								</div>
							</div>
							<!-- //리뷰 포토 -->
		
						</div>
						<!-- //리뷰 작성 푸터 -->
					</form>
				</div>
	
				<!-- 리뷰 등록 -->
				<div class="box_bk_btn">
					<button class="bk_btn"><span class="btn_txt">리뷰 등록</span></button>
				</div>
				<!-- //리뷰 등록 -->
				
			</div>
		</div>
	</div>
	<footer>
		<jsp:include page="footer.jsp"/>
	</footer>
	<input type="file" class="hidden_input" id="reviewImageFileOpenInput" name="input_photo" accept="image/*" multiple="true">
	<jsp:include page="commonScript.jsp"/>
	<script type="text/javascript" src="js/reviewWrite.js"></script>
</body>
</html>