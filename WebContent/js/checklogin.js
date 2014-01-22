function checkLogin(userInfo){
	if(userInfo!=null){
		//调用Ajax发送请求，获取对应的页面
		sendRequest("${pageContext.request.contextPath}/searchLoginUser.action");
	}else{
		sendRequest("/login.jsp");
	}
}
//发送请求函数
function sendRequest(url){
	var XMLHttpReq;
	if(window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	}else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {}
		}
	}	
	XMLHttpReq.open("GET", url, true);
	XMLHttpReq.onreadystatechange = function(){
		if (XMLHttpReq.readyState == 4) { // 判断对象状态
			if (XMLHttpReq.status == 200) { 
				// 信息已经成功返回，开始处理信息，将返回的信息写入"dl"DIV框中
				//alert(XMLHttpReq.responseText);
				document.getElementById("dl").innerHTML=XMLHttpReq.responseText;
			} 
		}
	};//指定响应函数
	XMLHttpReq.send(null);  // 发送请求
}