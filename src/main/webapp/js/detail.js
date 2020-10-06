
function getDisplayInfoResponse(displayInfoId){
	$.ajax({
		type:"GET",
		url:"product/"+displayInfoId,
		dataType:"json",
		success:function(response){
			for(key in response.displayInfoResponse){
				localStorage[key] = JSON.stringify(response.displayInfoResponse[key]);
			}
		}
	}).done(function(response){
		console.log("ajax completed");
		console.log("response",response);
		console.log("displayInfoResponse", response.displayInfoResponse);
		if(document.URL.includes("detail")){
			setDetail(response);
		} else if(document.URL.includes("review")){
			setReview(response);
		} else if(document.URL.includes("reserve")){
			setReserve(response);
		}
	});
};

function setDetail(response){
		var target = ".visual_img";
		var displayInfoResponse = response.displayInfoResponse;
		var productImages = displayInfoResponse.productImages;
		//slide section
		if(productImages.length-1 < 2){
			document.querySelector(".prev").classList.add("hide");
			document.querySelector(".nxt").classList.add("hide");
			document.querySelectorAll(".spr_book2").forEach(function(e){
				e.classList.add("hide");
			});
		}
		insertImage(".visual_img", response.displayInfoResponse.productImages);
		
		//comment section
		graphSet(response);
		commentTemplating(displayInfoResponse.comments);
		translateDecimalFormat();
}

function setReview(response){
	graphSet(response);
	commentTemplating(response.displayInfoResponse.comments);
	translateDecimalFormat();
}

function graphSet(response){
	var graphWidth = response.displayInfoResponse.averageScore/5*100;
	document.querySelector(".graph_value").style.cssText = `width: ${graphWidth}%;`;
}

function insertImage(target, productImages){
	let imgLen = productImages.length-1;
	let idx = 0;
	productImages.forEach(function(e){
		if((e.type=="et"||e.type=="th")){
			$(target).append(
					`<li class="item"> 
						<img alt="${e.type}" class="img_thumb" src="${e.saveFileName}"> <span class="img_bg"></span>
					</li>`
			);
		}
	})
	var totalWidth = imgLen * 100;
	var itemWidth = 100 / imgLen;
	document.querySelector(target).style.cssText = `width:${totalWidth}%;`;
	document.querySelectorAll(".container_visual .item").forEach(function(e){
		e.style.cssText = `width:${itemWidth}%;`;
	})
}
function commentTemplating(comments){
	var resultTemplate = ""
	var commentTemplate = `
							<li class="list_item">
								<div>
									<h4 class="resoc_name">{{description}}</h4>
									<div class="review_area">
										<p class="review">{{comment}}</p>
										<div class="thumb_area">
											{{~#if hasImages}}
												<a href="{{commentImages.[0].saveFileName}}" class="thumb" title="이미지 크게 보기"> <img width="90" height="90" class="img_vertical_top" src="{{commentImages.[0].saveFileName}}" alt="리뷰이미지"> </a> <span class="img_count" style="display:none;">1</span>                                                
											{{~/if~}}
										</div>
									</div>
									<div class="info_area">
										<div class="review_info"> <span class="grade">{{score}}</span> <span class="name">{{reservationEmail}}</span> <span class="date">{{reservationDate}} 방문</span> </div>
									</div>
								</div>
							</li>
                            `
	var bindTemplate = Handlebars.compile(commentTemplate);
	console.log(comments.length);
	if(comments.length==0){
		resultTemplate = `		<div class="review_box">
									<h3 class="title_h3">아직 등록된 한줄평이 없어요.</h3>
								</div>`

	}else{
		if(!document.URL.includes("review")){
			comments = comments.slice(0,4);
		}
		resultTemplate = comments.reduce(function(p,c){
			c["hasImages"] = (c.commentImages.length != 0);
			return p += bindTemplate(c);
		},"\n");
	}
	document.querySelector(".list_short_review").innerHTML = resultTemplate;
}

function sliding(direction) {
	let idx = Number(document.querySelector(".num").innerText);
	let totalLen = Number(document.querySelector(".off>span").innerText);
	if(direction == "left"){
		if(idx<=1)return;
		idx -= 1;
		document.querySelector(".num").innerText = idx;
		console.log("go left!");
	} else if(direction == "right"){
		if(idx>=totalLen)return;
		idx += 1;
		console.log("go right!");
		document.querySelector(".num").innerText = idx;
	}
	$(".visual_img").css("margin-left",((1-idx) * 100) + "%");
}

function hideTabUi(e){
	divs = document.querySelectorAll(".section_info_tab>div");
	divs.forEach(function(v){
		v.classList.remove("hide");
	});
	targetClassName = e.target.parentElement.parentElement.classList[1];
	console.log(targetClassName);
	if(targetClassName != "_detail"){
		document.querySelector(".detail_area_wrap").classList.add("hide");
	} else if(targetClassName != "_path"){
		document.querySelector(".detail_location").classList.add("hide");
	}
}

function translateDecimalFormat(){
	document.querySelectorAll(".grade").forEach(function(e){
		let decimalForm = Number(e.innerText).toFixed("1");
		e.innerText = decimalForm;
	});
}

function goReserve(displayInfoId){
	console.log("reserve?displayInfoId="+displayInfoId);
	location.href= "reserve?displayInfoId="+displayInfoId;
}

$(document).ready(function(){
	var url = document.URL;
	var paramString = url.match(/(?<=\?).+/gms);
	var params = new URLSearchParams(paramString[0]);
	var displayInfoId = params.get('displayInfoId');
	getDisplayInfoResponse(displayInfoId);
})

$(".prev").click(function(){
	sliding("left", JSON.parse(localStorage.productImages));
})
$(".nxt").click(function(){
	console.log(localStorage.productImages);
	sliding("right", JSON.parse(localStorage.productImages));
})

$(".bk_more").click(function(e){
	$(".store_details").toggleClass("close3")
	$(".bk_more").toggleClass("_on")
})
$(".info_tab_lst").click(function(e){
	if(e.target.tagName == "SPAN"){
		document.querySelector(".info_tab_lst .active").classList.remove("active");
		e.target.parentElement.classList.add("active");
		hideTabUi(e);
	}
})

//$("more_comment").click(function(e){
//	localStorage['detailPage'] = document.querySelector("body").innerHTML;
//	displayInfo = JSON.parse(localStorage['displayInfo']);
//	displayInfoImage = JSON.parse(localStorage['displayInfoImage']);
//	var contactTemplate = 
//		`<img class='map' src = '${displayInfoImage.saveFileName}'>
//			<div id = "comment_container">
//				<h4>예약자 한줄평</h4>
//				<c:forEach var="i" begin="1" end="5">
//					<c:if test="${displayInfoResponse.averageScore>i}">
//						<img src="img/red_star.png">
//					</c:if>
//					<c:if test="${displayInfoResponse.averageScore<=i}">
//						<img src="img/gray_star.png">
//					</c:if>
//				</c:forEach>
//				${displayInfoResponse.averageScore}/ 5.0 
//				${displayInfoResponse.comments}건 등록
//				<div id = "comments">
//				<ul>
//					<c:forEach items="${displayInfoResponse.comments}" var="comment" end="2">
//						<li class = "each_comment">
//							<h5>${comment.description}</h5>
//							<p>${comment.comment}</p>
//							<c:if test="${comment.commentImages} > 0">
//								<c:forEach items="${comment.commentImages}" var="image">>
//									<img src ="${img.fileName}">
//								</c:forEach>
//							</c:if>			
//							${comment.score}
//							${comment.reservationEmail}
//							${comment.reservationDate}
//						</li>
//					</c:forEach>
//				</ul>
//				</div>	
//			</div>
//		`
//		
//		
//	var bindTemplate = Handlebars.compile(contactTemplate);
//	var resultTemplate = bindTemplate(displayInfo);
//	return resultTemplate;
//})
