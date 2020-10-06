/**
 * 
 */

/*var mainPageResource = {
		api:["product","promotion","category"],
		startIdx:0,
		categoryId:0,
		categoryCount:0
}

function detailPageResource(){
		api = ["product"];
}
var api = pageResource.api[pageResource.page];
function ajax(pageResource){
	if(pageResource.page == "mainpage")
	let query = "";
	 
}
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
	console.log("displayInfoResponse", response.displayInfoResponse);
	if(document.URL.includes("detail")){
		setDetail(response);
	} else if(document.URL.includes("review")){
		setReview(response);
	} else if(document.URL.includes("reserve")){
		setReserve(response);
	}
});
*/

Handlebars.registerHelper(
		{
			transComma:function(number){
				return number.toLocaleString();
			},
			typeTrans:function(type){
				switch(type){
					case "A":
						return "성인";
					case "Y":
						return "청소년";
					case "B":
						return "유아";
					case "S":
						return "세트";
					case "D":
						return "장애인";
					case "C":
						return "지역주민";
					case "E":
						return "얼리버드";
					case "V":
						return "VIP";
					case "R":
						return "R석";
					default:
						return "기타";
				}
			},
			buttonRender:function(type){
				var template;
				console.log(type);
				if(type=="confirmed"){
					template = `
					<div class="booking_cancel">
						<button class="btn">
							<span>취소</span>
						</button>
					</div>
					`
				} else if(type=="used"){
					template = `
					<div class="review_write">
						<button class="btn">
							<span>예매자 리뷰 남기기</span>
						</button>
					</div>
					`
				} else {
					template = ""
				}
				return template; 
			},
			"$eq":function(a, b){
				return a==b;
			},
			"$ne":function(a, b){
				return a!=b;
			}
		})

function PageResource(pageUrl){
	if(pageUrl.match(/(?<=\/)\w*(?=\?)/gm) == null){
		this.pageName = "mainpage";
	} else {
		this.pageName = pageUrl.match(/(?<=\/)\w*(?=\?)/gm)[0];
		this.query = pageUrl.match(/(?<=\?)\S*/)[0];
	}
}
PageResource.prototype.getParams = function(){
	var result = {};
	var searchParams = new URLSearchParams(this.query);
	searchParams.forEach(function(value,key){
		result[key] = value
	})
	return result;
};
PageResource.prototype.getAPI = function(){
		this.params = this.getParams();
		var api = {
				mainpage:["product","promotion","category"],
				detail:`product/${this.params['displayInfoId']}`,
				reserve:`reservation/${this.params['displayInfoId']}`,
				myreservation:`reservation?reservationEmail=${this.params['reservationEmail']}`,
				reviewWrite:`reservation/${this.params['reservationInfoId']}/comments`
		};
		return api[this.pageName];
}

var pageResource = new PageResource(document.URL);

//function AjaxParams(){
//	this.url = pageResource.getAPI();
//	this.contentType = "json";
//}
//
//function ajax(){
//	ajaxParams = new AjaxParams();
//}

function goBack(){
	history.back();
}

function stringNumberWithCommaToInt(str){
	return str.replace(/,/g,"");
}

function btnChecker(e){
	let inputTag = e.children[1];
	if(inputTag.value=="0"){
		if(!e.children[0].classList.contains("disabled")){
			e.children[0].classList.toggle("disabled");
		}
		if(!inputTag.classList.contains("disabled")){
			inputTag.classList.toggle("disabled");
		}
	}else if(inputTag.value == "99"){
		if(!e.children[2].classList.contains("disabled")){
			e.children[2].classList.toggle("disabled");
		}
	}else{
		e.childNodes.forEach(function(c){
			if(c.nodeName!="#text"){
				if(c.classList.contains("disabled")){
					c.classList.toggle("disabled")
				}
			}
		})
	}
	
}

function scoreComponent(target){
	this.scoreBoard = target.parentElement.querySelectorAll("input[type=checkbox]");
	console.log(this.scoreBoard);
	this.setScore = function(score){
		for(let i = 0; i < this.scoreBoard.length; i++){
			if(i<score){
				this.scoreBoard[i].checked = true;
			} else{
				this.scoreBoard[i].checked = false;
			}
		}
	}
	this.getScore = function(){
		for(var i = 0; i < this.scoreBoard.length; i++){
			if(this.scoreBoard[i].checked == false){
				return i;
			}
		}
	}
}

function Validate(){
	this.regExp = {
			name:/[^\w\s]/g,
			email:/\w*@\w*.com/g,
			tel:/[01]\d-\d{3-4}-\d{4}/g
	}
}
Validate.prototype = {
			maxTicket: function(ticketCnt){
				let ticketCount = Number(document.querySelector("#totalCount").innerText);
				if(ticketCount != 0){
					validations.ticketCnt = true;
				} else {
					validations.ticketCnt = false;
				}
			},
			isName: function(name){
				var maxNameLen = 24;
				var minNameLen = 3;
				if(name.length>this.minNameLen && name.length<this.maxNameLen && name.match(this.regExe.name) == null){
					validations.name = true;
				} else {
					validations.name = false;
				}
			},
			isTelNum:function(tellNumber){
				if(telInput.value != "" && tellNumber.match(this.regExe.tel) != null){
					validations.tel = true;
				} else {
					validations.tel = false;
				}
			},
			isEmail:function(email){
				let emailValidate = email.match(this.regExe.email);
				if(emailValidate != null){
					if(emailValidate.length==1){
						return true;
//						this.validations.email = true;
					}
				} else {
					return false;
//					validations.email = false;
				}
			},
			isValidate:function(){
				for(var check in this.validataions){
					if(check == false){
						return false
					}
				}
				return true
			}
}
//	if(!document.querySelector("#chk3").checked){
//		validations.agreementChk = true;
//	} else {
//		validations.agreementChk = true;
//	}

