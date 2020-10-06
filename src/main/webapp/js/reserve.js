/**
 * 
 */
var validations = {
		name:false,
		tel:false,
		email:false,
		ticketCnt:false,
		agreementChk:false,
		check:function(){
			console.log(this.name);
			let btn = document.querySelector(".bk_btn");
			console.log(btn);
			console.log(this.name==true&&this.phoneNumber==true&&this.email==true&&this.ticketCnt==true&&this.agreementChk==true);
			if(this.name==true&&this.tel==true&&this.email==true&&this.ticketCnt==true&&this.agreementChk==true){
				btn.classList.remove("disable");
				btn.removeAttribute("disabled");
			} else {
				btn.classList.add("disable");
				btn.setAttribute("disabled", "disabled");
			}
		}
}
document.addEventListener("click", function(){
	console.log("clicked");
	validate();
	validations.check();
})
document.addEventListener("keydown", function(){
	console.log("keydown");
	validate();
	validations.check();
})
document.addEventListener("keyup", function(){
	console.log("keyup");
	validate();
	validations.check();
})
document.querySelector(".section_booking_agreement").addEventListener("click",function(e){
	if(e.target.className.includes("btn_text")){
		let target = e.target.parentElement.parentElement.children[2];
		console.log(target.className.includes("open"));
		target.classList.toggle("open");
		if(target.className.includes("open")){
			e.target.innerText ="접기";
		}else{
			e.target.innerText = "보기";
		}
		
	}
})

document.querySelector(".ticket_body").addEventListener("click",function(e){
	if(e.target.className.includes("btn_plus_minus")){
		let totalCount = Number(document.querySelector("#totalCount").innerText);
		let targetInputTag = e.target.parentElement.querySelector("input");
		let prevAmount = Number(targetInputTag.value);
		let targetTotalPrice = e.target.parentElement.nextElementSibling.querySelector(".total_price");
		let targetPrice = e.target.parentElement.parentElement.previousElementSibling.querySelector(".price");
		let price = stringNumberWithCommaToInt(targetPrice.innerText);
		if(e.target.classList.contains("ico_plus3") && prevAmount < 99){
			prevAmount++;
			totalCount++;
			targetInputTag.value = prevAmount;
		} else if(e.target.classList.contains("ico_minus3") && prevAmount>0) {
			prevAmount--;
			totalCount--;
			targetInputTag.value = prevAmount;
		};
		btnChecker(e.target.parentElement);
		let totalPrice = prevAmount * price;
		targetTotalPrice.innerText = totalPrice.toLocaleString();
		document.querySelector("#totalCount").innerText = totalCount;
	};
});

document.querySelector("#tel").addEventListener("keyup",function(e){
	if(!e.target.value.match(/(?<=(010|011|016|017|018|019))\d{7,8}|-\d{3,4}-\d{4}/g)){
		document.querySelector(".warning_msg").classList.remove("hide");
	} else{
		document.querySelector(".warning_msg").classList.add("hide");
	}
	if(e.target.value==""){
		document.querySelector(".warning_msg").classList.add("hide");
	}
});

document.querySelector(".bk_btn").addEventListener("click",function(e){
	sendReserve();
})
function submitBtnActivate(){
	document.querySelector(".bk_btn").classList.add("disable");
	document.querySelector(".bk_btn").classList.remove("disable");
	
}

function setReserve(response){
	console.log("reserve side", response);
	var displayInfo = response.displayInfoResponse.displayInfo;
	var dsc = displayInfo.openingHours.split('\n');
	document.querySelector(".dsc").innerText += " "+dsc[0].replace("-","").trim();
	var prices = response.displayInfoResponse.productPrices;
	var resultTeamplate = "";
	var ticketBodyTemplate = 
		`
	<div class="qty">
	    <div class="qty_info_icon"> <strong class="product_amount"> <span>{{typeTrans priceTypeName}}</span> </strong> <strong class="product_price"> <span class="price">{{transComma price}}</span> <span class="price_type">원</span> </strong> <em class="product_dsc">{{transComma price}}원 ({{discountRate}}% 할인가)</em> </div>
	    <div class="count_control">
	        <!-- [D] 수량이 최소 값이 일때 ico_minus3, count_control_input에 disabled 각각 추가, 수량이 최대 값일 때는 ico_plus3에 disabled 추가 -->
	        <div class="clearfix">
	            <button class="btn_plus_minus spr_book2 ico_minus3 disabled" title="빼기">&ndash;</button><input type="tel" class="count_control_input disabled" name={{priceTypeName}} value="0" readonly title="수량"><button class="btn_plus_minus spr_book2 ico_plus3" title="더하기">+</button>                                    
	        </div>
	        <!-- [D] 금액이 0 이상이면 individual_price에 on_color 추가 -->
	        <div class="individual_price"><span class="total_price">0</span><span class="price_type">원</span></div>
	    </div>
	</div>
	`
	
	var bindTemplate = Handlebars.compile(ticketBodyTemplate);
	
	resultTemplate = prices.reduce(function(p,c){
		return p += bindTemplate(c);
	},"")
	document.querySelector(".ticket_body").innerHTML = resultTemplate;
}

function sendReserve(){
	$.ajax({
		url:"reservation",
		type:"post",
		dataType:"json",
		success:function(response){
			console.log(response)
		}
	})
}