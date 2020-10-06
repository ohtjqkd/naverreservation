/**
 * 
 */
var fileBuffer = [];
var addedFiles;
var textArea = document.querySelector("textarea");

var vali = new Validate();
console.log(vali.textarea);

function addImage(file){
	var liEle = document.createElement("li");
	liEle.className = "item";
	
	var btnEle = document.createElement("button");
	btnEle.className = "anchor";
	
	var imgEle = document.createElement("img");
	imgEle.className = "item_thumb";
	imgEle.src = URL.createObjectURL(file); 
	
	var icoDel = document.createElement("span");
	icoDel.className = "spr_book ico_del";
	
	var imgBorder = document.createElement("span");
	imgBorder.className = "img_border";

	liEle.appendChild(btnEle).appendChild(icoDel);
	liEle.appendChild(imgEle);
	liEle.appendChild(imgBorder);
	
	document.querySelector("ul.lst_thumb").appendChild(liEle);
}

$(".bk_btn").click(function(){
	var formData = new FormData($("#reviewForm")[0]);
	var score = document.querySelector(".star_rank.gray_star").innerText;
	formData.append("score", score);
	for(var i = 0; i < fileBuffer.length; i++){
		console.log(addedFiles[i]);
		var fileName = `file${i}`;
		formData.append(fileName, fileBuffer[i]);
	};
	$.ajax({
		url:pageResource.getAPI(),
		type:"post",
		data:formData,
		enctype:"multipart/form-data",
		contentType:false,
		processData:false,
		success:function(response){
			console.log("success!")
		},
		error:function(response){
			if(response.status==500){
				console.log("사진전송 실패");
			};
		}
	})
})

document.querySelector("html").addEventListener("click", function(e){
	console.log(e.target);
	switch(e.target.tagName){
		case "INPUT":
			if(e.target.className == "rating_rdo"){
				var scoreComp = new scoreComponent(e.target);
				scoreComp.setScore(e.target.value);
				document.querySelector(".star_rank").innerText = e.target.value;
			};
			break;
		case "BUTTON":
			if(e.target.className == "anchor"){
				console.log("clicked anchor btn");
				var li = e.target.closest(".item");
				var ul = document.querySelector(".lst_thumb");
				var arr = Array.from(ul.children);
				var idx = arr.indexOf(li);
				fileBuffer.splice(idx,1);
				e.target.closest(".item").remove();
			};
			break;
		default:
			break;
	}
	switch(e.target.className){
		default:
			console.log(e.target.className);
			break;
	}
})

document.querySelector(".review_write_info").addEventListener("click", function(e){
	e.currentTarget.classList.add("hide");
	textArea.focus();
})
document.querySelector("input[type=file]").addEventListener("change", function(e){
	addedFiles = e.srcElement.files;
	console.log(e.srcElement.files);
	for(let i = 0; i < addedFiles.length;i++){
		fileBuffer.push(addedFiles[i]);
		addImage(addedFiles[i]);
	};
})

textArea.addEventListener("onchange", function(e){
	document.querySelector(".guide_review>span").innerText = e.target.textLength;
})
textArea.addEventListener("keyup", function(e){
	document.querySelector(".guide_review>span").innerText = e.target.textLength;
})
textArea.addEventListener("keydown", function(e){
	document.querySelector(".guide_review>span").innerText = e.target.textLength;
})
textArea.addEventListener("blur", function(e){
	console.log("focus out");
	if(e.target.textLength==0){
		document.querySelector(".review_write_info").classList.remove("hide");
	}
})