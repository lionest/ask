<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/m/css/common.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/m/css/list.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/m/css/mainzone.css" type="text/css" />
<title>管理区域</title>
</head>
<body>
	<div id="man_zone">
		<div class="title_content">
			参数列表&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/m/sysdata/add.jsp" >增加新参数</a><br/><br/>
		</div>
		<div class="pageContent">
			<table class="table" width="800" layoutH="138">
				<thead>
					<tr>
						<th width="20%" class="asc">参数名称</th>
						<th width="68%" class="asc">参数值</th>
						<th class="asc">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.pageRecords}" var="sysdata" varStatus="s">
						<tr target="sid_user" rel="1">
							<td align="left">${sysdata.cname}</td>
							<td align="left">
							
								<c:if test="${fn:length(sysdata.content)>38}">
									${fn:substring(sysdata.content,0,36) }...
								</c:if>
								<c:if test="${fn:length(sysdata.content)<=38}">
									${sysdata.content }
								</c:if>
							</td>
							<td>
								<c:choose>
									<c:when test="${sysdata.name == 'logo' }" >
										——<%-- <a href="${pageContext.request.contextPath}/m/sysdata/detailSysdataLogo.action?id=${sysdata.id}">修改Logo</a> --%>
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/m/sysdata/detailSysdata.action?id=${sysdata.id}">修改</a>
									</c:otherwise>
								</c:choose>
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
