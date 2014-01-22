<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<wd:sysData var="sitename" name="sitename" />
		<wd:sysData var="title" name="title" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/></title>
		<link href="${pageContext.request.contextPath}/css/info.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<script type="text/javascript">
	//控制登陆后的跳转页面
		function clickLogin(){
			var pathname = window.location.pathname;
			var url;
			//跳转提问页面页面
			if(pathname.substring(10,13) == "new"){
				var tmp = pathname.substring(10,pathname.length);
				url ="http://${applicationScope.casUrl }/login?service=http%3A%2F%2F${applicationScope.siteUrl }%2Fquestion%2F"+ tmp;
			//如果是从个人中心跳来的
			}else if(pathname.substring(1,2) == "i"){
				url ="http://${applicationScope.casUrl }/login?service=http%3A%2F%2F${applicationScope.siteUrl }%2Fi%2Findex.html";
			//跳转单个问题页面
			}else if(pathname.substring(1,2) == "p"){
				<% 
				if(session.getAttribute("LoginUrlQuestionId")!=null){
			     int temp=(Integer)session.getAttribute("LoginUrlQuestionId"); //拿到session中的值
			     out.print("var questionid="+temp+";");//输出script脚本并给t变量赋值
				}else{
					out.print("var questionid=0;");
				}
			    %>
			    if(questionid==0){
				    url ="http://${applicationScope.casUrl }/login?service=http%3A%2F%2F${applicationScope.siteUrl }%2Findex.html";
			    }else{
			        url ="http://${applicationScope.casUrl }/login?service=http%3A%2F%2F${applicationScope.siteUrl }%2Fquestion%2F"+ questionid +".html";
			    }
			//跳转
			}else{
				var tmp = encodeURIComponent(pathname);
				url ="http://${applicationScope.casUrl }/login?service=http%3A%2F%2F${applicationScope.siteUrl }"+tmp;
			};
			//alert(url);
			window.location.href=url;
		}
	
	</script>
		<!--顶部开始-->
		<jsp:include page="top.jsp" />
		<!--内容区开始-->
		<div class="ask_nav">
			<a href="${pageContext.request.contextPath}/index.html">首页</a> >
			<a>信息提示</a>
		</div>
		<div class="info_area">
			<div class="info_con1">
				<span class="fon14">对不起，您还没有登录，请登录以后进行此操作。</span>
			</div>
			<div class="info_btn">
				<div class="btn fl">
					<img onclick="window.location.href='${pageContext.request.contextPath}/index.html'"
						src="${pageContext.request.contextPath}/images/pic_04.jpg" style="cursor: pointer" width="113"
						height="35" />
					&nbsp;&nbsp;&nbsp;&nbsp;
					<img onclick="clickLogin()"
						src="${pageContext.request.contextPath}/images/login.jpg" style="cursor: pointer" width="113"
						height="35" />
				</div>
			</div>
		</div>
		<!--内容区结束-->
		<!--底部开始-->
		<jsp:include page="bottom.jsp" />
		<!--底部结束-->
	</body>
</html>
