<style>
.search{float:left;}
.UpLayer dl dt{width:70px;position:absolute; z-index:3;padding:0 5px;line-height:20px;text-align: center;}
.UpLayer dt a{color: #019774;line-height: 22px;}
.UpLayer02{ border-bottom:none;background:#F8F8F8; margin:-1px 0 0 -1px;}
.UpLayer dl dd{ width:70px;position:absolute;z-index:2;border:#E6E6E8 1px solid;border-top:none;padding:5px; line-height:20px; background:#f1f1f1; display:none; margin:21px 0 0 -1px;text-align: center;}
.UpLayer dl dd a{ display:block;border-bottom:#ccc 1px dashed;}
</style>
<script>
	//顶部下拉小菜单
	$(document).ready(function(){
	    var objStr = ".UpLayer";
	    $(objStr).each(function(i){
	        $(this).hover(function(){
	            $($(objStr+" dd")[i]).show();
	            $($(objStr+" dt")[i]).addClass("UpLayer02");
	        });
	        $(this).hover(function(){},function(){
	            $($(objStr+" dd")[i]).hide();
	            $($(objStr+" dt")[i]).removeClass("UpLayer02");
	        });
	    });
	});
	//搜索栏
	function checkSearchWord(){
		var searchContent = document.getElementById("inpu").value;
		searchContent = searchContent.replace(/^[" "|" "]*/,"");//去左空格
		searchContent = searchContent.replace(/[" "|" "]*$/,"");//右
		//把去掉空格的内容放入搜索框
		document.getElementById("inpu").value = searchContent;
		if(searchContent==""||searchContent=="请输入关键字"){
			alert("请输入关键字后进行搜索...");
		}else{
			var url = encodeURI('${bathPath}/search/index.action?t='+searchContent);
			window.open(url);
		}
	}
	
	//定时对页头我的最新回复，评论等的模块进行数据刷新
	setInterval("pushNotice()",1000*60);
    function pushNotice() {
    	var url = "$${bathPath}/showNoticeCount.action";
    	//alert(url);
    	$.post(url,{},
    			function(d){
    				//新动态提示框
    				$("#noticetip").empty();
    				if(d.data.noticeReplyCount!='0'||d.data.noticeAcceptCount!='0'||d.data.noticeCommentCount!='0'||d.data.noticeProbingCount!='0'){
    					$("#noticetip").append("您有新动态！<img src='${bathPath}/images/icon_hot.gif' />&nbsp;");
    				}else{
    					$("#noticetip").append("暂时没有新动态！");
    				}
    				//新回复数
    				$("#noticeReplyCount").empty();
    				if(d.data.noticeReplyCount!='0'){
    					$("#noticeReplyCount").append("<a href='${bathPath}/i/reply.html' target='_blank'>新回复(<font color='red' style='font-weight: bold;'>"+d.data.noticeReplyCount+"</font></a>)");
    				}
    				//新采纳数
    				$("#noticeAcceptCount").empty();
    				if(d.data.noticeAcceptCount!='0'){
    					$("#noticeAcceptCount").append("<a href='$${bathPath}/i/accept.html' target='_blank'>新采纳(<font color='red' style='font-weight: bold;'>"+d.data.noticeAcceptCount+"</font></a>)");
    				}
    				//新评论数
    				$("#noticeCommentCount").empty();
    				if(d.data.noticeCommentCount!='0'){
    					$("#noticeCommentCount").append("<a href='${bathPath}/i/comment.html' target='_blank'>新评论(<font color='red' style='font-weight: bold;'>"+d.data.noticeCommentCount+"</font></a>)");
    				}
    				//新追问数
    				$("#noticeProbingCount").empty();
    				if(d.data.noticeProbingCount!='0'){
    					$("#noticeProbingCount").append("<a href='${bathPath}/i/probing.html' target='_blank'>新追问(<font color='red' style='font-weight: bold;'>"+d.data.noticeProbingCount+"</font></a>)");
    				}
    		});
     }
</script>

<div class="top">
  <div class="topnav">
    <div class="topleft">
    	<div style="float: left;">欢迎来到三农问答！（静态化页面）&nbsp;</div>
    	<#if user??>
    		<div style="float: left;"><font color="red" style="font-weight: bold;">${user.username }</font></div>
    		<div style="float: left;margin-top: 4px;" class="UpLayer" >
	    		<dl>
			        <dt><a href="${bathPath}/i/index.html" target="_blank">个人中心</a></dt>
			        <dd>
			            <a href="${bathPath}/i/q.html" target="_blank">我的提问</a>
			            <a href="${bathPath}/i/r.html" target="_blank">我的回答</a>
			            <a href="${bathPath}/i/reply.html" target="_blank">最新动态</a>
			        </dd>
			    </dl>
		    </div>
		    <div style="float: left;margin-left: 80px;margin-top: 1px;">
		    	<a href="http://sso.passport.longcom.com/logout?service=http://ask.longcom.com">退出</a>
		    </div>
    	<#else>
			<a href="http://sso.passport.longcom.com/login?service=http://ask.longcom.com/index.html" style="color:red" >登录</a>
			&nbsp;还没有账号？
			&nbsp;<a href="http://sso.passport.longcom.com" style="color:red" >注册</a>
		</#if>
	</div>
    <div class="tright">
   		<#if user??>
	   		<span id="noticetip">
   				<#if noticeReplyCount gt 0||noticeAcceptCount gt 0||noticeCommentCount gt 0||noticeProbingCount gt 0 >
					您有新动态！<img src="../images/icon_hot.gif" />
   				<#else>
   					暂时没有新动态！
   				</#if>
			</span>
			<span id="noticeReplyCount">
				<#if noticeReplyCount gt 0>
					<a href="${bathPath}/i/reply.html" target="_blank">新回复(<font color="red" style="font-weight: bold;">${noticeReplyCount }</font></a>)
				</#if>
			</span>
			<span id="noticeAcceptCount">
				<#if noticeAcceptCount gt 0>
					<a href="${bathPath}/i/accept.html" target="_blank">新采纳(<font color="red" style="font-weight: bold;">${noticeAcceptCount }</font></a>)
				</#if>
			</span>
			<span id="noticeCommentCount">
				<#if noticeCommentCount gt 0 >
					<a href="${bathPath}/i/comment.html" target="_blank">新评论(<font color="red" style="font-weight: bold;">${noticeCommentCount }</font></a>)
				</#if>
			</span>
			<span id="noticeProbingCount">
				<#if noticeProbingCount gt 0>
					<a href="${bathPath}/i/probing.html" target="_blank">新追问(<font color="red" style="font-weight: bold;">${noticeProbingCount }</font></a>)
				</#if>
			</span>
		 </#if>
	</div>
  </div>
</div>
<!--顶部结束--> 
<div class="header">
  <h1 class="logo"><a href="${bathPath}/index.html" title="三农问答" target="_self">三农问答</a></h1>
  <div class="ser">
    <div class="input">
    <div class="search" >
     <#if t1??>
      <input type="text" value="${t1}" style="width:415px; _margin-top:5PX; font-size:16px;"  id="inpu" name="t1"
       onClick="if(this.value=='请输入关键字'){this.value=''}" 
      maxlength="60" />
      <#else>
       <input type="text" value="" style="width:415px; _margin-top:5PX; font-size:16px;"  id="inpu" name="t1"
       onClick="if(this.value=='请输入关键字'){this.value=''}" onblur="if(this.value==''){this.value='请输入关键字'}"
      maxlength="60" />
      </#if>
    </div>
    <div class="search">
    <a href="javascript:void(0);" ><input type="button" value="" style="width:80px; height:40px; background-color: transparent; cursor:pointer; " onclick="checkSearchWord()"/></a>
    </div>
    </div>
    <div class="btn"><a href="${bathPath}/question/new.html" target="_blank"><img src="${bathPath}/images/twbt.jpg" alt="提问" width="91" height="32" /></a></div>
    <div class="btn"><a href="${bathPath}/domain/index.html" target="_blank"><img src="${bathPath}/images/hdbt.jpg" alt="回答" width="91" height="32" /></a></div>
  </div>
 </div>
