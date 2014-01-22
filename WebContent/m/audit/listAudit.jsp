<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<link rel="stylesheet" href="../css/mainzone.css" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.1.js"> </script>
<title>管理区域</title>
<script>
$(document).ready(function()
{	
	var qaudit = "${questionSysdata.content}";
	$("#question"+qaudit).attr("checked","checked");
	var raudit = "${replySysdata.content}";
	$("#reply"+raudit).attr("checked","checked");
	var paudit = "${probingSysdata.content}";
	$("#probing"+paudit).attr("checked","checked");
			
});

	function btnlogin()
{
     if(navigator.userAgent.indexOf("MSIE")>0) {
	  
            return ""; 
        }
    if(navigator.userAgent.indexOf("Firefox")>0){
            return "m/audit/"; 
        }
    if(navigator.userAgent.indexOf("Opera")>0){
           return "m/audit/";
        }
    if(navigator.userAgent.indexOf("Safari")>0) { 
            return "m/audit/"; 
        } 
    if(navigator.userAgent.indexOf("Camino")>0){ 
            return "m/audit/";
        } 
}


	function ddd(object){
		object.style.display="none";
		document.getElementById("showContent").style.display="block";
	}

var id = 1;
function findAllChecBox(){
  var ids = document.getElementById("ids");
   var r = document.getElementsByName("entityIds");
           if(id==1){
       for(var i=0;i<r.length;i++){
	     r[i].checked=true;
	       id=2;
		 }
	   }else{
	     for(var i=0;i<r.length;i++){
	     r[i].checked=false;
	       id=1
		 }
	   }
}


</script>
</head>
<body>
	<div id="man_zone">
		<div class="title_content">审核设置</div>
		<div class="pageContent" >
			<table class="table" width="800" layoutH="138">
				<thead>
					<tr>
						<th width="20%" class="asc">审核类型</th>
						<th>修改</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center" height="40px">${questionSysdata.cname }</td>
						<td align="center" height="40px">
							<form action="${pageContext.request.contextPath}/m/audit/editAudit.action" method="post">
								<input type="radio" name="sysdata.content" value="1" id="question1"> 审核 <input type="radio" name="sysdata.content" value="0" id="question0">不审核 <input type="radio"
									name="sysdata.content" value="2" id="question2">关键字审核 <input type="hidden" name="sysdata.id" value="${questionSysdata.id}" /> <input type="submit" value="修改" />
							</form>
						</td>
					</tr>
					<tr>
						<td align="center">${replySysdata.cname }</td>
						<td align="center" height="40px">
							<form action="${pageContext.request.contextPath}/m/audit/editAudit.action" method="post">
								<input type="radio" name="sysdata.content" value="1" id="reply1"> 审核 <input type="radio" name="sysdata.content" value="0" id="reply0">不审核 <input type="radio"
									name="sysdata.content" value="2" id="reply2">关键字审核 <input type="hidden" name="sysdata.id" value="${replySysdata.id}" /> <input type="submit" value="修改" />
							</form>
						</td>
					</tr>
					<tr>
						<td align="center">${probingSysdata.cname }</td>
						<td align="center" height="40px">
							<form action="${pageContext.request.contextPath}/m/audit/editAudit.action" method="post">
								<input type="radio" name="sysdata.content" value="1" id="probing1"> 审核 <input type="radio" name="sysdata.content" value="0" id="probing0">不审核 <input type="radio"
									name="sysdata.content" value="2" id="probing2">关键字审核 <input type="hidden" name="sysdata.id" value="${probingSysdata.id}" /> <input type="submit" value="修改" />
							</form>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
