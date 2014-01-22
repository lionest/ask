<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://ask.ah3nong.com/tags/security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<wd:sysData var="sitename" name="sitename" />
<wd:sysData var="title" name="title" />
<wd:sysData var="keywords" name="keywords" />
<wd:sysData var="description" name="description" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="<s:property value='#attr.description.content'/>" />
<meta name="keywords" content="<s:property value='#attr.keywords.content'/>" />
<title>
	知识专题_<s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/>
</title>
<link href="${pageContext.request.contextPath}/css/wdfenlei.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/topic.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/logon_2.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/checklogin.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
</head>
<body>
	<%@include file="../top.jsp"%>
	<!--内容区开始-->
	<div class="area">
		<!--左侧开始-->
		<div class="c_leftarea" style="margin-top: 0px;">
			<!--  所有问题开始-->
			<div class="c_allqus" style="margin-top: 0px;" >
				<div class="sbar">
					<h2>知识专题列表</h2>
				</div>
				<!-- 标题开始-->
				<div class="pad6">
					<div class="img_list1">
						<ul>
						<s:iterator value="topicList" id="topic" status="st">
							<li>
								<a href="${pageContext.request.contextPath}/topic/<s:property value='#topic.id'/>.html" target="_blank" >
									<img class="imgboderv" src="${pageContext.request.contextPath}/m/topic/<s:property value='#topic.imgUrl'/>" width="188" height="128" />								
								</a>
								<div class="topcititle" style="text-align:center;"><a href="${pageContext.request.contextPath}/topic/<s:property value='#topic.id'/>.html" target="_blank" ><s:property value="#topic.title"/></a></div>
							</li>
						</s:iterator>
						</ul>
					</div>					
				</div>
				<div class="Paging">
					共有${count }条信息 页次: ${currentPage }/${countSize}
					<s:if test="currentPage != 1">
						      【<a href="${pageContext.request.contextPath}/topic/p_1.html">第一页</a>】
						      【<a href="${pageContext.request.contextPath}/topic/p_${currentPage-1 }.html">上一页</a>】 
				     </s:if>
					<s:else>
						<span style="color: #CCCCCC">【第一页】</span>
						<span style="color: #CCCCCC">【上一页】</span>
					</s:else>
					<s:if test="currentPage < countSize">
					      【<a href="${pageContext.request.contextPath}/topic/p_${currentPage+1 }.html">下一页</a>】 
					      【<a href="${pageContext.request.contextPath}/topic/p_${countSize}.html">最末页</a>】
      				</s:if>
					<s:else>
						<span style="color: #CCCCCC">【下一页】</span>
						<span style="color: #CCCCCC">【最末页】</span>
					</s:else>
				</div>
			</div>
			<!--  所有问题结束-->
		</div>
		<!--左侧结束-->
		<!--右侧开始-->
		<div class="c_rightarea">
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
	<!--底部开始-->
	<jsp:include page="../bottom.jsp" />
	<!--底部结束-->
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/common/checkLogin/checkLogin.jsp"></script> --%>
</body>
</html>
