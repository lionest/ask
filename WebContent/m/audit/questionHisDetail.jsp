<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<%@page import="com.yy.qa.bean.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.1.js"></script>
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
     <th>问题标题</th>
        <td colspan="3"><span style="color:black">${question.subject }</span></td>
    </tr>
      <tr>
    <th>问题内容</th>
        <td colspan="3"><span style="color:black">${question.content }</span></td>
    </tr>
    <th>问题补充</th>
        <td colspan="3"><span style="color:black">${question.extraContent }</span></td>
    </tr>
    <tr>
       <th>提交时间</th><td><span style="color:black"><fmt:formatDate value="${question.createdTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/></span></td>
    </tr>
    <tr>
        <th>提问者</th>
        <td colspan="3"><span style="color:black">${question.user.username }</span></td>
    </tr>
    <tr>
        <th>所属领域</th>
        <td colspan="3"><span style="color:black">${question.domain.name }</span></td>
    </tr>
	<tr>
		<td height="60px;">审核操作</td>
		<td>
			<c:if test="${question.status == 5}">
				<font color="red">该问题审核未通过！</font>
			</c:if>
			<c:if test="${question.status == 4}">
				<a color="red" href="javascript:void(0);" onclick="changestatus('${question.id}','status','5')">不通过</a>
			</c:if>
			<a color="blue" href="javascript:void(0);" onclick="changestatus('${question.id}','status','1')">通过审核</a>&nbsp;&nbsp;
		</td>
	</tr>
</table>
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
</script>
</body>
</html>
		