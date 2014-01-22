<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<%@ taglib prefix="sec" uri="http://ask.ah3nong.com/tags/security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<wd:sysData var="sitename" name="sitename" />
<wd:sysData var="title" name="title" />
<wd:sysData var="keywords" name="keywords" />
<wd:sysData var="description" name="description" />
<wd:sysData var="aboutus" name="aboutus" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" Content="0" />
<meta name="keywords" content="<s:property value='#attr.keywords.content'/>" />
<meta name="description" content="<s:property value='#attr.description.content'/>" />
<title><s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/></title>
<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/logon_1.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/checklogin.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
<!-- 幻灯片的css和js -->
<link href="${pageContext.request.contextPath}/css/slideshow.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/slideshow.js" type="text/javascript"></script>
<!-- 热门关键词 -->
<link href="${pageContext.request.contextPath}/css/hotkeyword.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/hotkeyword.js" type="text/javascript"></script>
<%-- <link href="${pageContext.request.contextPath}/css/nav.css" rel="stylesheet" type="text/css" /> --%>
<script type="text/javascript">
$(document).ready(function() {
		$(".fenlei ul li").hover(function() {
			$(this).addClass("hover_bg");
			$(this).children("div").show(); 
		}, function() {
			$(this).removeClass("hover_bg");
			$(this).children("div").hide();
		});
	});
	
function urlEnco(keyword){
	var url = encodeURI('${pageContext.request.contextPath}/search/index.action?t='+keyword);
	window.open(url);
};

function changeavatar(){
	$('#addavatar').fadeIn();
	$('#addavatar').center();
}

function closeavatar(){
	$('#addavatar').fadeOut();
}

function checkAvatar() {
	var file = $("#useravatar").val();
    if(file!=null&&file!=''){
		if (!/.(gif|jpg|png)$/.test(file)) {
			alert("上传图片类型必须是gif,jpg,png中的一种。");
			return false;
		}
	}
	return true; 
}
</script>
</head>
<body>
	<%@include file="top.jsp"%>
	<!--内容区开始-->
	<div class="area">
		<!--左侧开始-->
		<div class="leftarea">
			<div class="fenlei">
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/agriculture/index.html">问答分类</a>
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/agriculture/index.html">更多&gt;&gt;</a> </span>
				</div>
				<ul class="fllist">
					<wd:domainList var="arcdomains" recommended="true" orderBy="node_path"/>
					<s:iterator value="#attr.arcdomains" id="adomain" status="st">
						<s:if test="#adomain.layer==1">
							<s:if test="#lastLayer==2">
								</li>
							</s:if>
							<li class="tit"><a href="${pageContext.request.contextPath}/agriculture/<s:property value="#adomain.id" />.html" id="domainId_<s:property value="#adomain.id" />"><s:property
										value="#adomain.name" /></a></li>
						</s:if>
						<s:else>
							<s:if test="#lastLayer==1">
								<li>
							</s:if>
							<a href="${pageContext.request.contextPath}/agriculture/<s:property value='#adomain.id'/>.html"> <s:property value="#adomain.name" />
							</a>
						</s:else>
						<s:set var="lastLayer" value="#adomain.layer" />
					</s:iterator>
					<s:if test="#lastLayer==2">
						</li>
					</s:if>
					<wd:domainList var="domains" recommended="true" orderBy="node_path" />
					<li class="tit">
						<a href="/market/index.html" id="domainId_1">市场</a>
					</li>
					<li>
						<s:iterator value="#attr.domains" id="domain" status="st">
							<s:if test="#domain.parentId==-2">
								<a href="${pageContext.request.contextPath}/market/<s:property value='#domain.id'/>.html"> <s:property value="#domain.name" />
								</a>
							</s:if>
						</s:iterator>
					</li>
					<li class="tit">
						<a href="/policy/index.html" id="domainId_1">政策</a>
					</li>
					<li>
						<s:iterator value="#attr.domains" id="domain" status="st">
							<s:if test="#domain.parentId==-3">
								<a href="${pageContext.request.contextPath}/policy/<s:property value='#domain.id'/>.html"> <s:property value="#domain.name" />
								</a>
							</s:if>
						</s:iterator>
					</li>
					
					<li class="tit">
						<a href="/life/index.html" id="domainId_1">生活</a>
					</li>
					<li>
						<s:iterator value="#attr.domains" id="domain" status="st">
							<s:if test="#domain.parentId==-4">
								<a href="${pageContext.request.contextPath}/life/<s:property value='#domain.id'/>.html"> <s:property value="#domain.name" />
								</a>
							</s:if>
						</s:iterator>
					</li>
				</ul>
			</div>
			<!-- 热门标签-->
			<div class="denglu" style="width: 210px;height: 260px;margin-top: 10px;">
				<div class="sbar">
					<h2>
						<a href="#">热门标签</a>
					</h2>
				</div>
				<div class="lg_form" >
					<div id="div1" style="margin-top: 90px;margin-left: 7px;">
						<!-- <a href="#" title="11">三农</a>
						<a href="#" title="11">农业</a>
						<a href="#" title="11">物联网</a> -->
						<s:iterator value="qkList" id="qk" status="st">
							<a onclick=urlEnco("<s:property value='#qk.keyword' />"); target="_blank"><s:property value="#qk.keyword" /></a>
						</s:iterator>
					</div>
				</div>
			</div>
		</div>
		<!--中间开始-->
		<div class="midarea">
			<!-- 推荐信息开始-->
			<div class="mtj">
			<!-- 幻灯片 -->
				<div class="leftimg fl">
					<div class="comiis_wrapad" id="slideContainer" style="float: left;">
						<div id="frameHlicAe" class="frame cl">
							<div class="temp"></div>
							<div class="block" >
								<div class="cl" >
									<ul class="slideshow" id="slidesImgs">
										<s:iterator value="imgQuestions" id="imgQ" status="st">
											<li><a href="${pageContext.request.contextPath}/question/<s:property value='#imgQ.id'/>.html" target="_blank"> 
											<img src="${pageContext.request.contextPath}/question/<s:property value='#imgQ.url'/>" width="206" height="163" title="${imgQ.subject }" />
											</a></li>
										</s:iterator>
									</ul>
								</div>
								<div class="slidebar" id="slideBar">
									<ul>
										<li class="on">1</li>
										<li>2</li>
										<li>3</li>
										<li>4</li>
										<li>5</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<script type="text/javascript">
						SlideShow(3000);
					</script>
				</div>
				<div class="righttxt fr">
					<wd:questionList var="recommendedQuestions" count="6" recommended="true" orderBy="created_time desc" />
					<s:iterator value="#attr.recommendedQuestions" id="question" status="st">
						<s:if test="#st.index==0">
							<div class="bigtit" >
								<c:if test="${fn:length(question.subject)>14}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"> ${fn:substring(question.subject,0,12)}...
									</a>
								</c:if>
								<c:if test="${fn:length(question.subject)<=14}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"> ${question.subject}
									</a>
								</c:if>
								<s:iterator value="#attr.exptrecommendedQuestions" id="eqId">
									<s:if test="#eqId == #question.id">
										<img src="images/q_04.jpg" width="15" height="13" />
									</s:if>
								</s:iterator>
								<!-- 最新 -->
								<s:iterator value="#attr.newqrecommendedQuestions" id="nqId">
									<s:if test="#nqId == #question.id">
										<img src="images/new.gif"  />
									</s:if>
								</s:iterator>
							</div>
							<s:if test="#attr.recommendedQuestions.size>1">
								<ul class="nlistn">
							</s:if>
						</s:if>
						<s:else>
							<li>
								<div style="height: 26px;float: left;">
								<c:if test="${fn:length(question.subject)>17}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank">${fn:substring(question.subject,0,15)}...</a>
								</c:if> 
								<c:if test="${fn:length(question.subject)<=17}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank">${question.subject}</a>
								</c:if>
								</div>
								<!-- 专家 -->
								<s:iterator value="#attr.exptrecommendedQuestions" id="eqId">
									<s:if test="#eqId == #question.id">
										<div style="height: 20px;float: left;margin-top: 6px;"><img src="images/q_04.jpg" width="15" height="13" /></div>
									</s:if>
								</s:iterator>
								<!-- 最新 -->
								<s:iterator value="#attr.newqrecommendedQuestions" id="nqId">
									<s:if test="#nqId == #question.id">
										<div style="height: 20px;float: left;margin-top: 6px;"><img src="images/new.gif"  /></div>
									</s:if>
								</s:iterator><br/>
							</li>
						</s:else>
					</s:iterator>
					<s:if test="#attr.recommendedQuestions.size>1">
						</ul>
					</s:if>
				</div>
			</div>
			<!-- 推荐信息结束-->
			<!-- 最新提问开始-->
			<div class="mntw">
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/agriculture/index.html">最新问题</a>
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/agriculture/index.html">更多&gt;&gt;</a> </span>
				</div>
				<div class="pad6">
					<ul class="nlistn">
						<wd:questionList var="latestOpenedQuestions" count="8" orderBy="created_time desc" />
						<s:iterator value="#attr.latestOpenedQuestions" id="question">
							<li>
								<span class="rlist fr"><s:property value="#question.extraContent" /></span> 
								<!-- 图片 -->
								<s:iterator value="#attr.imglatestOpenedQuestions" id="imgId">
									<s:if test="#imgId == #question.id">
										<div title="图片" style="height: 20px;float: left;margin-top: 6px;margin-right: 4px;"><img src="images/imglogo.jpg" width="15" height="13" /></div>
									</s:if>
								</s:iterator> 
								<span style="height: 26px;float: left;">
								<c:if test="${fn:length(question.subject)>28}">
									<a href="question/<s:property value="#question.id"/>.html" target="_blank">${fn:substring(question.subject,0,26)}...</a>
								</c:if> 
								<c:if test="${fn:length(question.subject)<=28}">
									<a href="question/<s:property value="#question.id"/>.html" target="_blank">${question.subject}</a>
								</c:if> 
								</span>
								<!-- 专家 -->
								<s:iterator value="#attr.exptlatestOpenedQuestions" id="eqId">
									<s:if test="#eqId == #question.id">
										<div title="专家" style="height: 20px;float: left;margin-top: 6px;"><img src="images/q_04.jpg" width="15" height="13" /></div>
									</s:if>
								</s:iterator> 
								<!-- 最新 -->
								<s:iterator value="#attr.newqlatestOpenedQuestions" id="nqId">
									<s:if test="#nqId == #question.id">
										<div title="最新" style="height: 20px;float: left;margin-top: 6px;"><img src="images/new.gif"  /></div>
									</s:if>
								</s:iterator>
								<span class="titfl">&nbsp;
								</span>
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 最新提问结束-->
			<!-- 专家团队开始-->
			<div class="mzjtd">
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/expert/index.html">专家团队</a>
					</h2>
					<span class="more">
						<a href="${pageContext.request.contextPath}/expert/index.html">更多&gt;&gt;</a>
					</span>
				</div>
				<table width="516" border="0" cellspacing="0" cellpadding="0" style="margin: 12px 0 0 12px; line-height: 24px;">
					<wd:expertList var="recommendedExperts" count="6" recommended="true" />
					<s:iterator value="#attr.recommendedExperts" id="expert" status="st">
						<s:if test="#st.odd">
							<tr>
								<td width="90" height="110" align="left" valign="top"><a href="${pageContext.request.contextPath}/user/<s:property value="#expert.id"/>.html" target="_blank"><img
										src="question/<s:property value="#expert.photoUrl" />" class="imgboder" width="70" height="91" /></a></td>
								<td width="150" align="left" valign="top"><span class="f14tit"><a href="${pageContext.request.contextPath}/user/<s:property value="#expert.id"/>.html" target="_blank"><s:property value="#expert.expertName" /></a></span><br />
									<s:property value="#expert.summary" /><br /> <a href="${pageContext.request.contextPath}/question/new_<s:property value='#expert.id' />.html" target="_blank"><img
										src="${pageContext.request.contextPath}/images/zxzjbt.jpg" alt="咨询专家" width="71" height="24" /></a></td>
						</s:if>
						<s:else>
							<td width="110" align="center" valign="top"><a href="${pageContext.request.contextPath}/user/<s:property value="#expert.id"/>.html" target="_blank"><img src="question/<s:property value="#expert.photoUrl" />"
									class="imgboder" width="70" height="91" /></a></td>
							<td align="left" valign="top"><span class="f14tit"><a href="${pageContext.request.contextPath}/user/<s:property value="#expert.id"/>.html" target="_blank"><s:property value="#expert.expertName" /></a></span><br /> <s:property
									value="#expert.summary" /><br /> <a href="${pageContext.request.contextPath}/question/new_<s:property value='#expert.id' />.html" target="_blank"><img
									src="${pageContext.request.contextPath}/images/zxzjbt.jpg" alt="咨询专家" width="71" height="24" /></a></td>
							</tr>
						</s:else>
					</s:iterator>
					<s:if test="#attr.recommendedExperts.size%2==1">
						</tr>
					</s:if>
				</table>
			</div>
			<!-- 专家团队结束-->
			<!-- 已解答问题开始-->
			<div class="mntw" style="height: 214px;">
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/agriculture/resolved/0.html">已解决问题</a>
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/question/new.html" target="_blank">我要提问&gt;&gt;</a> </span>
				</div>
				<div class="pad6">
					<ul class="nlistn">
						<wd:questionList var="latestSolvedQuestions" count="6" status="2" orderBy="created_time desc" />
						<s:iterator value="#attr.latestSolvedQuestions" id="question">
							<li><span class="rlist fr"><s:property value="#question.extraContent" /></span> 
							<!-- 图片 -->
							<s:iterator value="#attr.imglatestSolvedQuestions" id="imgId">
								<s:if test="#imgId == #question.id">
									<div title="图片" style="height: 20px;float: left;margin-top: 6px;margin-right: 4px;"><img src="images/imglogo.jpg" width="15" height="13" /></div>
								</s:if>
							</s:iterator> 
							<span style="height: 26px;float: left;">
								<c:if test="${fn:length(question.subject)>28}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"> ${fn:substring(question.subject,0,26)}...
									</a>
								</c:if> 
								<c:if test="${fn:length(question.subject)<=28}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"> ${question.subject}
									</a>
								</c:if> 
							</span>
							<s:if test="#question.experience>0" >
								<!-- 经验 -->
								<div title="悬赏${question.experience }分" style="height: 20px;float: left;margin-top: 6px;margin-left: 2px;"><img src="images/explogo.jpg" width="13" height="13" /></div>
							</s:if>
								<!-- 专家 -->
								<s:iterator value="#attr.exptlatestSolvedQuestions" id="eqId">
									<s:if test="#eqId == #question.id">
										<div title="专家" style="height: 20px;float: left;margin-top: 6px;"><img src="images/q_04.jpg" width="15" height="13" /></div>
									</s:if>
								</s:iterator> 
								<!-- 最新 -->
								<s:iterator value="#attr.newqlatestSolvedQuestions" id="nqId">
									<s:if test="#nqId == #question.id">
										<div title="最新" style="height: 20px;float: left;margin-top: 6px;"><img src="images/new.gif"  /></div>
									</s:if>
								</s:iterator>
								<span class="titfl">&nbsp;
								</span>
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 已解答问题结束-->
		</div>
		<!--右侧开始-->
		<div class="rightarea">
			<!-- 热门专题开始-->
			<div class="r_zjft" style="margin-top: 0px;">
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/topic/index.html" title="知识专题" target="_blank" >知识专题</a>
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/topic/index.html" target="_blank">更多>></a> </span>
				</div>
				<div class="vimg pad12">
					<a href="${pageContext.request.contextPath}/topic/${topic.id}.html" target="_blank" ><img class="imgboderv" src="${pageContext.request.contextPath}/m/topic/${topic.imgUrl}" width="188" height="128" /></a><br /> 
					<b><a href="${pageContext.request.contextPath}/topic/${topic.id}.html" target="_blank" >${topic.title}</a></b>
				</div>
				<ul class="olistn">
					<s:iterator value="topicQuestions" id="tq" status="st">
						<li><a href="${pageContext.request.contextPath}/question/<s:property value="#tq.id"/>.html" target="_blank">
						<c:if test="${fn:length(tq.subject)>15}">
							${fn:substring(tq.subject,0,13)}...
						</c:if>
						<c:if test="${fn:length(tq.subject)<=15}">
							${tq.subject}
						</c:if>
						</a></li>
					</s:iterator>
				</ul>
			</div>
			<!-- 热门专题结束-->
			<!-- 问答集萃开始-->
			<div class="r_tjzj" style="height: 412px;">
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/agriculture/noResolve/0.html" target="_blank" title="问答集萃">待解决问题</a>
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/agriculture/noResolve/0.html" target="_blank">更多&gt;&gt;</a> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
						<wd:questionList var="recommendedLists" count="14" status="1" orderBy="created_time desc" />
						<s:iterator value="#attr.recommendedLists" id="question">
							<li><c:if test="${fn:length(question.subject)>15}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank">${fn:substring(question.subject,0,13)}...</a>
								</c:if> <c:if test="${fn:length(question.subject)<= 15 }">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank">${question.subject}</a>
								</c:if>
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 问答集萃结束-->
		</div>
	</div>
	<!--内容区结束-->
	<div class="clear"></div>
	<%@include file="bottom.jsp"%>
	<!-- 上传头像 -->
	<div style="position: absolute;  display: none;height:180px;width:290px;border: 1px solid #006940 ;background-color: #F3FCF9;" id="addavatar" >
		<h3 style="margin: 8px;">
			上传头像
		</h3>
		<div style="width: 200px;margin-top: 20px;margin-left: 50px;">
			<form action="${pageContext.request.contextPath}/addUserAvatar.action" method="post" enctype="multipart/form-data" onsubmit="return checkAvatar();">
				<input type="file" id="useravatar" name="useravatar" size="40"  />
				<br/><br/>
				用户可以上传1M之内的JPG,GIF,PNG图片 来作为自己的头像。
				<br/><br/>
				<input type="submit"  style="background:url(${pageContext.request.contextPath}/images/detail_01.jpg); width:48px;height:35px;" value="" />
				&nbsp;&nbsp;
				<input type="button"  onclick="closeavatar()" style="background:url(${pageContext.request.contextPath}/images/detail_02.jpg); width:48px;height:35px;" value="" />
				<input type="hidden" name="avamsg" value="toIndex" />
			</form>
		</div>
	</div>
</body>
</html>