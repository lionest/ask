<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员中心-个人中心-三农问答</title>
<link href="${pageContext.request.contextPath}/member/css/comm.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/member/css/member.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/member/js/png.js"></script>
<script src="${pageContext.request.contextPath}/js/jq.js"></script>
</head>

<body onload="setLeftHight();">
<!--topStart-->
	<div class="mb_top">
    	<div class="w1000">
        	<a href="#" class="mb_logo"><img src="${pageContext.request.contextPath}/member/images/mb_logo.png" /></a>
            
            <div class="mb_top_menu">
            	<a href="#" class="active">个人中心</a>
                <a href="#">我的主页</a>
            </div>
            
            <div class="fr">
            	<div class="img_head"><a href="#"><img src="${pageContext.request.contextPath}/member/images/img_head.jpg" /></a></div>
                <span class="mb_name">${user.username}</span>
                <a href="#" class="mb_link">[退出]</a>
                <div class="mb_top_icons">
                	<a href="#" class="mail"></a>
                    <a href="#" class="bulletin"></a>
                    <a href="#" class="setting"></a>
                    <a href="#" class="home"></a>
                </div>
                <div class="mb_top_search">
                	<input type="text" value="搜&nbsp;好友/ 公司/ 产品/ 资讯" />
                    <a href="#"></a>
                </div>
            </div>
            
        </div>        
    </div>
<!--topEnd-->

<div class="w1000">
<!--topImgStart-->
<div class="mb_top_img"><img src="${pageContext.request.contextPath}/member/images/top_img.jpg" /></div>
<!--topImgEnd-->

<!--leftStart-->
<script type="text/javascript"> 
	function setLeftHight(){
		document.getElementById("mb_left").style.height = document.getElementById("mb_right").offsetHeight + "px";
	}
</script> 
<div class="mb_left" id="mb_left">
    <!--user_info_start-->
    <div class="ml_info">
        <div class="head"><a href="#"><img src="${pageContext.request.contextPath}/member/images/img_head60.jpg" /></a></div>
        <div class="info">
            <div><strong>username</strong></div>
            <div class="lv"></div>
            <div><a href="#">[更改资料]</a></div>
        </div>
    </div>
    <!--user_info_end-->
    <div class="ml_line"></div>
    
    <!--menuStart-->
    <div class="ml_menu">
        <a href="#"><img src="${pageContext.request.contextPath}/member/images/icon_news.png" /><font>新鲜事</font></a>
        <a href="#"><img src="${pageContext.request.contextPath}/member/images/icon_friends.png" /><font>我的好友</font><span>3</span></a>
        <a href="#"><img src="${pageContext.request.contextPath}/member/images/icon_message.png" /><font>我的私信</font></a>
        <a href="#"><img src="${pageContext.request.contextPath}/member/images/icon_review.png" /><font>最新评论</font><span>2</span></a>
        <a href="#"><img src="${pageContext.request.contextPath}/member/images/icon_collection.png" /><font>我的收藏</font></a>
    </div>    
    <div class="ml_line"></div>
    
    <div class="ml_menu">
        <a href="#"><img src="${pageContext.request.contextPath}/member/images/icon_app.png" /><font>物联网应用</font></a>
        <a href="#"><img src="${pageContext.request.contextPath}/member/images/icon_productInfo.png" /><font>农产品信息</font></a>
        <a href="#"><img src="${pageContext.request.contextPath}/member/images/icon_onlineMall.png" /><font>网上商城</font></a>
        <a href="#"><img src="${pageContext.request.contextPath}/member/images/icon_expertService.png" /><font>专家服务</font></a>
        <a href="#" class="active"><img src="${pageContext.request.contextPath}/member/images/icon_Q&A.png" /><font>三农问答</font></a>
        <a href="#"><img src="${pageContext.request.contextPath}/member/images/icon_3nong.png" /><font>安徽三农</font></a>
        <a href="#"><img src="${pageContext.request.contextPath}/member/images/icon_3nongTv.png" /><font>三农电视</font></a>
    </div>  
    <div class="ml_line"></div>
    <!--menuEnd-->
</div>
<!--leftEnd-->
  
<!--rightStart-->
<div class="mb_right" id="mb_right">
<!--rightStart-->
	<div class="pr8">
        <div class="mr_tag_title">
        	<a href="#" class="tag">基本情况</a>
            <div class="fr">
            	<a href="http://ask.longcom.com" class="link" target="_blank">问答首页</a><span>|</span><a href="http://ask.longcom.com/expert/index.html" class="link" target="_blank">专家目录</a>
                <a href="http://ask.longcom.com/question/new.html" class="ques" target="_blank">我要提问</a>
            </div>
        </div>
        
        <div class="ques_frame">
        	<div class="ques_box">
            	<div><font>提问数：</font></div>
                <div><span>${askCount}</span></div>
            </div>
        	<div class="ques_box">
            	<div><font>回答数：</font></div>
                <div><span>${replyCount}</span></div>
            </div>
        	<div class="ques_box">
            	<div><font>采纳数：</font></div>
                <div><span>${acceptedCount}</span></div>
            </div>
        	<div class="ques_box">
            	<div><font>采纳率：</font></div>
                <div><span>${acceptedPrecent}</span></div>
            </div>
            <div class="ques_boxb">
            	<div><font>我的经验：</font><img src="${pageContext.request.contextPath}/member/images/icon_gold.png" /><font>${user.experience}</font></div>
                <div><font>热门标签：</font>
	                <c:forEach items="${hotkeywords}" var="hkw">
						<a onclick="urlEnco('${hkw.keyword}');" target="_blank"><span>${hkw.keyword}</span></a>
					</c:forEach><br/>
                	<font>...</font></div>
                <div><font>擅长领域：</font>
	                <c:if test="${user.expert==1}" >
						<c:forEach items="${domainList}" var="domain">
							<a href="http://ask.longcom.com/domain/${domain.domainId}.html" target="_blank"><span>${domain.name}</span></a>
						</c:forEach>
						<a href="http://ask.longcom.com/domain/index.html" target="_blank"><span>...</span></a>
					</c:if>
					<c:if test="${user.expert!=1}" >
						非专家用户暂未开放设置领域功能
					</c:if>
                	<%-- <img src="${pageContext.request.contextPath}/member/images/icon_edit.png" /><a href="#">编辑领域</a> --%>
                </div>                
            </div>
        </div><div class="clear"></div>
        
        <div class="mr_types w818">
            <a onclick=myChoose('ask','1'); class="active" id="ask">我的提问</a>
            <a onclick=myChoose('answer','1'); id="answer">我的回答</a>
            <c:if test="${user.expert==1}" >
            <a onclick=myChoose('help','1'); id="help">求助问题</a>
            </c:if>
            <c:if test="${user.expert!=1}" >
            <a onclick=myChoose('help','1'); id="help" style="display:none";></a>
            </c:if>
            <a onclick=myChoose('recommend','1'); id="recommend">推荐问题</a>
            <div class="clear"></div>
        </div><div class="clear"></div>
        
        <div class="ques_list_frame">
        	<div class="title">
            	<div class="fl"><span>标题</span><font id="countNum">(共&nbsp;${count}&nbsp;项)</font></div>
                <div class="fr"><span class="w50" >回答数</span><span class="w70">提问时间</span></div>
            </div>
            <div class="qlist">
            	<c:choose>
					<c:when test="${fn:length(questionList) != 0}">
						<c:forEach items="${questionList}" var="question">
			            	<div>
			                	<a href="http://ask.longcom.com/question/${question.id}.html" target="_blank">${question.subject }</a>
			                	<!-- <font>[玉米]</font> -->
			                    <span class="w70">${question.createdTime}</span>
			                    <span class="w50">&nbsp;&nbsp;${question.replyNum}</span>
			                </div>
            			</c:forEach>
					</c:when>
					<c:otherwise>
						该分类暂时没有问题
					</c:otherwise>
				</c:choose>
            </div>
            
            <div class="paging">
               <!--  <a href="#">上一页</a>
                <a onclick="myChoose('ask','1')" class="active">1</a>
                <a href="#">2</a>
                <font>...</font>
                <a href="#">20</a>
                <a onclick="myChoose('ask','2')">下一页</a> -->
            </div>
        </div>
    </div>
<!--rightEnd-->
</div>
<!--rightEnd-->
<div class="clear"></div>
<!--copyrightStart-->
<div class="copyright">
	<div><a href="#">网站首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">关于我们</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">解决方案</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">合作伙伴</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">联系我们</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">网站统计</a>&nbsp;&nbsp;|&nbsp;&nbsp;皖ICP备05009138号</div>
    <div>Copyright © 2012 - 2013 Longcom All Rights Reserved.</div>
    <div>朗坤物联网 版权所有</div>
</div>
<!--copyrightEnd-->
</div>
<script>
	//选择我的提问，回答，求助， 推荐+翻页 choose为选择问题类型，page为页数
	function myChoose(choose,page){
		//设置选中按钮的样式
		$("#ask").removeClass();
		$("#answer").removeClass();
		$("#help").removeClass();
		$("#recommend").removeClass();
		$("#"+choose).addClass("active");
		
		var url = "http://ask.longcom.com/s/me/"+choose+".action";
		//alert(url);
		$.post(url,{username:"${username}",size:"20",p:page},
				function(d){
					//清空该分类的问题数
					$("#countNum").empty();
					//填充
					if('${user.expert}'!='1'&&choose=='recommend'&&d.data.count==50){
						$("#countNum").append("(共&nbsp;60&nbsp;项)");
					}else{
						$("#countNum").append("(共&nbsp;"+d.data.count+"&nbsp;项)");
					}
					//清空原来页面问题
					$(".qlist").empty();
					//填充内容
					for(var i=0;i<d.data.questions.length;i++){
						$(".qlist").append("<div><a href='http://ask.longcom.com/question/"+d.data.questions[i].id+".html' target='_blank'>"+d.data.questions[i].subject+"</a><span class='w70'>"+d.data.questions[i].createdTime+"</span><span class='w50'>&nbsp;&nbsp;"+d.data.questions[i].replyNum+"</span></div>");
					}
					//翻页处理
					$(".paging").empty();
					if(page<=1){
						$(".paging").append("<a href='#'>上一页</a>");
					}else{
						var s =page-1;
						$(".paging").append("<a onclick=myChoose('"+choose+"','"+s+"');>上一页</a>");
					}
					//除后取大于小数的整数
					var c = d.data.count;
					//总页面数
					var pg = Math.ceil(c/20);
					//如果少于十页，页码全部列出
					if(pg<=10){
						for(var j=1;j<pg+1;j++){
							if(j==page){
								//如果该页面是当前页，设置样式
								$(".paging").append(" <a onclick=myChoose('"+choose+"','"+j+"'); class='active'>"+j+"</a>");							
							}else{
								$(".paging").append(" <a onclick=myChoose('"+choose+"','"+j+"');>"+j+"</a>");
							};
						};
					//否则..... 
					}else{
						var v2=pg-2;
						var v1=pg-1;
					//1.如果当前页面在前三页，则列出前6页和最后3页
						if(page<=6){
							//前6页
							for(var u=1;u<7;u++){
								if(u==page){
									//如果该页面是当前页，设置样式
									$(".paging").append(" <a onclick=myChoose('"+choose+"','"+u+"'); class='active'>"+u+"</a>");							
								}else{
									$(".paging").append(" <a onclick=myChoose('"+choose+"','"+u+"');>"+u+"</a>");
								};
							};
							$(".paging").append("<font>...</font>");
							//最后三页
							$(".paging").append(" <a onclick=myChoose('"+choose+"','"+v2+"');>"+v2+"</a>");
							$(".paging").append(" <a onclick=myChoose('"+choose+"','"+v1+"');>"+v1+"</a>");
							$(".paging").append(" <a onclick=myChoose('"+choose+"','"+pg+"');>"+pg+"</a>");
					//2.如果当前页面在最后三页，则列出前3页和最后6页
						}else if(page>=(v2-3)){
							//前三页
							$(".paging").append(" <a onclick=myChoose('"+choose+"','1');>"+1+"</a>");
							$(".paging").append(" <a onclick=myChoose('"+choose+"','2');>"+2+"</a>");
							$(".paging").append(" <a onclick=myChoose('"+choose+"','3');>"+3+"</a>");
							$(".paging").append("<font>...</font>");
							//最后6页
							var w = pg-5;
							for(w;w<=pg;w++){
								if(w==page){
									//如果该页面是当前页，设置样式
									$(".paging").append(" <a onclick=myChoose('"+choose+"','"+w+"'); class='active'>"+w+"</a>");							
								}else{
									$(".paging").append(" <a onclick=myChoose('"+choose+"','"+w+"');>"+w+"</a>");
								};
							};
					//3.如果当前页面不在前三页也不在后三页则列出前三页，中间所在页面和后三页
						}else{
							//前3页
							$(".paging").append(" <a onclick=myChoose('"+choose+"','1');>"+1+"</a>");
							$(".paging").append(" <a onclick=myChoose('"+choose+"','2');>"+2+"</a>");
							$(".paging").append(" <a onclick=myChoose('"+choose+"','3');>"+3+"</a>");
							$(".paging").append("<font>...</font>");
							//中间三页
							var tm1 =page-1;
							$(".paging").append("<a onclick=myChoose('"+choose+"','"+tm1+"');>"+tm1+"</a>");
							$(".paging").append("<a onclick=myChoose('"+choose+"','"+page+"'); class='active'>"+page+"</a>");
							var tm2 = parseInt(page)+1;
							$(".paging").append("<a onclick=myChoose('"+choose+"','"+tm2+"');>"+tm2+"</a>");
							$(".paging").append("<font>...</font>");
							//后三页
							var l2=pg-2;
							var l1=pg-1;
							$(".paging").append(" <a onclick=myChoose('"+choose+"','"+l2+"');>"+l2+"</a>");
							$(".paging").append(" <a onclick=myChoose('"+choose+"','"+l1+"');>"+l1+"</a>");
							$(".paging").append(" <a onclick=myChoose('"+choose+"','"+pg+"');>"+pg+"</a>");
						};
					}
					if(page>=pg){
						$(".paging").append("<a href='#'>下一页</a>");
					}else{
						//转为int型
						var xyy = parseInt(page)+1;
						$(".paging").append("<a onclick=myChoose('"+choose+"','"+xyy+"');>下一页</a>");
					};
			});
	};
	
	//第一次跳转到个人中心的翻页页码处理
	window.onload=function(){    
		   askpg();    
	};   
	function askpg(){
		var c = ${askCount};
		//总页面数
		var pg = Math.ceil(c/20);
		//翻页处理
		$(".paging").empty();
		$(".paging").append("<a href='#'>上一页</a>");
		//如果少于十页，页码全部列出
		if(pg<=10){
			for(var j=1;j<pg+1;j++){
				if(j=='1'){
					//如果该页面是当前页，设置样式
					$(".paging").append(" <a onclick=myChoose('ask','"+j+"'); class='active'>"+j+"</a>");							
				}else{
					$(".paging").append(" <a onclick=myChoose('ask','"+j+"');>"+j+"</a>");
				};
			};
		//否则列出前六页和最后3页
		}else{
			//前六页
			for(var k=1;k<7;k++){
				if(k=='1'){
					//如果该页面是当前页，设置样式
					$(".paging").append(" <a onclick=myChoose('ask','"+k+"'); class='active'>"+k+"</a>");							
				}else{
					$(".paging").append(" <a onclick=myChoose('ask','"+k+"');>"+k+"</a>");
				}
				//如果问题太多增加去问答系统个人中心链接
				$(".paging").append("<a href='http://ask.longcom.com/i/index.html'>MORE...</a>");
			};
			//最后3页
			var v2=pg-2;
			var v1=pg-1;
			$(".paging").append(" <a onclick=myChoose('"+choose+"','"+v2+"');>"+v2+"</a>");
			$(".paging").append(" <a onclick=myChoose('"+choose+"','"+v1+"');>"+v1+"</a>");
			$(".paging").append(" <a onclick=myChoose('"+choose+"','"+pg+"');>"+pg+"</a>");
		}
		if(pg<=1){
			$(".paging").append("<a href='#'>下一页</a>");
		}else{
			$(".paging").append("<a onclick=myChoose('ask','2');>下一页</a>");
		};
	};
	//处理编码后跳转
	function urlEnco(keyword){
		var url = encodeURI('http://ask.longcom.com/search/index.action?t='+keyword);
		window.open(url);
	};
</script>
</body>
</html>
