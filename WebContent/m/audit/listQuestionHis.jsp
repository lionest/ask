<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
			按问题状态查询：
			<select name="qstatus" id="qstatus">
				<option value="4,5">请选择状态</option>
				<option value="4,5">全部</option>
				<option value="4">未审核</option>
				<option value="5">未通过</option>
			</select>
			<input type="button" id="selectStatus" value="查询"/>
		</div>
		<div class="pageContent" align="center">
			<table class="table" width="1200" layoutH="138">
				<thead>
					<tr>
						<th width="5%" class="asc">ID</th>
						<th width="35%" class="asc">标题</th>
						<th width="15%">提交时间</th>
						<th width="10%">提问人</th>
						<th width="10%">领域</th>
						<th width="10%" align="center">状态</th>
						<th align="center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.pageRecords}" var="q" varStatus="s">
						<tr target="sid_user" rel="1">
							<td align="left" height="30px;">${q.id}</td>
							<td align="left" >
								<c:if test="${fn:length(q.subject)>26}">
									${fn:substring(q.subject,0,24)}...
								</c:if>
								<c:if test="${fn:length(q.subject)<=26}">
									${q.subject}
								</c:if>
							</td>
							<td align="center"><fmt:formatDate value="${q.createdTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss " /></td>
							<td align="center">${q.user.username}</td>
							<td align="center">${q.domain.name}</td>
							<td align="center"><c:if test="${q.status==4 }">
									<font color="blue">待审核</font>
								</c:if> <c:if test="${q.status==5 }">
									<font color="red">未通过</font>
								</c:if></td>
							<td align="center">
								<c:if test="${q.status==4 }">
									<a href="javascript:void(0);" onclick="changestatus('${q.id}','status','1')">通过</a>丨
									<a href="javascript:void(0);" onclick="changestatus('${q.id}','status','5')">不通过</a>
								</c:if>
								<c:if test="${q.status==5 }">
									<a href="javascript:void(0);" onclick="changestatus('${q.id}','status','1')">通过</a>丨
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</c:if>
								丨<a href="javascript:void(0);" onclick="window.location.href=btnlogin()+'questionHisDetail.action?id=${q.id }';return false;">详情</a>
							</td>
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
	<script>
	//审核操作
	function changestatus(id,column,status){
		var url="${pageContext.request.contextPath}/m/question/edits.action";
		$.post(url,{id:id,column:column,status:status},
				function(d){
					if(d.result="success"){
						alert("设置成功");
						window.location.href="${pageContext.request.contextPath}/m/audit/viewQuestionHis.action?status=4,5";
					}else{
						alert("设置失败");
					};
				}
		); 
	}
	
	$(document).ready(function(){
		$("#selectStatus").click(function(){
		 	var status = $("#qstatus  option:selected").val();
		 	window.location.href="${pageContext.request.contextPath}/m/audit/viewQuestionHis.action?status="+status;
		});
	});
	</script>
</body>
</html>
