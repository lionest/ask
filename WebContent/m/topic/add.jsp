<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.1.js"> </script> 
<title>管理区域</title>
<style>
table,th,td,caption {
	margin: 0px;
	padding: 0px;
	line-height: 18px;
	color: #0099CC;
	font-size: 12px;
	background: #D5DEDB;
	font-weight: bold;
}

.mytable th {
	text-align: left;
	font-weight: normal;
	width: 150px;
	padding: 6px;
	font-weight: bold;
}

.mytable caption {
	color: #0099CC;
	line-height: 30px;
}

.mytable td {
	padding: 3px;
}
</style>
		<style type="text/css">
<!--
body {
	font-size: 12px;
}

h2 {
	margin: 0px;
	padding: 0px;
	font-size: 12px;
	font-weight: bold;
}

.bton {
	border: 1px solid #CCC;
	background: #DDD;
}

.cont {
	padding: 10px;
}

#main {
	width: 400px;
	margin: 0px auto;
}

#selectItem {
	background: #FFF;
	position: absolute;
	top: 0px;
	left: center;
	border: 1px solid #000;
	overflow: hidden;
	margin-top: 10px;
	width: 700px;
	z-index: 2;
}

#preview {
	margin: 1px;
	border: 1px solid #CCC;
}

#result {
	border: 1px solid #CCC;
	margin-top: 10px;
}

.tit {
	line-height: 20px;
	height: 20px;
	margin: 1px;
	padding-left: 10px;
}

.bgc_ccc {
	background: #CCC;
}

.bgc_eee {
	background: #eee;
}

.c_999 {
	color: #999
}

.pointer {
	cursor: pointer;
}

.left {
	float: left;
}

.right {
	float: right;
}

.cls {
	clear: both;
	font-size: 0px;
	height: 0px;
	overflow: hidden;
}

#bg {
	background: #CCC;
	filter: alpha(opacity = 70);
	opacity: 0.7;
	width: 100%;;
	position: absolute;
	left: 0px;
	top: 0px;
	display: none;
	z-index: 1;
}

.hidden {
	display: none;
}
-->
</style>
<script>
//表单验证
function checkForm() {
	var title = $("#title").val().length;
	//验证标题
	if (title > 15) {
		alert("您输入的标题长度超过了15个字符...");
		return false;
	}
	if (title == 0) {
		alert("请输入标题...");
		return false;
	}
	//验证图片
	var img =  $("#topicImg").val().length;
	if (img == 0) {
		alert("请选择专题图片...");
		return false;
	}
	return true;
}
</script>
</head>
<body>
	<div id="man_zone">
		<div style="margin-left: 10px;">
			<form method="post" action="topic/addTopic.action" onsubmit="return checkForm();" enctype="multipart/form-data">
				<table class="mytable" cellpadding="0" cellspacing="0" style="margin-top: 10px;" width="800">
					<tr>
						<td height="40px" colspan="4" style="border-bottom: 1px solid; font-size: 14px; font-weight: bold; padding-left: 20px;">添加专题</td>
					</tr>
					<tr>
						<th>标题</th>
						<td colspan="3"><input name="title" id="title" size="30" /> <span style="color: #A0A0A4; font-size: 11px">*最多可输入不超过15个汉字 </span></td>
					</tr>
					<tr>
						<th>专题图片</th>
						<td colspan="3"><input type="file" name="topicImg" id="topicImg"/> <span style="color: #A0A0A4; font-size: 11px">*图片在网页显示大小为188*128 </span></td>
					</tr>
					<tr>
						<td colspan="4" align="center" height="50px"><input type="submit" value="提交" /> <input type="reset" value="重填" style="margin-left: 30px;" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
