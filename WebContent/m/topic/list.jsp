<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			专题列表
		</div>
		<div class="pageContent">
			<table class="table" width="900" layoutH="138">
				<thead>
					<tr>
						<th width="33%" class="asc">专题名</th>
						<th width="27%" class="asc">专题包含问题ID</th>
						<th width="7%" class="asc"></th>
						<th width="25%" class="asc">创建时间</th>
						<th align="center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.pageRecords}" var="topic" varStatus="s">
						<tr target="sid_user" rel="1">
							<td align="left">${topic.title}</td>
							<td align="left">
								<c:choose>
									<c:when test="${topic.questionIds==null||topic.questionIds==''}">
										<font color="blue">该专题暂时没有问题...</font>&nbsp;&nbsp;
									</c:when>
									<c:otherwise>
										${topic.questionIds}
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${topic.questionIds==null||topic.questionIds==''}">
										<a href="${pageContext.request.contextPath}/m/question/view.action">添加</a>
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/m/topic/detailTopic.action?id=${topic.id}">编辑</a>
									</c:otherwise>
								</c:choose>
							</td>
							<td><fmt:formatDate value="${topic.createdTime}" pattern="yyyy.MM.dd HH:mm:ss"/></td>
							<td align="center">
								<a href="${pageContext.request.contextPath}/m/topic/deleteTopic.action?id=${topic.id}">删除</a>
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
