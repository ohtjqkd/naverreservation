/**

 * 
 */
var reservationRes;
var myReservation = {
		ajax:function(){
			$.ajax({
				type:"GET",
				url:pageResource.getAPI(),
				dataType:"json",
				success:function(response){
					console.log(response);
				}
			
			}).done(function(response){
				var reservationInfoResponse = response.reservationInfoResponse;
				var reservations = reservationInfoResponse.reservations;
				reservationRes = {
						"confirmed":[],
						"used":[],
						"canceled":[]
				}
				reservations.forEach(function(e){
					if(e.cancelYn == false){
						if(e.usedYn == true){
							reservationRes["used"].push(e);
						} else {
							reservationRes["confirmed"].push(e);
						}
					} else {
						reservationRes["canceled"].push(e);
					}
				})
				if(response.reservationInfoResponse.size==0){
					document.querySelector(".err").style.display = "block";
				} else {
					myReservation.templating(reservationRes);
					myReservation.setSummaryBoard();
				}
			});
		},
		templating:function(reservations){
			var myReservationTemplate = `
										{{#each this}}
											{{#if this.length}}
											<li class="card {{@key}}">
													<!--[D] 예약확정: .confirmed, 취소된 예약&이용완료: .used 추가 card -->
												<div class=link_booking_details>
													<div class="card_header">
														<div class="left"></div>
														<div class="middle">
															<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book2-->
															<i class="spr_book2 ico_clock"></i>
															{{#if ($eq @key "confirmed")}}
															<span class="tit">예약확정</span>
															{{/if}}
															{{#if ($eq @key "used")}}
															<span class="tit">이용완료</span>
															{{/if}}
															{{#if ($eq @key "canceled")}}
															<span class="tit">취소된 예약</span>
															{{/if}}
														</div>
														<div class="right"></div>
													</div>
												</div>
												{{#each this as | reservation |}}
												<article class="card_item">
													<div href="#" class="link_booking_details">
														<div class="card_body">
															<div class="left"></div>
															<div class="middle">
																<div class="card_detail">
																	<em class="booking_number">No.{{reservation.reservationInfoId}}</em>
																	<h4 class="tit">{{reservation.displayInfo.description}}</h4>
																	<ul class="detail">
																		<li class="item">
																			<span class="item_tit">일정</span>
																			<em class="item_dsc">
																				{{reservation.displayInfo.openingHours}}
																			</em>
																		</li>
																		<li class="item">
																			<span class="item_tit">내역</span>
																			<em class="item_dsc">
																				내역이 없습니다.
																			</em>
																		</li>
																		<li class="item">
																			<span class="item_tit">장소</span>
																			<em class="item_dsc">
																				{{reservation.displayInfo.placeName}}
																			</em>
																		</li>
																		<li class="item">
																			<span class="item_tit">업체</span>
																			<em class="item_dsc">
																				{{reservation.displayInfo.placeStreet}}
																			</em>
																		</li>
																	</ul>
																	<div class="price_summary">
																		<span class="price_tit">결제 예정금액</span>
																		<em class="price_amount">
																			<span>{{transComma reservation.totalPrice}}</span>
																			<span class="unit">원</span>
																		</em>
																	</div>
																	<!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
																	{{#if ($eq @../key "confirmed")}}
																	<div class="booking_cancel">
																		<button class="btn">
																			<span>취소</span>
																		</button>
																	</div>
																	<!-- 취소 팝업 -->
																	<!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
																	<div class="popup_booking_wrapper hide">
																		<div class="dimm_dark" style="display:block"></div>
																		<div class="popup_booking refund">
																			<h1 class="pop_tit">
																				<span>{{reservation.displayInfo.description}}</span>
																			<!-- <small class="sm">{{reservation.displayInfo.openingHours}}(일)</small> -->
																			</h1>
																			<div class="nomember_alert">
																				<p>취소하시겠습니까?</p>
																			</div>
																			<div class="pop_bottom_btnarea">
																				<div class="btn_green">
																					<button class="btn_bottom true">예</button>
																				</div>
																				<div class="btn_gray">
																					<button class="btn_bottom false">아니오</button>
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
																	{{/if}}
																	{{#if ($eq @../key "used")}}
																		<div class="review_write">
																			<button class="btn">
																				<span>예매자 리뷰 남기기</span>
																			</button>
																		</div>
																	{{/if}}
																</div>
															</div>
															<div class="right"></div>
														</div>
														<div class="card_footer">
															<div class="left"></div>
															<div class="middle"></div>
															<div class="right"></div>
														</div>
													</div>
													<a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
												</article>
											{{/each}}
											{{else}}
												<li class="card {{@key}} hide">
													<div class=link_booking_details>
														<div class="card_header">
															<div class="left"></div>
															<div class="middle">
																<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book2-->
																<i class="spr_book2 ico_clock"></i>
																{{#if ($eq @key "confirmed")}}
																<span class="tit">예약확정</span>
																{{/if}}
																{{#if ($eq @key "used")}}
																<span class="tit">이용완료</span>
																{{/if}}
																{{#if ($eq @key "canceled")}}
																<span class="tit">취소된 예약</span>
																{{/if}}
															</div>
															<div class="right"></div>
														</div>
													</div>
											{{/if}}
											</li>
										{{/each}}
										`;
			var bindTemplate = Handlebars.compile(myReservationTemplate);
			document.querySelector(".list_cards").innerHTML = bindTemplate(reservations);
		},
		setSummaryBoard:function(){
			confirmedLength = document.querySelector(".card.confirmed").childElementCount-1;
			usedLength = document.querySelector(".card.used").childElementCount-1;
			canceledLength = document.querySelector(".card.canceled").childElementCount-1;
			document.querySelector(".tit.confirmed").nextElementSibling.innerText = confirmedLength;
			document.querySelector(".tit.used").nextElementSibling.innerText = usedLength;
			document.querySelector(".tit.canceled").nextElementSibling.innerText = canceledLength;
			document.querySelector(".tit.total").nextElementSibling.innerText = confirmedLength+usedLength+canceledLength;	
		},
		btnAction:function(e){
			if(e.target.tagName != "BUTTON") return;
			if(e.target.className == "btn"){
				var targetParentElement = e.target.parentElement;
				if(targetParentElement.className == "booking_cancel"){
					targetParentElement.nextElementSibling.classList.toggle("hide");
				} else if(targetParentElement.className == "review_write"){
					var bookingId = e.target.closest(".card_detail").querySelector(".booking_number").innerText.match(/(?<=No.)\d*/g);
					console.log(bookingId);
					location.href = "reviewWrite?reservationInfoId="+bookingId;
				}
			}
			if(e.target.classList.contains("btn_bottom")){
				var popupWrapper = e.target.closest(".popup_booking_wrapper");
				var cancelButton = popupWrapper.previousElementSibling;
				var targetArticle = e.target.closest("article");
				var targetCard = targetArticle.parentElement;
				if(e.target.classList[1] == "true"){
					var canceledCard = document.querySelector(".card.canceled"); 
					cancelButton.remove();
					popupWrapper.remove();
					canceledCard.appendChild(targetArticle);
					if (targetCard.querySelector(".card_item") == null){
						targetCard.classList.add("hide");
					}
					canceledCard.classList.remove("hide");
					var bookingId = targetArticle.querySelector(".booking_number").innerText.match(/(?<=No.)\d*/g);
					$.ajax({
						type:"PUT",
						url:pageResource.api[pageResource.page]+"/"+bookingId,
						dataType:"json",
						success:function(response){
							console.log("cancel complete!");
						}
					})
				} else {
					popupWrapper.classList.toggle("hide");
				}
			}
			myReservation.setSummaryBoard();
		}
}




document.addEventListener("DOMContentLoaded",function(e){
	myReservation.ajax();
});

document.querySelector(".wrap_mylist").addEventListener("click", function(e){
	myReservation.btnAction(e);
});