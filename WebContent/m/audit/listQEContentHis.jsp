<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- <base href="<%=basePath %>m/question/" /> -->
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.1.js"> </script> 
<title>管理区域</title>
<style type="text/css"> 
/* CSS Document */ 

body { 
font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
color: #4f6b72; 
background: #E6EAE9; 
} 

a { 
color: #c75f3e; 
} 

#mytable { 
width: 700px; 
padding: 0; 
margin: 0; 
} 

caption { 
padding: 0 0 5px 0; 
width: 700px; 
font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
text-align: right; 
} 

th { 
padding: 6px 6px 6px 12px; 
background: #CAE8EA no-repeat; 
} 
/*power by www.winshell.cn*/ 
th.nobg { 
border-top: 0; 
border-left: 0; 
border-right: 1px solid #C1DAD7; 
background: none; 
} 

td { 
padding: 0px 6px 0px 12px; 
} 
/*power by www.winshell.cn*/ 

td.alt { 
background: #F5FAFA; 
color: #797268; 
} 

th.spec { 
border-left: 1px solid #C1DAD7; 
border-top: 0; 
background: #fff no-repeat; 
font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
} 

th.specalt { 
border-left: 1px solid #C1DAD7; 
border-top: 0; 
background: #f5fafa no-repeat; 
font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
color: #797268; 
} 
/*---------for IE 5.x bug*/ 
html>body td{ font-size:13px;} 
body,td,th { 
font-family: 宋体, Arial; 
font-size: 13px; 
} 
.btn1_mouseover{
BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#C1DAD7); BORDER-LEFT: #7EBF4F 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #7EBF4F 1px solid
}

</style> 
<script>

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

function submitQuestionForm(){
	document.questionForm.action=btnlogin()+"viewQuestionHis.action";
	document.questionForm.submit();
}
</script>
</head>
<body>
<div id="man_zone">
  <div style="margin:10px;">
  <div>
  <form action="viewQuestionHis.action" method="post"  name="questionForm">
  <!-- input type="text" value="请输入问题内容" style="color:#999999;"  onfocus="ddd(this);"/> -->
  请输入问题内容：<input type="text" value="${content}"  name="content"/>
  <select name="status">
  		<option value="0">请选择状态</option>
  		<c:forEach items="${statusMap}" var="item">
  			 <option value="${item.value}" ${status == item.value?'selected=selected':'' }>${item.key }</option>
  		</c:forEach>
  </select>
  <input type="button" onclick="submitQuestionForm();return false;" value="查询"/>
  </form>
  </div>
  </div>
 <div class="pageContent" align="center">
    <table class="table" width="1200" layoutH="138">
        <thead>
            <tr>
                <th width="5%" align="center">
                  <input type="checkbox" group="entityIds" class="checkboxCtrl" onclick="findAllChecBox();"/>
                </th>
         		<th width="20%" class="asc">补充内容</th>
  				<th width="15%">提交时间</th>
          		<th width="10%">问题ID</th>
           		<th width="10%" align="center">状态</th>
     			<th  align="center">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.pageRecords}" var="q" varStatus="s">
            <tr target="sid_user" rel="1" >
                <td height="30px"  align="center">
                    <input name="entityIds" value="" type="checkbox" />
                </td>
                <td align="left">${q.extraContent}</td>
                <td  align="center"><fmt:formatDate value="${q.createdTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/></td>
         		<td  align="center">
         		    ${q.questionId}  
                </td>
                <td  align="center">
                	<c:forEach items="${statusMap}" var="item">
                		<c:if test="${q.status eq item.value }"><span style="color:red">${item.key}</span></c:if>		
			  		</c:forEach>
				</td>
                <td align="center">
                	<a href="javascript:void(0);" onclick="window.location.href=btnlogin()+'qExHisDetail.action?id=${q.id }';return false;">详情</a>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="panelBar">
        <div class="pages" align="right" style="padding-right:50px; margin-top:10px;">
        <jsp:include page="../pager.jsp" />
        </div>
    </div>
</div>



</div>
</body>
</html>
