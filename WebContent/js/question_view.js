function checktext(text) {
	allValid = true;
	for ( var i = 0; i < text.length; i++) {
		if (text.charAt(i) != " ") {
			allValid = false;
			break;
		}
		;
	}
	return allValid;
}
function gbcount(message, total, used, remain) {
	var max;
	max = total.value;
	if (message.value.length > max) {
		message.value = message.value.substring(0, max);
		used.value = max;
		remain.value = 0;
		alert("留言不能超过 200 个字!");
		alert("不能超过" + total.value + "个字!");
	} else {
		if (/^[\x00-\xff]*$/.test(message.value)) {
			used.value = message.value.length;
		} else {
			used.value = message.value.length * 2;
		}
		remain.value = max - used.value;
	}
	;
}
function count(s, total, used, remain) {
	var str = CKEDITOR.instances.textfield.getData().replace(/[^\x00-\xff]/g, '**');
	var c = 0;// 统计字数
	var i = 0;
	for (i = 0; i < str.length; i++) {
		c++;
	}
	var max = total.value;
	used.value = c;
	if (c <= 4000) {
		remain.value = max - used.value;
	} else {
		remain.value = 0;
	}
	;
};

function checkReplyLength(){
	// 验证回复内容是否为空
	var replyContent = $("#replyContent").val().replace(/(^\s*)|(\s*$)/g, "").length;
	if (replyContent >= 2000) {
		alert("您输入的回复过长...");
		return false;
	}
	if (replyContent == 0) {
		alert("回复内容不能为空...");
		return false;
	}
}

//访客登录时检查回复框是否有内容
function checkReplyForm(){
	var replyContent = CKEDITOR.instances.textfield.getData();
	replyContent = replyContent.replace(/^[" "|" "]*/,"");//去左空格
	replyContent = replyContent.replace(/[" "|" "]*$/,"");//右
	var len = replyContent.replace(/[^\x00-\xff]/g, '**').length;
	if(len>0){
		alert("您还没有登陆，请登录以后进行回复操作。如有需要，请将回复内容妥善备份。");
		return false;
	}else{
		return true;
	};
}

