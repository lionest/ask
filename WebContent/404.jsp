<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<wd:sysData var="sitename" name="sitename" />
<wd:sysData var="title" name="title" />
<title>${sitename.content }-${title.content}</title>
<link href="${pageContext.request.contextPath}/css/info.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
</head>
<body>
	<!--顶部开始-->
	<jsp:include page="top.jsp" />
	<!-- 内容开始 -->
	<div class="ask_nav">
		<a href="${pageContext.request.contextPath}/index.html">首页</a> &gt; <a>信息提示</a>
	</div>
	<div class="info_area">
		<div class="info_con1">
			<span class="fon14">您访问的页面出错了。</span>
		</div>
		<div class="info_btn">
			<div class="btn fl">
				<img onclick="window.location.href='${pageContext.request.contextPath}/index.html'" src="${pageContext.request.contextPath}/images/pic_04.jpg" style="cursor: pointer" width="113" height="35" />
			</div>
		</div>
	</div>
	<!--底部开始-->
	<jsp:include page="bottom.jsp" />
</body>
</html>
