<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<wd:sysData var="sitename" name="sitename" />
<wd:sysData var="title" name="title" />
<wd:sysData var="keywords" name="keywords" />
<wd:sysData var="description" name="description" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" Content="0" />
<meta name="keywords" content="<s:property value='#attr.keywords.content'/>" />
<meta name="description" content="<s:property value='#attr.description.content'/>" />
<title><s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/></title>
<link href="${pageContext.request.contextPath}/css/info.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
</head>
<body>
<wd:sysData var="LEVEL1" name="LEVEL1" />
<wd:sysData var="LEVEL2" name="LEVEL2" />
<wd:sysData var="LEVEL3" name="LEVEL3" />
<wd:sysData var="LEVEL4" name="LEVEL4" />
<wd:sysData var="LEVEL5" name="LEVEL5" />
<wd:sysData var="LEVEL6" name="LEVEL6" />
	<!--顶部开始-->
	<jsp:include page="../top.jsp" />
	<!--内容区开始-->
	<div class="ask_nav">
		<a href="${pageContext.request.contextPath}/index.action">首页</a>&nbsp;&gt;&nbsp;经验等级说明
	</div>
	<div class="info_area" style="height: auto;">
		<div class="levelhelper" >
			<h3>三农问答获得经验值规则</h3>
			<p>
				1.用户每新增一个提问增加5个经验。<br/>
				2.用户每新增一个回复增加3个经验。<br/>
				3.用户每一个回复被采纳，根据被采纳问题星级评分（一分，二分，三分，四分，五分）获得对应（2，4，6，8，10个）经验。<br/>
			</p>
			<h3>三农问答经验等级划分</h3>
			<table>
				<tr>
					<td width="70px;">
						经验值
					</td>
					<td width="100px;">
						0-<s:property value='#attr.LEVEL2.content'/>
					</td>
					<td width="80px;">
						对应称号
					</td>
					<td width="150px;">
						<s:property value='#attr.LEVEL1.cname'/>
					</td>
				</tr>
				<tr>
					<td>
						经验值
					</td>
					<td>
						<s:property value='#attr.LEVEL2.content'/>-<s:property value='#attr.LEVEL3.content'/>
					</td>
					<td>
						对应称号
					</td>
					<td>
						<s:property value='#attr.LEVEL2.cname'/>
					</td>
				</tr>
				<tr>
					<td>
						经验值
					</td>
					<td>
						<s:property value='#attr.LEVEL3.content'/>-<s:property value='#attr.LEVEL4.content'/>
					</td>
					<td>
						对应称号
					</td>
					<td>
						<s:property value='#attr.LEVEL3.cname'/>
					</td>
				</tr>
				<tr>
					<td>
						经验值
					</td>
					<td>
						<s:property value='#attr.LEVEL4.content'/>-<s:property value='#attr.LEVEL5.content'/>
					</td>
					<td>
						对应称号
					</td>
					<td>
						<s:property value='#attr.LEVEL4.cname'/>
					</td>
				</tr>
				<tr>
					<td>
						经验值
					</td>
					<td>
						<s:property value='#attr.LEVEL5.content'/>-<s:property value='#attr.LEVEL6.content'/>
					</td>
					<td>
						对应称号
					</td>
					<td>
						<s:property value='#attr.LEVEL5.cname'/>
					</td>
				</tr>
				<tr>
					<td>
						经验值
					</td>
					<td>
						<s:property value='#attr.LEVEL6.content'/>-？
					</td>
					<td>
						对应称号
					</td>
					<td>
						<s:property value='#attr.LEVEL6.cname'/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!--内容区结束-->
	<!--底部开始-->
	<jsp:include page="../bottom.jsp" />
	<!--底部结束-->
</body>
</html>
