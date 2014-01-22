<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="三农专家问答是一个农业类的互动问答社区。提供向网友提问、回答、及贡献分享个人知识的服务。在这里可以感受到最热烈的互助气氛，浏览到最精彩的问答内容。" />
<title>
<#if titleDomains?size gt 0 >
	<#list titleDomains as tdo>${tdo.name}_</#list>问题分类_三农问答-中国最大农业问答平台
<#else>
	问题分类_三农问答-中国最大农业问答平台
</#if>
</title>
<link href="${bathPath}/css/wdfenlei.css" rel="stylesheet" type="text/css" />
<link href="${bathPath}/css/logon_2.css" rel="stylesheet" type="text/css" />
<link href="${bathPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<script src="${bathPath}/js/checklogin.js" type="text/javascript">
</script>
<script src="${bathPath}/js/tip_jq.js" type="text/javascript"></script>
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
	<#include "top.ftl"> 
	<!--导航开始-->
	<div class="nav">
	  <ul class="menu">
	     <li><a href="${bathPath}/index.html">问答首页</a></li>
	     <li class="on"><a href="${bathPath}/domain/index.html">问题分类</a></li>
	     <li><a href="${bathPath}/expert/index.html">专家目录</a></li>
	     <li><a href="${bathPath}/topic/index.html">知识专题</a></li>
	  </ul>
	  <div class="fr"><img src="${bathPath}/images/menu04.jpg" width="1" height="38" /></div>
	</div>
	<!--内容区开始-->
	<div style="margin-top: 10px;"></div>
	<div class="area">
		<!--左侧开始-->
		<div class="c_leftarea">
			<!--  分类开始-->
			<div class="c_fenlei">
				<div class="c_flsbar">
					<h2>
						问答分类
						 <a href="${bathPath}/domain/index.html" style="font-size: 12px;">所有分类</a>
						<#if domain!=0 >
							<#list domains as d>&gt;
								<a href="${bathPath}/domain/${d.id}.html"  >
									<span style="font-size: 12px;">${d.name}</span>
								</a>
							</#list>
						</#if>
					</h2>
				</div>
				<div class="c_fllist">
					<ul>
						<#if  mostDomainAndQuestionsNum?size gt 0 >
							<#list mostDomainAndQuestionsNum as D>
								<li><a href="${bathPath}/domain/${(D.id)?c}.html" id="domain_${(D.id)?c}">
								${D.name}
								</a>
								<span class="rlist"> ( 
										<#if  D.count??>
										${(D.count)?c}
										<#else>
										0
										</#if>
										 )
								</span></li>
							</#list>
						<#else>
							<p style="margin-top: 40px; font-size: 15px; margin-left: 80px;">
								该分类下面已经没有分类了...&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1);">返回上一页</a>
							</p>
						</#if>
					</ul>
				</div>
			</div>
			<!--  分类结束-->
			<!--  所有问题开始-->
			<div class="c_allqus">
				<div class="sbar">
					<ul class="c_nav">
						<#if  terms=='status'>
							<li class="on"><a href="${bathPath}/domain/${domain }.html">全部问题</a></li>
						<#else>
							<li><a href="${bathPath}/domain/${domain }.html">全部问题</a></li>
						</#if>
						<#if terms=='noReply'>
							<li class="on"><a href="${bathPath}/domain/noReply/${domain }.html">零回答</a></li>
						<#else>
							<li><a href="${bathPath}/domain/noReply/${domain }.html">零回答</a></li>
						</#if>
						<#if terms=='noResolve'>
							<li class="on"><a href="${bathPath}/domain/noResolve/${domain }.html">待解决</a></li>
						<#else>
							<li><a href="${bathPath}/domain/noResolve/${domain }.html">待解决</a></li>
						</#if>
						<#if terms=='resolved'>
							<li class="on"><a href="${bathPath}/domain/resolved/${domain }.html">已解决</a></li>
						<#else>
							<li><a href="${bathPath}/domain/resolved/${domain }.html">已解决</a></li>
						</#if>
						<#if terms=='recommend'>
							<li class="on"><a href="${bathPath}/domain/recommend/${domain }.html">精彩推荐</a></li>
						<#else>
							<li><a href="${bathPath}/domain/recommend/${domain }.html">精彩推荐</a></li>
						</#if>
					</ul>
				</div>
				<!-- 标题开始-->
				<div class="pad6">
					<ul class="c_tit">
						<li><span class="rlist fr">提问时间 </span><span class="rlist fr">回答数</span>标题 <span class="titfl">(共 ${count } 项)</span></li>
					</ul>
					<ul class="c_wtlist">
						<#list allQuestions as Q>
							<li>
								<span class="rlist fr">${Q.extraContent}
								</span>
								<span class="rlist fr" style="width:60px;">${Q.replyNum}</span> 
								<#if Q.subject?length lt 30 >   
								     <a href="${bathPath}/question/${(Q.id)?c}.html" target="_blank">${Q.subject}</a>
								<#else>
									<a href="${bathPath}/question/${(Q.id)?c}.html" target="_blank">${Q.subject[0..28]}...</a>
								</#if>
								<span class="titfl">[${Q.domain.name}]
							</span></li>
						</#list>
					</ul>
				</div>
				<div class="Paging">
					共有${count }条信息 页次: ${currentPage }/${countSize}
				<#if terms != 'status'>
					<#if currentPage != 1 >
      【<a href="${bathPath}/domain/${terms }/${domain }_1.html">第一页</a>】
      【<a href="${bathPath}/domain/${terms }/${domain }_${currentPage-1 }.html">上一页</a>】 
					<#else>
						<span style="color: #CCCCCC">【第一页】</span>
						<span style="color: #CCCCCC">【上一页】</span>
					</#if>
					<#if  currentPage lt countSize>
      【<a href="${bathPath}/domain/${terms }/${domain }_${currentPage+1 }.html">下一页</a>】 
      【<a href="${bathPath}/domain/${terms }/${domain }_${countSize}.html">最末页</a>】
					<#else>
						<span style="color: #CCCCCC">【下一页】</span>
						<span style="color: #CCCCCC">【最末页】</span>
					</#if>
				<#else>
					<#if currentPage!=1 >
      【<a href="${bathPath}/domain/${domain }_1.html">第一页</a>】
      【<a href="${bathPath}/domain/${domain }_${currentPage-1 }.html">上一页</a>】 
					<#else>
						<span style="color: #CCCCCC">【第一页】</span>
						<span style="color: #CCCCCC">【上一页】</span>
					</#if>
					<#if  currentPage lt countSize >
      【<a href="${bathPath}/domain/${domain }_${currentPage+1 }.html">下一页</a>】 
      【<a href="${bathPath}/domain/${domain }_${countSize}.html">最末页</a>】
					<#else>
						<span style="color: #CCCCCC">【下一页】</span>
						<span style="color: #CCCCCC">【最末页】</span>
					</#if>
				</#if>
				</div>
			</div>
			<!--  所有问题结束-->
		</div>
		<!--左侧结束-->
		<!--右侧开始-->
		<div class="c_rightarea">
			<!-- 登录框开始-->
			<!-- <div id="dl"><font color="red">Loading……</font></div> -->
			<!-- 登录框结束-->
			<!-- 光荣榜开始-->
			<div class="cr_tjzj" style="margin-top: 0px;">
				<div class="sbar">
					<h2>
						助人光荣榜
					</h2>
					<span class="more"></span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
						<#list expertAcceptedCountMap as expert>
							<li class="no${expert_index+1}"><span class="rlist fr">${expert.count}条回答被采纳</span>${expert.fullName}</li>
						</#list>
					</ul>
				</div>
			</div>
			<!-- 光荣榜结束-->
			<!-- 已解决问题开始-->
			<div class="cr_tjzj">
				<div class="sbar">
					<h2>
						<a href="${bathPath}/domain/resolved/0.html" title="已解决问题">已解决问题</a>
					</h2>
					<span class="more"><a href="${bathPath}/domain/resolved/0.html">更多>></a> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
						<#list latestSolvedQuestions as lsq>
							<li><a href="${bathPath}/question/${(lsq.id)?c}.html" target="_blank">
								<#if lsq.subject?length lt 20 >   
									${lsq.subject}
								<#else>
								   ${lsq.subject[0..18]}...
								</#if>
								</a>
							</li>
						</#list>
					</ul>
				</div>
			</div>
			<!-- 已解决问题结束-->
		</div>
	</div>
	<!--内容区结束-->
	<div class="clear"></div>
	<!--底部开始-->
	<#include "bottom.ftl"> 
	<!--底部结束-->
</body>
</html>
