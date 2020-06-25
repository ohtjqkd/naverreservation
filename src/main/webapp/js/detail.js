
function getDisplayInfoResponse(displayInfoId){
	$.ajax({
		type:"GET",
		async:false,
		url:"product/"+displayInfoId,
		dataType:"json",
		success:function(response){
			
			console.log("ajax start");
			console.log(response.displayInfoResponse);
			response.displayInfoResponse.productImages.splice(0,2);
			for(key in response.displayInfoResponse){
				localStorage[key] = JSON.stringify(response.displayInfoResponse[key]);
				console.log(JSON.stringify(response.displayInfoResponse[key]))
			}
		}
	});
}

function sliding(direction, productImages) {
	let idx = Number(document.querySelector("#nth").innerText);
	console.log("current Index = " + idx);
	let totalLen = Number(document.querySelector("#img_cnt").innerText);
	if(direction == "left"){
		if(idx<=1)return;
		idx -= 1;
		document.querySelector("#nth").innerText = idx;
		console.log("go left!");
	} else if(direction == "right"){
		if(idx>=totalLen)return;
		idx += 1;
		console.log("go right!");
		document.querySelector("#nth").innerText = idx;
		let target = $(".slide_dummy>ul");
		console.log("ul length = " + target.children().length);
		if(target.children().length == idx) insertImage(target, productImages);
	}
	$(".slide_dummy").css("margin-left",((1-idx) * 100) + "%");
	
}

function insertImage(target, productImages){
	if(productImages.length>0){
		target.css("width",(target.children().length+1)*100 + "%");
		target.append(`<li class='product_image'><img src='${productImages[0].saveFileName}'></li>`);
		productImages.splice(0,1);
		localStorage['productImages'] = JSON.stringify(productImages);
	}
}

function getContact(){
	displayInfo = JSON.parse(localStorage['displayInfo']);
	displayInfoImage = JSON.parse(localStorage['displayInfoImage']);
	var contactTemplate = 
		`<img class='map' src = '${displayInfoImage.saveFileName}'>
			<h4>${displayInfo.placeStreet}</h4>
			<p>${displayInfo.placeLot}</p>
			<p>${displayInfo.placeName}</p>
			<p>${displayInfo.tel}</p>`
		
	var bindTemplate = Handlebars.compile(contactTemplate);
	var resultTemplate = bindTemplate(displayInfo);
	return resultTemplate;
}

$("#more_description").click(function(e){
	console.log(e.target.innerHtml);
	if($(".content").hasClass("hide")){
		console.log("Has hide class");
		$(".content").toggleClass("hide");
		e.target.innerText = '접기';
	} else {
		$(".content").toggleClass("hide");
		console.log("I cant find this");
		e.target.innerText = '펼쳐보기';
	}
})



$(document).ready(function(){
	console.log("page loaded");
	var url = document.URL;
	var paramString = url.match(/(?<=\?).+/gms);
	var params = new URLSearchParams(paramString[0]);
	var displayInfoId = params.get('displayInfoId');
	console.log("displayInfoId = " + displayInfoId);
	getDisplayInfoResponse(displayInfoId);
	if(JSON.parse(localStorage.productImages).length>0) {
		insertImage($(".slide_dummy>ul"), JSON.parse(localStorage['productImages']));
		console.log(localStorage['productImages']);
	}
})

$(".left").click(function(){
	sliding("left", JSON.parse(localStorage.productImages));
})
$(".right").click(function(){
	console.log(localStorage.productImages);
	sliding("right", JSON.parse(localStorage.productImages));
})
$("#tab_menu>ul").click(function(e){
	console.log("clicked");
	if(e.target.id == "contact") {
//		localStorage['inform'] = document.querySelector("#information>section").innerHTML;
		console.log(localStorage['inform'])
		e.target.classList.toggle("on",true);
		$("#detail").removeClass();
		$("#information>section").html(getContact());
		return;
	}
	if(e.target.id == "detail") {
		e.target.classList.toggle("on",true);
		$("#contact").removeClass();
		$("#information>section").html(localStorage['inform']);
	}
})

console.log(localStorage)