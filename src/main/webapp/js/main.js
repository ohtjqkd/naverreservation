var startIndex = 4;
var categoryId;
var categoryCount;
//XMLHttpRequest version

var navUlLi = document.querySelectorAll("#main_nav>ul>li");
var nav = document.querySelector("#main_nav");
var more_btn = document.querySelector("#more_btn");

console.log("js load complete");
function promotionAjax(){
	$.ajax({
		type: "GET",
		url: "promotion",
		data: "",
		dataType: "json",
		success: function (response) {
			var promotionTemplate = 
					"<li class = 'promotion_content'>"+
							"<img src = '{{productImageUrl}}'>"+
					"</li>"
			var bindTemplate = Handlebars.compile(promotionTemplate);
			var resultTemplate = response.promotionResponse.items.reduce(function(p,c){
				return p += bindTemplate(c);
			},"")
			console.log(resultTemplate);
			$("#promotion>ul").append(resultTemplate);
			const wrap = one("#promotion");
			const target = wrap.children[0];
			const len = target.children.length;
			target.style.cssText = `width:calc(100*${len}%);`;
			Array.from(target.children).forEach(ele =>ele.style.cssText = `width:calc(100% / ${len});`)
		},
		error: function (){
			console.log("error");
		}
	});
}

function productAjax(startIndex, categoryId){
	let url = "";
	if(categoryId == 0 || categoryId == null){
		url = "product?start=" + startIndex;
	} else {
		url = "product?categoryId=" + categoryId + "&start=" + startIndex; 
	}
	$.ajax({
		type: "GET",
		url: url,
		async:false,
		data: "",
		dataType: "json",
		success: function (response) {
			console.log(typeof response.productResponse.items);
			console.log("startIndex: " + startIndex);
			console.log("networking success");
			var productTemplate =
			"<li class='main_content'>" +
			"<a href = 'detail?displayInfoId={{displayInfoId}}'>" +
			"<div class = 'main_img'>" +
			"<img src = '{{productImageUrl}}'>" +
			"</div>" +
			"<div class = 'description'>" +
			"<h4>{{productDescription}}</h4>" +
			"</div>" +
			"<div class = 'content'>" + 
			"<h5>{{productContent}}</h5>" +
			"</div>" +
			"</a>"
			"</li>"
			categoryCount = response.productResponse.totalCount;
			var bindTemplate = Handlebars.compile(productTemplate);
			var leftTemplate="";
			var rightTemplate="";
			var resultTemplate = response.productResponse.items.map(function(v,i){
				if(i%2==0){
					leftTemplate += bindTemplate(v);
				} else {
					rightTemplate += bindTemplate(v);
				}
			});
			if(startIndex == 0){
				$("#left_container>ul").html(leftTemplate); 
				$("#right_container>ul").html(rightTemplate); 
			} else {
				$("#left_container>ul").append(leftTemplate); 
				$("#right_container>ul").append(rightTemplate); 
			}
			console.log(resultTemplate);
			
		}
	});
}

function buttonController(){
	if(startIndex >= categoryCount) {
		$("#more_btn").css("display","none");
	} else {
		$("#more_btn").css("display","block");
	}	
}

const all = ele => document.querySelectorAll(ele);
const one = ele => document.querySelector(ele);
const slide = _ =>{
	const wrap = one("#promotion");
	const target = wrap.children[0];
	const len = target.children.length;
	let pos = 0
	setInterval(() => {
		pos = (pos + 1) % len // 장면 선택
		target.style.marginLeft = `${-pos * 100}%` // 장면 전환
	}, 3000)
}

$(document).ready(function(){
	promotionAjax();
	setTimeout(() => {
		slide()
	}, 500);
})

$("#more_btn").click(function(e){
	productAjax(startIndex,categoryId);
	startIndex += 4;
	buttonController();
})

$("#tab_menu").click(function(e){
	var navUlLi = document.querySelectorAll("#tab_menu>ul>li");
	if(e.target.tagName=="LI"){
		navUlLi.forEach(function(element,i){
			element.removeAttribute("class");
		})
		e.target.classList.toggle("on",true);
		startIndex = 0;
		categoryId = e.target.value;
		console.log("categoryId = " + categoryId);
		$(".product_li").html = productAjax(startIndex,e.target.value);
		startIndex += 4;
	}
	$("#cnt").html(categoryCount + "개");
	buttonController();
})

//jquery version
//$("#main_nav").click(function (e) { 
//	console.log(e.target);
//	console.log(e.currentTarget);
//	if(e.target.tagName=="LI"){
//		$("#main_nav>ul>li").removeClass();
//		$(e.target).addClass("on");
//		$.ajax({
//			type: "GET",
//			url: "main/" + e.target.value,
//			data: "",
//			dataType: "json",
//			success: function (response) {
//				console.log(response);
//				let leftContents = "";
//				let rightContents = "";
//				response.list.forEach((element,i) => {
//					let resultTemplate = mainContentTemplate.replace("{productId}",element.productId)
//					.replace("{id}",element.id)
//					.replace("{content}",element.content)
//					.replace("{description}",element.description);
//					if(i%2==0){
//						leftContents += resultTemplate;
//					} else {
//						rightContents += resultTemplate;
//					}
//				});
//				
//				
//				$("#cnt").html(response.count+"개");
//				$("#left>ul").html(leftContents);
//				$("#right>ul").html(rightContents);
//				$("#more_btn").css("display","block");
//				categoryId = e.target.value;
//				startIndex = 4;
//			}
//		});
//	}
//});
//
//$("#more_btn").click(function (e) { 
//	console.log(e.target.id);
//	$.ajax({
//		type: "GET",
//		url: "main/" + categoryId +"?start=" + startIndex,
//		data: "",
//		dataType: "json",
//		success: function (response) {
//			response.list.forEach((element,i) => {
//				let resultTemplate = mainContentTemplate.replace("{productId}",element.productId)
//													.replace("{id}",element.id)
//													.replace("{content}",element.content)
//													.replace("{description}",element.description);
//				if(i%2==0){
//					$("#right>ul").append(resultTemplate);
//				} else{
//					$("#left>ul").append(resultTemplate);
//				}
//			});
//			startIndex += 4;
//			if(startIndex>=response.count){
//				$("#more_btn").css("display","none");
//			}
//		}
//	});
//	
//});

// var buttonCollection = document.querySelectorAll(".move_button");
// var array = document.getElementsByClassName("move_button");
// var testInput = document.getElementById("addTest");
// var testBtn = document.getElementById("testBtn");
// var backwardBtn = document.getElementById("backward");
// btnListener();

// var inputTitle = document.querySelectorAll(".todo_form input").item(0);
// var inputName = document.querySelectorAll(".todo_form input").item(1);
// // for를 이용한 addEventListener
// function btnListener(){
// 	for(let i = 0; i < array.length; i++){
// 		let btn = array.item(i);
// 		let btnId = btn.getAttribute("value");
// 		let btnType = btn.getAttribute("name");
// 		btn.addEventListener("click",function(){
// 				console.log(btnId+","+btnType)
// 				moveType(btn,btnId,btnType)
// 		});
// 	}
// };
			
//개선된 addEventListner
// var article = document.querySelector("#main_nav");
// article.addEventListener("click",function(evt){
//     loadCategoryContent(evt.target);
// });
// function loadCategoryContent(target){
//     function handler(){
// 		if(xhr.readyState===4){
// 			if(xhr.status===200){
// 				console.log(xhr.responseType, xhr.responseText);
// 			}
// 		}
// 	}
// 	var xhr = new XMLHttpRequest();
// 	xhr.open("GET","http://localhost:8080/naverreservation/main"+target.id);
// 	xhr.setRequestHeader('Content-Type','application/json');
// 	// var data = "{ \"id\" : \""+btnId+"\" , \"type\" : \""+btnType+"\"} ";
// 	xhr.onreadystatechange = handler;
// 	xhr.send();    
// 	console.log('send data');
// }


// function validateForm(){
// 	var inputs = document.querySelectorAll("input");
// 	for(var values of inputs){
// 		if(values.value==''){
// 			alert("fill all input");
// 			return false;
// 		}
// 	}
// 	/*inputs.forEach((item)=>{
// 		if(item.value==""){
// 			alert("fill all input");
// 			return false;
// 		};
// 	});*/
// };

//		var oReq = new XMLHttpRequest;
//		oReq.open("GET","main/" + e.target.value)
//		oReq.setRequestHeader('Content-Type','application/json');
//		oReq.onreadystatechange = handler;
//		oReq.send();
//		function handler(){
//			if(oReq.readyState===4) {
//				if(oReq.status===200){
//					let leftContents = "";
//					let rightContents = "";
//					json = JSON.parse(oReq.response);
//					json.list.forEach((element,i) => {
//						let resultTemplate = mainContentTemplate.replace("{productId}",element.productId)
//						.replace("{id}",element.id)
//						.replace("{content}",element.content)
//						.replace("{description}",element.description);
//						if(i%2==0){
//							leftContents += resultTemplate;
//						} else {
//							rightContents += resultTemplate;
//						}
//					});
//					document.getElementById("cnt").innerText = json.count+"개";
//					document.querySelector("section#left>ul").innerHTML = leftContents;
//					document.querySelector("section#right>ul").innerHTML = rightContents;
//					document.getElementById("more_btn").style.display = "block";
//					
//					categoryId = e.target.value;
//					startIndex = 4;
//				}
//				
//			}
//		}

//more_btn.addEventListener("click",function(){
//	function handler(){
//		if(oReq.readyState===4){
//			if(oReq.status===200){
//				let productResponse = oReq.response.productResponse;
////				json = JSON.parse(oReq.response);
////				json.list.forEach((element,i) => {
////					let resultTemplate = mainContentTemplate.replace("{productId}",element.productId)
////													.replace("{id}",element.id)
////													.replace("{content}",element.content)
////													.replace("{description}",element.description);
////					if(i%2==0){
////						document.querySelector("section#left>ul").innerHTML += resultTemplate;
////					} else{
////						document.querySelector("section#right>ul").innerHTML += resultTemplate;
////					}
////				});
//				
//				
//				console.log(productResponse);
//				startIndex += 4;
//				if(startIndex>productResponse.totalCount){
//					document.getElementById("more_btn").style.display = "none";
//				}
//			}
//		}
//	}
//	var oReq = new XMLHttpRequest;
//	oReq.open("GET","product?categoryId=" + categoryId +"&start=" + startIndex)
//	oReq.setRequestHeader('Content-Type','application/json');
//	oReq.onreadystatechange = handler;
//	oReq.send();
//});