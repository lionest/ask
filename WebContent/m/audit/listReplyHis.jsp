<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- <base href="<%=basePath%>m/question/" /> -->
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<link rel="stylesheet" href="../css/mainzone.css" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.1.js"></script>
<title>管理区域</title>
<script>
	function btnlogin() {
		if (navigator.userAgent.indexOf("MSIE") > 0) {

			return "";
		}
		if (navigator.userAgent.indexOf("Firefox") > 0) {
			return "m/audit/";
		}
		if (navigator.userAgent.indexOf("Opera") > 0) {
			return "m/audit/";
		}
		if (navigator.userAgent.indexOf("Safari") > 0) {
			return "m/audit/";
		}
		if (navigator.userAgent.indexOf("Camino") > 0) {
			return "m/audit/";
		}
	}

	function ddd(object) {
		object.style.display = "none";
		document.getElementById("showContent").style.display = "block";
	}

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

	function submitQuestionForm() {
		document.questionForm.action = btnlogin() + "viewQuestionHis.action";
		document.questionForm.submit();
	}
</script>
</head>
<body>
	<div id="man_zone">
		<div class="title_content">
				按回答审核状态查询： <select name="rstatus" id="rstatus">
					<option value="4,5">请选择状态</option>
					<option value="4,5">全部</option>
					<option value="4">未审核</option>
					<option value="5">未通过</option>
				</select> <input type="button" id="selectStatus" value="查询" />
		</div>
		<div class="pageContent" align="center">
			<table class="table" width="1200" layoutH="138">
				<thead>
					<tr>
						<th width="5%" class="asc">ID</th>
						<th width="30%" class="asc">回复内容</th>
						<th width="10%" class="asc">问题ID</th>
						<th width="15%">提交时间</th>
						<th width="10%">回答人</th>
						<th width="10%" align="center">状态</th>
						<th align="center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.pageRecords}" var="r" varStatus="s">
						<tr target="sid_user" rel="1">
							<td align="left" height="30px;">${r.id}</td>
							<td align="left">
								<c:if test="${fn:length(r.content)>26}">
									${fn:substring(r.content,0,24)}...
								</c:if> <c:if test="${fn:length(r.content)<=26}">
									${r.content}
								</c:if>
							</td>
							<td align="center">${r.questionId}</td>
							<td align="center"><fmt:formatDate value="${r.createdTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss " /></td>
							<td align="center">${r.user.username}</td>
							<td align="center"><c:if test="${r.status==4 }">
									<font color="blue">待审核</font>
								</c:if> <c:if test="${r.status==5 }">
									<font color="red">未通过</font>
								</c:if></td>
							<td align="center"><c:if test="${r.status==4 }">
									<a href="${pageContext.request.contextPath}/m/audit/updateReplyStatus.action?id=${r.id}&status=1">通过</a>丨
									<a href="${pageContext.request.contextPath}/m/audit/updateReplyStatus.action?id=${r.id}&status=5">不通过</a>
								</c:if> <c:if test="${r.status==5 }">
									<a href="${pageContext.request.contextPath}/m/audit/updateReplyStatus.action?id=${r.id}&status=1">通过</a>丨
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</c:if> 丨
								<a href="${pageContext.request.contextPath}/m/audit/replyHisDetail.action?id=${r.id}">详情</a></td>
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
	<script type="text/javascript">
	$(document).ready(function(){
		$("#selectStatus").click(function(){
		 	var status = $("#rstatus  option:selected").val();
		 	window.location.href="${pageContext.request.contextPath}/m/audit/viewReplyHis.action?status="+status;
		});
	});
	</script>
</body>
</html>
