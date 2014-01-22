<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
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
	padding-top: 50px;
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
	filter: alpha(opacity =   70);
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
</head>
<body>
	<script>
		function checkcount(){
			var k = document.getElementById("keywords");
			var d = document.getElementById("summary");
			if(k.value.length >63){
				alert("关键字请控制在63字以内！");
				return false;
			}
		    if(d.value.length>255){
				alert("问题概述请控制在255字以内！");
				return false;
			}
		    return true;
		}
	
	</script>
	<div style="margin-left: 10px;">
		<form method="post" action="updateKeyword.action?question.id=${question.id}" onsubmit="return checkcount();">
			<table class="mytable" cellpadding="0" cellspacing="0" style="margin-top: 10px;" width="800">
				<tr>
					<td height="40px" colspan="2" style="border-bottom: 1px solid; font-size: 14px; font-weight: bold; padding-left: 20px;">编辑关键词</td>
				</tr>
				<tr>
					<th>问题标题</th>
					<td>${question.subject }</td>
				</tr>
				<tr>
					<th>关键字</th>
					<td colspan="3"><textarea id="keywords" name="question.keywords" rows="2"  cols="50">${questionKeywords}</textarea><br/>(关键字之间用半角逗号[,]隔开)</td>
				</tr>
				<tr>
					<th>问题概述</th>
					<td><textarea id="summary" name="question.summary" rows="5"  cols="50" >${question.summary}</textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="提交" /></td>
</body>
</html>
