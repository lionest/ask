<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<wd:sysData var="sitename" name="sitename" />
<wd:sysData var="title" name="title" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/></title>
<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/wdfenlei.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
</head>
<body>
	<script>
		function sendRequest(id) {
			var terms =document.getElementById("inpu").value;
			if (id == 'x') {
				window.location.href = "${pageContext.request.contextPath}/search/getNext.action?t=" + terms + "&currentPage=${currentPage+1}&status=${status}";
			} else if (id == 'm') {
				window.location.href = "${pageContext.request.contextPath}/search/getNext.action?t=" + terms + "&currentPage=${countSize}&status=${status}";
			} else if (id == 's') {
				window.location.href = "${pageContext.request.contextPath}/search/getNext.action?t=" + terms + "&currentPage=${currentPage-1}&status=${status}";
			} else if (id == 'd') {
				window.location.href = "${pageContext.request.contextPath}/search/getNext.action?t=" + terms + "&currentPage=1&status=${status}";
			}
		}
		
		function changeQuestionStatus(status){
			var terms =document.getElementById("inpu").value;
			if(status==1||status==2){
				window.location.href = "${pageContext.request.contextPath}/search/index.action?status="+status+"&t="+terms;
			}else{
				window.location.href = "${pageContext.request.contextPath}/search/index.action?t="+terms;
			}
		}
	</script>
	<!--顶部开始-->
	<%@include file="top.jsp"%>
	<!--内容区开始-->
	<div class="ask_nav">
		<div style="float: left"><a href="index.html">首页</a> &gt; 搜索结果：共找到与“</div>
		<div style="float: left"><font color="#2E8B57" ><xmp>${t}</xmp></font></div>
		<div style="float: left">”相关的知识<font color="#2E8B57" >${count }</font>项，用时<font color="#2E8B57" >${time }</font>毫秒。</div>
	</div>
	<div class="area">
		<!--左侧开始-->
		<div class="c_leftarea">
			<!--  所有问题开始-->
			<div class="c_allqus">
				<div class="sbar">
					<ul class="c_nav">
					<c:if test="${status!=1&&status!=2}">
						<li class="on"><a onclick="changeQuestionStatus(3)">全部问题</a></li>
						<li><a onclick="changeQuestionStatus(1)">待解决</a></li>
						<li><a onclick="changeQuestionStatus(2)">已解决</a></li>
					</c:if>
					<c:if test="${status==1}">
						<li><a a onclick="changeQuestionStatus(3)">全部问题</a></li>
						<li class="on"><a a onclick="changeQuestionStatus(1)">待解决</a></li>
						<li><a a onclick="changeQuestionStatus(2)">已解决</a></li>
					</c:if>
					<c:if test="${status==2}">
						<li><a a onclick="changeQuestionStatus(3)">全部问题</a></li>
						<li><a a onclick="changeQuestionStatus(1)">待解决</a></li>
						<li class="on"><a a onclick="changeQuestionStatus(2)">已解决</a></li>
					</c:if>
					</ul>
				</div>
				<!-- 标题开始-->
				<div class="pad6">
					<ul class="c_tit">
						<li><span class="rlist fr">提问时间 </span><span class="rlist fr">回答数</span>标题 <span class="titfl">(共 ${count } 项)</span></li>
					</ul>
					<ul class="c_wtlist">
						<s:if test="questions.size()>0">
							<s:iterator value="questions" id="q" status="T">
								<li><span class="rlist fr"><s:date name="#q.createdTime" format="MM-dd" /></span><span class="rlist fr">&nbsp;&nbsp;<s:property value="#q.replyNum" /></span> <c:if test="${fn:length(q.subject)>38}">
										<a href="${pageContext.request.contextPath}/question/<s:property value="#q.id" />.html" target="_blank"><s:property value="#q.subject.substring(0,36)" />...</a>
									</c:if> <c:if test="${fn:length(q.subject)<=38}">
										<a href="${pageContext.request.contextPath}/question/<s:property value="#q.id" />.html" target="_blank"><s:property value="#q.subject" /></a>
									</c:if> <span class="titfl">[<s:property value="#q.domainName" />]
								</span></li>
							</s:iterator>
						</s:if>
						<s:else>
							<div align="center">抱歉！没有找到您需要的内容！</div>
						</s:else>
					</ul>
				</div>
				<div class="Paging">
					<div class="Paging">
					<!-- 传递问题状态参数 -->
					<input type="hidden" name="status" value="${status}" />
						共有${count }条信息 页次: ${currentPage}/${countSize}
						<s:if test="currentPage>1">
      【<a href="javascript:void(0);" onclick="sendRequest('d');">第一页</a>】
      【<a href="javascript:void(0);" onclick="sendRequest('s');">上一页</a>】 
     </s:if>
						<s:else>
							<span style="color: #CCCCCC">【第一页】</span>
							<span style="color: #CCCCCC">【上一页】</span>
						</s:else>
						<s:if test="currentPage < countSize">
      【<a href="javascript:void(0);" onclick="sendRequest('x');">下一页</a>】 
      【<a href="javascript:void(0);" onclick="sendRequest('m');">最末页</a>】
      </s:if>
						<s:else>
							<span style="color: #CCCCCC">【下一页】</span>
							<span style="color: #CCCCCC">【最末页】</span>
						</s:else>
					</div>
				</div>
			</div>
			<!--  所有问题结束-->
		</div>
		<!--左侧结束-->
		<!--右侧开始-->
		<div class="c_rightarea">
			<!-- 最新问题开始-->
			<div class="cr_tjzj">
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/agriculture/index.html" title="最新问题">最新问题</a>
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/agriculture/index.html">更多>></a> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
						<wd:questionList var="latestOpenedQuestions" count="8" status="1" orderBy="created_time desc" />
						<s:iterator value="#attr.latestOpenedQuestions" id="question">
							<li><span class="rlist fr"><s:property value="#question.replyNum" />回答</span> <c:if test="${fn:length(question.subject)>17}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"><s:property value="#question.subject.substring(0,16)" />...</a>
								</c:if> <c:if test="${fn:length(question.subject)<=17}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"><s:property value="#question.subject" /> </a>
								</c:if> 
							</span></li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 最新问题结束-->
		</div>
	</div>
	<!--内容区结束-->
	<div class="clear"></div>
	<!--底部开始-->
<%@include file="bottom.jsp"%>
	<!--底部结束-->
</body>
</html>
