<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
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
<script type="text/javascript">
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
            return "m/expert/"; 
        }
    if(navigator.userAgent.indexOf("Opera")>0){
            return "m/expert/"; 
        }
    if(navigator.userAgent.indexOf("Safari")>0) { 
           return "m/expert/";
        } 
}

function checkform(){
	var username = document.getElementsByName("expertUsername")[0].value;
	window.open (btnlogin()+'NewExpert.action?username='+username , 'newwindow', 'height=498, width=698, top=200, left=400, toolbar=no, menubar=no, scrollbars=no,resizable=no,location=no, status=no');
}

</script>
<div id="man_zone">
<div class="title_content">
	用户列表
</div>
 <div class="pageContent">
    <table class="table" width="1200" layoutH="138">
        <thead>
            <tr>
                <th><input type="checkbox" group="entityIds" class="checkboxCtrl" onclick="findAllChecBox();"/></th>
				<th>用户名</th>
          		<th orderField="name" class="asc">昵称</th>
          		<th>邮箱</th>
				<th>性别</th>
				<th align="center">姓名</th>
           		<th align="center">设为专家</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.pageRecords}" var="experts" varStatus="s">
            <tr target="sid_user" rel="1" >
                <td height="30px" align="center"><input name="entityIds" value="" type="checkbox" /></td>
                <td align="left">${experts.username } </td>
                <td align="left">${experts.nickname }</td>
                <td align="left">${experts.email }</td>
                <td align="center">${experts.sex }</td>
                <td align="left">${experts.fullName }</td>
                <td align="center"><a href="${pageContext.request.contextPath}/m/expert/NewExpert.action?id=${experts.id}&username=${experts.username} ">设为专家</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="panelBar">
   		<br/> 按用户名添加专家：<input name="expertUsername" size="20"/>
		<input type="button" value="设为专家" onclick="checkform()"/>
        <div class="pages" align="right" style="padding-right:50px; margin-top:10px;">
        	<jsp:include page="../pager.jsp" />
        </div>
    </div>
 </div>
</div>
</body>
</html>