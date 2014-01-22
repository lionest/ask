<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://ask.ah3nong.com/tags/security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<wd:sysData var="sitename" name="sitename" />
<wd:sysData var="title" name="title" />
<!-- 关键字 -->
<c:if test="${questionKeywords==null}">
	<meta name="keywords" content="${domainKeyWord }" />
</c:if>
<c:if test="${questionKeywords!=null}">
	<meta name="keywords" content="${domainKeyWord },${questionKeywords}" />
</c:if>
<!-- description -->
<c:if test="${fn:length(question.content)<=50}">
	<meta name="description" content="${question.subject }_${question.content }" />
</c:if>
<c:if test="${fn:length(question.content)>50}">
	<meta name="description" content="${question.subject }_${fn:substring(question.content,0,48)}..." />
</c:if>
<title>${question.subject }_<s:iterator value="titleDomains" id="tdo" status="st"><s:property value="#tdo.name"/>_</s:iterator>问题分类_<s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/></title>
<link href="${pageContext.servletContext.contextPath}/css/question.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.servletContext.contextPath}/css/logon_2.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/js/checklogin.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/question_view.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.lightbox-0.5.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.lightbox-0.5.css" media="screen" />
<script type="text/javascript">
$(function() {
    $('#gallery a ').lightBox();
});
	var questionId = ${question.id};
	//打开评论
	function openComment(replyId){
		//alert(replyId);
		var url="${pageContext.request.contextPath}/s/rc/listReplyComment.action";
		$.post(url,{replyId:replyId},
			function(d){
				if(d.data.replyCommentList.length==0){
					$("#"+replyId+"listc").append("<font color='#C8C8C8' id='emptynotice"+replyId+"'>————————————————————————————————————————————————————<br/>该回复暂无评论...</font><br/>");
				}else{
					for(var i=0;i<d.data.replyCommentList.length;i++){
						var repc = d.data.replyCommentList[i];
						$("#"+replyId+"listc").append("<font color='#C8C8C8'>————————————————————————————————————————————————————</font><br/><font color='#6E6E6E'>"+repc.username+"&nbsp;&nbsp;&nbsp;&nbsp;"+repc.createdTime+"</font><br/>"+repc.content+"<br/>");
					};
				}
				$("#"+replyId+"listcomment").show();
				$("#"+replyId+"listcomment").css("background",'#EEFAF6').css("border","2px solid #DFF6EE").css("padding","6px");
				$("#"+replyId+"commenturl").hide();
				$("#"+replyId+"removecomment").show();
			});
	}
	//关闭评论
	function removecomment(replyId){
		$("#"+replyId+"listc").empty();
		$("#"+replyId+"listcomment").hide();
		$("#"+replyId+"commenturl").show();
		$("#"+replyId+"removecomment").hide();
	}
	//增加评论
	function addreplycomment(replyId,replyNum){
		var url = "${pageContext.request.contextPath}/s/rc/addReplyComment.action";
		var content = document.getElementById(replyId+"replyCommentContent").value;
		if(content.length>200){
			alert("评论字数过多，请控制在200字以内...");
		}else{
			$.post(url,{replyId:replyId,replyCommentContent:content},
				function(d){
					//如果开始没有回复则在增加回复时把之前的提示无回复去掉.
					if(replyNum==0){
						$("#emptynotice"+replyId).empty();
					}
					if(d.result=="success"){
						$("#"+replyId+"listc").prepend("<font color='#C8C8C8'>————————————————————————————————————————————————————</font><br/><font color='#6E6E6E'>"+d.data.newComment.username+"&nbsp;&nbsp;&nbsp;&nbsp;"+d.data.newComment.createdTime+"</font><br/>"+d.data.newComment.content+"<br/>");
						//评论数显示增加一条
						$("#"+replyId+"commentNum").empty();
						var num = parseInt(replyNum)+1;
						$("#"+replyId+"commentNum").append("评论("+num+")");
						//把文本框内容清空
						$("#"+replyId+"replyCommentContent").val("");
						replyNum= replyNum+1;
					}else{
						alert("评论失败...");
					};
			});
		};
	};
	//处理编码
	function urlEnco(keyword){
		var url = encodeURI('${pageContext.request.contextPath}/search/index.action?t='+keyword);
		window.open(url);
	};
</script>
</head>
<body>
	<!--顶部开始-->
	<jsp:include page="../top.jsp" />
	<!--内容区开始-->
	
	<div class="ask_nav">
		<a href="${pageContext.request.contextPath}/index.html">首页</a>
		<c:forEach items="${domainList}" var="domain" varStatus="s">
		 >&nbsp;<a href="${pageContext.request.contextPath}/${homeon }/${domain.id }.html">${domain.name }</a>
		</c:forEach>
	</div>
	<div class="area">
		<div class="q_leftarea">
			<c:choose>
				<c:when test="${userType == 1}">
					<jsp:include page="view2.jsp"></jsp:include>
				</c:when>
				<c:when test="${userType == 4}">
					<jsp:include page="view3.jsp"></jsp:include>
				</c:when>
				<c:otherwise>
					<jsp:include page="view1.jsp"></jsp:include>
				</c:otherwise>
			</c:choose>
			<!--  相关内容结束-->
			<!--  分享开始-->
			
			<div class="jiathis_share_slide jiathis_share_24x24" id="jiathis_share_slide" style="margin-top: 34px;background-color: rgb(238, 250, 246);">
				<div class="jiathis_share_slide_top" id="jiathis_share_title"></div>
					<div class="jiathis_share_slide_inner">
						<div class="jiathis_style_24x24">
						<a class="jiathis_button_qzone"></a>
						<a class="jiathis_button_tsina"></a>
						<a class="jiathis_button_weixin"></a>
						<a class="jiathis_button_tqq"></a>
						<a class="jiathis_button_renren"></a>
						<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
						<script type="text/javascript">
						var jiathis_config = {
							slide:{
								divid:'jiathis_main',
								pos:'left'
							}
						};
						</script>
						<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>	
						<script type="text/javascript" src="http://v3.jiathis.com/code/jiathis_slide.js" charset="utf-8"></script>
					</div>
				</div>
			</div>
			<!--  分享结束-->
			<!--  相关问题开始-->
			<div class="q_xgqus">
				<div class="sbar">
					<h2>相关问题</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/${homeon }/${question.domain.id}.html" target="_blank">更多问题>></a></span>
				</div>
				<!-- 标题开始-->
				<div class="pad6">
					<ul class="q_wtlist">
						<c:if test="${fn:length(relevantQuestions)==0}">
							<li >暂时没有相关问题。</li>
						</c:if>
						<s:iterator value="relevantQuestions" id="xgquestion">
							<li><span class="rlist fr"><s:property value="#xgquestion.replyNum" />回答</span> 
							<a href="${pageContext.request.contextPath}/question/<s:property value="#xgquestion.id"/>.html" target="_blank">
								<c:if test="${fn:length(xgquestion.subject)<=34}">
									<s:property value="#xgquestion.subject" />
								</c:if>
								<c:if test="${fn:length(xgquestion.subject)>34}">
									<s:property value="#xgquestion.subject.substring(0,32)" />...
								</c:if>
							</a> 
							<!-- 最新问题图标 -->
							<s:iterator value="newQuestions" id="nqId">
								<s:if test="#nqId == #xgquestion.id">
									<img src="../images/new.gif" />
								</s:if>
							</s:iterator>
							<span class="titfl">[<s:property value="#xgquestion.domain.name" />]
							</span>
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!--  相关问题结束-->
		</div>
		<!--左侧结束-->
		<!--右侧开始-->
		<div class="c_rightarea">
			<!-- 登录框开始-->
			<!-- <div id="dl"><font color="red">Loading……</font></div> -->
			<!-- 登录框结束-->
			
			<!-- 浏览量最多问题开始-->
			<div class="r_tjzj" style="margin-top: 0px;">
				<div class="sbar">
					<h2>
						浏览最多
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/agriculture/index.html">更多>></a> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
						<s:iterator value="mostViewCountQuestions" id="mvquestion">
							<li><c:if test="${fn:length(mvquestion.subject)>20}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#mvquestion.id"/>.html" target="_blank"><s:property value="#mvquestion.subject.substring(0,18)" />...</a>
								</c:if> 
								<c:if test="${fn:length(mvquestion.subject)<= 20 }">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#mvquestion.id"/>.html" target="_blank"><s:property value="#mvquestion.subject" /></a>
								</c:if> 
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 浏览量最多问题结束-->
			<!-- 待解决开始-->
			<div class="r_tjzj" >
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/agriculture/index.html" title="待解决问题">等您回答</a>
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/agriculture/index.html">更多>></a> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
					<wd:questionList var="latestOpenedQuestions" count="8" status="1" orderBy="created_time desc" />
						<s:iterator value="#attr.latestOpenedQuestions" id="questionl">
							<li><c:if test="${fn:length(questionl.subject)>20}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#questionl.id"/>.html" target="_blank"><s:property value="#questionl.subject.substring(0,18)" />...</a>
								</c:if> 
								<c:if test="${fn:length(questionl.subject)<= 20 }">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#questionl.id"/>.html" target="_blank"><s:property value="#questionl.subject" /></a>
								</c:if> 
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 待解决结束-->
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
						<s:iterator value="#attr.latestSolvedQuestions" id="questionv">
							<li><c:if test="${fn:length(questionv.subject)>20}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#questionv.id"/>.html" target="_blank"><s:property value="#questionv.subject.substring(0,18)" />...</a>
								</c:if> 
								<c:if test="${fn:length(questionv.subject)<= 20 }">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#questionv.id"/>.html" target="_blank"><s:property value="#questionv.subject" /></a>
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