<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>${user.username}的最新动态_<s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/></title>
<link href="${pageContext.request.contextPath}/css/center.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/logon_2.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/checklogin.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
</head>
<body>
<script>
//选择最新回复，采纳，评论，追问
function myChoose(choose){
	//设置选中按钮的样式
	$("#noticeReply").removeClass();
	$("#noticeAccept").removeClass();
	$("#noticeComment").removeClass();
	$("#noticeProbing").removeClass();
	$("#noticeAuditFail").removeClass();
	$("#"+choose).addClass("onchoose");
	var type;
	if(choose=="noticeReply"){
		type='1';
	}else if(choose=="noticeAccept"){
		type='2';
	}else if(choose=="noticeComment"){
		type='3';
	}else if(choose=="noticeProbing"){
		type='4';
	}else{
		type='5';
	}
	var url = "${pageContext.request.contextPath}/showNoticeQuestions.action";
	//alert(url);
	$.post(url,{type:type},
			function(d){
				//清空原来页面问题
				$("#noticeQuestion").empty();
				if(d.data.questions.length!=0){
					//填充内容
					for(var i=0;i<d.data.questions.length;i++){
						if(d.data.questions[i].anchor!=null&&d.data.questions[i].anchor!=''){
							$("#noticeQuestion").append("<li><span class='rlist fr'>"+d.data.questions[i].createdTime+"</span><span style='float:right;margin-right:35px;' >"+d.data.questions[i].replyNum+"</span><a href='${pageContext.request.contextPath}/question/"+d.data.questions[i].id+".html#"+d.data.questions[i].anchor+"' target='_blank'>"+d.data.questions[i].subject+"</a><span class='titfl'>["+d.data.questions[i].domain+"]</span></li>");
						}else{
							$("#noticeQuestion").append("<li><span class='rlist fr'>"+d.data.questions[i].createdTime+"</span><span style='float:right;margin-right:35px;' >"+d.data.questions[i].replyNum+"</span><a href='${pageContext.request.contextPath}/question/"+d.data.questions[i].id+".html' target='_blank'>"+d.data.questions[i].subject+"</a><span class='titfl'>["+d.data.questions[i].domain+"]</span></li>");
						}
					}
				}else{
					$("#noticeQuestion").append("<li >该分类暂时没有问题。</li>");
				}
		});
};
var t1 = '${t1}';
function giveStyle(){
	//alert(t1);
	if(t1!=null&&t1!=''){
		$('#my'+t1).addClass('u_list_choosed');
	}
};
</script>
	<%@include file="../top.jsp"%>
	<!--内容区开始-->
	<div class="ask_nav">
		<a href="${pageContext.request.contextPath}/index.html">首页</a>&gt;<a href="${pageContext.request.contextPath}/i/reply.html">&nbsp;个人中心&nbsp;</a>&gt;<a href="${pageContext.request.contextPath}/i/reply.html">&nbsp;最新动态</a>
	</div>
	<div class="area">
		<!--左侧开始-->
		<div class="c_rightarea">
			<!-- 登录框开始-->
			<div id="dl">
				<font color="red">Loading……</font>
			</div>
			<!-- 登录框结束-->
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
							<li><span class="rlist fr"><s:property value="#question.replyNum" />回答</span> <c:if test="${fn:length(question.subject)>16}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"><s:property value="#question.subject.substring(0,14)" />... </a>
								</c:if> <c:if test="${fn:length(question.subject)<=16}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"><s:property value="#question.subject" /> </a>
								</c:if></li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 最新问题结束-->
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
							<li><c:if test="${fn:length(question.subject)>19}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"><s:property value="#question.subject.substring(0,17)" />...</a>
								</c:if> <c:if test="${fn:length(question.subject)<= 19 }">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"><s:property value="#question.subject" /></a>
								</c:if></li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 已解决问题结束-->
		</div>
		<!--左侧结束-->
		<!--右侧开始-->
		<div class="c_leftarea">
		<!-- 有新通知问题 -->
			<div class="c_allqus1" style="margin-bottom: 10px;">
				<div class="sbar">
					<ul class="c_nav">
						<c:if test="${type=='1'}">
							<li id="noticeReply" class="onchoose"><a onclick="myChoose('noticeReply')">最新回复</a></li>
							<li id="noticeAccept"><a onclick="myChoose('noticeAccept')">最新采纳</a></li>
							<!-- <li id="noticeComment"><a onclick="myChoose('noticeComment')">最新评论</a></li> -->
							<li id="noticeProbing"><a onclick="myChoose('noticeProbing')">最新追问</a></li>
							<li id="noticeAuditFail"><a onclick="myChoose('noticeAuditFail')">审核未通过</a></li>
						</c:if>
						<c:if test="${type=='2'}">
							<li id="noticeReply"><a onclick="myChoose('noticeReply')">最新回复</a></li>
							<li id="noticeAccept" class="onchoose"><a onclick="myChoose('noticeAccept')">最新采纳</a></li>
							<!-- <li id="noticeComment"><a onclick="myChoose('noticeComment')">最新评论</a></li> -->
							<li id="noticeProbing"><a onclick="myChoose('noticeProbing')">最新追问</a></li>
							<li id="noticeAuditFail"><a onclick="myChoose('noticeAuditFail')">审核未通过</a></li>
						</c:if>
						<c:if test="${type=='3'}">
							<li id="noticeReply"><a onclick="myChoose('noticeReply')">最新回复</a></li>
							<li id="noticeAccept"><a onclick="myChoose('noticeAccept')">最新采纳</a></li>
							<!-- <li id="noticeComment"  class="onchoose"><a onclick="myChoose('noticeComment')">最新评论</a></li> -->
							<li id="noticeProbing"><a onclick="myChoose('noticeProbing')">最新追问</a></li>
							<li id="noticeAuditFail"><a onclick="myChoose('noticeAuditFail')">审核未通过</a></li>
						</c:if>
						<c:if test="${type=='4'}">
							<li id="noticeReply"><a onclick="myChoose('noticeReply')">最新回复</a></li>
							<li id="noticeAccept"><a onclick="myChoose('noticeAccept')">最新采纳</a></li>
							<!-- <li id="noticeComment"><a onclick="myChoose('noticeComment')">最新评论</a></li> -->
							<li id="noticeProbing"  class="onchoose"><a onclick="myChoose('noticeProbing')">最新追问</a></li>
							<li id="noticeAuditFail"><a onclick="myChoose('noticeAuditFail')">审核未通过</a></li>
						</c:if>
						<c:if test="${type=='5'}">
							<li id="noticeReply"><a onclick="myChoose('noticeReply')">最新回复</a></li>
							<li id="noticeAccept"><a onclick="myChoose('noticeAccept')">最新采纳</a></li>
							<li id="noticeProbing"><a onclick="myChoose('noticeProbing')">最新追问</a></li>
							<li id="noticeAuditFail"  class="onchoose"><a onclick="myChoose('noticeAuditFail')">审核未通过</a></li>
						</c:if>
					</ul>
				</div>
				<!-- 标题开始-->
				<div class="pad6" style="margin-bottom: 12px;">
					<ul class="c_tit">
						<li><span class="rlist fr">&nbsp;&nbsp;&nbsp;&nbsp;提问时间 </span>
						<span style="float:right;width:52px;" >回复数 </span>
						标题
					</ul>
					<ul class="c_wtlist" id="noticeQuestion">
						<c:if test="${fn:length(noticeQuestions)==0}">
							<li id="noQuestionNotice">该分类暂时没有问题。</li>
						</c:if>
						<c:if test="${ fn:length(noticeQuestions)>0}">
							<c:forEach items="${noticeQuestions}" var="noticeQ" varStatus="T">
								<li><span class="rlist fr">${noticeQ.createdTime}</span> 
									<span style="float:right;margin-right:35px;" >
										${noticeQ.replyNum }
									</span>
									<c:choose>
										<c:when test="${noticeQ.anchor!=null && noticeQ.anchor!=0}">
											<a href="${pageContext.request.contextPath}/question/${noticeQ.id}.html#${noticeQ.anchor}" target="_blank">
										</c:when>
										<c:otherwise>
											<a href="${pageContext.request.contextPath}/question/${noticeQ.id}.html" target="_blank">
										</c:otherwise>
									</c:choose>
										<c:if test="${fn:length(noticeQ.subject)>30}">
											${fn:substring(noticeQ.subject,0,28)}...
										</c:if> 
										<c:if test="${fn:length(noticeQ.subject)<=30}">
											${noticeQ.subject }
										</c:if> 
									</a>
									<span class="titfl">[${noticeQ.domain}]</span>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
			</div>
			<!-- 有新通知问题结束 -->

		</div>
	</div>
	<!--内容区结束-->
	<div class="clear"></div>
	<!--底部开始-->
	<%@include file="../bottom.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/checkLogin/checkLogin.jsp"></script>
	<script type="text/javascript">
		giveStyle();
	</script>
</body>
</html>
