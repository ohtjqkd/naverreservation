/**
 * 
 */
var startIndex = 4;
var categoryId = 0;
var mainContentTemplate = "<li class = 'main_content'><div class = 'main_img'><img src = 'img/{productId}_th_{id}.png'></div><div class='description'><h3>{description}</h3></div><div class='content'><h4>{content}</h4></div></li>";

var nav = document.querySelector("#main_nav");
nav.addEventListener("click",function(e){
	var navUlLi = document.querySelectorAll("#main_nav>ul>li");
	navUlLi.forEach(function(element,i){
		element.removeAttribute("class");
	})
	e.target
	
})

$("#main_nav").click(function (e) { 
	console.log(e.target.value);
	$("#main_nav>ul>li").removeClass();
	$(e.target).addClass("on");
    $.ajax({
        type: "GET",
        url: "main/" + e.target.value,
        data: "",
        dataType: "json",
        success: function (response) {
			console.log(response);
			let leftContents = "";
			let rightContents = "";
			response.list.forEach((element,i) => {
				let resultTemplate = mainContentTemplate.replace("{productId}",element.productId)
										.replace("{id}",element.id)
										.replace("{content}",element.content)
										.replace("{description}",element.description);
				if(i%2==0){
					leftContents += resultTemplate;
				} else {
					rightContents += resultTemplate;
				}
			});

			
			$("#cnt").html(response.count+"개");
			$("#left>ul").html(leftContents);
			$("#right>ul").html(rightContents);
			$("#more_btn").css("display","block");
			categoryId = e.target.value;
			startIndex = 4;
		}
    });
});

$("#more_btn").click(function (e) { 
	console.log(e.target.id);
	$.ajax({
		type: "GET",
		url: "main/" + categoryId +"?start=" + startIndex,
		data: "",
		dataType: "json",
		success: function (response) {
			response.list.forEach((element,i) => {
				let resultTemplate = mainContentTemplate.replace("{productId}",element.productId)
													.replace("{id}",element.id)
													.replace("{content}",element.content)
													.replace("{description}",element.description);
				if(i%2==0){
					$("#right>ul").append(resultTemplate);
				} else{
					$("#left>ul").append(resultTemplate);
				}
			});
			startIndex += 4;
			if(startIndex>=response.count){
				$("#more_btn").css("display","none");
			}
		}
	});
	
});