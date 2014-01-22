<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="三农问答，中国最大的涉农类问答网站，是安徽朗坤物联网有限公司基于三农问题自主研发的农业知识互动问答分享平台。在这里用户可以提出各种农业问题，会有权威农业专家及时解答，也有热心网友提供答案。" />
		<title>三农问答-中国最大农业问答平台</title>
		<link href="${pageContext.request.contextPath}/css/info.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<!--顶部开始-->
		<jsp:include page="../top.jsp" />

		<!--内容区开始-->
		<div class="ask_nav">
			<a href="${pageContext.request.contextPath}/index.action">首页</a> >
			<a>信息提示</a>
		</div>
		<div class="info_area">
			<div class="info_con">
				<span class="fon14">您在问答系统内还没有账号( ⊙ o ⊙ )肿么会这样o(≧v≦)o~~<br/>╮(╯▽╰)╭还是继续回首页看新闻吧</span>&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
			<div class="info_btn">
				<div class="btn fl">
					<img onclick="window.location.href='${pageContext.request.contextPath}/index.html'" src="${pageContext.request.contextPath}/images/info_03.jpg" width="113" height="35" />
				</div>
			</div>
		</div>
		<!--内容区结束-->
		<!--底部开始-->
		<jsp:include page="../bottom.jsp" />
		<!--底部结束-->
	</body>
</html>
