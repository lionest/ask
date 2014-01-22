<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.1.js">
	
</script>
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
		
		return true;
	}
</script>
</head>
<body>
	<div id="man_zone">
		<div style="margin-left: 10px;">
		<form method="post" action="topic/updateTopic.action" onsubmit="return checkForm();" enctype="multipart/form-data">
			<table class="mytable" cellpadding="0" cellspacing="0" style="margin-top: 10px;" width="800">
				<tr>
					<th>专题名：</th>
					<td height="40px" colspan="2" >
						<input type="text" name="topic.title" id="title" value="${topic.title}" />
						<input type="hidden" name="topic.id" value="${topic.id }" />
					</td>
				</tr>
				<tr>
					<th>专题图片：</th>
					<td height="40px" colspan="2" >
						<img src="${pageContext.request.contextPath}/m/topic/${topic.imgUrl}" width="188" height="128" />
						<input type="file" name="topicImg" />
					</td>
				</tr>
				<tr>
					<th>专题问题列表</th>
					<td colspan="2">
						<c:forEach items="${topicQuestions}" var="q" varStatus="s">
								${q.subject}&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="deleteTopicQuestion.action?questionId=${q.id}">删除</a>
							<br />
						</c:forEach>
						<br/>
						<a href="${pageContext.request.contextPath}/m/question/view.action">去添加问题</a>&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/m/topic/listTopic.action">返回专题列表</a>
					</td>
				</tr>
				<tr>
					<td height="40px" colspan="3" style=" padding-left: 20px;">
						<input type="submit" value="提交" />
					</td>
				</tr>
				
			</table>
			</form>
		</div>
	</div>
</body>
</html>
