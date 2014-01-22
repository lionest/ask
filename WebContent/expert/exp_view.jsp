<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://ask.ah3nong.com/tags/security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<wd:sysData var="sitename" name="sitename" />
<wd:sysData var="title" name="title" />
<wd:sysData var="keywords" name="keywords" />
<wd:sysData var="description" name="description" />
<meta name="keywords" content="<s:property value='#attr.keywords.content'/>" />
<meta name="description" content="<s:property value='#attr.description.content'/>" />
<title><s:property value="#e.fullName" />_专家目录_<s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/></title>
</s:iterator>
<link href="${pageContext.request.contextPath}/css/exp_view.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/checklogin.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
</head>
<body>
	<%@include file="../top.jsp"%>
	<!--内容区开始-->
	<div class="ask_nav">
		<a href="${pageContext.request.contextPath}/index.html">首页</a> >
		<s:iterator value="expert" id="e" status="st">
			<a href="${pageContext.request.contextPath}/expert/index.html">专家目录</a> &gt; <s:property value="#e.fullName" />
		</s:iterator>
	</div>
	<div class="area">
		<!--左侧开始-->
		<div class="q_leftarea">
			<!--  内容开始-->
			<s:iterator value="expert" id="e" status="st">
				<div class="q_quest01">
					<!--标题开始-->
					<div class="exp_tit">
						农业专家：
						<s:property value="#e.fullName" />
					</div>
					<!--专家信息开始-->
					<div class="exp_Content">
						<div class="exppic fl">
							<s:if test="#e.photoUrl != null">
								<img src="${pageContext.request.contextPath}/question/<s:property value='#e.photoUrl'/>" class="imgboder" width="150" height="190" />
							</s:if>
							<s:else>
								<img src="${pageContext.request.contextPath}/question/images/150X190.jpg" class="imgboder" width="150" height="190" />
							</s:else>
						</div>
						<div class="exptxt fl">
							<p>
								<b>擅&nbsp;&nbsp;长：</b>
								<s:property value="#e.summary" />
								<br /> <b>回答数：</b><span class="g12"> <s:if test="#e.count > 0">
										<s:property value="#e.count" />
									</s:if> <s:else>
            0
            </s:else></span>个
							</p>
							<p>
								<b>采纳率：</b>
								<s:if test="#e.isAcceptedExpertsNum ==  'null'">
									<span class="g12">0</span>
									<br />
								</s:if>
								<s:else>
									<s:if test="#e.isAcceptedExpertsNum >= 10">
										<span class="g12"><s:property value="#e.isAcceptedExpertsNum.substring(0,2)+'%'" /></span>
										<br />
									</s:if>
									<s:else>
										<span class="g12"><s:property value="#e.isAcceptedExpertsNum.substring(0,1)+'%'" /></span>
										<br />
									</s:else>
								</s:else>
								<b>领&nbsp;&nbsp;域：</b>
								<s:iterator value="#e.domains" id="d" status="st">
									<span class="g12"><a href="${pageContext.request.contextPath}/domain/<s:property value="#d.id" />.html"><s:property value="#d.name" /></a></span>
								</s:iterator>
								<br /> <a href="${pageContext.request.contextPath}/question/new_<s:property value='#e.id' />.html" target="_blank"><img src="${pageContext.request.contextPath}/images/zj_05.jpg" alt="咨询专家"
									width="113" height="35" /></a>
							</p>
						</div>
					</div>
					<!--专家简介开始-->
					<div class="exp_jj">专家简介</div>
					<div class="exp_jj_con">
					<c:if test="${fn:length(e.resume)>232}" >
						&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#e.resume.substring(0,230)" />...
					</c:if>
					<c:if test="${fn:length(e.resume)<=232 }" >
						<s:property value="#e.resume" />
					</c:if>
					</div>
				</div>
			</s:iterator>
			<!--  相关内容开始-->
			<div class="q_xgqus">
				<div class="sbar">
					<ul class="c_nav">
						<c:choose>
							<c:when test="${s == 'a' }">
								<li><a href="${pageContext.request.contextPath}/expert/q/<s:property value='#e.id'/>.html">他的回答</a></li>
								<li class="on"><a href="${pageContext.request.contextPath}/expert/a/<s:property value='#e.id'/>.html">他的提问</a></li>
							</c:when>
							<c:otherwise>
								<li class="on"><a href="${pageContext.request.contextPath}/expert/q/<s:property value='#e.id'/>.html">他的回答</a></li>
								<li><a href="${pageContext.request.contextPath}/expert/a/<s:property value='#e.id'/>.html">他的提问</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<!-- 标题开始-->
				<div class="pad6">
					<ul class="q_wtlist">
						<s:iterator value="getQuestionsBuExpertId" id="q" status="st">
							<li><s:if test="#q.status == 1">
									<span class="rlist fr">未解决</span>
								</s:if> <s:else>
									<span class="rlist fr">已解决</span>
								</s:else> <a href="${pageContext.request.contextPath}/question/<s:property value="#q.questionId" />.html" target="_blank"><s:property value="#q.subject" /></a> <span class="titfl">[<a href="#"><s:property
											value="#q.name" /></a>]
							</span></li>
						</s:iterator>
					</ul>
				</div>
				<div class="Paging">
				<if test="${s==null }">
					<input type="hidden" name="s" value="q" />
				</if>
					共有${count }条信息 页次: ${currentPage }/${countSize}
					<s:if test="currentPage != 1">
      【<a href="${pageContext.request.contextPath}/expert/${s}/${expertId }_1.html">第一页</a>】
      【<a href="${pageContext.request.contextPath}/expert/${s}/${expertId }_${currentPage-1 }.html">上一页</a>】 
     </s:if>
					<s:else>
						<span style="color: #CCCCCC">【第一页】</span>
						<span style="color: #CCCCCC">【上一页】</span>
					</s:else>
					<s:if test="currentPage < countSize">
      【<a href="${pageContext.request.contextPath}/expert/${s}/${expertId }_${currentPage+1 }.html">下一页</a>】 
      【<a href="${pageContext.request.contextPath}/expert/${s}/${expertId }_${countSize}.html">最末页</a>】
      </s:if>
					<s:else>
						<span style="color: #CCCCCC">【下一页】</span>
						<span style="color: #CCCCCC">【最末页】</span>
					</s:else>
				</div>
			</div>
			<!--  相关内容结束-->
		</div>
		<!--左侧结束-->
		<!--右侧开始-->
		<div class="c_rightarea">
			<!-- 登录框开始-->
			<!-- <div id="dl"><font color="red">Loading……</font></div> -->
			<!-- 登录框结束-->
			<!-- 推荐专家开始-->
			<div class="cr_tjzj" style="margin-top: 0px;">
				<div class="sbar">
					<h2>
						助人光荣榜
					</h2>
					<span class="more"> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
						<s:iterator value="expertAcceptedCountMap" id="expert" status="st">
							<li class="no<s:property value='#st.index+1'/>"><span class="rlist fr"><s:property value="#expert.count" />条回答被采纳</span>
								<a href="${pageContext.request.contextPath}/user/<s:property value="#expert.id" />.html" target="_blank"><s:property value="#expert.fullName" /></a>
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 推荐专家结束-->
			<!-- 已解决问题开始-->
			<div class="cr_tjzj">
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
