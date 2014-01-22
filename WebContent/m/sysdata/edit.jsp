<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/m/css/common.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/m/css/detail.css" type="text/css" />
<script src="${pageContext.request.contextPath}/m/js/jquery-1.7.1.js" type="text/javascript"></script>
<title>管理区域</title>
<script>
//表单验证
function checkForm() {
	var content = $("#content").val();
	content = content.replace(/^[" "|" "]*/, "");//去左空格
	content = content.replace(/[" "|" "]*$/, "");//右
	var len = content.length;
  	if (len == 0) {
		alert("参数值不能为空...");
		return false;
	}
	return true; 
}
</script>
</head>
<body>
	<div id="man_zone">
		<div style="margin-left: 10px;">
			<form method="post" action="editSysdata.action" onsubmit="return checkForm();" >
				<table class="mytable" cellpadding="0" cellspacing="0" style="margin-top: 10px;width: 600px;" >
					<tr>
						<td height="40px" colspan="2" style="border-bottom: 1px solid; font-size: 14px; font-weight: bold; padding-left: 20px;">修改系统参数</td>
					</tr>
					<tr>
						<th>参数名称</th>
						<td >${sysdata.cname }</td>
					</tr>
					<tr>
						<th>参数值</th>
						<td >
							<textarea name="sysdata.content" id="content"  cols="40" rows="5" >${sysdata.content }</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" height="50px">
							<input type="submit" value="提交" />
							<input type="reset" value="重填" style="margin-left: 30px;" />
							<input type="hidden" name="sysdata.id" value="${sysdata.id }" /> 
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
