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
   ; font-size:14px; font-weight:bold; padding-left:20px;">审核记录详情
   </td>
   </tr>
    <tr>
     <th>记录类型</th>
        <td colspan="3">
	        <span style="color:red">
	        	<c:forEach items="${typeMap}" var="item">
	                <c:if test="${audit.type eq item.value }"><span style="color:red">${item.key}</span></c:if>		
				  </c:forEach>
	        </span>
        </td>
    </tr> 
    <tr>   
     <th>记录ID</th>
        <td colspan="3"><span style="color:black">${audit.recordId }</span></td>
    </tr>
    <tr>
       <th>审核时间</th><td><span style="color:black"><fmt:formatDate value="${audit.createdTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/></span></td>
    </tr>
    
    <tr>
        <th>审核人</th>
        <td colspan="3"><span style="color:black">${audit.userId }</span></td>
    </tr>
    <tr>
        <th>原因</th>
        <td colspan="3"><span style="color:black">${audit.reason }</span></td>
    </tr>
</table>

	<div style="width:650px;margin-top:10px" align="center">
		<input type="button" value="返回" onclick="window.location.href='viewAudit.action'" />
	</div>

	
</div>
</body>
</html>
		