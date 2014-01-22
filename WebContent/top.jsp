<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
.search {
	float: left;
}

.UpLayer dl dt {
	width: 70px;
	position: absolute;
	z-index: 3;
	padding: 0 5px;
	line-height: 20px;
	text-align: center;
}

.UpLayer dt a {
	color: #019774;
	line-height: 22px;
}

.UpLayer02 {
	border-bottom: none;
	background: #F8F8F8;
	margin: -1px 0 0 -1px;
}

.UpLayer dl dd {
	width: 70px;
	position: absolute;
	z-index: 2;
	border: #E6E6E8 1px solid;
	border-top: none;
	padding: 5px;
	line-height: 20px;
	background: #f1f1f1;
	display: none;
	margin: 21px 0 0 -1px;
	text-align: center;
}

.UpLayer dl dd a {
	display: block;
	border-bottom: #ccc 1px dashed;
}
</style>
<script>
	//顶部下拉小菜单
	$(document).ready(function() {
		var objStr = ".UpLayer";
		$(objStr).each(function(i) {
			$(this).hover(function() {
				$($(objStr + " dd")[i]).show();
				$($(objStr + " dt")[i]).addClass("UpLayer02");
			});
			$(this).hover(function() {
			}, function() {
				$($(objStr + " dd")[i]).hide();
				$($(objStr + " dt")[i]).removeClass("UpLayer02");
			});
		});
		
		//选择搜索问题
		$("#searchQuestion").click(function(){
			$("#searchQuestion").css("background-color","#00A47E");
			$("#searchExpert").css("background-color","white");
			$("#searchTopic").css("background-color","white");
			$("#searchQuestiona").css("color","white");
			$("#searchExperta").css("color","#333333");
			$("#searchTopica").css("color","#333333");
			$("#searchFor").val("question");
		});
		//选择搜专家
		$("#searchExpert").click(function(){
			$("#searchQuestion").css("background-color","white");
			$("#searchExpert").css("background-color","#00A47E");
			$("#searchTopic").css("background-color","white");
			$("#searchQuestiona").css("color","#333333");
			$("#searchExperta").css("color","white");
			$("#searchTopica").css("color","#333333");
			$("#searchFor").val("expert");
		});
		//选择搜专题
		$("#searchTopic").click(function(){
			$("#searchQuestion").css("background-color","white");
			$("#searchExpert").css("background-color","white");
			$("#searchTopic").css("background-color","#00A47E");
			$("#searchQuestiona").css("color","#333333");
			$("#searchExperta").css("color","#333333");
			$("#searchTopica").css("color","white");
			$("#searchFor").val("topic");
		});
		
		//点击退出
		$("#clickLoginOut").click(function(){
			var pathname = window.location.pathname;
			var tmp = encodeURIComponent(pathname);
			url ="http://${applicationScope.casUrl }/logout?service=http%3A%2F%2F${applicationScope.siteUrl }"+tmp;
			window.location.href=url;
		});
	});
	//搜索栏
	function checkSearchWord() {
		var searchContent = document.getElementById("inpu").value;
		searchContent = searchContent.replace(/^[" "|" "]*/, "");//去左空格
		searchContent = searchContent.replace(/[" "|" "]*$/, "");//右
		//把去掉空格的内容放入搜索框
		document.getElementById("inpu").value = searchContent;
		if (searchContent == "" || searchContent == "请输入关键字") {
			alert("请输入关键字后进行搜索...");
			return false;
		} else {
			return true;
		}
	}
	//定时对页头我的最新回复，评论等的模块进行数据刷新
	setInterval("pushNotice()", 1000 * 60);
	function pushNotice() {
		var url = "${pageContext.request.contextPath}/showNoticeCount.action";
		//alert(url);
		$.post(url, {}, function(d) {
			//新动态提示框
			$("#noticetip").empty();
			if (d.data.noticeReplyCount != '0' || d.data.noticeAcceptCount != '0' || d.data.noticeProbingCount != '0'|| d.data.auditFailedCount != '0') {
				$("#noticetip").append("您有新动态！<img src='${pageContext.request.contextPath}/images/icon_hot.gif' />&nbsp;");
			} else {
				$("#noticetip").append("暂时没有新动态！");
			}
			//新回复数
			$("#noticeReplyCount").empty();
			if (d.data.noticeReplyCount != '0') {
				$("#noticeReplyCount").append(
						"<a href='${pageContext.request.contextPath}/i/reply.html' target='_blank'>新回复(<font color='red' style='font-weight: bold;'>" + d.data.noticeReplyCount + "</font></a>)");
			}
			//新采纳数
			$("#noticeAcceptCount").empty();
			if (d.data.noticeAcceptCount != '0') {
				$("#noticeAcceptCount").append(
						"<a href='${pageContext.request.contextPath}/i/accept.html' target='_blank'>新采纳(<font color='red' style='font-weight: bold;'>" + d.data.noticeAcceptCount + "</font></a>)");
			}
			//新评论数
			/* $("#noticeCommentCount").empty();
			if (d.data.noticeCommentCount != '0') {
				$("#noticeCommentCount").append(
						"<a href='${pageContext.request.contextPath}/i/comment.html' target='_blank'>新评论(<font color='red' style='font-weight: bold;'>" + d.data.noticeCommentCount + "</font></a>)");
			} */
			//新追问数
			$("#noticeProbingCount").empty();
			if (d.data.noticeProbingCount != '0') {
				$("#noticeProbingCount").append(
						"<a href='${pageContext.request.contextPath}/i/probing.html' target='_blank'>新追问(<font color='red' style='font-weight: bold;'>" + d.data.noticeProbingCount + "</font></a>)");
			}
			//审核未通过问题
			$("#auditFailedCount").empty();
			if (d.data.auditFailedCount != '0') {
				$("#auditFailedCount").append(
						"<a href='${pageContext.request.contextPath}/i/auditfail.html' target='_blank'>审核未通过(<font color='red' style='font-weight: bold;'>" + d.data.auditFailedCount + "</font></a>)");
			}
		});
	}
</script>
<!--顶部开始-->
<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico" media="screen" />
<div class="top">
	<div class="topnav">
		<div class="topleft">
			<div style="float: left;">欢迎来到三农问答！&nbsp;</div>
			<c:if test="${user!=null }">
				<div style="float: left;">
					<font color="red" style="font-weight: bold;">${user.username }</font>
				</div>
				<div style="float: left; margin-top: 4px;" class="UpLayer">
					<dl>
						<dt>
							<a href="${pageContext.request.contextPath}/i/index.html" target="_blank">我的问答</a>
						</dt>
						<dd>
							<a href="${pageContext.request.contextPath}/i/q.html" target="_blank">我的提问</a> <a href="${pageContext.request.contextPath}/i/r.html" target="_blank">我的回答</a> <a
								href="${pageContext.request.contextPath}/i/reply.html" target="_blank">最新动态</a>
						</dd>
					</dl>
				</div>
				<div style="float: left; margin-left: 80px; margin-top: 1px;">
					<a style="color: #019774;" href="${applicationScope.editUserUrl }" target="_blank">账户设置</a>&nbsp;&nbsp;
					<a href="javascript:void(0);" id="clickLoginOut">退出</a>
				</div>
			</c:if>
			<c:if test="${user==null }">
				<!-- <a href="http://sso.passport.longcom.com/login?service=http://ask.longcom.com/index.html" style="color: red">登录</a> -->
				用户名：<s:textfield size="12" style="border: 1px solid #019C7A;" id="J_Username"></s:textfield> &nbsp;
				密码：<s:textfield type="password" size="12" style="border: 1px solid #019C7A;" id="J_Password"></s:textfield>&nbsp;
				<a href="javascript:void(0);" onclick="submit();" style="background-color: #006940;color: white;border: 2px solid #006940;">登录</a>
				<font id="errMsg" color="red">&nbsp;</font> 
				还没有账号？
			&nbsp;<a href="${applicationScope.regUrl }" target="_blank" style="color: red">注册</a>
			<script>
			String.prototype.trim = function() {
				return this.replace(/(^\s*)|(\s*$)/g, "");
			}
			function submit(){
				var username =document.getElementById("J_Username").value.trim();
				var password = document.getElementById("J_Password").value.trim();
				if(username==""){
					$("#errMsg").html("请输入用户名！");
					$("#J_Username").val("");
					$("#J_Username").focus();
					return false;
				}
				if(password==""){
					$("#errMsg").html("请输入密码！");
					$("#J_Password").val("");
					$("#J_Password").focus();
					return false;
				}
				$('body').append($('<iframe/>').attr({ 
					style: "display:none;width:0;height:0;scrolling:no",
				    id:"ssoLoginFrame",
				    src:  "http://${applicationScope.casUrl }/logout?service=http://${applicationScope.siteUrl }/logoutCheck.jsp%3Frand="+Math.random() + "%26casUsername=" +username+"%26casPassword="+password     
				}));
			}
			function callBack(errMsg){
				if(errMsg!=""){
					$("#errMsg").html(errMsg);
					$("#J_Password").val("");
					$("#J_Password").focus();
					 deleteIFrame('#ssoLoginFrame');
				}
			}
			function deleteIFrame(iframeName) {  
			    var iframe = $(iframeName);   
			    if (iframe) { // 删除用完的iframe，避免页面刷新或前进、后退时，重复执行该iframe的请求  
			        iframe.remove()  
			    }  
			} 
			</script>
			</c:if>
		</div>
		<div class="tright">
			<c:if test="${user!=null }">
				<span id="noticetip">
					<c:choose>
						<c:when test="${noticeReplyCount>0||noticeAcceptCount>0||noticeProbingCount>0 || auditFailedCount>0 }">
							您有新动态！<img src="${pageContext.request.contextPath}/images/icon_hot.gif" />
						</c:when>
						<c:otherwise>
	   						暂时没有新动态！
	   					</c:otherwise>
					</c:choose>
				</span>
				<span id="noticeReplyCount">
					<c:if test="${sessionScope.noticeReplyCount != null &&sessionScope.noticeReplyCount gt 0}">
						<a href="${pageContext.request.contextPath}/i/reply.html" target="_blank">新回复(<font color="red" style="font-weight: bold;">${noticeReplyCount }</font></a>)
					</c:if>
				</span>
				<span id="noticeAcceptCount">
					<c:if test="${sessionScope.noticeAcceptCount != null && sessionScope.noticeAcceptCount gt 0 }">
						<a href="${pageContext.request.contextPath}/i/accept.html" target="_blank">新采纳(<font color="red" style="font-weight: bold;">${noticeAcceptCount }</font></a>)
					</c:if>
				</span>
				<%-- <span id="noticeCommentCount"> <s:if test="#session.noticeCommentCount>0">
						<a href="${pageContext.request.contextPath}/i/comment.html" target="_blank">新评论(<font color="red" style="font-weight: bold;">${noticeCommentCount }</font></a>)
				</s:if>
				</span> --%>
				<span id="noticeProbingCount">
					<c:if test="${sessionScope.noticeProbingCount != null && sessionScope.noticeProbingCount gt 0 }">
						<a href="${pageContext.request.contextPath}/i/probing.html" target="_blank">新追问(<font color="red" style="font-weight: bold;">${noticeProbingCount }</font></a>)
					</c:if>
				</span>
				<span id="auditFailedCount">
					<c:if test="${sessionScope.auditFailedCount != null && sessionScope.auditFailedCount gt 0 }">
						<a href="${pageContext.request.contextPath}/i/auditfail.html" target="_blank">审核未通过(<font color="red" style="font-weight: bold;">${auditFailedCount }</font></a>)
					</c:if>
				</span>
			</c:if>
			<c:if test="${user==null }">
			</c:if>
		</div>
	</div>
</div>
<!--顶部结束-->
<div class="header">
	<h1 class="logo">
		<a href="${pageContext.request.contextPath}/index.html" title="三农问答" target="_self">三农问答</a>
	</h1>
	<div class="ser" style="margin-top: -6px;" >
		<div style="width: 300px;height: 20px;">
			<div id="searchQuestion" style="float: left;background-color:#00A47E; width: 60px;line-height: 20px;height:20px;color: white;">&nbsp;
				<a id="searchQuestiona" style="color: white">搜问题</a>
			</div>
			<div id="searchExpert" style="float: left; width: 60px;line-height: 20px;height:20px;">&nbsp;
				<a id="searchExperta">搜专家</a>
			</div>
			<div id="searchTopic" style="float: left; width: 60px;line-height: 20px;height:20px;">&nbsp;
				<a id="searchTopica">搜知识</a>
			</div>
		</div>
		<form action="${pageContext.request.contextPath}/search/index.action" method="post" onsubmit="return checkSearchWord();">
			<input type="hidden" name="searchFor" id="searchFor" value="question"/>
			<div class="input">
				<div class="search">
					<c:if test="${requestScope.t != null}">
						<input type="text" value="<s:property value='t' />" style="width: 515px; font-size: 14px;" id="inpu" name="t" onClick="if(this.value=='请输入关键字'){this.value=''}"
							onblur="if(this.value==''){this.value='请输入关键字'}" maxlength="60" />
					</c:if>
					<c:if test="${requestScope.t == null}">
						<input type="text" value="请输入关键字" style="width: 515px; font-size: 14px;" id="inpu" name="t" onClick="if(this.value=='请输入关键字'){this.value=''}"
							onblur="if(this.value==''){this.value='请输入关键字'}" maxlength="60" />
					</c:if>
				</div>
				<div class="search">
					<a href="javascript:void(0);"><input type="submit" value="" style="width: 80px;height:25px;background-color: transparent; cursor: pointer;" /></a>
				</div>
			</div>
		</form>
		<div class="btn">
			<a href="${pageContext.request.contextPath}/question/new.html?askAll=1" target="_blank"><img src="${pageContext.request.contextPath}/images/twbt.jpg" alt="提问" width="91" height="32" /></a>
		</div>
	</div>
</div>
<%
	String str = (String) request.getParameter("homeon");
	if (str == null) {
		str = "str";
	}
%>
<!--导航开始-->
<div class="nav">
	<ul class="menu">
		<%
			if (str.equals("home") || str.equals("str")) {
		%>
		<li class="homeon"><a href="${pageContext.request.contextPath}/index.html">问答首页</a></li>
		<%
			} else {
		%>
		<li class="home"><a href="${pageContext.request.contextPath}/index.html">问答首页</a></li>
		<%
			}
			if (str.equals("agriculture")) {
		%>
		<li class="on"><a href="${pageContext.request.contextPath}/agriculture/index.html">问农事</a></li>
		<%
			} else {
		%>
		<li><a href="${pageContext.request.contextPath}/agriculture/index.html">问农事</a></li>
		<%
			}
			if (str.equals("market")) {
		%>
		<li class="on"><a href="${pageContext.request.contextPath}/market/index.html">问市场</a></li>
		<%
			} else {
		%>
		<li><a href="${pageContext.request.contextPath}/market/index.html">问市场</a></li>
		
		<%
			}
			if (str.equals("policy")) {
		%>
		<li class="on"><a href="${pageContext.request.contextPath}/policy/index.html">问政策</a></li>
		<%
			} else {
		%>
		<li><a href="${pageContext.request.contextPath}/policy/index.html">问政策</a></li>
		<%
			}
			if (str.equals("life")) {
		%>
		<li class="on"><a href="${pageContext.request.contextPath}/life/index.html">问生活</a></li>
		<%
			} else {
		%>
		<li><a href="${pageContext.request.contextPath}/life/index.html">问生活</a></li>
		<%
			}
			if (str.equals("expert")) {
		%>
		<li class="on"><a href="${pageContext.request.contextPath}/expert/index.html">找专家</a></li>
		<%
			} else {
		%>
		<li><a href="${pageContext.request.contextPath}/expert/index.html">找专家</a></li>
		<%
			}
			if (str.equals("topic")) {
		%>
		<li class="on"><a href="${pageContext.request.contextPath}/topic/index.html">学知识</a></li>
		<%
			} else {
		%>
		<li><a href="${pageContext.request.contextPath}/topic/index.html">学知识</a></li>
		<%
			}
		%>
	</ul>
	<div class="fr">
		<img src="${pageContext.request.contextPath}/images/menu04.jpg" width="1" height="38" />
	</div>
</div>
<!--导航结束-->
