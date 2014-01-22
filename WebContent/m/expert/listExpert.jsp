<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<%@page import="com.yy.qa.bean.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<link rel="stylesheet" href="../css/mainzone.css" type="text/css" />
<title>管理区域</title>
</head>
<body>
	<script>
		var id = 1;
		function findAllChecBox() {
			var ids = document.getElementById("ids");
			var r = document.getElementsByName("entityIds");
			if (id == 1) {
				for ( var i = 0; i < r.length; i++) {
					r[i].checked = true;
					id = 2;
				}
			} else {
				for ( var i = 0; i < r.length; i++) {
					r[i].checked = false;
					id = 1
				}
			}
		}

		function btnlogin() {
			if (navigator.userAgent.indexOf("MSIE") > 0) {

				return "";
			}
			if (navigator.userAgent.indexOf("Firefox") > 0) {
				return "m/expert/";
			}
			if (navigator.userAgent.indexOf("Opera") > 0) {
				return "m/expert/";
			}
			if (navigator.userAgent.indexOf("Safari") > 0) {
				return "m/expert/";
			}

		}
	</script>
	<div id="man_zone">
		<div class="title_content">
			专家列表
		</div>
		<div class="pageContent" >
			<table class="table" width="1000" layoutH="138">
				<thead>
					<tr>
						<th width="157" orderField="name" class="asc">专家姓名</th>
						<th width="246">所在单位</th>
						<th width="176">是否推荐</th>
						<th width="222">用户名</th>
						<th width="149" align="center">专家性别</th>
						<th width="146">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.pageRecords}" var="experts" varStatus="s">
						<tr target="sid_user" rel="1">
							<td align="left" height="30px">${experts.fullName }</td>
							<td align="left">${experts.organization }</td>
							<td align="center"><c:choose>
									<c:when test="${experts.recommended eq 1 }">是</c:when>
									<c:otherwise>否</c:otherwise>
								</c:choose></td>
							<td align="left">${experts.username }</td>
							<td align="center">${experts.sex}</td>
							<td align="center"><a href="javascript:void(0);" onclick="window.location.href=btnlogin()+'delete.action?id=${experts.id}&username=${experts.username}'">取消专家</a> <a
								href="javascript:void(0);" onclick="window.location.href='${pageContext.request.contextPath}/m/expert/editExpert.action?id=${experts.id}&username=${experts.username}';">编辑</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="panelBar">
				<div class="pages" align="right" style="padding-right: 50px; margin-top: 10px;">
					<jsp:include page="../pager.jsp" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
