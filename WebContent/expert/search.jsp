<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<%@ taglib prefix="sec" uri="http://ask.ah3nong.com/tags/security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>
<c:if test="${domainName=='所有专家' }" >
	专家目录_<s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/>
</c:if>
<c:if test="${domainName!='所有专家' }" >
	${domainName }_专家目录_<s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/>
</c:if>
</title>
<link href="${pageContext.request.contextPath}/css/expert.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/logon_2.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/checklogin.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
</head>
<body>
	<script>
		window.onload = function() {
			var r =
	<%=request.getParameter("domain")%>
		;
			var domainId = document.getElementById("domain_" + r);
			if (domainId != null) {
				domainId.setAttribute("style", "font-weight:bold");
			}
			
			$("#searchQuestion").css("background-color","white");
			$("#searchExpert").css("background-color","#00A47E");
			$("#searchTopic").css("background-color","white");
			$("#searchQuestiona").css("color","#333333");
			$("#searchExperta").css("color","white");
			$("#searchTopica").css("color","#333333");
			$("#searchFor").val("expert");
		}
	</script>
	<%@include file="../top.jsp"%>
	<!--内容区开始-->
	<div class="area">
		<!--左侧开始-->
		<div class="c_leftarea">
			<!--  所有问题开始-->
			<div class="c_allqus" style="margin-top: 0px;">
				<div class="sbar">
					<h2>
						专家搜索结果
					</h2>
					<span class="more">共搜到 ${count } 个与<font color="#61A707">${t}</font>相关的专家</span>
				</div>
				<!-- 专家列表开始-->
				<s:iterator value="expertsList" id="e" status="st">
					<div class="zhuanj pad6">
						<div class="zjpic fl">
							<s:if test="#e.photoUrl != null">
								<img src="${pageContext.request.contextPath}/question/<s:property value='#e.photoUrl'/>" class="imgboder" width="82" height="106" />
							</s:if>
							<s:else>
								<img src="${pageContext.request.contextPath}/question/images/70X91.jpg" class="imgboder" width="82" height="106" />
							</s:else>
						</div>
						<div class="zjtxt fl">
						<div style="float: left;width: 250px;">
							<span class="bfon14"><a href="${pageContext.request.contextPath}/user/<s:property value='#e.id'/>.html" target="_blank"><s:property value="#e.name" /></a></span><br />
							专家描述：<s:property value="#e.summary" />
							<br /> 回答数：<span class="g12"> <s:if test="#e.count > 0">
									<s:property value="#e.count" />
								</s:if> <s:else>
            0
            </s:else>
							</span>个 采纳率：
							<s:if test="#e.isAcceptedCount ==  'null'">
								<span class="g12">0</span>
								<br />
							</s:if>
							<s:else>
								<s:if test="#e.isAcceptedCount >= 10">
									<span class="g12"><s:property value="#e.isAcceptedCount.substring(0,2)+'%'" /></span>
									<br />
								</s:if>
								<s:else>
									<span class="g12"><s:property value="#e.isAcceptedCount.substring(0,1)+'%'" /></span>
									<br />
								</s:else>
							</s:else>
							领域：
							<s:iterator value="#e.domains" id="d" status="st">
								<span class="g12"><a href="${pageContext.request.contextPath}/domain/<s:property value="#d.id" />.html"><s:property value="#d.name" /></a></span>
							</s:iterator>
							<br /> <a href="${pageContext.request.contextPath}/question/new_<s:property value='#e.id' />.html" target="_blank"><img src="${pageContext.request.contextPath}/images/zxzjbt.jpg"
								alt="咨询专家" width="71" height="24" /></a>
						</div>
						<!-- 专家简历 -->
						<div >
							<c:if test="${e.resume == null || fn:length(e.resume)== 0 }" >
								<span class="g12">该专家暂时没有上传简历...</span>
							</c:if>
							<c:if test="${fn:length(e.resume)>0}">
								<span class="bfon14">专家简历：</span><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<c:if test="${fn:length(e.resume)<=98}">
									<s:property value="#e.resume" />
								</c:if>
								<c:if test="${fn:length(e.resume)>98}">
									<s:property value="#e.resume.substring(0,96)" />...
								</c:if>
							</c:if>
						</div>
						</div>
					</div>
				</s:iterator>
				<div class="Paging">
					共有${count }条信息 页次: ${currentPage }/${countSize}
					<s:if test="currentPage gt 1">
      【<a href="${pageContext.request.contextPath}/search/index.action?searchFor=expert&t=${t}">第一页</a>】
      【<a href="${pageContext.request.contextPath}/search/index.action?searchFor=expert&t=${t}&currentPage=${currentPage-1 }">上一页</a>】 
     </s:if>
					<s:else>
						<span style="color: #CCCCCC">【第一页】</span>
						<span style="color: #CCCCCC">【上一页】</span>
					</s:else>
					<s:if test="currentPage lt countSize">
      【<a href="${pageContext.request.contextPath}/search/index.action?searchFor=expert&t=${t}&currentPage=${currentPage+1 }">下一页</a>】 
      【<a href="${pageContext.request.contextPath}/search/index.action?searchFor=expert&t=${t}&currentPage=${countSize }">最末页</a>】
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
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/common/checkLogin/checkLogin.jsp"></script> --%>
</body>
</html>
