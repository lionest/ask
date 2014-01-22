<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://ask.ah3nong.com/tags/security" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>${topic.title}_知识专题_<s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/></title>
<link href="${pageContext.request.contextPath}/css/exp_view.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/topic.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/checklogin.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
</head>
<body>
	<%@include file="../top.jsp"%>
	<!--内容区开始-->
	<div class="area">
		<!--左侧开始-->
		<div class="q_leftarea">
			<!--  专题详情开始-->
			<div class="q_quest01">
				<div class="topicview">
					<div class="topic_tit">专题：${topic.title}</div>
					<div class="topicviewleft">
						<div class="topicviewimg">
							<img src="${pageContext.request.contextPath}/m/topic/${topic.imgUrl}" width="188" height="128" />
						</div>
						<div class="topicviewtitle">
							创建时间：<fmt:formatDate value="${topic.createdTime}" pattern="yyyy.MM.dd HH:mm:ss"/>
						</div>
					</div>
					<div class="topicviewright">
						<s:iterator value="topicQuestions" id="tq" status="st">
							<div class="topicq_subject">
								<a href="${pageContext.request.contextPath}/question/<s:property value='#tq.id'/>.html" target="_blank">
								<font color="#006940">
									<c:if test="${fn:length(tq.subject)>22}">
										<s:property value="#tq.subject.substring(0,20)"/>...
									</c:if>
									<c:if test="${fn:length(tq.subject)<=22}">
										<s:property value="#tq.subject"/>
									</c:if>
								</font>
								</a>
							</div>
						</s:iterator>
					</div>
				</div>
			</div>
			<!-- 专题详情结束 -->
			<!-- 最新专题开始 -->
			<div class="cr_tjzj" style="width: 701px;height: 169px;">
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/topic/index.html" title="其他专题" target="_blank">其他专题</a>
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/topic/index.html" target="_blank">更多>></a> </span>
				</div>
					<div class="topic_list">
						<ul>
							<s:iterator value="topicList" id="topic" status="st">
								<li>
									<a href="${pageContext.request.contextPath}/topic/<s:property value='#topic.id'/>.html" target="_blank" >
										<img class="imgboderv" src="${pageContext.request.contextPath}/m/topic/<s:property value='#topic.imgUrl'/>" width="138" height="93" />								
									</a>
									<div class="topcititle"><a href="${pageContext.request.contextPath}/topic/<s:property value='#topic.id'/>.html" target="_blank" ><s:property value="#topic.title"/></a></div>
								</li>
							</s:iterator>
						</ul>
					</div>
			</div>
			<!-- 最新专题结束 -->
		</div>
		<!--左侧结束-->
		<!--右侧开始-->
		<div class="c_rightarea">
			<!-- <div id="dl"><font color="red">Loading……</font></div> -->
			<!-- 登录框结束-->
			<!-- 已解决问题开始-->
			<div class="cr_tjzj" style="margin-top: 0px;">
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/agriculture/resolved/0.html" title="已解决问题">已解决问题</a>
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/agriculture/resolved/0.html">更多>></a> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
					<wd:questionList var="latestSolvedQuestions" count="8" status="2" orderBy="solved_time desc" />
						<s:iterator value="#attr.latestSolvedQuestions" id="question">
							<li><c:if test="${fn:length(question.subject)>20}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"><s:property value="#question.subject.substring(0,18)" />...</a>
								</c:if> <c:if test="${fn:length(question.subject)<= 20 }">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"><s:property value="#question.subject" /></a>
								</c:if> 
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 已解决问题结束-->
		</div>
	</div>
	<!--内容区结束-->
	<div class="clear"></div>
	<%@include file="../bottom.jsp"%>
	<!--底部结束-->
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/common/checkLogin/checkLogin.jsp"></script> --%>
</body>
</html>
