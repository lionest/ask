<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<link rel="stylesheet" href="../css/list.css" type="text/css" />
<link rel="stylesheet" href="../css/mainzone.css" type="text/css" />
<title>管理区域</title>
</head>
<body>
	<script>
		function btnlogin() {
			if (navigator.userAgent.indexOf("MSIE") > 0) {
				return "";
			}
			if (navigator.userAgent.indexOf("Firefox") > 0) {
				return "m/domain/";
			}
			if (navigator.userAgent.indexOf("Opera") > 0) {
				return "m/domain/";
			}
			if (navigator.userAgent.indexOf("Safari") > 0) {
				return "m/domain/";
			}
			if (navigator.userAgent.indexOf("Camino") > 0) {
				return "m/domain/";
			}
		}
		function confirmRemove(id) {
			if (confirm("确认删除吗？将会删除本条分类及该分类下所有问题.")) {
				window.location.href = '${pageContext.request.contextPath}/m/domain/remove.action?id=' + id;
			}
		}
	</script>
	<div id="man_zone">
		<div class="title_content">
			<!-- 分类头部导航 -->
			分类导航 - 
			<a href="${pageContext.request.contextPath}/m/domain/view.action">一级分类</a>
			<s:if test="domain!=0">
				<s:iterator value="domains" id="d" status="st">&gt;
					<a href="${pageContext.request.contextPath}/m/domain/view.action?domain=<s:property value='#d.id'/>"> <s:property value="#d.name" />
					</a>
				</s:iterator>
			</s:if>
		</div>
		<div class="pageContent" >
			<!-- 分类 -->
			<table class="table" width="400" layoutH="138">
				<thead>
					<tr>
						<th width="50%" class="asc">名称</th>
						<th width="50%" class="asc">操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="mostDomainAndQuestionsNum" id="D" status="st">
						<tr>
							<td align="center"><a href="${pageContext.request.contextPath}/m/domain/view.action?domain=<s:property value='#D.id'/>"> <s:property value="#D.name" />
							</a></td>
							<td align="center"><a href="javascript:void(0);" onclick="confirmRemove(${D.id });return false;">删除</a>
							<a href="${pageContext.request.contextPath}/m/domain/editList.action?id=${D.id }">编辑</a></td>
						</tr>
					</s:iterator>
					
					   <s:if test="mostDomainAndQuestionsNum==null">
					   <tr>
					   <td colspan="2">
					   		没有子分类
					   </td>
					   </tr>
					   </s:if>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
