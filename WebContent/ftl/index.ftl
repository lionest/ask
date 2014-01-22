<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" Content="0" />
<meta name="keywords" content="三农问题,农业问题,农业专家,农业问答,三农问答" />
<meta name="description" content="三农问答，中国最大的涉农类问答网站，是安徽朗坤物联网有限公司基于三农问题自主研发的农业知识互动问答分享平台。在这里用户可以提出各种农业问题，会有权威农业专家及时解答，也有热心网友提供答案。" />
<title>三农问答-中国最大农业问答平台</title>
<link href="${bathPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<link href="${bathPath}/css/logon_1.css" rel="stylesheet" type="text/css" />
<link href="${bathPath}/css/login.css" rel="stylesheet" type="text/css" />
<script src="${bathPath}/js/checklogin.js" type="text/javascript"></script>
<script src="${bathPath}/js/tip_jq.js" type="text/javascript"></script>
<!-- 幻灯片的css和js -->
<link href="${bathPath}/css/slideshow.css" rel="stylesheet" />
<script src="${bathPath}/js/slideshow.js" type="text/javascript"></script>
<!-- 热门关键词 -->
<link href="${bathPath}/css/hotkeyword.css" rel="stylesheet" type="text/css" />
<script src="${bathPath}/js/hotkeyword.js" type="text/javascript"></script>
</head>
<body>
<script type="text/javascript">
function urlEnco(keyword){
	var url = encodeURI('${bathPath}/search/index.action?t='+keyword);
	window.open(url);
};
</script>
<#include "top.ftl"> 
<!--导航开始-->
<div class="nav">
  <ul class="menu">
     <li class="homeon"><a href="${bathPath}/index.html">问答首页</a></li>
     <li><a href="${bathPath}/domain/index.html">问题分类</a></li>
     <li><a href="${bathPath}/expert/index.html">专家目录</a></li>
     <li><a href="${bathPath}/topic/index.html">知识专题</a></li>
  </ul>
  <div class="fr"><img src="${bathPath}/images/menu04.jpg" width="1" height="38" /></div>
</div>
<!--内容区开始-->
<div class="area">
	<!--左侧开始-->
	<div class="leftarea">
		<div class="fenlei">
			<div class="sbar">
				<h2>
					<a href="${bathPath}/domain/index.html">问答分类</a>
				</h2>
				<span class="more"><a href="${bathPath}/domain/index.html">更多&gt;&gt;</a> </span>
			</div>
			<ul class="fllist">
				<#list domains as domain>
					<#if !(domain.parentId)??>
						<li class="tit"><a href="${bathPath}/domain/${(domain.id)?c}.html" id="domainId_${(domain.id)?c}">${domain.name}</a></li>
						<li>
							<#list domains as childDomain>
								<#if (childDomain.parentId)??&&(childDomain.parentId==domain.id)>
								<a href="${bathPath}/domain/${(childDomain.id)?c}.html" id="domainId_${childDomain.id}">${childDomain.name}</a>
								</#if>
							</#list>
						</li>
					</#if>
				</#list>
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
					<#list qkList as qk>
						<a onclick=urlEnco("${qk.keyword}"); target="_blank">${qk.keyword}</a>
					</#list>
				</div>
			</div>
		</div>
		<div class="denglu" style="width: 210px;height: 186px;margin-top: 10px;">
			<div class="sbar">
				<h2>
					<a href="#">关于我们</a>
				</h2>
			</div>
			<div style="margin: 9px;line-height: 160%;">
				<font size="2">&nbsp;&nbsp;&nbsp;&nbsp;三农问答，中国最大的涉农类问答网站，是安徽朗坤物联网有限公司基于三农问题自主研发的农业知识互动问答分享平台。在这里用户可以提出各种农业问题，会有权威农业专家及时解答，也有热心网友提供答案。</font>
			</div>
		</div>	
	</div>
	<!-- 左侧结束-->
	<!-- 中间开始-->
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
									<#list imgQuestions as imgQ>
										<li><a href="${bathPath}/question/${(imgQ.id)?c}.html" target="_blank"> 
											<img src="${bathPath}/question/${imgQ.url}" width="206" height="163" alt="${imgQ.subject}" />
											</a>
										</li>
									</#list>
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
			<!--推荐问题-->
			<div class="righttxt fr">
				<#list recommendedQuestions as question >
					<#if question_index==0 >
						<div class="bigtit">
							<#if question.subject?length gt 14 >
								<a href="${bathPath}/question/${(question.id)?c}.html" target="_blank">${question.subject[0..12]}...
								</a>
							<#else>
								<a href="${bathPath}/question/${(question.id)?c}.html" target="_blank">${question.subject}
								</a>
							</#if>
							<!-- 专家 -->
							<#list askForExpertList0 as eqId>
								<#if eqId==question.id >   
									<img src="${bathPath}/images/q_04.jpg" width="15" height="13" />
								</#if>
							</#list>
							<!-- 最新 -->
							<#list newQuestions0 as nqId>
								<#if nqId==question.id >   
									<img src="${bathPath}/images/new.gif" />
								</#if>
							</#list>
						</div>
						<#if recommendedQuestions?size gt 1>
							<ul class="nlistn">
						</#if>
					<#else>
						<li>
							<#if question.subject?length gt 17 >
								<a href="${bathPath}/question/${(question.id)?c}.html" target="_blank">${question.subject[0..15]}...
								</a>
							<#else>
								<a href="${bathPath}/question/${(question.id)?c}.html" target="_blank">${question.subject}
								</a>
							</#if>
							<!-- 专家 -->
							<#list askForExpertList0 as eqId>
								<#if eqId==question.id >   
									<img src="${bathPath}/images/q_04.jpg" width="15" height="13" />
								</#if>
							</#list>
							<!-- 最新 -->
							<#list newQuestions0 as nqId>
								<#if nqId==question.id >   
									<img src="${bathPath}/images/new.gif" />
								</#if>
							</#list>
						</li>
					</#if>
				</#list>
				<#if recommendedQuestions?size gt 1>
					<ul class="nlistn">
				</#if>
			</div>
		</div>
		<!-- 推荐信息结束-->
		<!-- 最新提问开始-->
		<div class="mntw">
			<div class="sbar">
				<h2>
					<a href="${bathPath}/domain/index.html">最新问题</a>
				</h2>
				<span class="more"><a href="${bathPath}/domain/index.html">更多&gt;&gt;</a> </span>
			</div>
			<div class="pad6">
				<ul class="nlistn">
					<#list latestOpenedQuestions as latQuestion>
						<li>
							<span class="rlist fr">${latQuestion.extraContent}</span> 
							<#if latQuestion.subject?length lt 24 >   
								<a href="${bathPath}/question/${(latQuestion.id)?c}.html" target="_blank"> ${latQuestion.subject}</a>
							<#else>
							    <a href="${bathPath}/question/${(latQuestion.id)?c}.html" target="_blank">${latQuestion.subject[0..22]}...</a>
							</#if>
							<!-- 专家 -->
							<#list askForExpertList as eqId>
								<#if eqId==latQuestion.id >   
									<img src="${bathPath}/images/q_04.jpg" width="15" height="13" />
								</#if>
							</#list>
							<!-- 最新 -->
							<#list newQuestions as nqId>
								<#if nqId==latQuestion.id >   
									<img src="${bathPath}/images/new.gif" />
								</#if>
							</#list>
							<span class="titfl">[${latQuestion.replyNum}回答]
							</span>
						</li>
					</#list>
				</ul>
			</div>
		</div>
		<!-- 最新提问结束-->
		<!-- 专家团队开始-->
		<div class="mzjtd">
			<div class="sbar">
				<h2>
					<a href="${bathPath}/expert/index.html">专家团队</a>
				</h2>
				<span class="more">
					<a href="${bathPath}/expert/index.html">更多&gt;&gt;</a>
				</span>
			</div>
			<table width="516" border="0" cellspacing="0" cellpadding="0" style="margin: 12px 0 0 12px; line-height: 24px;">
				<#list recommendedExperts as expert>
					 <#if expert_index==0||expert_index==2||expert_index==4>
						<tr>
					    <td width="90" height="110" align="left" valign="top"><a href="${bathPath}/expert/${(expert.id)?c}.html" target="_blank"><img
									src="${bathPath}/question/${expert.photoUrl}" class="imgboder" width="70" height="91" /></a></td>
							<td width="150" align="left" valign="top"><span class="f14tit"><a href="${bathPath}/expert/${(expert.id)?c}.html" target="_blank">${expert.expertName}</a></span><br />
								${expert.summary}<br /> <a href="${bathPath}/question/new_${(expert.id)?c}.html" target="_blank"><img
									src="${bathPath}/images/zxzjbt.jpg" alt="咨询专家" width="71" height="24" /></a></td>
					 </#if>
				    <#if expert_index==1||expert_index==3||expert_index==5>
					    	<td width="110" align="center" valign="top"><a href="${bathPath}./expert/${(expert.id)?c}.html" target="_blank"><img
									src="${bathPath}/question/${expert.photoUrl}" class="imgboder" width="70" height="91" /></a></td>
							<td align="left" valign="top"><span class="f14tit"><a href="${bathPath}/expert/${(expert.id)?c}.html" target="_blank">${expert.expertName}</a></span><br />
								${expert.summary}<br /> <a href="${bathPath}/question/new_${(expert.id)?c}.html" target="_blank"><img
									src="${bathPath}/images/zxzjbt.jpg" alt="咨询专家" width="71" height="24" /></a></td>
						</tr>
				    </#if>
				</#list>
				<#if (recommendedExperts?size%2==1) >
					</tr>
				</#if>
			</table>
		</div>
		<!-- 专家团队结束-->
		<!-- 已解答问题开始-->
		<div class="mntw">
			<div class="sbar">
				<h2>
					<a href="${bathPath}/domain/resolved/0.html">已解决问题</a>
				</h2>
				<span class="more"><a href="${bathPath}/question/new.html" target="_blank">我要提问&gt;&gt;</a> </span>
			</div>
			<div class="pad6">
				<ul class="nlistn">
					<#list latestSolvedQuestions as latsQuestion>
						<li>
							<span class="rlist fr">${latsQuestion.extraContent}</span> 
							<#if latsQuestion.subject?length lt 24 >   
								<a href="${bathPath}/question/${(latsQuestion.id)?c}.html" target="_blank"> ${latsQuestion.subject}</a>
							<#else>
							    <a href="${bathPath}/question/${(latsQuestion.id)?c}.html" target="_blank">${latsQuestion.subject[0..22]}...</a>
							</#if>
							<!-- 专家 -->
							<#list askForExpertList1 as eqId1>
								<#if eqId1==latsQuestion.id >   
									<img src="${bathPath}/images/q_04.jpg" width="15" height="13" />
								</#if>
							</#list>
							<!-- 最新 -->
							<#list newQuestions1 as nqId1>
								<#if nqId1==latsQuestion.id >   
									<img src="${bathPath}/images/new.gif" />
								</#if>
							</#list>
							<span class="titfl">[${latsQuestion.replyNum}回答]
							</span>
						</li>
					</#list>
				</ul>
			</div>
		</div>
		<!-- 已解答问题结束-->
	</div>
	<!-- 右侧开始 -->
	<div class="rightarea">
	<!-- 登录框开始 -->
	<#if user??>
		<div class="c_denglu">
			<div class="sbar">
				<h2>
					<a href="$${bathPath}/i/index.html" target="_blank">${user.username}</a>
				</h2>
				<span class="more_u"><a href="${bathPath}/i/index.html" target="_blank">个人中心</a> | <a
					href="http://sso.passport.longcom.com/logout?service=http://ask.longcom.com">退出>></a> </span>
			</div>
			<table width="220" border="0" cellspacing="0" cellpadding="0"
				style="margin: 8px 0 0 8px; line-height: 22px;">
				<tr>
					<td width="100">
						<#if user.sex=='1'>
							<img src="${bathPath}/images/tx.jpg" class="imgboder" width="73" height="60" />
						<#else>
							<img src="${bathPath}/images/tx_nan.jpg" class="imgboder" width="73" height="60" />
						</#if>
					</td>
					<td align="left">
						<a href="${bathPath}/i/q.html" target="_blank">提问数：${askCount} 个</a>
						<br />
						<a href="${bathPath}/i/r.html" target="_blank">回答数：${replyCount} 个</a>
						<br />
						<#if user.expert==1 >
							<a href="${bathPath}/i/e.html" target="_blank">向我提问</a>
						</#if>
						</li>
					</td>
				</tr>
			</table>
			<div class="u_list">
				<ul>
					<li>
						<a href="${bathPath}/i/q.html" target="_blank">我的提问</a>
					</li>
					<li>
						<a href="${bathPath}/i/r.html" target="_blank">我的回答</a>
					</li>
					<li>
						<a href="${bathPath}/i/c.html" target="_blank">我的评论</a>
					</li>
					<li>
						<a href="${bathPath}/i/reply.html" target="_blank">最新动态</a>
					</li>
				</ul>
			</div>
		</div>
	<#else>
		<div class="denglu tj_bj">
	      	<div class="sbar">
	        <h2>统计数据</h2>
	        <span class="more_u"><a href="http://sso.passport.longcom.com/login?service=http://ask.longcom.com/index.html">登录</a> | <a href="http://sso.passport.longcom.com">注册>></a></span> </div>
	        <div class="tj_info">
	            <p>已解决问题：<a href="${bathPath}/domain/resolved/0.html" target="_blank">${resolvedCount}</a></p>
	            <p>待解决问题：<a href="${bathPath}/domain/noResolve/0.html" target="_blank">${noResolveCount}</a></p>
	            <p>今日问题数：<a href="${bathPath}/domain/index.html" target="_blank">${todayQuestionCount}</a></p>
	            <p>今日回答数：<a href="${bathPath}/domain/index.html" target="_blank">${todayReplyCount}</a></p>
	        </div>
	    </div>
	</#if>
	<!-- 登录框结束-->
	<!-- 光荣榜开始-->
	<div class="cr_tjzj">
		<div class="sbar">
			<h2>
				助人光荣榜
			</h2>
			<span class="more"> </span>
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
	<!-- 知识专题开始-->
	<div class="r_zjft">
		<div class="sbar">
			<h2>
				<a href="${bathPath}/topic/index.html" title="知识专题" target="_blank" >知识专题</a>
			</h2>
			<span class="more"><a href="${bathPath}/topic/index.html" target="_blank">更多>></a> </span>
		</div>
		<div class="vimg pad12">
			<a href="${bathPath}/topic/${(topic.id)?c}.html" target="_blank" ><img class="imgboderv" src="${bathPath}/m/topic/${topic.imgUrl}" width="188" height="128" /></a><br /> 
			<b><a href="${bathPath}/topic/${(topic.id)?c}.html" target="_blank" >${topic.title}</a></b>
		</div>
		<ul class="olistn">
			<#list topicQuestions as tq>
				<li><a href="${bathPath}/question/${(tq.id)?c}.html" target="_blank">
				<#if tq.subject?length lt 15 >   
					${tq.subject}
				<#else>
				   ${tq.subject[0..13]}...
				</#if>
				</a></li>
			</#list>
		</ul>
	</div>
	<!-- 知识专题结束-->
	<!-- 待解决开始-->
	<div class="r_tjzj">
		<div class="sbar">
			<h2>
				<a href="${bathPath}/domain/index.html" title="问答集萃">待解决问题</a>
			</h2>
			<span class="more"><a href="${bathPath}/domain/index.html">更多>></a> </span>
		</div>
		<div class="olist pad6">
			<ul class="olistn">
				<#list noSolvedQuestions as noq>
					<li><a href="${bathPath}/question/${(noq.id)?c}.html" target="_blank">
						<#if noq.subject?length lt 15 >   
							${noq.subject}
						<#else>
						   ${noq.subject[0..13]}...
						</#if>
						</a>
					</li>
				</#list>
			</ul>
		</div>
	</div>
	<!-- 待解决结束-->
</div>
<div class="clear"></div>
<#include "bottom.ftl"> 
</body>
</html>