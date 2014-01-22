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
	//验证参数英文名称 
	var name = $("#name").val();
	name = name.replace(/^[" "|" "]*/, "");//去左空格
	name = name.replace(/[" "|" "]*$/, "");//右
	var namelen = name.length;
	if (namelen > 20) {
		alert("您输入的参数英文名称长度超过了20个字符...");
		return false;
	}
	if (namelen == 0) {
		alert("请输入参数英文名称...");
		return false;
	}
	//验证参数中文名称
	var cname = $("#cname").val();
	cname = cname.replace(/^[" "|" "]*/, "");//去左空格
	cname = cname.replace(/[" "|" "]*$/, "");//右
	var cnamelen = cname.legth;
	if (cnamelen > 20) {
		alert("您输入的参数中文名称长度超过了20个字符...");
		return false;
	}
	if (cnamelen == 0) {
		alert("请输入参数中文名称...");
		return false;
	}
	//验证标题
	var content = $("#content").val();
	content = content.replace(/^[" "|" "]*/, "");//去左空格
	content = content.replace(/[" "|" "]*$/, "");//右
	var contentlen = content.length;
	if (contentlen > 2000) {
		alert("您输入的参数值长度超过了2000个字符...");
		return false;
	}
	if (contentlen == 0) {
		alert("请输入参数值...");
		return false;
	}
	return true;
}
</script>
</head>
<body>
	<div id="man_zone">
		<div style="margin-left: 10px;">
			<form method="post" action="addSysdata.action" onsubmit="return checkForm();" >
				<table class="mytable" cellpadding="0" cellspacing="0" style="margin-top: 10px;width: 600px;" >
					<tr>
						<td height="40px" colspan="2" style="border-bottom: 1px solid; font-size: 14px; font-weight: bold; padding-left: 20px;">增加新的系统参数</td>
					</tr>
					<tr>
						<th>参数英文名称</th>
						<td ><input type="text" name="sysdata.name" id="name" size="40" /> </td>
					</tr>
					<tr>
						<th>参数中文名称</th>
						<td ><input type="text" name="sysdata.cname" id="cname" size="40" /> </td>
					</tr>
					<tr>
						<th>参数值</th>
						<td >
							<textarea name="sysdata.content" id="content" cols="40" rows="5" ></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" height="50px">
							<input type="submit" value="提交" />
							<input type="reset" value="重填" style="margin-left: 30px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
