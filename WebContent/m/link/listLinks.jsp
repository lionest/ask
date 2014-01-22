<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<link rel="stylesheet" href="../css/mainzone.css" type="text/css" />
<title>管理区域</title>
</head>
<body>
	<div id="man_zone">
		<div class="title_content">
			<form action="${pageContext.request.contextPath}/m/link/viewLink.action" method="post">
			按状态查询：
				<select name="status">
					<option value="0,1,2">全部</option>
					<option value="0">申请中</option>
					<option value="1">已通过</option>
					<option value="2">未通过</option>
				</select>
				<input type="submit" value="确定"/>
				&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/m/link/add.jsp">增加友情链接</a>
			</form>
		</div>
		<div class="pageContent">
			<table class="table" width="900" layoutH="138">
				<thead>
					<tr>
						<th width="30%" class="asc">链接名</th>
						<th width="40%" class="asc">链接url</th>
						<th class="asc">状态</th>
						<th class="asc">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.pageRecords}" var="link" varStatus="s">
						<tr target="sid_user" rel="1">
							<td align="left">
								<a title="点击修改" href="${pageContext.request.contextPath}/m/link/detail.action?id=${link.id}" >${link.name}</a>
							</td>
							<td align="left"><a title="点击打开当前网站" href="${link.url}" target="_blank">${link.url}</a></td>
							<td align="left">
								<c:if test="${link.status==0}">
									<font color="blue" >申请中</font>
								</c:if>
								<c:if test="${link.status==1}">
									已通过
								</c:if>
								<c:if test="${link.status==2}">
									<font color="red" >未通过</font>
								</c:if>
							</td>
							<td align="left">
								<c:if test="${link.status==0}">
									<a href="${pageContext.request.contextPath}/m/link/updateLinkStatus.action?status=1&id=${link.id}">通过</a>
									<a href="${pageContext.request.contextPath}/m/link/updateLinkStatus.action?status=2&id=${link.id}">拒绝</a>
								</c:if>
								<c:if test="${link.status==1}">
									<a href="${pageContext.request.contextPath}/m/link/updateLinkStatus.action?status=2&id=${link.id}">取消</a>
								</c:if>
								<c:if test="${link.status==2}">
									<a href="${pageContext.request.contextPath}/m/link/updateLinkStatus.action?status=1&id=${link.id}">通过</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="panelBar">
				<jsp:include page="../pager.jsp" />
			</div>
		</div>
	</div>
</body>
</html>
