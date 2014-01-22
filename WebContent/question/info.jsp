<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<wd:sysData var="sitename" name="sitename" />
		<wd:sysData var="title" name="title" />
		<wd:sysData var="keywords" name="keywords" />
		<wd:sysData var="description" name="description" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="<s:property value='#attr.keywords.content'/>" />
		<meta name="description" content="<s:property value='#attr.description.content'/>" />
		<title><s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/></title>
		<link href="${pageContext.request.contextPath}/css/info.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/autojumptourl.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
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
			<c:choose>
				<c:when test="${auding == null || auding != 1 }">
					<div class="info_con">
						<span class="fon14">${tips }</span>&nbsp;&nbsp;&nbsp;&nbsp;
						(&nbsp;&nbsp;<span  id="jumpTo" style="color: blue">5</span>&nbsp;秒后自动返回该问题页面...)
						<script type="text/javascript">countDown(5,'${pageContext.request.contextPath}/question/${questionId}.html');</script> 
					</div>
					<div class="info_btn">
						<div class="btn fl">
							<img onclick="window.location.href='${pageContext.request.contextPath}/question/${questionId}.html';" src="${pageContext.request.contextPath}/images/info_02.jpg" width="113" height="35" />
						</div>
						<div class="btn fl">
							<img onclick="window.location.href='${pageContext.request.contextPath}/index.html'" src="${pageContext.request.contextPath}/images/info_03.jpg" width="113" height="35" />
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="info_con">
						<span class="fon14">您的问题我们已经收到，审核完成后将会在问题列表显示！<br />
						</span> <br /> (&nbsp;&nbsp;<span id="jumpTo" style="color: blue">5</span>&nbsp;秒后自动返回首页...)
						<script type="text/javascript">countDown(5,'${pageContext.request.contextPath}/index.html');</script>
					</div>
					<div class="info_btn">
						<div class="btn fl" style="margin-top: 26px;">
							<img onclick="window.location.href='${pageContext.request.contextPath}/index.html'" src="${pageContext.request.contextPath}/images/info_03.jpg" style="cursor: pointer" width="113" height="35" />
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<!--内容区结束-->
		<!--底部开始-->
		<jsp:include page="../bottom.jsp" />
		<!--底部结束-->
	</body>
</html>
