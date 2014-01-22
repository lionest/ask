<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<link rel="stylesheet" href="../css/admin.css" type="text/css" />
<title>管理区域</title>
</head>
<body>
	<script>
		function btnlogin() {
			var name = document.getElementById("name").value;
			if (name == "null" || name.trim().length == 0) {
				alert("名称不合法，请重新输入");
				return false;
			}
			if (navigator.userAgent.indexOf("MSIE") > 0) {
				window.location.href = "edit.action?id=${domain.id}";
				return true;
			}
			if (navigator.userAgent.indexOf("Firefox") > 0) {
				window.location.href = "m/domain/edit.action?id=${domain.id}";
				return true;
			}
			if (navigator.userAgent.indexOf("Opera") > 0) {
				window.location.href = "m/domain/edit.action?id=${domain.id}";
			}
			if (navigator.userAgent.indexOf("Safari") > 0) {
				window.location.href = "m/domain/edit.action?id=${domain.id}";
			}
		}
	</script>
	<div id="man_zone">
		<div style="margin-left: 10px;">
			<form method="post" action="edit.action?id=${domain.id}" onsubmit="return btnlogin();">
				<table class="mytable" cellpadding="0" cellspacing="0" style="margin-top: 10px;" width="800">
					<tr>
						<td height="40px" colspan="4" style="border-bottom: 1px solid; font-size: 14px; font-weight: bold; padding-left: 20px;">修改领域信息</td>
					</tr>
					<tr>
						<th>领域名称</th>
						<td colspan="3"><input id="name" name="domain.name" size="20" value="${domain.name}"></input>${errorMsg } 
						 <input type="hidden" name="domain.id" value="${domain.id}" />
						<input type="hidden" name="parentId" value="${domain.parentId }"/>
						 <span style="color: #A0A0A4; font-size: 11px">*最多可输入不超过20个汉字 </span></td>
					</tr>
					<tr>
						<th>是否推荐</th>
						<td><c:choose>
								<c:when test="${domain.recommended==true }">
									<input type="radio" name="recommended" value="1" checked="checked" />推荐&nbsp;
	           					 	<input type="radio" name="recommended" value="0" />不推荐&nbsp;
								</c:when>
								<c:otherwise>
									<input type="radio" name="recommended" value="1" />推荐&nbsp;
	           					 	<input type="radio" name="recommended" value="0" checked="checked" />不推荐&nbsp;
								</c:otherwise>
							</c:choose></td>
					</tr>
					<!--tr>
						<th>NodePath</th>
						<td>
							<input type="text" readonly="readonly" name="domain.nodePath" value="${domain.nodePath }"/>
						</td>
					</tr>
					<tr>
						<th>parentId</th>
						<td>
							<input type="text" name="domain.parentId" readonly="readonly" value="${domain.parentId}"/>
						</td>
					</tr-->
					<tr>
						<td colspan="4" align="center" height="50px"><input type="submit" value="提交" /> <input type="reset" value="重填" style="margin-left: 30px;" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
