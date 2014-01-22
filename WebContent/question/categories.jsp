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
<c:if test="${domainKeyword!=null}" >
	<s:iterator value="titleDomains" id="tdo" status="st"><s:property value="#tdo.name"/>_</s:iterator>问题分类_<s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/>
</c:if>
<c:if test="${domainKeyword==null}" >
	问题分类_<s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/>
</c:if>
</title>
<link href="${pageContext.request.contextPath}/css/wdfenlei.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/logon_2.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/checklogin.js" type="text/javascript">
</script>
<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
</head>
<body>
	<script>
		window.onload = function() {
			var r = <%=request.getParameter("domain")%> ;
			var domainId = document.getElementById("domain_" + r);
			if (domainId != null) {
				domainId.setAttribute("style", "font-weight:bold");
			}
		};
	</script>
	<%@include file="../top.jsp"%>
	<!--内容区开始-->
	<div style="margin-top: 10px;"></div>
	<div class="area">
		<!--左侧开始-->
		<div class="c_leftarea">
			<c:if test="${ fn:length(imgQuestionList)>0 }" >
				<!-- 图片问题 -->
				<div class="c_allqus" style="margin-top: 0px;margin-bottom: 10px;">
					<div class="sbar">
						<h2>精彩提问</h2>
					</div>
					<div style="height:110px;">
						<ul>
							<s:iterator value="imgQuestionList" id="imgQuestion">
								<li style="float: left;width: 113px;text-align: left;height: 86px;margin: 12px;display: inline;position:relative; ">
									<a href="${pageContext.request.contextPath}/question/${imgQuestion.id}.html" title="${imgQuestion.subject}" target="_blank">
										<img src="${pageContext.request.contextPath}/question/${imgQuestion.url}"  width="110px" height="83px;" style="border: 1px solid #DADADA;padding: 2px;"/>
									</a>
								</li>
							</s:iterator>
						</ul>
					</div>
				</div>
				<!-- 图片问题结束 -->
			</c:if>
			<!--  分类开始-->
			<div class="c_fenlei">
				<div class="c_flsbar">
					<h2>
						<a href="${pageContext.request.contextPath}/${homeon}/index.html" >
							<c:if test="${homeon=='agriculture' }">
								农务类
							</c:if>
							<c:if test="${homeon=='market' }">
								商务类
							</c:if>
							<c:if test="${homeon=='policy' }">
								政务类
							</c:if>
							<c:if test="${homeon=='life' }">
								生活类
							</c:if>
						</a>
						<s:if test="domain!=0">
							<s:iterator value="domains" id="d" status="st">&gt;
								<a href="${pageContext.request.contextPath}/${homeon}/<s:property value='#d.id'/>.html"  >
									<span style="font-size: 12px;"><s:property value="#d.name"/></span>
								</a>
							</s:iterator>
						</s:if>
					</h2>
					<!-- 马上提问 -->
					<h2 style="float: right;padding-top: 6px;padding-right: 10px;">
						<a href="${pageContext.request.contextPath}/question/new.html?domainId=${domain}" target="_blank" style="color: #F99700;font-size: 12px;">
							<img src="${pageContext.request.contextPath}/images/askbutton_03.png" />
						</a>
					</h2>
					<!-- 马上提问 -->
				</div>
				<div class="c_fllist">
					<ul>
						<s:if test="mostDomainAndQuestionsNum.size()!=0">
							<s:iterator value="mostDomainAndQuestionsNum" id="D" status="st">
								<li><a href="${pageContext.request.contextPath}/${homeon}/<s:property value='#D.id'/>.html" id="domain_<s:property value='#D.id'/>">
								<s:property value="#D.name" />
								</a>
								<span class="rlist"> ( <s:property value="#D.count" />
									<s:if test="#D.count == null">0</s:if> )
								</span></li>
							</s:iterator>
						</s:if>
						<s:else>
							<p style="margin-top: 40px; font-size: 15px; margin-left: 80px;">
								该分类下面已经没有分类了...&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1);">返回上一页</a>
							</p>
						</s:else>
					</ul>
				</div>
			</div>
			<!--  分类结束-->
			<!--  所有问题开始-->
			<div class="c_allqus">
				<div class="sbar">
					<ul class="c_nav">
						<s:if test="terms.equals('status')">
							<li class="on"><a href="${pageContext.request.contextPath}/${homeon}/${domain }.html">全部问题</a></li>
						</s:if>
						<s:else>
							<li><a href="${pageContext.request.contextPath}/${homeon}/${domain }.html">全部问题</a></li>
						</s:else>
						<s:if test="terms.equals('noReply')">
							<li class="on"><a href="${pageContext.request.contextPath}/${homeon}/noReply/${domain }.html">零回答</a></li>
						</s:if>
						<s:else>
							<li><a href="${pageContext.request.contextPath}/${homeon}/noReply/${domain }.html">零回答</a></li>
						</s:else>
						<s:if test="terms.equals('noResolve')">
							<li class="on"><a href="${pageContext.request.contextPath}/${homeon}/noResolve/${domain }.html">待解决</a></li>
						</s:if>
						<s:else>
							<li><a href="${pageContext.request.contextPath}/${homeon}/noResolve/${domain }.html">待解决</a></li>
						</s:else>
						<s:if test="terms.equals('resolved')">
							<li class="on"><a href="${pageContext.request.contextPath}/${homeon}/resolved/${domain }.html">已解决</a></li>
						</s:if>
						<s:else>
							<li><a href="${pageContext.request.contextPath}/${homeon}/resolved/${domain }.html">已解决</a></li>
						</s:else>
						<s:if test="terms.equals('recommend')">
							<li class="on"><a href="${pageContext.request.contextPath}/${homeon}/recommend/${domain }.html">精彩推荐</a></li>
						</s:if>
						<s:else>
							<li><a href="${pageContext.request.contextPath}/${homeon}/recommend/${domain }.html">精彩推荐</a></li>
						</s:else>
						<s:if test="terms.equals('experience')">
							<li class="on"><a href="${pageContext.request.contextPath}/${homeon}/experience/${domain }.html">悬赏问题</a></li>
						</s:if>
					</ul>
				</div>
				<!-- 标题开始-->
				<div class="pad6">
					<ul class="c_tit">
						<li>
							<span class="rlist fr">提问时间 </span>
							<span class="rlist fr" style="width:50px;">回答数</span>
							<s:if test="terms.equals('experience')">
								<span class="rlist fr" style="width:55px;">悬赏分数</span>
							</s:if>
							标题
							<span class="titfl">(共 ${count } 项)</span>
						 </li>
					</ul>
					<ul class="c_wtlist">
						<s:iterator value="allQuestions" id="Q" status="T">
							<li>
								<span class="rlist fr"><s:property value="#Q.extraContent" /> 
								</span>
								<span class="rlist fr" style="width:40px;"><s:property value="#Q.replyNum" /></span> 
								<s:if test="terms.equals('experience')">
									<span class="rlist fr" style="width:60px;">
										<img src="${pageContext.request.contextPath}/images/explogo.jpg" width="13px" height="13px" />
										<font color="#DA8301"><s:property value="#Q.experience" /></font>
									</span>
								</s:if>
								<!-- 图片 -->
								<s:iterator value="imgQuestions" id="imgId">
									<c:if test="${imgId == Q.id}">
										<div title="图片" style="height: 17px;float: left;margin-top: 9px;margin-right: 6px;"><img src="${pageContext.request.contextPath}/images/imglogo.jpg" width="15" height="13" /></div>
									</c:if>
								</s:iterator>
								<span style="height: 26px;float: left;"> 
									<c:if test="${fn:length(Q.subject)>30}">
										<a href="${pageContext.request.contextPath}/question/<s:property value="#Q.id"/>.html" target="_blank"><s:property value="#Q.subject.substring(0,28)" />...</a>
									</c:if> <c:if test="${fn:length(Q.subject)<=30}">
										<a href="${pageContext.request.contextPath}/question/<s:property value="#Q.id"/>.html" target="_blank"><s:property value="#Q.subject" /></a>
									</c:if> 
								</span>
								<!-- 悬赏小图标 -->
								<s:if test="!'experience'.equals(terms)">
									<s:if test="#Q.experience>0" >
										<div title="悬赏${Q.experience }分" style="height: 18px;margin-top: 8px;margin-left: 2px;float: left;"><img src="${pageContext.request.contextPath}/images/explogo.jpg" width="13" height="13" /></div>
									</s:if>
								</s:if>
								<span class="titfl">[<s:property value="#Q.domain.name" />]</span>
							</li>
						</s:iterator>
					</ul>
				</div>
				<div class="Paging" style="height: 43px;">
					共有${count }条信息 页次: ${currentPage }/${countSize}
				<s:if test="terms != 'status' ">
										<s:if test="currentPage != 1">
      【<a href="${pageContext.request.contextPath}/${homeon}/${terms }/${domain }_1.html">第一页</a>】
      【<a href="${pageContext.request.contextPath}/${homeon}/${terms }/${domain }_${currentPage-1 }.html">上一页</a>】 
     </s:if>
					<s:else>
						<span style="color: #CCCCCC">【第一页】</span>
						<span style="color: #CCCCCC">【上一页】</span>
					</s:else>
					<s:if test="currentPage < countSize">
      【<a href="${pageContext.request.contextPath}/${homeon}/${terms }/${domain }_${currentPage+1 }.html">下一页</a>】 
      【<a href="${pageContext.request.contextPath}/${homeon}/${terms }/${domain }_${countSize}.html">最末页</a>】
      </s:if>
					<s:else>
						<span style="color: #CCCCCC">【下一页】</span>
						<span style="color: #CCCCCC">【最末页】</span>
					</s:else>
				</s:if>
				<s:if test="terms == 'status'">
					<s:if test="currentPage != 1">
      【<a href="${pageContext.request.contextPath}/${homeon}/${domain }_1.html">第一页</a>】
      【<a href="${pageContext.request.contextPath}/${homeon}/${domain }_${currentPage-1 }.html">上一页</a>】 
     </s:if>
					<s:else>
						<span style="color: #CCCCCC">【第一页】</span>
						<span style="color: #CCCCCC">【上一页】</span>
					</s:else>
					<s:if test="currentPage < countSize">
      【<a href="${pageContext.request.contextPath}/${homeon}/${domain }_${currentPage+1 }.html">下一页</a>】 
      【<a href="${pageContext.request.contextPath}/${homeon}/${domain }_${countSize}.html">最末页</a>】
      </s:if>
					<s:else>
						<span style="color: #CCCCCC">【下一页】</span>
						<span style="color: #CCCCCC">【最末页】</span>
					</s:else>
				</s:if>
				</div>
			</div>
			<!--  所有问题结束-->
		</div>
		<!--左侧结束-->
		<!--右侧开始-->
		<div class="c_rightarea">
			<!-- 最新问题开始-->
			<div class="cr_tjzj" style="margin-top: 0px;">
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/${homeon}/index.html" title="已解决问题">最新问题</a>
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/${homeon}/index.html">更多>></a> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
					<wd:questionList var="latestQuestions" count="8" orderBy="created_time desc"/>
						<s:iterator value="#attr.latestQuestions" id="questionla">
							<li><c:if test="${fn:length(questionla.subject)>20}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#questionla.id"/>.html" target="_blank"><s:property value="#questionla.subject.substring(0,18)" />...</a>
								</c:if> 
								<c:if test="${fn:length(questionla.subject)<= 20 }">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#questionla.id"/>.html" target="_blank"><s:property value="#questionla.subject" /></a>
								</c:if> 
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 最新问题结束-->
			<!-- 已解决问题开始-->
			<div class="cr_tjzj">
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/${homeon}/resolved/0.html" title="已解决问题">已解决问题</a>
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/${homeon}/resolved/0.html">更多>></a> </span>
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
</body>
</html>
