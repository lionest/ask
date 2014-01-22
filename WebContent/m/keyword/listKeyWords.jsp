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

function btnlogin()
{
     if(navigator.userAgent.indexOf("MSIE")>0) {
	  
            return ""; 
        }
    if(navigator.userAgent.indexOf("Firefox")>0){
            return "m/keyword/"; 
        }
    if(navigator.userAgent.indexOf("Opera")>0){
             return "m/keyword/";  
        }
    if(navigator.userAgent.indexOf("Safari")>0) { 
            return "m/keyword/";  
        } 
   
}

</script>
	<div id="man_zone">
		<div class="title_content">关键字列表</div>
		<div class="pageContent">
			<form action="m/keyword/dels.action" method="post">
				<table class="table" width="800" layoutH="138">
					<thead>
						<tr>
							<th align="center" width="300" height="30px" orderField="name" class="asc">关键字名称</th>
							<th align="center" width="315">状态</th>
							<th align="center" width="327">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pager.pageRecords}" var="keywords" varStatus="s">
							<tr target="sid_user" rel="1">
								<td align="center" height="30px">${keywords.content}</td>
								<td align="center"><c:choose>
										<c:when test="${keywords.status eq 1}">
											<font color="blue">已激活</font>
										</c:when>
										<c:otherwise>
											<font color="red">已停用</font>
										</c:otherwise>
									</c:choose>&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="window.location.href=btnlogin()+'update.action?id=${keywords.id}&status=${keywords.status}'" href="javascript:void(0);">修改状态</a></td>
								<td align="center"><a onclick="if(confirm('确认删除吗？')){window.location.href='m/keyword/del.action?id=${keywords.id}';};" href="javascript:void(0);">删除</a> <a
									onclick="window.location.href='${pageContext.request.contextPath}/m/keyword/edit.action?id=${keywords.id}';" href="javascript:void(0);">编辑</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div align="left" style="margin-top: 10px; padding-left: 5px;"></div>
			</form>
			<div class="panelBar">
				<div class="pages" align="right" style="padding-right: 50px; margin-top: 10px;">
					<jsp:include page="../pager.jsp" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
