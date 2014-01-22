<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<%@page import="com.yy.qa.bean.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<title>管理区域</title>

<style>  
  table,th,td, caption{ margin:0px; padding:0px; line-height:18px; color: #0099CC; font-size:12px; background:#D5DEDB;font-weight:bold; }
   .mytable th{   text-align:left; font-weight:normal; width:150px;  padding:6px; font-weight:bold; }
   .mytable caption{ color:#0099CC; line-height:30px; }
   .mytable td{ padding:3px;  }
</style>

</head>
<body>
<div style="margin-top:7px">  
  <table class="mytable" cellpadding="0" cellspacing="0"  width="700" id="auditDetail">
   <tr>
   <td height="40px" colspan="4" style="border-bottom:1px solid 
   ; font-size:14px; font-weight:bold; padding-left:20px;">审核
   </td>
   </tr>
    <tr>
     <th>追问内容</th>
        <td colspan="3"><span style="color:black">${probing.content }</span></td>
    </tr>   
    <tr>
       <th>提交时间</th><td><span style="color:black"><fmt:formatDate value="${probing.createdTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/></span></td>
    </tr>
    <tr>
     <th>问题ID</th>
        <td colspan="3"><span style="color:black">${question.id }</span></td>
    </tr>
    <tr>
        <th>问题标题</th>
        <td colspan="3"><span style="color:black">${question.subject }</span></td>
    </tr>
    <tr>
        <th>回复内容</th>
        <td colspan="3"><span style="color:black">${reply.content}</span></td>
    </tr>
    <th height="50">审核状态</th>
        <td colspan="3"><span>
			<c:if test="${probing.status==5 }" ><font color="red">该问题审核未通过！</font></c:if>
			<c:if test="${probing.status==4 }" >
				<a href="${pageContext.request.contextPath}/m/audit/updateProbingStatus.action?id=${probing.id}&status=5">不通过</a>
			</c:if>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/m/audit/updateProbingStatus.action?id=${probing.id}&status=1">通过审核</a>
		</span>
	</td>
    </tr> 
</table>
<%-- <c:if test="${probingHis.status != 1}">
	该记录已经审核！
</c:if>
<c:if test="${probingHis.status eq 1}">	
	<form action="auditProbingHis.action" method="post" onsubmit="return cheakReason();">
		<input type="hidden" value="${probingHis.id }" name="probingHis.id"/>
		<input type="hidden" name="flag" id="flag" value="pass" />
		<div style="height:20px"></div>
		
		<div style="color:red;margin-left:5px;height:30px">
			<input onclick="audit(1);" checked="checked" type="radio" value="2" name="status" />审核通过 <input onclick="audit(2);" type="radio" value="3" name="status" />审核不通过
		</div>
		<div style="margin-left:5px">
			<div >原因：<span style="color:red">（如果审核不通过需填写理由！）</span></div>
			<textarea  name="audit.reason" id="reason" rows="5" cols="83"></textarea>
		</div>
		<div style="width:650px;margin-top:10px" align="center">
			<input type="submit" value="提交"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="返回" onclick="window.location.href='viewQuestionHis.action'" />
		</div>
	</form>
</c:if>	 --%>
</div>
<script>
	function audit(o){
		if(o=="1"){
			document.getElementById("flag").value="pass";
		}else{
			document.getElementById("flag").value="notPass";
		}		
			
	}
	
	function cheakReason(){
		var flag=document.getElementById("flag").value;
		var reason = document.getElementById("reason").value;
		if(flag == "notPass" && reason == ""){
			alert("请输入审核不通过理由！");
			return false;
		}else{
			return true;
		}
		
	}

</script>
</body>
</html>
		