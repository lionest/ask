<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="pragram" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache, must-revalidate"> 
<meta http-equiv="expires" content="0"> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 关键字 -->
<#if questionKeywords??>
	<meta name="keywords" content="${domainKeyWord },${questionKeywords}" />
<#else>
	<meta name="keywords" content="${domainKeyWord }" />
</#if>
<!-- description -->
<meta name="description" content="${question.subject }_${question.content }" />
<title>${question.subject }_<#list titleDomains as tdo>${tdo.name}_</#list>问题分类_三农问答-中国最大农业问答平台</title>
<link href="${bathPath}/css/question.css" rel="stylesheet" type="text/css" />
<link href="${bathPath}/css/logon_2.css" rel="stylesheet" type="text/css" />
<link href="${bathPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${bathPath}/ckeditor/ckeditor.js"></script>
<script src="${bathPath}/js/checklogin.js" type="text/javascript"></script>
<script src="${bathPath}/js/tip_jq.js" type="text/javascript"></script>
<script src="${bathPath}/js/question_view.js" type="text/javascript"></script>
<script type="text/javascript" src="${bathPath}/js/jquery.lightbox-0.5.js"></script>
<link rel="stylesheet" type="text/css" href="${bathPath}/css/jquery.lightbox-0.5.css" media="screen" />

<script type="text/javascript">
$(function() {
    $('#gallery a ').lightBox();
});
	var questionId = ${(question.id)?c};
	//打开评论
	function openComment(replyId){
		//alert(replyId);
		var url="${bathPath}/s/rc/listReplyComment.action";
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
		var url = "${bathPath}/s/rc/addReplyComment.action";
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
		var url = encodeURI('${bathPath}/search/index.action?t='+keyword);
		window.open(url);
	};
</script>
</head>
<body>
	<!--顶部开始-->
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
	<div class="ask_nav">
		<a href="${bathPath}/index.html">首页</a>
		<#list domainList as domain>
		 &gt;&nbsp;<a href="${bathPath}/domain/${(domain.id)?c}.html">${domain.name }</a>
		</#list>
	</div>
	<div class="area">
		<div class="q_leftarea">
			<#if userType == 1>
				<#include "view2.ftl"> 
			<#elseif userType == 4>
				<#include "view3.ftl"> 
			<#else>
				<#include "view1.ftl"> 
			</#if>
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
					<span class="more"><a href="${bathPath}/domain/${(question.domain.id)?c}.html" target="_blank">更多相关分类问题>></a></span>
				</div>
				<!-- 标题开始-->
				<div class="pad6">
					<ul class="q_wtlist">
						<#if relevantQuestions??>
							<#list relevantQuestions as xgquestion>
								<li><span class="rlist fr">${xgquestion.replyNum}回答</span> 
								<a href="${bathPath}/question/${(xgquestion.id)?c}.html" target="_blank">
									<#if xgquestion.subject?length lt 35>
										${xgquestion.subject}
									<#else>
										${xgquestion.subject[0..32]}...
									</#if>
								</a> 
								<!-- 最新问题图标 -->
								<#list newQuestions as nqId>
									<#if nqId == xgquestion.id >
										<img src="${bathPath}/images/new.gif" />
									</#if>
								</#list>
								<span class="titfl">[${xgquestion.domain.name}]
								</span>
								</li>
							</#list>
						<#else>
							<li >暂时没有相关问题。</li>
						</#if>
					</ul>
				</div>
			</div>
			<!--  相关问题结束-->
		</div>
		<!--左侧结束-->
		<!--右侧开始-->
		<div class="c_rightarea">
			<!-- 浏览量最多问题开始-->
			<div class="r_tjzj" style="margin-top: 0px;">
				<div class="sbar">
					<h2>
						浏览最多
					</h2>
					<span class="more"><a href="${bathPath}/domain/index.html">更多>></a> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
						<#list mostViewCountQuestions as mvquestion>
							<li><#if mvquestion.subject?length gt 20>
									<a href="${bathPath}/question/${(mvquestion.id)?c}.html" target="_blank">${mvquestion.subject[0..18]}...</a>
								<#else> 
									<a href="${bathPath}/question/${(mvquestion.id)?c}.html" target="_blank">${mvquestion.subject}</a>
								</#if> 
							</li>
						</#list>
					</ul>
				</div>
			</div>
			<!-- 浏览量最多问题结束-->
			<!-- 待解决开始-->
			<div class="r_tjzj" >
				<div class="sbar">
					<h2>
						<a href="${bathPath}/domain/index.html" title="待解决问题">等您回答</a>
					</h2>
					<span class="more"><a href="${bathPath}/domain/index.html">更多>></a> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
						<#list latestQuestions as questionl >
							<li><#if questionl.subject?length gt 20>
									<a href="${bathPath}/question/${(questionl.id)?c}.html" target="_blank">${questionl.subject[0..18]}...</a>
								<#else> 
									<a href="${bathPath}/question/${(questionl.id)?c}.html" target="_blank">${questionl.subject}</a>
								</#if> 
							</li>
						</#list>
					</ul>
				</div>
			</div>
			<!-- 待解决结束-->
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
						<#list latestSolvedQuestions as questionv>
							<li><#if questionv.subject?length gt 20>
									<a href="${bathPath}/question/${(questionv.id)?c}.html" target="_blank">${questionv.subject[0..18]}...</a>
								<#else> 
									<a href="${bathPath}/question/${(questionv.id)?c}.html" target="_blank">${questionv.subject}</a>
								</#if> 
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
</body>
</html>